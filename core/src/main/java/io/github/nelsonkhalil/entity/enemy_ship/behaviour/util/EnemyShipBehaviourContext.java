package io.github.nelsonkhalil.entity.enemy_ship.behaviour.util;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.entity.player.Player;

public class EnemyShipBehaviourContext {
    public final float size;
    public final Vector2 position;
    public final Player player;
    public final World.WorldContext worldContext;
    public final AssetLoader assetLoader;

    public EnemyShipBehaviourContext(float size, Vector2 position, Player player, World.WorldContext worldContext, AssetLoader assetLoader) {
        this.size = size;
        this.position = position;
        this.player = player;
        this.worldContext = worldContext;
        this.assetLoader = assetLoader;
    }

    public float getMoveSpeed(float dt, float speed) {
        return dt * speed * size;
    }

    public Vector2 getPlayerPosition() {
        return player.getPosition();
    }

    public float getYDist() {
        return position.y - getPlayerPosition().y;
    }

    public float getXDist() {
        return Math.abs(position.x - getPlayerPosition().x);
    }

    public Vector2 getMoveToPlayerVector() {
        return getPlayerPosition().sub(position);
    }

    public void setVectorWithSpeed(Vector2 vector, float moveSpeed) {
        vector.nor().setLength(moveSpeed);
    }
}
