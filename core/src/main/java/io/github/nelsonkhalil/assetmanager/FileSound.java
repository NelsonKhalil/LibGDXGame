package io.github.nelsonkhalil.assetmanager;

public enum FileSound {
    PLAYER_SHOOT("player/shoot.wav", Type.SFX),
    PLAYER_HIT("player/player_hit.wav", Type.SFX),
    PLAYER_HIT_SHIELD("player/player_hit_shield.wav", Type.SFX),
    PLAYER_DEATH("player/player_death.wav", Type.SFX),

    ENEMY_SHIP_SHOOT("enemy_ship/enemy_shoot.wav", Type.SFX),
    ENEMY_SHIP_HIT("enemy_ship/enemy_hit.wav", Type.SFX),
    ENEMY_SHIP_DEATH("enemy_ship/enemy_death.wav", Type.SFX),

    ASTEROID_HIT("asteroid/asteroid_hit.wav", Type.SFX),
    ASTEROID_DEATH("asteroid/asteroid_death.wav", Type.SFX),
    ASTEROID_PLAYER_COLLIDE("asteroid/asteroid_player_collide.wav", Type.SFX);

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
