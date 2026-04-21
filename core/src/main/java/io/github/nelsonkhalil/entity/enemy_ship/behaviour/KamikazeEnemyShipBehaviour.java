package io.github.nelsonkhalil.entity.enemy_ship.behaviour;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipActionReturnable;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviour;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviourContext;

public class KamikazeEnemyShipBehaviour implements EnemyShipBehaviour {
    private static final float SPEED = 2.5F;

    public KamikazeEnemyShipBehaviour() {
    }

    @Override
    public EnemyShipActionReturnable update(float dt, EnemyShipBehaviourContext context) {
        float moveSpeed = context.getMoveSpeed(dt, SPEED);

        Vector2 move = context.getMoveToPlayerVector();
        context.setVectorWithSpeed(move, moveSpeed);

        return new EnemyShipActionReturnable(move, false);
    }
}
