package io.github.nelsonkhalil.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.FileSound;
import io.github.nelsonkhalil.assetmanager.FileTexture;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.asteroid.Asteroid;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyBullet;
import io.github.nelsonkhalil.helper.VectorHelper;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class Player implements Entity {
    private static final float SPEED = 4;

    private final Texture spriteTexture;
    private final Sprite sprite;
    private final float size;
    private final Vector2 position;

    private final Texture shield1;
    private final Texture shield2;
    private final Texture shield3;

    private float shield;

    private float shootCooldown;

    public Player(AssetLoader al) {
        position = new Vector2(Main.VIEW_WIDTH / 2, 150);

        spriteTexture = al.getTexture(FileTexture.PLAYER);
        size = spriteTexture.getWidth();

        sprite = new Sprite(spriteTexture);
        sprite.setOriginCenter();

        shield = 10;

        shootCooldown = 0;

        shield1 = al.getTexture(FileTexture.SHIELD_1);
        shield2 = al.getTexture(FileTexture.SHIELD_2);
        shield3 = al.getTexture(FileTexture.SHIELD_3);
    }

    @Override
    public void update(float dt, World.WorldContext context, AssetLoader al, GameState gameState) {
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

        VectorHelper.clampToView(position);

        shootCooldown = Math.max(shootCooldown - dt, 0);
        if (keyPressed(Input.Keys.SPACE) && shootCooldown == 0) {
            Vector2 bulletPosition = position.cpy();
            bulletPosition.add(0, size / 2 - 20);
            context.createBullet(bulletPosition);

            al.getSound(FileSound.PLAYER_SHOOT).play();
            shootCooldown = 0.5F;
        }

        shield = Math.min(shield + dt, 10);
    }

    @Override
    public void render(DrawContext context) {
        sprite.setCenter(position.x, position.y);
        sprite.draw(context.batch);
        Texture shieldSprite = shield1;
        if (shield > 5) shieldSprite = shield2;
        if (shield == 10) shieldSprite = shield3;
        context.batch.draw(shieldSprite, position.x - (shieldSprite.getWidth() / 2F), position.y - (shieldSprite.getHeight() / 2F));
    }

    @Override
    public boolean shouldRemove() {
        return false;
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
        if (entity instanceof Asteroid || entity instanceof EnemyBullet) {
            if (shield == 10) {
                shield = 0;
                al.getSound(FileSound.PLAYER_HIT_SHIELD).play();
                return;
            }
            shield = 0;
            gameState.kill();
            if (gameState.getLives() == 0) {
                al.getSound(FileSound.PLAYER_DEATH).play();
            }
        }
    }

    private static boolean keyPressed(int... i) {
        for (int current : i) {
            if (Gdx.input.isKeyPressed(current))
                return true;
        }
        return false;
    }

}
