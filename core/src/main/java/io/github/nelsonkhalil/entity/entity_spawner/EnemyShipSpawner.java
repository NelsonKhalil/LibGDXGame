package io.github.nelsonkhalil.entity.entity_spawner;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;

import java.util.Random;

public class EnemyShipSpawner extends SimpleEntitySpawner {

    @Override
    float getSpawnCooldown() {
        return 15;
    }

    @Override
    void doSpawnUpdate(float dt, World.WorldContext context, Random random) {
        context.createEnemyShip(new Vector2(
            random.nextFloat(Main.VIEW_WIDTH),
            Main.VIEW_HEIGHT + 200
        ));
    }
}
