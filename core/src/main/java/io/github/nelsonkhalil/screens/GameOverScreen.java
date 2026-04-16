package io.github.nelsonkhalil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.render.Background;
import io.github.nelsonkhalil.render.DrawContext;

public class GameOverScreen implements Screen {

    private final DrawContext drawContext;

    private final Background background;

    private final AssetLoader assetLoader;

    public GameOverScreen(AssetLoader al) {
        this.assetLoader = al;

        drawContext = new DrawContext();

        background = new Background(assetLoader);
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
        drawContext.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
