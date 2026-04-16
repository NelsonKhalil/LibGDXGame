package io.github.nelsonkhalil.helper;

import com.badlogic.gdx.math.MathUtils;
import io.github.nelsonkhalil.Main;

import java.util.List;

public final class ListHelper {
    private ListHelper() {}

    public static <T> T randomEntry(List<T> list) {
        return list.get(MathUtils.random(list.size()));
    }

    public static <T> T randomEntry(T[] array) {
        return array[MathUtils.random(array.length - 1)];
    }
}

