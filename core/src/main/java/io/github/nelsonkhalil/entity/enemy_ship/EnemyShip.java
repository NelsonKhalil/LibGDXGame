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
import io.github.nelsonkhalil.entity.bullet.Bullet;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.entity.player.Player;
import io.github.nelsonkhalil.helper.VectorHelper;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

import java.util.Optional;

public class EnemyShip implements Entity {

    private static final float SPEED = 2F;

    private final Texture spriteTexture;
    private final Sprite sprite;
    private final float size;
    private final Vector2 position;

    private int health;
    private float shootCooldown;

    public EnemyShip(Vector2 position, AssetLoader al) {
        this.position = position.cpy();
        spriteTexture = al.getTexture(FileTexture.ENEMY_SHIP);
        size = spriteTexture.getWidth();
        sprite = new Sprite(spriteTexture);
        health = 5;
        shootCooldown = 0;
    }

    @Override
    public void update(float dt, World.WorldContext context, AssetLoader al, GameState gameState) {

        Optional<Player> optionalPlayer = context.requestPlayer();
        if (optionalPlayer.isEmpty()) return;
        Player player = optionalPlayer.get();

        Vector2 moveVector = new Vector2();
        float moveSpeed = size * SPEED * dt;

        Vector2 playerPosition = player.getPosition();
        float dist = position.dst(playerPosition);

        Vector2 move = playerPosition.cpy().sub(position);

        if (dist > 600) {
            moveVector.add(move.x / 4, move.y);
        } else if (dist > 400) {
            moveVector.add(move.x * (1 + (-shootCooldown * 1)), move.y / 10);
        } else {
            moveVector.add(move.x / 10, -move.y);
        }

        moveVector.nor();
        moveVector.setLength(moveSpeed);
        position.add(moveVector);

        float xDist = Math.abs(playerPosition.x - position.x);

        shootCooldown = Math.max(shootCooldown - dt, 0);
        if (xDist < 100 && shootCooldown == 0) {
            Vector2 bulletPosition = position.cpy();
            bulletPosition.add(0, size / -2 + 20);
            context.createEnemyBullet(bulletPosition);

            al.getSound(FileSound.ENEMY_SHIP_SHOOT).play();
            shootCooldown = MathUtils.random(0.75F, 2);
        }

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
    public void onCollide(Entity entity, AssetLoader al, GameState gameState) {
        if (entity instanceof Bullet) {
            al.getSound(FileSound.ENEMY_SHIP_HIT).play();

            health = Math.max(0, health - 1);
            if (health == 0) {
                al.getSound(FileSound.ENEMY_SHIP_DEATH).play();
                gameState.addScore(100);
            }
        }
    }
}
