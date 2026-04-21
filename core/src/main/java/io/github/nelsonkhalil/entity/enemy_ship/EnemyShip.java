package io.github.nelsonkhalil.entity.enemy_ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.FileSound;
import io.github.nelsonkhalil.assetmanager.FileTexture;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.ShootCooldown;
import io.github.nelsonkhalil.entity.bullet.Bullet;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.particle.BlackSmokeParticleInfo;
import io.github.nelsonkhalil.entity.particle.ExplosionParticleInfo;
import io.github.nelsonkhalil.entity.particle.PartialGeneralParticleInfo;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.helper.ListHelper;
import io.github.nelsonkhalil.helper.VectorHelper;
import io.github.nelsonkhalil.powerup.PowerupType;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

import java.util.Optional;

public class EnemyShip implements Entity {

    private static final float SPEED = 2F;

    private final Texture spriteTexture;
    private final Sprite sprite;
    private final float size;
    private final Vector2 position;

    private final EnemyShipBehaviour behaviourStrategy;

    private int health;
    private ShootCooldown shootCooldown;

    public EnemyShip(Vector2 position, EnemyShipBehaviour behaviourStrategy, AssetLoader al) {
        this.position = position.cpy();
        this.behaviourStrategy = behaviourStrategy;
        spriteTexture = al.getTexture(FileTexture.ENEMY_SHIP);
        size = spriteTexture.getWidth();
        sprite = new Sprite(spriteTexture);
        health = 4;
        shootCooldown = new ShootCooldown();
    }

    @Override
    public void update(float dt, World.WorldContext context, AssetLoader al, GameState gameState) {

        Optional<Player> optionalPlayer = context.requestPlayer();
        if (optionalPlayer.isEmpty()) return;
        Player player = optionalPlayer.get();

        shootCooldown.update(dt);
        behaviourStrategy.update(dt, size, position, player, shootCooldown, context, al);

        float offsetX = 0;
        float offsetY = -200;
        VectorHelper.clampToView(position, offsetX, offsetY);
    }

    @Override
    public void render(DrawContext context) {
        sprite.setCenter(position.x, position.y);
        sprite.draw(context.batch);
    }

    @Override
    public boolean shouldRemove() {
        return health == 0;
    }

    @Override
    public void dispose() {
    }

    @Override
    public CollisionShape getCollisionShape() {
        return new CollisionShape(position.cpy(), size / 2);
    }

    @Override
    public Vector2 getPosition() {
        return position.cpy();
    }

    @Override
    public void onCollide(Entity entity, World.WorldContext context, AssetLoader al, GameState gameState) {
        if (entity instanceof Bullet) {
            al.getSound(FileSound.ENEMY_SHIP_HIT).play();

            health = Math.max(0, health - 1);
            if (health == 0) {
                al.getSound(FileSound.ENEMY_SHIP_DEATH).play();
                explode(context);
                gameState.addScore(100);

                if (MathUtils.randomBoolean(0.50F)) {
                    context.createPowerupEntity(position, ListHelper.randomEntry(PowerupType.values()));
                }
            }
        }
        if (entity instanceof Player) {
            health = 0;
            al.getSound(FileSound.ENEMY_SHIP_DEATH).play();
            explode(context);
            gameState.addScore(50);


            if (MathUtils.randomBoolean(0.25F)) {
                context.createPowerupEntity(position, ListHelper.randomEntry(PowerupType.values()));
            }
        }
    }

    private void explode(World.WorldContext context) {
        for (int i = 0; i < 40; i++) {
            context.createBlackSmokeParticle(
                new PartialGeneralParticleInfo(position).withRandomVel(MathUtils.random(40, 150)),
                new BlackSmokeParticleInfo(size / 250)
            );
        }
        for (int i = 0; i < 20; i++) {
            context.createExplosionParticle(
                new PartialGeneralParticleInfo(position).withRandomVel(MathUtils.random(20, 75)),
                new ExplosionParticleInfo(size / 250)
            );
        }
    }
}
