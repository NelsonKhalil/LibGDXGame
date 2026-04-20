package io.github.nelsonkhalil.assetmanager;

import io.github.nelsonkhalil.helper.ListHelper;

public enum MultiFileTexture {

    ASTEROID_BIG(
        FileTexture.ASTEROID_BIG_1,
        FileTexture.ASTEROID_BIG_2,
        FileTexture.ASTEROID_BIG_3,
        FileTexture.ASTEROID_BIG_4
    ),

    ASTEROID_MED(
        FileTexture.ASTEROID_MED_1,
        FileTexture.ASTEROID_MED_2
    ),

    ASTEROID_SMALL(
        FileTexture.ASTEROID_SMALL_1,
        FileTexture.ASTEROID_SMALL_2
    ),

    ASTEROID_TINY(
        FileTexture.ASTEROID_TINY_1,
        FileTexture.ASTEROID_TINY_2
    ),

    BLACK_SMOKE(
        FileTexture.BLACK_SMOKE_0,
        FileTexture.BLACK_SMOKE_1,
        FileTexture.BLACK_SMOKE_2,
        FileTexture.BLACK_SMOKE_3,
        FileTexture.BLACK_SMOKE_4,
        FileTexture.BLACK_SMOKE_5,
        FileTexture.BLACK_SMOKE_6,
        FileTexture.BLACK_SMOKE_7,
        FileTexture.BLACK_SMOKE_8,
        FileTexture.BLACK_SMOKE_9,
        FileTexture.BLACK_SMOKE_10,
        FileTexture.BLACK_SMOKE_11,
        FileTexture.BLACK_SMOKE_12,
        FileTexture.BLACK_SMOKE_13,
        FileTexture.BLACK_SMOKE_14,
        FileTexture.BLACK_SMOKE_15,
        FileTexture.BLACK_SMOKE_16,
        FileTexture.BLACK_SMOKE_17,
        FileTexture.BLACK_SMOKE_18,
        FileTexture.BLACK_SMOKE_19,
        FileTexture.BLACK_SMOKE_20,
        FileTexture.BLACK_SMOKE_21,
        FileTexture.BLACK_SMOKE_22,
        FileTexture.BLACK_SMOKE_23,
        FileTexture.BLACK_SMOKE_24
    ),

    EXPLOSION(
        FileTexture.EXPLOSION_0,
        FileTexture.EXPLOSION_1,
        FileTexture.EXPLOSION_2,
        FileTexture.EXPLOSION_3,
        FileTexture.EXPLOSION_4,
        FileTexture.EXPLOSION_5,
        FileTexture.EXPLOSION_6,
        FileTexture.EXPLOSION_7,
        FileTexture.EXPLOSION_8
    ),

    NUMBERS(
        FileTexture.NUM_0,
        FileTexture.NUM_1,
        FileTexture.NUM_2,
        FileTexture.NUM_3,
        FileTexture.NUM_4,
        FileTexture.NUM_5,
        FileTexture.NUM_6,
        FileTexture.NUM_7,
        FileTexture.NUM_8,
        FileTexture.NUM_9
    );

    public final FileTexture[] textures;
    MultiFileTexture(FileTexture... fileTextures) {
        textures = fileTextures;
    }

    public FileTexture getRandom() {
        return ListHelper.randomEntry(textures);
    }

}
