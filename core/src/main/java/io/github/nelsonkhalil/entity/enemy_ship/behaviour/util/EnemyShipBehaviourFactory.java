package io.github.nelsonkhalil.entity.enemy_ship.behaviour.util;

import io.github.nelsonkhalil.entity.enemy_ship.behaviour.AfraidEnemyShipBehaviour;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.BasicEnemyShipBehaviour;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.KamikazeEnemyShipBehaviour;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.SineWaveEnemyShipBehaviour;

public class EnemyShipBehaviourFactory {
    private EnemyShipBehaviourFactory() {}

    public static EnemyShipBehaviour getStrategy(Type type) {
        return switch (type) {
            case BASIC -> new BasicEnemyShipBehaviour();
            case KAMIKAZE -> new KamikazeEnemyShipBehaviour();
            case SINE_WAVE -> new SineWaveEnemyShipBehaviour();
            case AFRAID -> new AfraidEnemyShipBehaviour();
        };
    }

    public enum Type {
        BASIC,
        KAMIKAZE,
        SINE_WAVE,
        AFRAID
    }
}
