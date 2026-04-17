package io.github.nelsonkhalil.render;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.nelsonkhalil.Main;

public class DrawContext {
    public final boolean DRAW_DEBUG_HITBOX = false;

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

    public void drawCenteredText(BitmapFont font, String text, float yOffset) {
        GlyphLayout layout = new GlyphLayout(font, text);
        float x = (Main.VIEW_WIDTH - layout.width) / 2;
        float y = (yOffset + layout.height);
        font.draw(batch, layout, x, y);
    }
}
