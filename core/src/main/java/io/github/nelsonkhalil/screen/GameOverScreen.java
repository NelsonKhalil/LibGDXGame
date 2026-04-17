package io.github.nelsonkhalil.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.FileFont;
import io.github.nelsonkhalil.helper.ListHelper;
import io.github.nelsonkhalil.render.Background;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class GameOverScreen implements Screen {

    private static final String[] messages = new String[]{
        "Better luck next time",
        "Nice try",
        "Maybe trying aiming",
        "Just give it a few years",
        "Never give up",
        "Oof"
    };

    private final DrawContext drawContext;

    private final Background background;

    private final AssetLoader assetLoader;

    private final GameState gameState;

    private final BitmapFont font;

    private final String message;

    public GameOverScreen(AssetLoader al, GameState gameState) {
        this.assetLoader = al;
        this.gameState = gameState;
        message = ListHelper.randomEntry(messages);

        drawContext = new DrawContext();
        font = assetLoader.getFont(FileFont.MAIN);

        background = new Background(assetLoader);
    }

    private void drawCenteredText(DrawContext drawContext, String text, float yOffset) {
        GlyphLayout layout = new GlyphLayout(font, text);
        float x = (Main.VIEW_WIDTH - layout.width) / 2;
        float y = (yOffset + layout.height);
        font.draw(drawContext.batch, layout, x, y);
    }

    @Override
    public void update(float dt) {
        background.update(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            Main.setScreen(new InGameScreen(assetLoader));
        }
    }

    @Override
    public void render() {
        drawContext.begin();

        background.render(drawContext);

        drawContext.drawCenteredText(font,"GAME OVER", Main.VIEW_HEIGHT - 50);
        drawContext.drawCenteredText(font, message, Main.VIEW_HEIGHT / 2 + 100);
        drawContext.drawCenteredText(font, "Score:", Main.VIEW_HEIGHT / 2);
        drawContext.drawCenteredText(font, String.valueOf(gameState.getScore()), Main.VIEW_HEIGHT / 2 - 50);
        drawContext.drawCenteredText(font, "Press [SPACE] to restart", 100);
        //drawContext.drawCenteredText(font, "Press [ESC] to enter menu", 50);

        drawContext.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
