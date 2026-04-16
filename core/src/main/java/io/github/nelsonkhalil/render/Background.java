package io.github.nelsonkhalil.render;

import com.badlogic.gdx.graphics.Texture;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.assetmanager.FileTexture;

public class Background {
    private static final float SPEED = 2;

    private final Texture sprite;
    private final float size;
    private float yOffset;

    public Background(AssetManager am) {
        sprite = am.getTexture(FileTexture.BACKGROUND);
        size = sprite.getWidth();
        yOffset = 0;
    }

    public void update(float dt) {
        yOffset += (size * SPEED) * dt;
    }

    public void render(DrawContext context) {
        if (!context.DRAW_BACKGROUND) return;

        int tileSize = (int) size;
        for (int x = -tileSize; x < Main.VIEW_WIDTH + tileSize; x += tileSize) {
            for (int y = -tileSize; y < Main.VIEW_HEIGHT + tileSize; y += tileSize) {
                context.batch.draw(sprite, x, y - yOffset % tileSize, tileSize, tileSize);
            }
        }
    }

    public void dispose() {
    }
}
