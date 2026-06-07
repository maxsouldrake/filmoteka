package io.github.maxsouldrake.filmoteka.util;

import java.util.HashSet;
import java.util.Set;

public class TestUtil {
    @SafeVarargs
    public static <T> Set<T> testSetOf(T... items) {
        return new HashSet<>(Set.of(items));
    }
}