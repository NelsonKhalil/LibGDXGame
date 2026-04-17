package io.github.nelsonkhalil.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.FileFont;
import io.github.nelsonkhalil.render.Background;
import io.github.nelsonkhalil.render.DrawContext;

public class MainMenuScreen implements Screen {


    private final DrawContext drawContext;

    private final Background background;

    private final AssetLoader assetLoader;

    private final BitmapFont font;

    private boolean wasPressingSpace;

    public MainMenuScreen(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
        drawContext = new DrawContext();
        background = new Background(assetLoader);
        font = assetLoader.getFont(FileFont.MAIN);

        wasPressingSpace = Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    @Override
    public void update(float dt) {
        background.update(dt);


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (!wasPressingSpace)
                Main.setScreen(new InGameScreen(assetLoader));
        } else {
            wasPressingSpace = false;
        }
    }

    @Override
    public void render() {
        drawContext.begin();
        background.render(drawContext);

        drawContext.drawCenteredText(font, "A-STEROIDS", Main.VIEW_HEIGHT - 100);
        drawContext.drawCenteredText(font, ". . .", Main.VIEW_HEIGHT - 150);

        drawContext.drawCenteredText(font, "Press [SPACE] to start", 50);

        drawContext.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
