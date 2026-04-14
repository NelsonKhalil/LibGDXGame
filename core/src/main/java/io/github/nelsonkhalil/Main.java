package io.github.nelsonkhalil;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private static final String TAG = "LibGDXGame";
    private static void log(Object message) { Gdx.app.log(TAG, message.toString()); }
    private static void error(Object message) { Gdx.app.error(TAG, message.toString()); }

    public static final float VIEW_WIDTH = 800;
    public static final float VIEW_HEIGHT = 800;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private Background background;
    private Player player;

    private BulletLayer bulletLayer;


    @Override
    public void create() {
        Gdx.graphics.setWindowedMode((int) VIEW_WIDTH, (int) VIEW_HEIGHT);

        camera = new OrthographicCamera();
        viewport = new FitViewport(VIEW_WIDTH, VIEW_HEIGHT, camera);
        batch = new SpriteBatch();

        background = new Background();
        player = new Player();
        bulletLayer = new BulletLayer();
    }

    private void updateGame(float dt) {
        background.update(dt);

        player.update(dt, bulletLayer);
        bulletLayer.update(dt);

    }

    private void renderGame(SpriteBatch batch) {
        background.render(batch);
        player.render(batch);

        bulletLayer.render(batch);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        final Color backgroundColor = new Color(0xffffffff);
        ScreenUtils.clear(backgroundColor);

        updateGame(dt);

        batch.begin();
        renderGame(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        player.dispose();

        bulletLayer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        final boolean centerCamera = true;
        viewport.update(width, height, centerCamera);
    }


}


