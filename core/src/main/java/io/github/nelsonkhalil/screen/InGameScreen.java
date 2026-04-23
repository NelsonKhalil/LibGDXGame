package io.github.nelsonkhalil.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.enemy_wave.EnemyLevelLayer;
import io.github.nelsonkhalil.entity.entity_spawner.AsteroidSpawner;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.render.Background;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.render.hud.LifeDisplay;
import io.github.nelsonkhalil.render.hud.NumberDisplay;
import io.github.nelsonkhalil.render.hud.PowerupDisplay;
import io.github.nelsonkhalil.state.GameState;

public class InGameScreen implements Screen {


    private final DrawContext drawContext;

    private final Background background;

    private final NumberDisplay scoreDisplay;
    private final LifeDisplay lifeDisplay;
    private final PowerupDisplay powerupDisplay;

    private final World world;
    private Player player;

    private final GameState gameState;

    private AsteroidSpawner asteroidSpawner;
    private EnemyLevelLayer levelLayer;

    private final AssetLoader assetLoader;

    public InGameScreen(AssetLoader al) {
        this.assetLoader = al;

        drawContext = new DrawContext();

        background = new Background(assetLoader);

        gameState = new GameState();
        world = new World(assetLoader, gameState);

        levelLayer = new EnemyLevelLayer();

        levelLayer.loadFromJson();

        startGame();

        scoreDisplay = new NumberDisplay(new Vector2(Main.VIEW_WIDTH - 30, Main.VIEW_HEIGHT - 40), assetLoader);
        lifeDisplay = new LifeDisplay(new Vector2(Main.VIEW_WIDTH - 30, Main.VIEW_HEIGHT - 80), assetLoader);
        powerupDisplay = new PowerupDisplay(new Vector2(10, Main.VIEW_HEIGHT - 40), player.powerupLayer, assetLoader);
    }

    private void startGame() {
        asteroidSpawner = new AsteroidSpawner();

        world.clear();
        gameState.reset();
        player = world.createPlayer();
    }

    @Override
    public void update(float dt, ScreenHost screenHost) {
        background.update(dt);
        World.WorldContext context = world.getContext();
        world.update(dt, context);

        asteroidSpawner.spawnUpdate(dt, context);

        if (gameState.gameOver()) {
            screenHost.setScreen(new GameOverScreen(assetLoader, gameState));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            screenHost.setScreen(new MainMenuScreen(assetLoader));
        }
    }

    @Override
    public void render() {
        drawContext.begin();

        background.render(drawContext);
        world.render(drawContext);
        scoreDisplay.render(drawContext, gameState.getScore());
        lifeDisplay.render(drawContext, gameState.getLives());
        powerupDisplay.render(drawContext);

        drawContext.end();
    }

    @Override
    public void dispose() {
        drawContext.dispose();
        background.dispose();
        world.dispose();
    }
}
