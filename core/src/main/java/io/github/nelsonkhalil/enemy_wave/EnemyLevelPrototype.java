package io.github.nelsonkhalil.enemy_wave;

public class EnemyLevelPrototype {
    private final EnemyLevel prototype;

    public EnemyLevelPrototype(EnemyLevel prototype) {
        this.prototype = prototype;
    }

    public EnemyLevel get() {
        return new EnemyLevel(prototype);
    }
}
