package io.github.nelsonkhalil.entity.enemy_ship.behaviour;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipActionReturnable;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviour;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviourContext;
import io.github.nelsonkhalil.util.Timer;

public class SineWaveEnemyShipBehaviour implements EnemyShipBehaviour {
    private static final float SPEED = 2.5F;

    private final Timer shootTimer;
    private float ticks;

    public SineWaveEnemyShipBehaviour() {
        shootTimer = new Timer();
        ticks = 0;
    }

    @Override
    public EnemyShipActionReturnable update(float dt, EnemyShipBehaviourContext context) {
        shootTimer.update(dt);
        ticks += dt * 3;

        float moveSpeed = context.getMoveSpeed(dt, SPEED);
        Vector2 moveVector = new Vector2();

        float yDist = context.getYDist();

        Vector2 move = context.getMoveToPlayerVector();
        float sine = (float) Math.sin(ticks);

        if (yDist > 400) {
            moveVector.add(move.x * sine, move.y / 10);
        } else {
            moveVector.add(move.x / 10, -move.y);
            moveSpeed *= 2;
        }

        context.setVectorWithSpeed(moveVector, moveSpeed);

        boolean shoot = false;
        float xDist = context.getXDist();
        if (xDist < 100 && shootTimer.isDue()) {
            shoot = true;
            shootTimer.setMilli(MathUtils.random(1500, 2750));
        }

        return new EnemyShipActionReturnable(moveVector, shoot);
    }
}
