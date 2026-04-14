package io.github.nelsonkhalil.render.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.assetmanager.FileTexture;

public class LifeDisplay {
    private final Vector2 position;
    private final Texture sprite;
    public LifeDisplay(Vector2 position, AssetManager am) {
        this.position = position;
        this.sprite = am.getTexture(FileTexture.LIFE);
    }

    public void render(DrawContext context, int number) {
        float width = sprite.getWidth();
        for (int i = 0; i < number; i++) {
            context.batch.draw(sprite, (position.x - width / 2) - (i * (width + 5)), position.y);
        }
    }
}
