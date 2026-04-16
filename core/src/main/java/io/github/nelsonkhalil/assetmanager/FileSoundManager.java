package io.github.nelsonkhalil.assetmanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import io.github.nelsonkhalil.SettingsManager;

import java.util.HashMap;
import java.util.Map;

public class FileSoundManager {
    private final Map<FileSound, Sound> soundMap;
    private final SettingsManager settingsManager;

    public FileSoundManager(SettingsManager settingsManager) {
        soundMap = new HashMap<>();
        this.settingsManager = settingsManager;
    }

    public Sound get(FileSound fileSound) {
        if (soundMap.containsKey(fileSound)) {
            return soundMap.get(fileSound);
        }
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(fileSound.path));
        soundMap.put(fileSound, sound);
        return sound;
    }

    public void play(FileSound fileSound) {
        Sound sound = get(fileSound);
        int volume = settingsManager.getMasterVolume();
        if (fileSound.type != FileSound.Type.MASTER) {
            int typeVolume = settingsManager.getVolume(fileSound);
            volume *= typeVolume;
        }
        sound.play(volume / 100F);
    }

    public void dispose() {
        for (Sound sound : soundMap.values()) {
            sound.dispose();
        }
    }
}
