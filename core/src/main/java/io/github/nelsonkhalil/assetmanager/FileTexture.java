package io.github.nelsonkhalil.assetmanager;

public enum FileTexture {
    BACKGROUND("background.png"),

    PLAYER("player/player.png"),
    BULLET("bullet/bullet.png"),

    ENEMY_SHIP("enemy_ship/enemy_ship.png"),
    ENEMY_BULLET("enemy_ship/enemy_bullet.png"),

    LIFE("life.png"),
    SHIELD_1("player/shield1.png"),
    SHIELD_2("player/shield2.png"),
    SHIELD_3("player/shield3.png"),

    ASTEROID_BIG_1("asteroid/asteroid_big_1.png"),
    ASTEROID_BIG_2("asteroid/asteroid_big_2.png"),
    ASTEROID_BIG_3("asteroid/asteroid_big_3.png"),
    ASTEROID_BIG_4("asteroid/asteroid_big_4.png"),
    ASTEROID_MED_1("asteroid/asteroid_med_1.png"),
    ASTEROID_MED_2("asteroid/asteroid_med_2.png"),
    ASTEROID_SMALL_1("asteroid/asteroid_small_1.png"),
    ASTEROID_SMALL_2("asteroid/asteroid_small_2.png"),
    ASTEROID_TINY_1("asteroid/asteroid_tiny_1.png"),
    ASTEROID_TINY_2("asteroid/asteroid_tiny_2.png"),

    NUM_0("numeral/numeral0.png"),
    NUM_1("numeral/numeral1.png"),
    NUM_2("numeral/numeral2.png"),
    NUM_3("numeral/numeral3.png"),
    NUM_4("numeral/numeral4.png"),
    NUM_5("numeral/numeral5.png"),
    NUM_6("numeral/numeral6.png"),
    NUM_7("numeral/numeral7.png"),
    NUM_8("numeral/numeral8.png"),
    NUM_9("numeral/numeral9.png"),

    BLACK_SMOKE_0("particle/black_smoke/blackSmoke00.png"),
    BLACK_SMOKE_1("particle/black_smoke/blackSmoke01.png"),
    BLACK_SMOKE_2("particle/black_smoke/blackSmoke02.png"),
    BLACK_SMOKE_3("particle/black_smoke/blackSmoke03.png"),
    BLACK_SMOKE_4("particle/black_smoke/blackSmoke04.png"),
    BLACK_SMOKE_5("particle/black_smoke/blackSmoke05.png"),
    BLACK_SMOKE_6("particle/black_smoke/blackSmoke06.png"),
    BLACK_SMOKE_7("particle/black_smoke/blackSmoke07.png"),
    BLACK_SMOKE_8("particle/black_smoke/blackSmoke08.png"),
    BLACK_SMOKE_9("particle/black_smoke/blackSmoke09.png"),
    BLACK_SMOKE_10("particle/black_smoke/blackSmoke10.png"),
    BLACK_SMOKE_11("particle/black_smoke/blackSmoke11.png"),
    BLACK_SMOKE_12("particle/black_smoke/blackSmoke12.png"),
    BLACK_SMOKE_13("particle/black_smoke/blackSmoke13.png"),
    BLACK_SMOKE_14("particle/black_smoke/blackSmoke14.png"),
    BLACK_SMOKE_15("particle/black_smoke/blackSmoke15.png"),
    BLACK_SMOKE_16("particle/black_smoke/blackSmoke16.png"),
    BLACK_SMOKE_17("particle/black_smoke/blackSmoke17.png"),
    BLACK_SMOKE_18("particle/black_smoke/blackSmoke18.png"),
    BLACK_SMOKE_19("particle/black_smoke/blackSmoke19.png"),
    BLACK_SMOKE_20("particle/black_smoke/blackSmoke20.png"),
    BLACK_SMOKE_21("particle/black_smoke/blackSmoke21.png"),
    BLACK_SMOKE_22("particle/black_smoke/blackSmoke22.png"),
    BLACK_SMOKE_23("particle/black_smoke/blackSmoke23.png"),
    BLACK_SMOKE_24("particle/black_smoke/blackSmoke24.png"),

    EXPLOSION_0("particle/explosion/explosion00.png"),
    EXPLOSION_1("particle/explosion/explosion01.png"),
    EXPLOSION_2("particle/explosion/explosion02.png"),
    EXPLOSION_3("particle/explosion/explosion03.png"),
    EXPLOSION_4("particle/explosion/explosion04.png"),
    EXPLOSION_5("particle/explosion/explosion05.png"),
    EXPLOSION_6("particle/explosion/explosion06.png"),
    EXPLOSION_7("particle/explosion/explosion07.png"),
    EXPLOSION_8("particle/explosion/explosion08.png"),

    POWER_UP_INVULNERABILITY("power_up/power_up_shield.png"),
    POWER_UP_PLUS_ONE("power_up/power_up_star.png"),
    POWER_UP_DOUBLE_SHOOT("power_up/power_up_bolt.png");


    public final String path;
    FileTexture(String path) {
        this.path = path;
    }
}
