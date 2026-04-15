package io.github.nelsonkhalil.entity;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.entity.asteroid.Asteroid;
import io.github.nelsonkhalil.entity.asteroid.AsteroidInfo;
import io.github.nelsonkhalil.entity.bullet.Bullet;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.collision.Collisions;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyBullet;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyShip;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

import java.util.*;

public class EntityLayer {
    private final List<Entity> entities;
    private final Stack<Entity> bufferedEntities;

    private final AssetManager assetManager;
    private final GameState gameState;

    private Player player;

    private final Map<Entity, Collisions> collisionsMap;
    public EntityLayer(AssetManager assetManager, GameState gameState) {
        this.assetManager = assetManager;
        this.gameState = gameState;
        entities = new ArrayList<>();
        bufferedEntities = new Stack<>();
        collisionsMap = new HashMap<>();
        player = null;
    }

    public void clear() {
        entities.clear();
    }

    public void addEntity(Entity entity) {
        bufferedEntities.add(entity);
    }

    public void updateAll(float dt, World.WorldContext context) {

        entities.addAll(bufferedEntities);
        bufferedEntities.clear();

        entities.removeIf(Entity::shouldRemove);
        precompute();
        for (Entity entity : entities) {
            entity.update(dt, context, assetManager, gameState);
        }
    }

    public void renderAll(DrawContext context) {
        for (Entity entity : entities) {
            if (context.DRAW_ENTITIES) entity.render(context, assetManager);
            if (context.DRAW_DEBUG_HITBOX) entity.getCollisionShape().renderDebug(entity.getPosition(), context);
        }
    }

    public void disposeAll() {
        for (Entity entity : entities) {
            entity.dispose();
        }
    }

    public Collisions getCollisions(Entity entity) {
        if (collisionsMap.containsKey(entity)) {
            return collisionsMap.get(entity);
        }
        return new Collisions();
    }

    private void precompute() {
        precomputeAllCollisions();
        precomputePlayer();
    }

    private void precomputePlayer() {
        player = null;
        for (Entity entity : entities) {
            if (entity instanceof Player playerEntity) {
                player = playerEntity;
                break;
            }
        }
    }

    private void precomputeAllCollisions() {
        for (Entity entity : entities) {
            precomputeCollisions(entity);
        }
    }

    private void precomputeCollisions(Entity entity) {
        collisionsMap.put(entity, computeCollisions(entity));
    }

    private Collisions computeCollisions(Entity entity) {
        Collisions collisions = new Collisions();

        CollisionShape collisionShape = entity.getCollisionShape();
        for (Entity other : entities) {
            if (other == entity) continue;
            CollisionShape otherShape = other.getCollisionShape();

            if (collisionShape.intersects(otherShape, entity.getPosition(), other.getPosition()))
                collisions.addOther(other);
        }

        return collisions;
    }

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    public Optional<Player> getPlayer() {
        return Optional.of(player);
    }

    public Player createPlayer() {
        Player player = new Player(assetManager);
        addEntity(player);
        return player;
    }

    public Bullet createBullet(Vector2 position) {
        Bullet bullet = new Bullet(position, assetManager);
        addEntity(bullet);
        return bullet;
    }

    public Asteroid createAsteroid(Vector2 position, AsteroidInfo info) {
        Asteroid asteroid = new Asteroid(position, info, assetManager);
        addEntity(asteroid);
        return asteroid;
    }

    public EnemyShip createEnemyShip(Vector2 position) {
        EnemyShip enemyShip = new EnemyShip(position, assetManager);
        addEntity(enemyShip);
        return enemyShip;
    }

    public EnemyBullet createEnemyBullet(Vector2 position) {
        EnemyBullet enemyBullet = new EnemyBullet(position, assetManager);
        addEntity(enemyBullet);
        return enemyBullet;
    }


}
