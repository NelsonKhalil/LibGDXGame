package io.github.nelsonkhalil;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.screens.InGameScreen;
import io.github.nelsonkhalil.screens.Screen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    public static final String TAG = "LibGDXGame";

    public static void log(Object message) { Gdx.app.log(TAG, message.toString()); }
    public static void error(Object message) { Gdx.app.error(TAG, message.toString()); }

    public static final float VIEW_WIDTH = 800;
    public static final float VIEW_HEIGHT = 800;

    private SettingsManager settingsManager;
    private AssetManager assetManager;

    private static Screen currentScreen = null;

    private OrthographicCamera camera;
    private Viewport viewport;

    @Override
    public void create() {

        Gdx.graphics.setWindowedMode((int) VIEW_WIDTH, (int) VIEW_HEIGHT);

        camera = new OrthographicCamera();
        viewport = new FitViewport(Main.VIEW_WIDTH, Main.VIEW_HEIGHT, camera);

        settingsManager = new SettingsManager();
        assetManager = new AssetManager(settingsManager);

        setScreen(new InGameScreen(assetManager));
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        final Color backgroundColor = new Color(0x3c3042ff);
        ScreenUtils.clear(backgroundColor);

        if (currentScreen != null) {
            currentScreen.update(dt);
            currentScreen.render();
        }

    }

    public static void setScreen(Screen screen) {
        if (currentScreen != null) currentScreen.dispose();
        currentScreen = screen;
    }

    @Override
    public void dispose() {
        setScreen(null);
    }

    @Override
    public void resize(int width, int height) {
        final boolean centerCamera = true;
        viewport.update(width, height, centerCamera);
    }

    public static void clampToView(Vector2 vector, float offsetX, float offsetY) {
        vector.x = Math.clamp(vector.x, offsetX, Main.VIEW_WIDTH - offsetX);
        vector.y = Math.clamp(vector.y, offsetY, Main.VIEW_HEIGHT - offsetY);
    }

    public static void clampToView(Vector2 vector) {
        clampToView(vector, 0, 0);
    }


}


