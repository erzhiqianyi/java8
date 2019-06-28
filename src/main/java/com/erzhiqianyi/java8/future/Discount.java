package com.erzhiqianyi.java8.future;

public class Discount {
    public enum Code{
        NONE(0),
        SILVER(5),
        GOLD(6),
        PLATINUM(15),
        DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }


    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
       return 0;
    }

}
