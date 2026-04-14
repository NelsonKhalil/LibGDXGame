package io.github.nelsonkhalil.entity.asteroid;

import io.github.nelsonkhalil.assetmanager.FileTexture;

public enum AsteroidSize {
    BIG(new FileTexture[]{FileTexture.ASTEROID_BIG_1, FileTexture.ASTEROID_BIG_2, FileTexture.ASTEROID_BIG_3, FileTexture.ASTEROID_BIG_4}, 3),
    MEDIUM(new FileTexture[]{FileTexture.ASTEROID_MED_1, FileTexture.ASTEROID_MED_2}, 2),
    SMALL(new FileTexture[]{FileTexture.ASTEROID_SMALL_1, FileTexture.ASTEROID_SMALL_2}, 1),
    TINY(new FileTexture[]{FileTexture.ASTEROID_TINY_1, FileTexture.ASTEROID_TINY_2}, 1);

    public final FileTexture[] textures;
    public final int health;
    AsteroidSize(FileTexture[] textures, int health) {
        this.textures = textures;
        this.health = health;
    }
}
