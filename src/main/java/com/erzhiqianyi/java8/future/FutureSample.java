package com.erzhiqianyi.java8.future;

import java.util.concurrent.*;

public class FutureSample {
    public static void main(String[] args) {
        before8();
    }

    public static void before8() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(() -> doSomeLongComputation());
        doSomeThingElse();
        try {
            double value = future.get(10, TimeUnit.SECONDS);
            System.out.println("value is  " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void doSomeThingElse() {
        System.out.println("do some thing else ");
    }

    public static Double doSomeLongComputation() {
        try {
            System.out.println(" sleep  ");
            Thread.sleep(6000);
            System.out.println(" finish sleep ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Math.random();
    }
}
