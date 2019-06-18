package com.erzhiqianyi.java8.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Shop {
    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    private Random random = new Random();

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }

        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceSupplyAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Shop shop = new Shop("Apple Shop");
        long start = System.nanoTime();
        System.out.println(" start calculate price at " + start);
        double price = shop.getPrice("Apple");
        System.out.println(" end calculate price,cost  " + (System.nanoTime() - start) + "nanoseconds");
        System.out.println(" price is " + price);
        start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("Apple");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " millis seconds");
        doSomething();
        try {
            System.out.printf("Price is %.2f%n", futurePrice.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println(" price returned after " + retrievalTime + "millis seconds");

    }

    public static void doSomething() {
        for (int i = 0; i < 10; i++) {
            System.out.println(" do other things " + i);
        }
    }

    public String getShopName() {
        return shopName;
    }
}
