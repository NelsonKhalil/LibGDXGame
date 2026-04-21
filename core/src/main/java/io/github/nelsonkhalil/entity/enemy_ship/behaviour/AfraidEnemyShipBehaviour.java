package io.github.nelsonkhalil.entity.enemy_ship.behaviour;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipActionReturnable;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviour;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviourContext;
import io.github.nelsonkhalil.util.Timer;

public class AfraidEnemyShipBehaviour implements EnemyShipBehaviour {
    private static final float SPEED = 2F;

    private final Timer shootTimer;
    private final Timer timeoutTimer;
    private int sequencedShots;

    public AfraidEnemyShipBehaviour() {
        shootTimer = new Timer();
        timeoutTimer = new Timer();
        sequencedShots = 0;
    }

    @Override
    public EnemyShipActionReturnable update(float dt, EnemyShipBehaviourContext context) {
        shootTimer.update(dt);
        timeoutTimer.update(dt);

        float moveSpeed = context.getMoveSpeed(dt, SPEED);
        Vector2 moveVector = new Vector2();

        float yDist = context.getYDist();

        Vector2 move = context.getMoveToPlayerVector();

        if (yDist > 600) {
            moveVector.add(move.x / 4, move.y);
        } else if (yDist > 550) {
            moveVector.add(move.x * ((shootTimer.isDue() || sequencedShots != 0) ? 1 : -1), -move.y / 10);
        } else {
            moveSpeed *= 3;
            moveVector.add(move.x / 10, -move.y);
        }
        if (!timeoutTimer.isDue()) {
            moveSpeed /= 10;
        }

        context.setVectorWithSpeed(moveVector, moveSpeed);

        boolean shoot = false;
        float xDist = context.getXDist();
        if (((xDist < 50 && yDist < 600) || sequencedShots != 0) && shootTimer.isDue()) {
            shoot = true;

            if (sequencedShots == 0) {
                sequencedShots = 8;
                shootTimer.setMilli(1500);
            } else {
                sequencedShots--;
                shootTimer.setMilli(sequencedShots * 10);

                if (sequencedShots == 0) {
                    shootTimer.setSeconds(MathUtils.random(10, 15));
                    timeoutTimer.setMilli(2500);
                }
            }
        }

        return new EnemyShipActionReturnable(moveVector, shoot);
    }
}
