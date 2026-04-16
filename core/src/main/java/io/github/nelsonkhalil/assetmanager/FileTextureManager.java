package io.github.nelsonkhalil.assetmanager;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class FileTextureManager {
    private final Map<FileTexture, Texture> textureMap;
    public FileTextureManager() {
        textureMap = new HashMap<>();
    }

    public Texture get(FileTexture fileTexture) {
        if (textureMap.containsKey(fileTexture)) {
            return textureMap.get(fileTexture);
        }
        Texture tex = new Texture(fileTexture.path);
        textureMap.put(fileTexture, tex);
        return tex;
    }

    public void dispose() {
        for (Texture texture : textureMap.values()) {
            texture.dispose();
        }
    }
}
