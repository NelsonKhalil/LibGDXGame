package io.github.nelsonkhalil.helper;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;

public final class VectorHelper {
    private VectorHelper() {
    }

    public static void clampToView(Vector2 vector, float offsetX, float offsetY) {
        vector.x = Math.clamp(vector.x, offsetX, Main.VIEW_WIDTH - offsetX);
        vector.y = Math.clamp(vector.y, offsetY, Main.VIEW_HEIGHT - offsetY);
    }

    public static void clampToView(Vector2 vector) {
        clampToView(vector, 0, 0);

    }
}
