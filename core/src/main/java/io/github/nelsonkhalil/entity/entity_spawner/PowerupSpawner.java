package io.github.nelsonkhalil.entity.entity_spawner;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.powerup.PowerupType;
import io.github.nelsonkhalil.helper.ListHelper;

public class PowerupSpawner extends SimpleEntitySpawner {
    @Override
    float getSpawnCooldown() {
        return 30;
    }

    @Override
    void doSpawnUpdate(float dt, World.WorldContext context) {
        context.createPowerupEntity(new Vector2(
            MathUtils.random(Main.VIEW_WIDTH),
            MathUtils.random(Main.VIEW_HEIGHT - 150)
        ), ListHelper.randomEntry(PowerupType.values()));
    }
}
