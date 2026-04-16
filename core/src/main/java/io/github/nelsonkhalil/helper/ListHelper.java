package io.github.nelsonkhalil.helper;

import com.badlogic.gdx.math.MathUtils;

public final class ListHelper {
    private ListHelper() {}

    public static <T> T randomEntry(T[] array) {
        return array[MathUtils.random(array.length - 1)];
    }
}

