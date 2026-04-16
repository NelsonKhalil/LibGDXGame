package io.github.nelsonkhalil.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.FileTexture;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.asteroid.Asteroid;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.collision.Collisions;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyShip;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class Bullet implements Entity {

    private static final float SPEED = 60F;

    private final Texture sprite;
    private final Vector2 size;
    private final Vector2 position;

    private boolean removeMarker = false;

    public Bullet(Vector2 pos, AssetLoader al) {
        this.sprite = al.getTexture(FileTexture.BULLET);
        this.position = pos.cpy();
        size = new Vector2(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update(float dt, World.WorldContext context, AssetLoader al, GameState gameState) {
        position.add(0, (size.y * SPEED) * dt);

        Collisions collisions = context.requestCollisions(this);
        if (collisions.collided()) {
            for (Entity entity : collisions.getOthers()) {
                if (entity instanceof Asteroid || entity instanceof EnemyShip) {
                    removeMarker = true;
                    break;
                }
            }
        }
    }

    @Override
    public void render(DrawContext context) {
        context.batch.draw(sprite, position.x - (size.x / 2), position.y - (size.y / 2), size.x, size.y);
    }

    @Override
    public void dispose() {
    }

    @Override
    public CollisionShape getCollisionShape() {
        return new CollisionShape(size.x / 2, new Vector2(0, size.y / 2));
    }

    @Override
    public Vector2 getPosition() {
        return position.cpy();
    }

    @Override
    public boolean shouldRemove() {
        return (!getCollisionShape().isOnScreen(position)) || removeMarker;
    }
}
