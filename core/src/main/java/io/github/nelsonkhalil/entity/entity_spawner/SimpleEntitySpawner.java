package io.github.nelsonkhalil.entity.entity_spawner;

import io.github.nelsonkhalil.World;

public abstract class SimpleEntitySpawner implements EntitySpawner {
    abstract float getSpawnCooldown();
    private float spawnCooldown;

    public SimpleEntitySpawner() {
        spawnCooldown = getSpawnCooldown();
    }

    public void spawnUpdate(float dt, World.WorldContext context) {
        if (spawnCooldown != 0) {
            spawnCooldown = Math.max(0, spawnCooldown - dt);
            return;
        }
        doSpawnUpdate(dt, context);
        spawnCooldown = getSpawnCooldown();
    }

    abstract void doSpawnUpdate(float dt, World.WorldContext context);
}
