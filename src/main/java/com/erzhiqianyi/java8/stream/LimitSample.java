package com.erzhiqianyi.java8.stream;

import java.util.stream.Stream;

public class LimitSample {
    public static void main(String[] args) {
        long count = Stream.of(1, 2, 3, 4, 5, 6)
                .limit(3)
                .count();
        assert count == 3;
    }


}
