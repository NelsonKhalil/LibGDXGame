package io.github.nelsonkhalil.entity.enemy_ship.behaviour.util;

import com.badlogic.gdx.math.Vector2;

public class EnemyShipActionReturnable {
    public final boolean shoot;
    public final Vector2 movement;

    public EnemyShipActionReturnable(Vector2 movement, boolean shoot) {
        this.movement = movement;
        this.shoot = shoot;
    }
}
