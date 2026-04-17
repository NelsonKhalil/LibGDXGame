package io.github.nelsonkhalil;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.screen.MainMenuScreen;
import io.github.nelsonkhalil.screen.Screen;
import io.github.nelsonkhalil.screen.ScreenHost;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements ScreenHost {

    public static final String TAG = "LibGDXGame";

    public static void log(Object message) { Gdx.app.log(TAG, message.toString()); }
    public static void error(Object message) { Gdx.app.error(TAG, message.toString()); }

    public static final float VIEW_WIDTH = 800;
    public static final float VIEW_HEIGHT = 800;

    private AssetLoader assetLoader;

    private Screen currentScreen = null;

    private OrthographicCamera camera;
    private Viewport viewport;

    @Override
    public void create() {

        Gdx.graphics.setWindowedMode((int) VIEW_WIDTH, (int) VIEW_HEIGHT);
        Gdx.graphics.setTitle("A-Steroids");

        camera = new OrthographicCamera();
        viewport = new FitViewport(Main.VIEW_WIDTH, Main.VIEW_HEIGHT, camera);

        assetLoader = new AssetLoader();
        assetLoader.load();

        setScreen(new MainMenuScreen(assetLoader));
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        final Color backgroundColor = new Color(0x3c3042ff);
        ScreenUtils.clear(backgroundColor);

        if (currentScreen != null) {
            currentScreen.update(dt, this);
            currentScreen.render();
        }

    }

    @Override
    public void setScreen(Screen screen) {
        if (currentScreen != null) currentScreen.dispose();
        currentScreen = screen;
    }

    @Override
    public void dispose() {
        setScreen(null);
        assetLoader.dispose();
    }

    @Override
    public void resize(int width, int height) {
        final boolean centerCamera = true;
        viewport.update(width, height, centerCamera);
    }
}


