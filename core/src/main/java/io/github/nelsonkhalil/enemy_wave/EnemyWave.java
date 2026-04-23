package io.github.nelsonkhalil.enemy_wave;

import com.badlogic.gdx.utils.JsonValue;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.entity.Entity;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviourFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class EnemyWave {
    private static final float DEFAULT_ENEMY_Y = Main.VIEW_HEIGHT + 200;

    private final Queue<EnemyWaveNode> nodes;
    private EnemyWaveNode currentNode;

    private final List<Entity> entities;

    private EnemyWave(Queue<EnemyWaveNode> nodes) {
        this.nodes = nodes;
        currentNode = null;
        entities = new ArrayList<>();
    }

    public void update(float dt, World.WorldContext context) {
        entities.removeIf(Entity::shouldRemove);

        if (currentNode == null || !currentNode.shouldBlock()) {
            currentNode = nodes.poll();
            if (currentNode == null) return;

            EnemyWaveNodeReturnable returnable = currentNode.start(context);
            entities.addAll(returnable.addedEntities());
        }
        currentNode.update(dt);
    }

    public boolean hasEnded() {
        return entities.isEmpty() && currentNode == null;
    }

    public static class Builder {
        private final Queue<EnemyWaveNode> nodes;

        public Builder() {
            nodes = new ArrayDeque<>();
        }

        public Builder addShip(EnemyWaveShipNode shipNode) {
            nodes.add(shipNode);
            return this;
        }

        public Builder addShip(float x, float y, EnemyShipBehaviourFactory.Type type) {
            return addShip(new EnemyWaveShipNode(x, y, type));
        }

        public Builder addShip(float x, EnemyShipBehaviourFactory.Type type) {
            return addShip(x, DEFAULT_ENEMY_Y, type);
        }

        public Builder addDelay(EnemyWaveDelayNode delayNode) {
            nodes.add(delayNode);
            return this;
        }

        public Builder addDelaySec(float seconds) {
            return addDelay(new EnemyWaveDelayNode(seconds));
        }

        public Builder addDelayMilSec(int milliseconds) {
            return addDelay(new EnemyWaveDelayNode(milliseconds));
        }

        public EnemyWave fromJson(JsonValue wave) {
            try {
                for (JsonValue node : wave.iterator()) {
                    addJsonNode(node);
                }

                return build();
            } catch (IllegalArgumentException iae) {
                throw  new LevelLoadExeption(iae);
            }
        }

        private void addJsonNode(JsonValue node) {
            String type = node.getString("type");

            switch (type.toLowerCase()) {
                case "ship":
                    EnemyShipBehaviourFactory.Type behaviour = EnemyShipBehaviourFactory.Type.fromString(node.getString("behaviour"));
                    if (behaviour == null) return;
                    float x = parseLocationX(node.get("location"));
                    addShip(x, behaviour);
                    break;
                case "wait":
                    addDelayMilSec(node.getInt("duration"));
                    break;
            }
        }

        private float parseLocationX(JsonValue location) {
            float loc = Main.VIEW_WIDTH;

            if (location.has("div")) loc /= location.getFloat("div");
            if (location.has("mul")) loc *= location.getFloat("mul");

            return loc;
        }

        public EnemyWave build() {
            return new EnemyWave(nodes);
        }
    }
}
