package com.erzhiqianyi.java8.collect;

import com.erzhiqianyi.java8.collect.model.Currency;
import com.erzhiqianyi.java8.collect.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class CollectSample {
    public static void main(String[] args) {
        //交易按货币分组，获得该货币的交易总额
        //交易分成两组，贵的和不贵的
        //多级分组，先按城市分组，再按贵和不贵分组
        collectWithoutStream();
        collectWithStream();
    }

    public static void collectWithoutStream() {
        List<Transaction> transactions = new ArrayList<>();
        int i = 100;
        for (Currency currency : Currency.values()) {
            Transaction one = new Transaction(currency, i++, currency.toString());
            Transaction another = new Transaction(currency, i % 2, currency.toString());
            transactions.add(one);
            transactions.add(another);
        }
        //交易按货币分组，获得该货币的交易总额
        Map<Currency, Double> currencySumMap = new HashMap<>();
        for (Transaction transaction : transactions) {
            Double value = currencySumMap.get(transaction.getCurrency());
            if (null == value) {
                value = transaction.getValue();
            } else {
                value = value + transaction.getValue();
            }
            currencySumMap.put(transaction.getCurrency(), value);
        }
        System.out.println(currencySumMap);

        //交易分成两组，贵的和不贵的,大于102算贵的
        Map<Boolean, List<Transaction>> valueMap = new HashMap<>();
        valueMap.put(Boolean.TRUE, new ArrayList<>());
        valueMap.put(Boolean.FALSE, new ArrayList<>());
        for (Transaction transaction : transactions) {
            List<Transaction> currencyList;
            if (transaction.getValue() > 102) {
                currencyList = valueMap.get(Boolean.TRUE);
                currencyList.add(transaction);
            } else {
                currencyList = valueMap.get(Boolean.FALSE);
                currencyList.add(transaction);
            }
        }
        System.out.println(valueMap);

        //多级分组，先按城市分组，再按贵和不贵分组
        Map<String, Map<Boolean, List<Transaction>>> cityMap = new HashMap<>();
        for (Transaction transaction : transactions) {
            Map<Boolean, List<Transaction>> map = cityMap.get(transaction.getCity());
            if (null == map) {
                map = new HashMap<>();
                map.put(Boolean.TRUE, new ArrayList<>());
                map.put(Boolean.FALSE, new ArrayList<>());
                cityMap.put(transaction.getCity(), map);
            }

            if (transaction.getValue() > 102) {
                map.get(Boolean.TRUE).add(transaction);
            } else {
                map.get(Boolean.FALSE).add(transaction);
            }

        }
        System.out.println(cityMap);

    }

    public static void collectWithStream() {

        List<Transaction> transactions = new ArrayList<>();
        int i = 100;

        for (Currency currency : Currency.values()) {
            Transaction one = new Transaction(currency, i++, currency.toString());
            transactions.add(one);
            Transaction another = new Transaction(currency, i % 2, currency.toString());
            transactions.add(another);
        }

        //交易按货币分组，获得该货币的交易总额
        Map<Currency, Double> currencySumMap = transactions
                .stream()
                .collect(groupingBy(Transaction::getCurrency,
                        Collectors.summingDouble(Transaction::getValue)));
        System.out.println(currencySumMap);

        //交易分成两组，贵的和不贵的,大于102算贵的
        Map<Boolean, List<Transaction>> valueMap =
                transactions
                        .stream()
                        .collect(groupingBy(trans -> {
                            if (trans.getValue() > 102) {
                                return Boolean.TRUE;
                            } else {
                                return Boolean.FALSE;
                            }
                        }));
        System.out.println(valueMap);

        //多级分组，先按城市分组，再按贵和不贵分组

        Map<String, Map<Boolean, List<Transaction>>> cityMap =
                transactions.stream()
                        .collect(groupingBy(Transaction::getCity,
                                groupingBy(trans -> {
                                    if (trans.getValue() > 102) {
                                        return Boolean.TRUE;
                                    } else {
                                        return Boolean.FALSE;
                                    }
                                })
                        ));

        System.out.println(cityMap);
    }
}

