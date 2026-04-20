package io.github.nelsonkhalil.powerup;

import java.util.ArrayList;
import java.util.List;

public class PowerupLayer {
    private final List<Powerup> powerups;

    public PowerupLayer() {
        powerups = new ArrayList<>();
    }

    public void updateAll(float dt) {
        for (Powerup powerup : powerups) {
            powerup.update(dt);
        }
        powerups.removeIf(Powerup::shouldRemove);
    }

    public boolean contains(PowerupType type) {
        for (Powerup powerup : powerups) {
            if (powerup.type == type) return true;
        }
        return false;
    }

    public boolean add(PowerupType type) {
        if (contains(type)) {
            if (type.duration < 0) return false; //INFINITE
            if (type.duration > 0) {
                for (Powerup powerup : powerups) {
                    if (powerup.type == type) {
                        powerup.duration = type.duration; // RESET DURATION
                        return true;
                    }
                }
            }
        }

        powerups.add(new Powerup(type));
        return true;
    }

    public List<Powerup> getPowerups() {
        return List.copyOf(powerups);
    }


}
