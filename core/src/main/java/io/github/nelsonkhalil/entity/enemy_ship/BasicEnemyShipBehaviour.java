package io.github.nelsonkhalil.entity.enemy_ship;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.FileSound;
import io.github.nelsonkhalil.entity.ShootCooldown;
import io.github.nelsonkhalil.entity.player.Player;

public class BasicEnemyShipBehaviour implements EnemyShipBehaviourStrategy {
    private static final float SPEED = 2F;

    public BasicEnemyShipBehaviour() {

    }

    @Override
    public void update(float dt, float size, Vector2 position, Player player, ShootCooldown shootCooldown, World.WorldContext context, AssetLoader al) {
        float moveSpeed = dt * size * SPEED;
        Vector2 moveVector = new Vector2();

        Vector2 playerPosition = player.getPosition();
        float dist = position.y - playerPosition.y;

        Vector2 move = playerPosition.cpy().sub(position);

        if (dist > 400) {
            moveVector.add(move.x / 4, move.y);
        } else if (dist > 200) {
            moveVector.add(move.x * (1 + (-shootCooldown.get() * 2)), move.y / 10);
        } else {
            moveVector.add(move.x / 10, -move.y);
        }

        moveVector.nor();
        moveVector.setLength(moveSpeed);
        position.add(moveVector);


        float xDist = Math.abs(playerPosition.x - position.x);
        if (xDist < 100 && shootCooldown.isZero()) {
            Vector2 bulletPosition = position.cpy();
            bulletPosition.add(0, size / -2 + 20);
            context.createEnemyBullet(bulletPosition);

            al.getSound(FileSound.ENEMY_SHIP_SHOOT).play();
            shootCooldown.set(MathUtils.random(0.75F, 2));
        }
    }
}
