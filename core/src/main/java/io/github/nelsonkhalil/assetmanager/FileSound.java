package io.github.nelsonkhalil.assetmanager;

public enum FileSound {
    PLAYER_SHOOT("player/shoot.wav"),
    PLAYER_HIT("player/player_hit.wav"),
    PLAYER_HIT_SHIELD("player/player_hit_shield.wav"),
    PLAYER_DEATH("player/player_death.wav"),

    POWER_UP("power_up/power_up.wav"),
    POWER_UP_FAIL("power_up/power_up_fail.wav"),

    ENEMY_SHIP_SHOOT("enemy_ship/enemy_shoot.wav"),
    ENEMY_SHIP_HIT("enemy_ship/enemy_hit.wav"),
    ENEMY_SHIP_DEATH("enemy_ship/enemy_death.wav"),

    ASTEROID_HIT("asteroid/asteroid_hit.wav"),
    ASTEROID_DEATH("asteroid/asteroid_death.wav"),
    ASTEROID_PLAYER_COLLIDE("asteroid/asteroid_player_collide.wav");

    public final String path;
    FileSound(String path) {
        this.path = path;
    }
}
