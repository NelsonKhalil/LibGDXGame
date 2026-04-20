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
import io.github.nelsonkhalil.entity.powerup.PowerupEntity;
import io.github.nelsonkhalil.powerup.PowerupLayer;
import io.github.nelsonkhalil.powerup.PowerupType;
import io.github.nelsonkhalil.helper.VectorHelper;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class Player implements Entity {
    private static final float SPEED = 4;

    public final PowerupLayer powerupLayer;

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

        powerupLayer = new PowerupLayer();

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

            boolean hasDoubleShoot = powerupLayer.contains(PowerupType.DOUBLE_SHOOT);
            if (hasDoubleShoot) {
                float shift = 25;

                bulletPosition.sub(shift, 0);
                context.createBullet(bulletPosition);
                bulletPosition.add(shift * 2, 0);
                context.createBullet(bulletPosition);
            } else {
                context.createBullet(bulletPosition);
            }

            al.getSound(FileSound.PLAYER_SHOOT).play();
            shootCooldown = 0.5F;
        }

        shield = Math.min(shield + dt, 10);
        powerupLayer.updateAll(dt);
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
    public void onCollide(Entity entity, World.WorldContext context, AssetLoader al, GameState gameState) {
        if (entity instanceof Asteroid || entity instanceof EnemyBullet) {
            boolean hasInvulnerability = powerupLayer.contains(PowerupType.INVULNERABILITY);

            if (shield == 10) {
                shield = hasInvulnerability ? 10 : 0;
                al.getSound(FileSound.PLAYER_HIT_SHIELD).play();
                return;
            }
            shield = hasInvulnerability ? 10 : 0;
            gameState.kill();
            if (gameState.getLives() == 0) {
                al.getSound(FileSound.PLAYER_DEATH).play();
            }
        }

        if (entity instanceof PowerupEntity powerupEntity) {
            PowerupType type = powerupEntity.type;
            onAddPowerup(type, gameState);
            if (powerupLayer.add(type)) {
                al.getSound(FileSound.POWER_UP).play();
            } else {
                al.getSound(FileSound.POWER_UP_FAIL).play();
            }
        }
    }

    private void onAddPowerup(PowerupType type, GameState gameState) {
        if (type == PowerupType.PLUS_ONE) {
            gameState.addLife();
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
