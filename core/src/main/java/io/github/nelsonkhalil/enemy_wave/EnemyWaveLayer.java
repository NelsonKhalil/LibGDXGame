package io.github.nelsonkhalil.enemy_wave;

import io.github.nelsonkhalil.World;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;

public class EnemyWaveLayer {
    private final Queue<EnemyWave> waves;
    private EnemyWave currentWave;

    public EnemyWaveLayer() {
        waves = new ArrayDeque<>();
        currentWave = null;
    }

    public void addWave(EnemyWave wave) {
        waves.add(wave);
    }

    public void loadFromRegistry() {
        for (Supplier<EnemyWave> enemyWaveSupplier : EnemyWaveRegistry.registry) {
            addWave(enemyWaveSupplier.get());
        }
    }

    public void update(float dt, World.WorldContext context) {
        if (currentWave == null) { //TODO: ADD WAVE END LOGIC
            currentWave = waves.poll();
            if (currentWave == null) return;
        }
        currentWave.update(dt, context);
    }

    public boolean done() {
        return waves.isEmpty();
    }
}
