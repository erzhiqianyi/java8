package com.erzhiqianyi.java8.stream;

import java.util.Objects;
import java.util.stream.IntStream;

public class ReduceSample {
    public static void main(String[] args) {
        sum();
        max();
        min();
    }

    public static void sum() {
        int sum = IntStream.of(1, 2, 3, 4, 5, 6).reduce(0, (x, y) -> x + y);
        assert sum == 21;
        sum = IntStream.of(1, 2, 3, 4, 5, 6).reduce(0, Integer::sum);
        assert sum == 21;
    }

    public static void max() {
        int max = IntStream.of(1, 2, 3, 4, 5, 6).reduce( (x, y) -> x > y ? x : y).getAsInt();
        assert max == 6;
        max = IntStream.of(1, 2, 3, 4, 5, 6).reduce(Integer::max).getAsInt();
        assert max == 6;
    }

    public static void min(){
        int min = IntStream.of(1, 2, 3, 4, 5, 6).reduce( (x, y) -> x < y ? x : y).getAsInt();
        assert min == 1;

        min = IntStream.of(1, 2, 3, 4, 5, 6).reduce( Integer::min).getAsInt();
        assert min == 1;
    }
}
