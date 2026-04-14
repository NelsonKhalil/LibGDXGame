package io.github.nelsonkhalil.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.assetmanager.FileSound;
import io.github.nelsonkhalil.assetmanager.FileTexture;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.asteroid.Asteroid;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.collision.Collisions;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class Player implements Entity {
    private static final float SPEED = 2;

    private final Texture spriteTexture;
    private final Sprite sprite;
    private final float size;
    private final Vector2 position;

    private float shield;

    private float shootCooldown;

    public Player(AssetManager am) {
        position = new Vector2(Main.VIEW_WIDTH / 2, 150);

        spriteTexture = am.getTexture(FileTexture.PLAYER);
        size = spriteTexture.getWidth();

        sprite = new Sprite(spriteTexture);
        sprite.setOriginCenter();

        shield = 10;

        shootCooldown = 0;
    }

    @Override
    public void update(float dt, World.WorldContext context, AssetManager am, GameState gameState) {
        float moveAmount = (size * SPEED) * dt;

        if (keyPressed(Input.Keys.UP, Input.Keys.W)) {
            position.add(0, moveAmount);
        }
        if (keyPressed(Input.Keys.DOWN, Input.Keys.S)) {
            position.sub(0, moveAmount);
        }
        if (keyPressed(Input.Keys.LEFT, Input.Keys.A)) {
            position.sub(moveAmount, 0);
        }
        if (keyPressed(Input.Keys.RIGHT, Input.Keys.D)) {
            position.add(moveAmount, 0);
        }

        position.x = Math.clamp(position.x, 0, Main.VIEW_WIDTH);
        position.y = Math.clamp(position.y, 0, Main.VIEW_HEIGHT);

        shootCooldown = Math.max(shootCooldown - dt, 0);
        if (keyPressed(Input.Keys.SPACE) && shootCooldown == 0) {
            Vector2 bulletPosition = position.cpy();
            bulletPosition.add(0, size / 2 - 20);
            context.createBullet(bulletPosition);

            am.playSound(FileSound.SHOOT);
            shootCooldown = 0.5F;
        }

        shield = Math.min(shield + dt, 10);

        Collisions collisions = context.requestCollisions(this);
        if (collisions.collided()) {
            for (Entity entity : collisions.getOthers()) {
                if (entity instanceof Asteroid) {
                    Main.log("PLAYER COLLIDED");
                    if (shield == 10) {
                        shield = 0;
                        am.playSound(FileSound.PLAYER_HIT_SHIELD);
                        continue;
                    }
                    shield = 0;
                    gameState.kill();
                }
            }
        }
    }

    @Override
    public void render(DrawContext context, AssetManager am) {
        sprite.setCenter(position.x, position.y);
        sprite.draw(context.batch);
        FileTexture shieldSprite = FileTexture.SHIELD_1;
        if (shield > 5) shieldSprite = FileTexture.SHIELD_2;
        if (shield == 10) shieldSprite = FileTexture.SHIELD_3;
        Texture shieldTexture = am.getTexture(shieldSprite);
        context.batch.draw(shieldTexture, position.x - (shieldTexture.getWidth() / 2F), position.y - (shieldTexture.getHeight() / 2F));
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void dispose() {
        spriteTexture.dispose();
    }

    @Override
    public CollisionShape getCollisionShape() {
        return new CollisionShape(size / 2);
    }

    @Override
    public Vector2 getPosition() {
        return position.cpy();
    }

    private static boolean keyPressed(int... i) {
        for (int current : i) {
            if (Gdx.input.isKeyPressed(current))
                return true;
        }
        return false;
    }

}
