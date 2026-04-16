package io.github.nelsonkhalil.assetmanager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
    private final AssetManager assetManager;

    public AssetLoader() {
        assetManager = new AssetManager();
    }

    public void load() {
        for (FileTexture fileTexture : FileTexture.values()) {
            assetManager.load(fileTexture.path, Texture.class);
        }
        for (FileSound fileSound : FileSound.values()) {
            assetManager.load(fileSound.path, Sound.class);
        }
        assetManager.finishLoading();
    }

    public Texture getTexture(FileTexture fileTexture) {
        return assetManager.get(fileTexture.path, Texture.class);
    }

    public Sound getSound(FileSound fileSound) {
        return assetManager.get(fileSound.path, Sound.class);
    }

    public void dispose() {
        assetManager.dispose();
    }
}
