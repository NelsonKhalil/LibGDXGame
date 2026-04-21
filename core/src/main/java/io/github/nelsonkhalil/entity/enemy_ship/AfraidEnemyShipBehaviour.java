package io.github.nelsonkhalil.entity.enemy_ship;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.FileSound;
import io.github.nelsonkhalil.entity.ShootCooldown;
import io.github.nelsonkhalil.entity.player.Player;

public class AfraidEnemyShipBehaviour implements EnemyShipBehaviour {
    private static final float SPEED = 2F;

    private int sequencedShots;
    private float sequencedShotCooldown;

    public AfraidEnemyShipBehaviour() {
        sequencedShots = 0;
        sequencedShotCooldown = 0;
    }

    @Override
    public void update(float dt, float size, Vector2 position, Player player, ShootCooldown shootCooldown, World.WorldContext context, AssetLoader al) {
        float moveSpeed = dt * size * SPEED;
        Vector2 moveVector = new Vector2();

        Vector2 playerPosition = player.getPosition();
        float dist = position.y - playerPosition.y;

        Vector2 move = playerPosition.cpy().sub(position);

        if (dist > 600) {
            moveVector.add(move.x / 4, move.y);
        } else if (dist > 550) {
            moveVector.add(move.x * ((shootCooldown.isZero() || sequencedShots != 0) ? 1 : -1), -move.y / 10);
        } else {
            moveSpeed *= 3;
            moveVector.add(move.x / 10, -move.y);
        }

        moveVector.nor();
        moveVector.setLength(moveSpeed);
        position.add(moveVector);

        sequencedShotCooldown = Math.max(0, sequencedShotCooldown - dt);

        float xDist = Math.abs(playerPosition.x - position.x);
        if ((xDist < 10 && shootCooldown.isZero()) || (sequencedShotCooldown == 0 && sequencedShots != 0)) {
            Vector2 bulletPosition = position.cpy();

            bulletPosition.add(0, size / -2 + 20);
            context.createEnemyBullet(bulletPosition);

            al.getSound(FileSound.ENEMY_SHIP_SHOOT).play();

            shootCooldown.set(MathUtils.random(20, 30));

            sequencedShotCooldown = 0.1F;
            if (sequencedShots == 0) {
                sequencedShots = 5;
                sequencedShotCooldown = 1.5F;
            } else {
                sequencedShots--;
            }
        }
    }
}
