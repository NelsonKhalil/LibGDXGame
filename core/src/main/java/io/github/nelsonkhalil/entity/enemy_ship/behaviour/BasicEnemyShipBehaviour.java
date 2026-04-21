package io.github.nelsonkhalil.entity.enemy_ship.behaviour;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipActionReturnable;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviour;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviourContext;
import io.github.nelsonkhalil.util.Timer;

public class BasicEnemyShipBehaviour implements EnemyShipBehaviour {
    private static final float SPEED = 1.75F;

    private final Timer shootTimer;

    public BasicEnemyShipBehaviour() {
        shootTimer = new Timer();
    }

    @Override
    public EnemyShipActionReturnable update(float dt, EnemyShipBehaviourContext context) {
        shootTimer.update(dt);

        float moveSpeed = context.getMoveSpeed(dt, SPEED);
        Vector2 moveVector = new Vector2();

        float yDist = context.getYDist();
        Vector2 move = context.getMoveToPlayerVector();

        if (yDist > 400) {
            moveVector.add(move.x / 4, move.y);
        } else if (yDist > 200) {
            moveVector.add(move.x * (shootTimer.isDue() ? 1 : -2), move.y / 10);
        } else {
            moveVector.add(move.x / 10, -move.y);
        }

        context.setVectorWithSpeed(moveVector, moveSpeed);

        boolean shoot = false;
        if (context.getXDist() < 100 && shootTimer.isDue()) {
            shoot = true;
            shootTimer.setMilli(MathUtils.random(750, 2000));
        }

        return new EnemyShipActionReturnable(moveVector, shoot);
    }
}
