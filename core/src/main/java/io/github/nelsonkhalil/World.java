package io.github.nelsonkhalil;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.EntityLayer;
import io.github.nelsonkhalil.entity.asteroid.Asteroid;
import io.github.nelsonkhalil.entity.asteroid.AsteroidInfo;
import io.github.nelsonkhalil.entity.bullet.Bullet;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyBullet;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyShip;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

import java.util.List;
import java.util.Optional;

public class World {
    private final EntityLayer entityLayer;

    public World(AssetLoader assetLoader, GameState gameState) {
        entityLayer = new EntityLayer(assetLoader, gameState);
    }

    public WorldContext getContext() {
        return new WorldContext();
    }

    public void update(float dt, WorldContext context) {
        entityLayer.updateAll(dt, context);
    }

    public void render(DrawContext context) {
        entityLayer.renderAll(context);
    }

    public void dispose() {
        entityLayer.disposeAll();
    }

    public Player createPlayer() {
        return entityLayer.createPlayer();
    }

    public void clear() {
        entityLayer.clear();
    }

    public class WorldContext {
        private WorldContext() {}

        public Optional<Player> requestPlayer() {
            return entityLayer.getPlayer();
        }

        public Bullet createBullet(Vector2 position) {
            return entityLayer.createBullet(position);
        }

        public EnemyBullet createEnemyBullet(Vector2 position) {
            return entityLayer.createEnemyBullet(position);
        }

        public Asteroid createAsteroid(Vector2 position, AsteroidInfo info) {
            return entityLayer.createAsteroid(position, info);
        }

        public EnemyShip createEnemyShip(Vector2 position) {
            return entityLayer.createEnemyShip(position);
        }
    }
}
