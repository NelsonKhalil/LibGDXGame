package io.github.nelsonkhalil.entity.asteroid;

import io.github.nelsonkhalil.assetmanager.FileTexture;
import io.github.nelsonkhalil.helper.ListHelper;

public record AsteroidInfo(FileTexture fileTexture, AsteroidSize size) {
    public static AsteroidInfo fromSize(AsteroidSize size) {
        return new AsteroidInfo(ListHelper.randomEntry(size.textures), size);
    }

    public static AsteroidInfo randomSize() {
        return fromSize(ListHelper.randomEntry(AsteroidSize.values()));
    }
}
