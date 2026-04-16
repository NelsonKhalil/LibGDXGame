package io.github.nelsonkhalil.assetmanager;

import com.badlogic.gdx.graphics.Texture;
import io.github.nelsonkhalil.SettingsManager;

public class AssetManager {
    private final FileTextureManager textureManager;
    private final FileSoundManager soundManager;

    public AssetManager(SettingsManager settingsManager) {
        textureManager = new FileTextureManager();
        soundManager = new FileSoundManager(settingsManager);
    }

    // TODO: REPLACE WITH LIBGDX
    // TODO: MANAGER IS A BAD NAME ( ANTI-PATTERN ), LOADER, ...

    public Texture getTexture(FileTexture texture) {
        return textureManager.get(texture);
    }

    public void playSound(FileSound sound) {
        soundManager.play(sound);
    }

    public void dispose() {
        textureManager.dispose();
        soundManager.dispose();
    }
}
