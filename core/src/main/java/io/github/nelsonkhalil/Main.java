package io.github.nelsonkhalil;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.entity.asteroid.AsteroidSpawner;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.render.Background;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.render.hud.LifeDisplay;
import io.github.nelsonkhalil.render.hud.NumberDisplay;
import io.github.nelsonkhalil.state.GameState;
import io.github.nelsonkhalil.state.MainState;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    public static final String TAG = "LibGDXGame";
    public static void log(Object message) { Gdx.app.log(TAG, message.toString()); }
    public static void error(Object message) { Gdx.app.error(TAG, message.toString()); }

    public static final Random RANDOM = new Random();

    public static final float VIEW_WIDTH = 800;
    public static final float VIEW_HEIGHT = 800;

    private MainState mainState;

    private SettingsManager settingsManager;
    private AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private DrawContext drawContext;

    private Background background;

    private NumberDisplay scoreDisplay;

    private LifeDisplay lifeDisplay;

    private World world;
    private Player player;

    private GameState gameState;

    private AsteroidSpawner asteroidSpawner;


    @Override
    public void create() {
        gameState = new GameState();

        Gdx.graphics.setWindowedMode((int) VIEW_WIDTH, (int) VIEW_HEIGHT);

        settingsManager = new SettingsManager();
        assetManager = new AssetManager(settingsManager);

        camera = new OrthographicCamera();
        viewport = new FitViewport(VIEW_WIDTH, VIEW_HEIGHT, camera);
        drawContext = new DrawContext();

        background = new Background(assetManager);
        scoreDisplay = new NumberDisplay(new Vector2(VIEW_WIDTH - 30, VIEW_HEIGHT - 40), assetManager);

        lifeDisplay = new LifeDisplay(new Vector2(VIEW_WIDTH - 30, VIEW_HEIGHT - 80), assetManager);

        world = new World(assetManager, gameState);
        asteroidSpawner = new AsteroidSpawner();

        startGame();
    }

    private void startGame() {
        mainState = MainState.PLAYING;
        world.clear();
        gameState.reset();
        player = world.createPlayer();
    }

    private void updateGameOver(float dt) {
        background.update(dt);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            startGame();
        }
    }

    private void renderGameOver() {
        background.render(drawContext);
    }

    private void updateGame(float dt) {
        if (mainState == MainState.GAME_OVER) {
            updateGameOver(dt);
            return;
        }

        background.update(dt);
        World.WorldContext context = world.getContext();
        world.update(dt, context);
        asteroidSpawner.spawnUpdate(dt, context);

        if (gameState.gameOver()) {
            mainState = MainState.GAME_OVER;
        }

    }

    private void renderGame() {
        if (mainState == MainState.GAME_OVER) {
            renderGameOver();
            return;
        }

        background.render(drawContext);
        world.render(drawContext);
        scoreDisplay.render(drawContext, gameState.getScore());
        lifeDisplay.render(drawContext, gameState.getLives());
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        final Color backgroundColor = new Color(0xffffffff);
        ScreenUtils.clear(backgroundColor);

        updateGame(dt);

        drawContext.begin();
        renderGame();
        drawContext.end();
    }

    @Override
    public void dispose() {
        drawContext.dispose();
        background.dispose();
        world.dispose();
    }

    @Override
    public void resize(int width, int height) {
        final boolean centerCamera = true;
        viewport.update(width, height, centerCamera);
    }


}


