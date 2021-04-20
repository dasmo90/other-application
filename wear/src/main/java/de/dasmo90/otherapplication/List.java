package de.dasmo90.otherapplication;

import java.util.Arrays;

public class List {

    @SafeVarargs
    public static <T> java.util.List<T> of(T... objects) {
        return Arrays.asList(objects);
    }
}
