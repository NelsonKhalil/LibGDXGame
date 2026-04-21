package io.github.nelsonkhalil.enemy_wave;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviour;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviourFactory;

public class EnemyWaveShipNode implements EnemyWaveNode {

    private final Vector2 position;
    private final EnemyShipBehaviour behaviour;

    public EnemyWaveShipNode(Vector2 position, EnemyShipBehaviour behaviour) {
        this.position = position;
        this.behaviour = behaviour;
    }

    public EnemyWaveShipNode(Vector2 position, EnemyShipBehaviourFactory.Type type) {
        this(position, EnemyShipBehaviourFactory.getBehaviour(type));
    }

    public EnemyWaveShipNode(float x, float y, EnemyShipBehaviour behaviour) {
        this(new Vector2(x, y), behaviour);
    }

    public EnemyWaveShipNode(float x, float y, EnemyShipBehaviourFactory.Type type) {
        this(new Vector2(x, y), type);
    }

    @Override
    public void start(World.WorldContext context) {
        context.createEnemyShip(position, behaviour);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean shouldBlock() {
        return false;
    }
}
