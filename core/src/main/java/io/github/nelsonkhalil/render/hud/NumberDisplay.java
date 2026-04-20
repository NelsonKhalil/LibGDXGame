package io.github.nelsonkhalil.render.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.MultiFileTexture;
import io.github.nelsonkhalil.render.DrawContext;

import java.util.Arrays;
import java.util.List;

public class NumberDisplay {

    private final Vector2 position;
    private final List<Texture> sprites;
    public NumberDisplay(Vector2 position, AssetLoader al) {
        this.position = position;
        sprites = Arrays.stream(MultiFileTexture.NUMBERS.textures).map(al::getTexture).toList();
    }

    public void render(DrawContext context, int number) {
        if (!context.DRAW_HUD) return;

        final int digits = 5;

        for (int i = digits; i >= 0; i--) {
            int digit = (int) ((number / (Math.pow(10, i))) % 10);
            context.batch.draw(sprites.get(digit), position.x - (i * 20), position.y);
        }
    }
}
