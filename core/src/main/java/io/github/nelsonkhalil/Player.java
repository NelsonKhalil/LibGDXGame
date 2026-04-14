package io.github.nelsonkhalil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private static final float SPEED = 2;

    private final Texture spriteTexture;
    private final Sprite sprite;
    private final float size;
    private final Vector2 position;

    private float shootCooldown;
    private Sound shootSound;

    public Player() {
        position = new Vector2(Main.VIEW_WIDTH / 2, 150);

        spriteTexture = new Texture("player.png");
        size = spriteTexture.getWidth();

        sprite = new Sprite(spriteTexture);
        sprite.setOriginCenter();

        shootCooldown = 0;
        shootSound = Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));
    }

    public void update(float dt, BulletLayer bulletLayer) {
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
            bulletLayer.addBullet(bulletPosition);

            shootSound.play(0.5F);
            shootCooldown = 0.5F;
        }
    }

    public void render(SpriteBatch batch) {
        sprite.setCenter(position.x, position.y);
        sprite.draw(batch);
    }

    public void dispose() {
        spriteTexture.dispose();
        shootSound.dispose();
    }

    private static boolean keyPressed(int... i) {
        for (int current : i) {
            if (Gdx.input.isKeyPressed(current))
                return true;
        }
        return false;
    }

}
