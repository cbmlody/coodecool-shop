package com.codecool.shop.model;

import java.util.Currency;
import java.util.concurrent.ThreadLocalRandom;

public class Payment {
    private String name;
    private Integer cardnumber;
    private Integer price;
    private Currency currency;

    public Payment(String name, Integer cardnumber, Integer price, Currency currency) {
        this.name = name;
        this.cardnumber = cardnumber;
        this.price = price;
        this.currency = currency;
    }
}

