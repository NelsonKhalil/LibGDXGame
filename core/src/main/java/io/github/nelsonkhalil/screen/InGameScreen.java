package io.github.nelsonkhalil.screen;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.entity.entity_spawner.AsteroidSpawner;
import io.github.nelsonkhalil.entity.entity_spawner.EnemyShipSpawner;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.render.Background;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.render.hud.LifeDisplay;
import io.github.nelsonkhalil.render.hud.NumberDisplay;
import io.github.nelsonkhalil.state.GameState;

public class InGameScreen implements Screen {


    private final DrawContext drawContext;

    private final Background background;

    private final NumberDisplay scoreDisplay;
    private final LifeDisplay lifeDisplay;

    private final World world;
    private Player player;

    private final GameState gameState;

    private AsteroidSpawner asteroidSpawner;
    private EnemyShipSpawner enemyShipSpawner;

    private final AssetLoader assetLoader;

    public InGameScreen(AssetLoader al) {
        this.assetLoader = al;

        drawContext = new DrawContext();

        background = new Background(assetLoader);

        scoreDisplay = new NumberDisplay(new Vector2(Main.VIEW_WIDTH - 30, Main.VIEW_HEIGHT - 40), assetLoader);
        lifeDisplay = new LifeDisplay(new Vector2(Main.VIEW_WIDTH - 30, Main.VIEW_HEIGHT - 80), assetLoader);

        gameState = new GameState();
        world = new World(assetLoader, gameState);

        startGame();
    }

    private void startGame() {
        asteroidSpawner = new AsteroidSpawner();
        enemyShipSpawner = new EnemyShipSpawner();

        world.clear();
        gameState.reset();
        player = world.createPlayer();
    }

    @Override
    public void update(float dt) {
        background.update(dt);
        World.WorldContext context = world.getContext();
        world.update(dt, context);

        asteroidSpawner.spawnUpdate(dt, context);
        enemyShipSpawner.spawnUpdate(dt, context);

        if (gameState.gameOver()) {
            Main.setScreen(new GameOverScreen(assetLoader, gameState));
        }
    }

    @Override
    public void render() {
        drawContext.begin();

        background.render(drawContext);
        world.render(drawContext);
        scoreDisplay.render(drawContext, gameState.getScore());
        lifeDisplay.render(drawContext, gameState.getLives());

        drawContext.end();
    }

    @Override
    public void dispose() {
        drawContext.dispose();
        background.dispose();
        world.dispose();
    }
}
