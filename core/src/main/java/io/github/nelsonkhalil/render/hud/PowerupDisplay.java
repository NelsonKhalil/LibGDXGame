package io.github.nelsonkhalil.render.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.powerup.Powerup;
import io.github.nelsonkhalil.powerup.PowerupLayer;
import io.github.nelsonkhalil.powerup.PowerupType;
import io.github.nelsonkhalil.render.DrawContext;

import java.util.HashMap;
import java.util.Map;

public class PowerupDisplay {
    private final PowerupLayer powerupLayer;
    private final Map<PowerupType, Texture> sprites;

    private final Vector2 position;
    public PowerupDisplay(Vector2 position, PowerupLayer powerupLayer, AssetLoader al) {
        this.position = position;
        this.powerupLayer = powerupLayer;

        sprites = new HashMap<>();
        for (PowerupType type : PowerupType.values()) {
            sprites.put(type, al.getTexture(type.texture));
        }
    }

    public void render(DrawContext context) {
        float x = position.x;
        for (Powerup powerup : powerupLayer.getPowerups()) {
            if (powerup.shouldRemove()) continue;
            Texture sprite = sprites.get(powerup.type);
            float width = sprite.getWidth();
            context.batch.draw(sprite, x, position.y);
            x += width + 10;
        }
    }
}
