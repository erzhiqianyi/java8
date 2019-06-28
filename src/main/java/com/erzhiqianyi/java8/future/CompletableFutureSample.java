package com.erzhiqianyi.java8.future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureSample {
    private static List<String> shops = Stream
            .of("Best Price", "LetsSaveBig", "MyFavoriteShop",
                    "BuyITAll", "Apple", "Orange", "Banana",
                    "Not So Good"
            )
            .collect(Collectors.toList());
    private static final Executor excutor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public static void main(String[] args) {
        String product = "apple";

        long start = System.nanoTime();
        System.out.println(findPrices(product));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("block Done in " + duration + " msecs");


        start = System.nanoTime();
        System.out.println(findPricesParallel(product));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("parallel Done in " + duration + " msecs");

        start = System.nanoTime();
        System.out.println(findPricesAsync(product));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("async Done in " + duration + " msecs");

    }


    private static List<String> findPrices(String product) {
        return shops.stream()
                .map(Shop::new)
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());

    }

    private static List<String> findPricesParallel(String product) {
        return shops.stream()
                .parallel()
                .map(Shop::new)
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    private static List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFuture =
                shops.stream()
                        .map(Shop::new)
                        .map(shop -> CompletableFuture.supplyAsync(() ->
                                String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)),
                                excutor))
                        .collect(Collectors.toList());
        return priceFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
