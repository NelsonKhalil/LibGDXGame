package io.github.nelsonkhalil.enemy_wave;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.SerializationException;
import io.github.nelsonkhalil.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EnemyLevelLayer {
    private final List<Supplier<EnemyLevel>> levels;
    private EnemyLevel currentEnemyLevel;

    public EnemyLevelLayer() {
        levels = new ArrayList<>();
        currentEnemyLevel = null;
    }

    public void addLevel(Supplier<EnemyLevel> level) {
        levels.add(level);
    }

    public boolean setLevel(int index) {
        if (index < 0) return false;
        if (index > levels.size() - 1) return false;
        currentEnemyLevel = levels.get(index).get();
        return true;
    }

    public void update(float dt, World.WorldContext context) {
        if (currentEnemyLevel == null) return;
        currentEnemyLevel.update(dt, context);
        if (currentEnemyLevel.done()) currentEnemyLevel = null;
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
        addLevel(() -> level); //TODO: USE PROTOTYPE
    }
}
