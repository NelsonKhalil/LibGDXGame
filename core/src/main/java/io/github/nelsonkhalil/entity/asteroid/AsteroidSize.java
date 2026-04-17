package io.github.nelsonkhalil.entity.asteroid;

import io.github.nelsonkhalil.assetmanager.MultiFileTexture;

public enum AsteroidSize {
    BIG(MultiFileTexture.ASTEROID_BIG, 3),
    MEDIUM(MultiFileTexture.ASTEROID_MED, 2),
    SMALL(MultiFileTexture.ASTEROID_SMALL, 1),
    TINY(MultiFileTexture.ASTEROID_TINY, 1);

    public final MultiFileTexture textures;
    public final int health;
    AsteroidSize(MultiFileTexture textures, int health) {
        this.textures = textures;
        this.health = health;
    }
}
