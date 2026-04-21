package io.github.nelsonkhalil.enemy_wave;

import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.util.Timer;

public class EnemyWaveDelayNode implements EnemyWaveNode {

    private final Timer timer;

    public EnemyWaveDelayNode(int milliseconds) {
        timer = new Timer();
        timer.setMilli(milliseconds);
    }

    public EnemyWaveDelayNode(float seconds) {
        timer = new Timer();
        timer.setSeconds(seconds);
    }

    @Override
    public EnemyWaveNodeReturnable start(World.WorldContext context) {
        return new EnemyWaveNodeReturnable();
    }

    @Override
    public void update(float dt) {
        timer.update(dt);
    }

    @Override
    public boolean shouldBlock() {
        return !timer.isDue();
    }
}
