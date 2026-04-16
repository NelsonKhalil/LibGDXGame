package io.github.nelsonkhalil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.render.Background;
import io.github.nelsonkhalil.render.DrawContext;

public class GameOverScreen implements Screen {

    private DrawContext drawContext;

    private Background background;

    private AssetManager assetManager;

    public GameOverScreen(AssetManager am) {
        this.assetManager = am;

        drawContext = new DrawContext();

        background = new Background(assetManager);
    }

    @Override
    public void update(float dt) {
        background.update(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            Main.setScreen(new InGameScreen(assetManager));
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
