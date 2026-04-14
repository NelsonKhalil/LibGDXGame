package io.github.nelsonkhalil.assetmanager;

public enum FileTexture {
    PLAYER("player.png"),
    BACKGROUND("background.png"),
    BULLET("bullet.png"),

    LIFE("life.png"),

    SHIELD_1("shield1.png"),
    SHIELD_2("shield2.png"),
    SHIELD_3("shield3.png"),

    ASTEROID_BIG_1("asteroid_big_1.png"),
    ASTEROID_BIG_2("asteroid_big_2.png"),
    ASTEROID_BIG_3("asteroid_big_3.png"),
    ASTEROID_BIG_4("asteroid_big_4.png"),
    ASTEROID_MED_1("asteroid_med_1.png"),
    ASTEROID_MED_2("asteroid_med_2.png"),
    ASTEROID_SMALL_1("asteroid_small_1.png"),
    ASTEROID_SMALL_2("asteroid_small_2.png"),
    ASTEROID_TINY_1("asteroid_tiny_1.png"),
    ASTEROID_TINY_2("asteroid_tiny_2.png"),

    NUM_0("numeral0.png"),
    NUM_1("numeral1.png"),
    NUM_2("numeral2.png"),
    NUM_3("numeral3.png"),
    NUM_4("numeral4.png"),
    NUM_5("numeral5.png"),
    NUM_6("numeral6.png"),
    NUM_7("numeral7.png"),
    NUM_8("numeral8.png"),
    NUM_9("numeral9.png");

    public final String path;
    FileTexture(String path) {
        this.path = path;
    }
}
