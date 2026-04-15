package io.github.nelsonkhalil.entity.entity_spawner;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.entity.asteroid.AsteroidInfo;

import java.util.Random;

public class AsteroidSpawner extends SimpleEntitySpawner {
    @Override
    float getSpawnCooldown() {
        return 0.5F;
    }

    @Override
    void doSpawnUpdate(float dt, World.WorldContext context, Random random) {
        context.createAsteroid(new Vector2(
            random.nextFloat(Main.VIEW_WIDTH),
            Main.VIEW_HEIGHT + 200
        ), AsteroidInfo.randomSize());
    }
}
