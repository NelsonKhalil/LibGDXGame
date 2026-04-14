package io.github.nelsonkhalil.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DrawContext {
    public final boolean DRAW_DEBUG_HITBOX = true;

    public final boolean DRAW_HUD = true;
    public final boolean DRAW_BACKGROUND = true;
    public final boolean DRAW_ENTITIES = true;

    public final SpriteBatch batch;
    public final ShapeRenderer shapeRenderer;

    public DrawContext(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
    }

    public DrawContext() {
        this(new SpriteBatch(), new ShapeRenderer());
    }

    public void begin() {
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    }

    public void end() {
        batch.end();
        shapeRenderer.end();
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
