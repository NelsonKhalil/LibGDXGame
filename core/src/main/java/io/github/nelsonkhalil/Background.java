package io.github.nelsonkhalil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private static final float SPEED = 2;

    private final Texture sprite;
    private final float size;
    private float yOffset;

    public Background() {
        sprite = new Texture("background.png");
        size = sprite.getWidth();
        yOffset = 0;
    }

    public void update(float dt) {
        yOffset += (size * SPEED) * dt;
    }

    public void render(SpriteBatch batch) {
        int tileSize = (int) size;
        for (int x = -tileSize; x < Main.VIEW_WIDTH + tileSize; x += tileSize) {
            for (int y = -tileSize; y < Main.VIEW_HEIGHT + tileSize; y += tileSize) {
                batch.draw(sprite, x, y - yOffset % tileSize, tileSize, tileSize);
            }
        }
    }

    public void dispose() {
        sprite.dispose();
    }
}
