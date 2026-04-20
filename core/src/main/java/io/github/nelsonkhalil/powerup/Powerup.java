package io.github.nelsonkhalil.powerup;

public class Powerup {

    public PowerupType type;
    public float duration;

    public Powerup(PowerupType type) {
        this.type = type;
        this.duration = type.duration;
    }

    public void update(float dt) {
        if (duration >= 0)
            duration = Math.max(0, duration - dt);
    }

    public boolean shouldRemove() {
        return duration == 0;
    }
}
