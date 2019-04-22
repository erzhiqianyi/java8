package com.erzhiqianyi.java8.stream;

import java.util.stream.Stream;

public class SkipSample {
    public static void main(String[] args) {
        int second = Stream.of(1, 2, 3, 4, 6)
                .skip(1)
                .limit(1)
                .findAny()
                .get();
        assert second == 2;
    }
}
