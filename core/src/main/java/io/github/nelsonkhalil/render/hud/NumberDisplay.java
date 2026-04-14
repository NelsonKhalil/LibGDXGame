package io.github.nelsonkhalil.render.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.assetmanager.FileTexture;
import io.github.nelsonkhalil.render.DrawContext;

import java.util.Arrays;
import java.util.List;

public class NumberDisplay {

    private FileTexture[] numFileTextures = new FileTexture[] {
        FileTexture.NUM_0,
        FileTexture.NUM_1,
        FileTexture.NUM_2,
        FileTexture.NUM_3,
        FileTexture.NUM_4,
        FileTexture.NUM_5,
        FileTexture.NUM_6,
        FileTexture.NUM_7,
        FileTexture.NUM_8,
        FileTexture.NUM_9
    };

    private final Vector2 position;
    private final List<Texture> sprites;
    public NumberDisplay(Vector2 position, AssetManager am) {
        this.position = position;
        sprites = Arrays.stream(numFileTextures).map(am::getTexture).toList();
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
