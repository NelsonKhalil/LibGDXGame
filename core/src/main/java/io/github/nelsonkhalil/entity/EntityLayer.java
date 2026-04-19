package io.github.nelsonkhalil.entity;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.entity.asteroid.Asteroid;
import io.github.nelsonkhalil.entity.asteroid.AsteroidInfo;
import io.github.nelsonkhalil.entity.bullet.Bullet;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyBullet;
import io.github.nelsonkhalil.entity.enemy_ship.EnemyShip;
import io.github.nelsonkhalil.entity.particle.*;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

import java.util.*;

public class EntityLayer {
    private final List<Entity> entities;
    private final List<Entity> entitiesToAdd;

    private final AssetLoader assetLoader;
    private final GameState gameState;

    private Player player;

    public EntityLayer(AssetLoader assetLoader, GameState gameState) {
        this.assetLoader = assetLoader;
        this.gameState = gameState;
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
        player = null;
    }

    public void clear() {
        entities.clear();
    }

    public void addEntity(Entity entity) {
        entitiesToAdd.add(entity);
    }

    public void updateAll(float dt, World.WorldContext context) {
        entities.addAll(entitiesToAdd);
        entitiesToAdd.clear();

        precomputePlayer();

        for (Entity entity : entities) {
            entity.update(dt, context, assetLoader, gameState);
        }

        onCollisionAll(context);
        entities.removeIf(Entity::shouldRemove);
    }

    public void renderAll(DrawContext context) {
        for (Entity entity : entities) {
            if (context.DRAW_ENTITIES) entity.render(context);
            if (context.DRAW_DEBUG_HITBOX) entity.getCollisionShape().renderDebug(context);
        }
    }

    public void disposeAll() {
        for (Entity entity : entities) {
            entity.dispose();
        }
    }

    private Map<Entity, CollisionShape> getCollisionShapeMap() {
        Map<Entity, CollisionShape> map = new HashMap<>();
        for (Entity entity : entities) {
            CollisionShape collisionShape = entity.getCollisionShape();
            if (collisionShape == null) continue;
            map.put(entity, collisionShape);
        }
        return map;
    }

    private void onCollisionAll(World.WorldContext context) {
        Map<Entity, CollisionShape> collisionShapeMap = getCollisionShapeMap();
        for (Entity entity : entities) {
            for (Entity other : entities) {
                if (entity == other) continue;
                if (!collisionShapeMap.containsKey(entity) || !collisionShapeMap.containsKey(other)) continue;
                if (collisionShapeMap.get(entity).intersects(collisionShapeMap.get(other)))
                    entity.onCollide(other, context, assetLoader, gameState);
            }
        }
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

    public Optional<Player> getPlayer() {
        return Optional.of(player);
    }

    public Player createPlayer() {
        Player player = new Player(assetLoader);
        addEntity(player);
        return player;
    }

    public Bullet createBullet(Vector2 position) {
        Bullet bullet = new Bullet(position, assetLoader);
        addEntity(bullet);
        return bullet;
    }

    public Asteroid createAsteroid(Vector2 position, AsteroidInfo info) {
        Asteroid asteroid = new Asteroid(position, info, assetLoader);
        addEntity(asteroid);
        return asteroid;
    }

    public EnemyShip createEnemyShip(Vector2 position) {
        EnemyShip enemyShip = new EnemyShip(position, assetLoader);
        addEntity(enemyShip);
        return enemyShip;
    }

    public EnemyBullet createEnemyBullet(Vector2 position) {
        EnemyBullet enemyBullet = new EnemyBullet(position, assetLoader);
        addEntity(enemyBullet);
        return enemyBullet;
    }

    public ExplosionParticle createExplosionParticle(PartialGeneralParticleInfo ginfo, ExplosionParticleInfo info) {
        ExplosionParticle explosionParticle = new ExplosionParticle(ginfo, info, assetLoader);
        addEntity(explosionParticle);
        return explosionParticle;
    }

    public BlackSmokeParticle createBlackSmokeParticle(PartialGeneralParticleInfo ginfo, BlackSmokeParticleInfo info) {
        BlackSmokeParticle blackSmokeParticle = new BlackSmokeParticle(ginfo, info, assetLoader);
        addEntity(blackSmokeParticle);
        return blackSmokeParticle;
    }

}
