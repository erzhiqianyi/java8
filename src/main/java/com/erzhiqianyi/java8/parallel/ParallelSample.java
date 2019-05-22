package com.erzhiqianyi.java8.parallel;

import java.util.stream.Stream;

public class ParallelSample {
    public static void main(String[] args) {
        System.out.println(parallelSum(1000000));
    }

    private static long parallelSum(int n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
