package com.erzhiqianyi.java8.future;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureSample {

    public static void main(String[] args) {
        String product = "apple";
        findPrices(product);
    }

    private static void findPrices(String product) {
        Stream.of("Best Price", "LetsSaveBig", "MyFavoriteShop", "BuyITAll")
                .map(Shop::new)
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .peek(System.out::println)
                .collect( Collectors.toList());


    }
}
