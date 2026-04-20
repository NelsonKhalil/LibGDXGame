package io.github.nelsonkhalil.powerup;

import io.github.nelsonkhalil.assetmanager.FileTexture;

public enum PowerupType {
    INVULNERABILITY(FileTexture.POWER_UP_INVULNERABILITY, 7.5F),
    PLUS_ONE(FileTexture.POWER_UP_PLUS_ONE, 0), // INSTANT
    DOUBLE_SHOOT(FileTexture.POWER_UP_DOUBLE_SHOOT, -1); // INFINITE

    public final FileTexture texture;
    public final float duration;
    PowerupType(FileTexture texture, float duration) {
        this.texture = texture;
        this.duration = duration;
    }
}
