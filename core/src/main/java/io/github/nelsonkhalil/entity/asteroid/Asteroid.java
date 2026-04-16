package io.github.nelsonkhalil.entity.asteroid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.FileSound;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.bullet.Bullet;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyBullet;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class Asteroid implements Entity {

    private static final float SPEED = 1;

    private final Texture sprite;
    private float size;
    private final Vector2 position;

    private int health;

    public Asteroid(Vector2 pos, AsteroidInfo info, AssetLoader al) {
        this.sprite = al.getTexture(info.fileTexture());
        this.position = pos.cpy();
        size = this.sprite.getWidth();
        health = info.size().health;
    }

    @Override
    public void update(float dt, World.WorldContext context, AssetLoader al, GameState gameState) {
        position.sub(0, (size * SPEED) * dt);
    }

    @Override
    public void render(DrawContext context) {
        float half = size / 2;
        context.batch.draw(sprite, position.x - half, position.y - half, size, size);
    }

    @Override
    public void dispose() {
    }

    @Override
    public CollisionShape getCollisionShape() {
        return new CollisionShape(position.cpy(), size / 2);
    }

    @Override
    public Vector2 getPosition() {
        return position.cpy();
    }

    @Override
    public void onCollide(Entity entity, AssetLoader al, GameState gameState) {
        if (entity instanceof Bullet || entity instanceof EnemyBullet || entity instanceof Player) {
            if (entity instanceof Player) {
                health = 0;
                al.getSound(FileSound.ASTEROID_PLAYER_COLLIDE).play();
                return;
            }
            health = Math.max(0, health - 1);
            size *= 0.8F;
            if (health == 0) {
                al.getSound(FileSound.ASTEROID_DEATH).play();
                gameState.addScore(30);
            } else {
                al.getSound(FileSound.ASTEROID_HIT).play();
                gameState.addScore(5);
            }
        }
    }

    @Override
    public boolean shouldRemove() {
        return (!getCollisionShape().isOnScreen() && position.y < size) || health == 0;
    }
}
