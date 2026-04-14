package io.github.nelsonkhalil.entity.collision;

import io.github.nelsonkhalil.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Collisions {
    private List<Entity> others;

    public Collisions() {
        others = new ArrayList<>();
    }

    public void addOther(Entity entity) {
        others.add(entity);
    }

    public boolean collided() {
        return !others.isEmpty();
    }

    public List<Entity> getOthers() {
        return others;
    }

}
