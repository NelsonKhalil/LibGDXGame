package io.github.nelsonkhalil.entity.entity_spawner;

import io.github.nelsonkhalil.World;

public interface EntitySpawner {
    void spawnUpdate(float dt, World.WorldContext context);
}
