package io.github.nelsonkhalil;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.asteroid.AsteroidInfo;
import io.github.nelsonkhalil.entity.collision.Collisions;
import io.github.nelsonkhalil.entity.EntityLayer;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.entity.asteroid.Asteroid;
import io.github.nelsonkhalil.entity.bullet.Bullet;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class World {
    private final EntityLayer entityLayer;

    public World(AssetManager assetManager, GameState gameState) {
        entityLayer = new EntityLayer(assetManager, gameState);
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

        public Collisions requestCollisions(Entity entity) {
            return entityLayer.getCollisions(entity);
        }

        public Bullet createBullet(Vector2 position) {
            return entityLayer.createBullet(position);
        }

        public Asteroid createAsteroid(Vector2 position, AsteroidInfo info) {
            return entityLayer.createAsteroid(position, info);
        }
    }
}
