package io.github.nelsonkhalil.entity.entity_spawner;

import io.github.nelsonkhalil.World;

import java.util.Random;

public interface EntitySpawner {
    void spawnUpdate(float dt, World.WorldContext context, Random random);
}
