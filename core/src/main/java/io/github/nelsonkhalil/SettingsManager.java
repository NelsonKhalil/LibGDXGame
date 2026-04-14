package io.github.nelsonkhalil;

import io.github.nelsonkhalil.assetmanager.FileSound;

import java.util.HashMap;
import java.util.Map;

public class SettingsManager {
    private Map<FileSound.Type, Integer> volumeMap;

    public SettingsManager() {
        volumeMap = new HashMap<>();
        for (FileSound.Type type : FileSound.Type.values()) {
            volumeMap.put(type, type.defaultVolume);
        }
    }

    public int getVolume(FileSound.Type type) {
        return volumeMap.get(type);
    }

    public int getVolume(FileSound sound) {
        return getVolume(sound.type);
    }

    public int getMasterVolume() {
        return getVolume(FileSound.Type.MASTER);
    }

    public void setVolume(FileSound.Type type, int volume) {
        volumeMap.put(type, Math.clamp(volume, 0, 100));
    }
}
