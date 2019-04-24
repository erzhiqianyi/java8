package com.erzhiqianyi.java8.collect;

import com.erzhiqianyi.java8.collect.model.Currency;
import com.erzhiqianyi.java8.collect.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectSample {
    public static void main(String[] args){
        //交易按货币分组，获得该货币的交易总额
        //交易分成两组，贵的和不贵的
        //多级分组，先按城市分组，再按贵和不贵分组
        collectWithoutStream();
        collectWithStream();
    }

    public static void collectWithoutStream(){
        List<Transaction> transactions = new ArrayList<>();
        int i = 100 ;
        for (Currency currency : Currency.values()){
            Transaction transaction = new Transaction(currency,i++,currency.toString())    ;
            transactions.add(transaction);
        }
        //交易按货币分组，获得该货币的交易总额
        Map<Currency,Double> currencySumMap = new HashMap<>();
        for (Transaction transaction : transactions){
            Double value = currencySumMap.get(transaction.getCurrency());
            if (null == value){
                value = transaction.getValue();
            }else {
                value = value + transaction.getValue();
            }
            currencySumMap.put(transaction.getCurrency(),value);
        }

        System.out.println(currencySumMap);




    }

    public static void collectWithStream(){

    }
}

