package io.github.nelsonkhalil.entity;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetManager;
import io.github.nelsonkhalil.entity.collision.CollisionShape;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public interface Entity {
    void update(float dt, World.WorldContext context, AssetManager am, GameState gameState);
    void render(DrawContext context, AssetManager am);
    boolean shouldRemove();
    void dispose();
    CollisionShape getCollisionShape();
    Vector2 getPosition();
}
