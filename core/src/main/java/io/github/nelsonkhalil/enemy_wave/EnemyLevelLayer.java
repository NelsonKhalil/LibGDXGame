package io.github.nelsonkhalil.enemy_wave;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.SerializationException;
import io.github.nelsonkhalil.World;

import java.util.HashMap;
import java.util.Map;

public class EnemyLevelLayer {
    private final Map<Integer, EnemyLevelPrototype> levelPrototypes;
    private EnemyLevel currentEnemyLevel;
    private int currentLevelIndex;

    public EnemyLevelLayer() {
        levelPrototypes = new HashMap<>();
        currentEnemyLevel = null;
        currentLevelIndex = -1;
    }

    public void addLevel(EnemyLevel level) {
        levelPrototypes.put(level.id, new EnemyLevelPrototype(level));
    }

    public boolean setLevel(int id) {
        if (id < 0) return false;
        if (!levelPrototypes.containsKey(id)) return false;
        currentEnemyLevel = levelPrototypes.get(id).get();
        currentLevelIndex = id;
        return true;
    }

    public void update(float dt, World.WorldContext context) {
        if (currentEnemyLevel == null) return;
        currentEnemyLevel.update(dt, context);
        if (currentEnemyLevel.done()) currentEnemyLevel = null;
    }

    public void nextLevel() {
        if (currentLevelIndex == -1) return;
        currentLevelIndex++;
        if (!setLevel(currentLevelIndex)) currentLevelIndex--;
    }

    public boolean done() {
        return currentEnemyLevel == null;
    }

    public void loadFromJson() {
        FileHandle levelsDir = Gdx.files.internal("level");
        if (!levelsDir.exists()) throw new LevelLoadExeption("Unable to locate level directory");
        if (!levelsDir.isDirectory()) throw new LevelLoadExeption("Level directory is not a directory");
        for (FileHandle file : levelsDir.list()) {
            if (!"json".equals(file.extension())) continue;
            readJsonLevelFile(file);
        }
    }

    private void readJsonLevelFile(FileHandle file) {
        JsonValue root;
        try {
            root = new JsonReader().parse(file);
        } catch (SerializationException se) {
            throw new LevelLoadExeption(se);
        }

        EnemyLevel level = new EnemyLevel.Builder().fromJson(root);
        addLevel(level);
    }
}
