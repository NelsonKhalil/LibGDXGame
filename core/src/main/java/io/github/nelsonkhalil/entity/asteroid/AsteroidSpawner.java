package io.github.nelsonkhalil.entity.asteroid;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;

import java.util.Random;

public class AsteroidSpawner {
    private float spawnCooldown;
    private Random random;

    public AsteroidSpawner() {
        spawnCooldown = 0;
        random = new Random();
    }

    public void spawnUpdate(float dt, World.WorldContext context) {
        if (spawnCooldown != 0) {
            spawnCooldown = Math.max(0, spawnCooldown - dt);
            return;
        }
        context.createAsteroid(new Vector2(
            random.nextFloat(Main.VIEW_WIDTH),
            Main.VIEW_HEIGHT + 200
        ), AsteroidInfo.randomSize());
        spawnCooldown = 0.5F;
    }
}
