package io.github.nelsonkhalil;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface Entity {
    void update(float dt);
    void render(SpriteBatch batch);
    void dispose();
    boolean shouldRemove();

    boolean isOnScreen();
}
