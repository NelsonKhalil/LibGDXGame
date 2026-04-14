package io.github.nelsonkhalil.helper;

import io.github.nelsonkhalil.Main;

import java.util.List;

public final class ListHelper {
    private ListHelper() {}

    public static <T> T randomEntry(List<T> list) {
        return list.get(Main.RANDOM.nextInt(list.size()));
    }

    public static <T> T randomEntry(T[] array) {
        return array[Main.RANDOM.nextInt(array.length)];
    }
}
