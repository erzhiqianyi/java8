package com.erzhiqianyi.java8.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;

public class ParallelMeasureSample {

    public static void main(String[] args) {
        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelMeasureSample::sequentialSum, 100_000_000) + " msecs");
        System.out.println("parallel sum done in: " + measureSumPerf(ParallelMeasureSample::parallelSum, 100_000_000) + " msecs");
        System.out.println("side effect sum done in: " + measureSumPerf(ParallelMeasureSample::sideEffectSum, 100_000_000) + " msecs");
        System.out.println("side effect parallel sum done in: " + measureSumPerf(ParallelMeasureSample::sideEffectParallelSum, 100_000_000) + " msecs");

    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }


    public static long parallelSum(long n) {
        return LongStream.rangeClosed(1L, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long sequentialSum(Long n) {
        return LongStream.rangeClosed(1l, n)
                .reduce(0l, Long::sum);
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).forEach(accumulator::add);
        return accumulator.total;

    }
    public static long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    static class Accumulator {

        private long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
