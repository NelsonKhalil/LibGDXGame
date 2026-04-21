package io.github.nelsonkhalil.entity.enemy_ship.behaviour.util;

public interface EnemyShipBehaviour {
    EnemyShipActionReturnable update(float dt, EnemyShipBehaviourContext context);
}
