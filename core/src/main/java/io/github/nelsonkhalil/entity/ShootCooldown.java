package io.github.nelsonkhalil.entity;

public class ShootCooldown {
    private float cooldown;

    public ShootCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public ShootCooldown() {
        this(0);
    }

    public float get() {
        return cooldown;
    }

    public void set(float cooldown) {
        this.cooldown = cooldown;
    }

    public void update(float dt) {
        cooldown = Math.max(0, cooldown - dt);
    }

    public boolean isZero() {
        return cooldown == 0;
    }
}
