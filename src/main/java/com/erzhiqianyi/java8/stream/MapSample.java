package com.erzhiqianyi.java8.stream;

import java.util.stream.Stream;

public class MapSample {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
                .map(num -> "id:" + num)
                .forEach(num -> {
                    assert num.startsWith("id:");
                });
    }
}
