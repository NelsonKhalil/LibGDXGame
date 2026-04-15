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
    NUM_9("numeral/numeral9.png");

    public final String path;
    FileTexture(String path) {
        this.path = path;
    }
}
