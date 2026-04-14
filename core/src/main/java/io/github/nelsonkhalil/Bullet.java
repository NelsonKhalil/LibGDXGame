package io.github.nelsonkhalil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet implements Entity {

    private static final float SPEED = 40;

    private final Texture sprite;
    private final Vector2 size;
    private Vector2 position;

    public Bullet(Vector2 pos, Texture sprite) {
        this.sprite = sprite;
        this.position = pos;
        size = new Vector2(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update(float dt) {
        position.add(0, (size.y * SPEED) * dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, position.x - (size.x / 2), position.y - (size.y / 2), size.x, size.y);
    }

    @Override
    public void dispose() {
        sprite.dispose();
    }

    @Override
    public boolean shouldRemove() {
        return !isOnScreen();
    }

    @Override
    public boolean isOnScreen() {
        float hSizeX = size.x / 2;
        float hSizeY = size.y / 2;
        if ((position.x + hSizeX) < 0 || (position.x - hSizeX) > Main.VIEW_WIDTH)
            return false;
        if ((position.y + hSizeY) < 0 || (position.y - hSizeY) > Main.VIEW_HEIGHT)
            return false;
        return true;
    }
}
