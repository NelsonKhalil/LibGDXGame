package io.github.nelsonkhalil.assetmanager;

public enum FileSound {
    SHOOT("shoot.wav", Type.SFX),
    PLAYER_HIT("player_hit.wav", Type.SFX),
    PLAYER_HIT_SHIELD("player_hit_shield.wav", Type.SFX),
    ASTEROID_HIT("asteroid_hit.wav", Type.SFX),
    ASTEROID_DEATH("asteroid_death.wav", Type.SFX),
    ASTEROID_PLAYER_COLLIDE("asteroid_player_collide.wav", Type.SFX);

    public final String path;
    public final Type type;
    FileSound(String path, Type type) {
        this.path = path;
        this.type = type;
    }

    public enum Type {
        MASTER(100),
        UI(100),
        SFX(100),
        MISC(100);

        public final int defaultVolume;
        Type(int defaultVolume) {
            this.defaultVolume = defaultVolume;
        }
    }
}
