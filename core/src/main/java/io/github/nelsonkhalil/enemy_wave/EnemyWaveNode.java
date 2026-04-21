package io.github.nelsonkhalil.enemy_wave;

import io.github.nelsonkhalil.World;

public interface EnemyWaveNode {
    void start(World.WorldContext context);
    void update(float dt);
    boolean shouldBlock();
}
