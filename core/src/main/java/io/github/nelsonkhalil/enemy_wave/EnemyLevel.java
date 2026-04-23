package io.github.nelsonkhalil.enemy_wave;

import com.badlogic.gdx.utils.JsonValue;
import io.github.nelsonkhalil.World;

import java.util.ArrayDeque;
import java.util.Queue;

public class EnemyLevel {
    private final Queue<EnemyWave> waves;
    private EnemyWave currentWave;
    public final int id;
    public final String name;
    public final int difficulty;

    private EnemyLevel(int id, String name, int difficulty, Queue<EnemyWave> waves) {
        this.waves = waves;
        currentWave = null;
        this.name = name;
        this.difficulty = difficulty;
        this.id = id;
    }

    public void update(float dt, World.WorldContext context) {
        if (currentWave == null) {
            currentWave = waves.poll();
            if (currentWave == null) return;
        }
        currentWave.update(dt, context);
        if (currentWave.hasEnded()) currentWave = null;
    }

    public boolean done() {
        return waves.isEmpty();
    }

    public static class Builder {
        private final Queue<EnemyWave> waves;

        public Builder() {
            waves = new ArrayDeque<>();
        }

        public Builder addWave(EnemyWave wave) {
            waves.add(wave);
            return this;
        }

        public EnemyLevel fromJson(JsonValue root) {
            try {
                int id = root.getInt("id");
                String name = root.getString("name");
                int difficulty = root.getInt("difficulty_out_of_ten");

                JsonValue.JsonIterator waves = root.get("waves").iterator();
                for (JsonValue wave : waves) {
                    addWave(new EnemyWave.Builder().fromJson(wave));
                }

                return build(id, name, difficulty);
            } catch (IllegalArgumentException iae) {
                throw new LevelLoadExeption(iae);
            }
        }

        public EnemyLevel build(int id, String name, int difficulty) {
            return new EnemyLevel(id, name, difficulty, waves);
        }
    }
}
