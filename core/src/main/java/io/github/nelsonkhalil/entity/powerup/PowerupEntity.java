package io.github.nelsonkhalil.entity.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.powerup.PowerupType;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class PowerupEntity implements Entity {

    public final PowerupType type;
    private final Vector2 position;
    private final Texture sprite;
    private final float size;
    private float life;
    public PowerupEntity(Vector2 position, PowerupType type, AssetLoader al) {
        this.position = position;
        this.type = type;
        sprite = al.getTexture(type.texture);
        size = sprite.getWidth();
        life = 20;
    }


    @Override
    public void update(float dt, World.WorldContext context, AssetLoader al, GameState gameState) {
        life = Math.max(0, life - dt);
    }

    @Override
    public void render(DrawContext context) {
        float half = size / 2;
        context.batch.draw(sprite, position.x - half, position.y - half, size, size);
    }

    @Override
    public boolean shouldRemove() {
        return life == 0;
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
    public void onCollide(Entity entity, World.WorldContext context, AssetLoader al, GameState gameState) {
        if (entity instanceof Player) {
            life = 0;
        }
    }
}
