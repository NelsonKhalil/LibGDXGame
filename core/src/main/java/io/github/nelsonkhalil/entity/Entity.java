package io.github.nelsonkhalil.entity;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.entity.collision.CollisionShape;

public interface Entity {
    void update(float dt, World.WorldContext context, AssetManager assetManager, GameState gameState);
    void render(DrawContext context, AssetManager assetManager);
    boolean shouldRemove();
    void dispose();
    CollisionShape getCollisionShape();
    Vector2 getPosition();
}
