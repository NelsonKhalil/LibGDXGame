package io.github.nelsonkhalil.entity;

import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.state.GameState;

public interface NonCollidingEntity extends Entity {
    @Override
    default CollisionShape getCollisionShape() {
        return null;
    }

    @Override
    default void onCollide(Entity entity, World.WorldContext context, AssetLoader al, GameState gameState) {

    }
}
