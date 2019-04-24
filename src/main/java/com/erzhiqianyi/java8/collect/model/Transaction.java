package com.erzhiqianyi.java8.collect.model;

import lombok.Data;

@Data
public class Transaction {
    private Currency currency;
    private double value;
    private String city;


    public Transaction(Currency currency, double value,String city) {
        this.currency = currency;
        this.value = value;
        this.city = city;
    }
}
