package io.github.nelsonkhalil.enemy_wave;

import io.github.nelsonkhalil.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public record EnemyWaveNodeReturnable(List<Entity> addedEntities) {
    public EnemyWaveNodeReturnable() {
        this(new ArrayList<>());
    }
}
