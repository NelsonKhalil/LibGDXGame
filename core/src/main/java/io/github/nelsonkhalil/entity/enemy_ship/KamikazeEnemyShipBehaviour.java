package io.github.nelsonkhalil.entity.enemy_ship;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.entity.ShootCooldown;
import io.github.nelsonkhalil.entity.player.Player;

public class KamikazeEnemyShipBehaviour implements EnemyShipBehaviourStrategy {
    private static final float SPEED = 2.75F;

    public KamikazeEnemyShipBehaviour() {

    }

    @Override
    public void update(float dt, float size, Vector2 position, Player player, ShootCooldown shootCooldown, World.WorldContext context, AssetLoader al) {
        float moveSpeed = dt * size * SPEED;

        Vector2 playerPosition = player.getPosition();
        Vector2 move = playerPosition.cpy().sub(position);

        move.nor();
        move.setLength(moveSpeed);
        position.add(move);
    }
}
