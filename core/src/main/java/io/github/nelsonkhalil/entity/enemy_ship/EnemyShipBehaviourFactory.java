package io.github.nelsonkhalil.entity.enemy_ship;

public class EnemyShipBehaviourFactory {
    private EnemyShipBehaviourFactory() {}

    public static EnemyShipBehaviourStrategy getStrategy(Type type) {
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
