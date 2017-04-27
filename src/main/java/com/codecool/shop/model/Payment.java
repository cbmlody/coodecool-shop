package com.codecool.shop.model;

import java.util.concurrent.ThreadLocalRandom;

public class Payment {
    private String name;
    private Integer cardnumber;
    private Integer price;
    private String currency;
    private boolean validate;

    public Payment(String name, Integer cardnumber, Integer price, String currency) {
        this.name = name;
        this.cardnumber = cardnumber;
        this.price = price;
        this.currency = currency;
        this.validate = validatePayment();
    }
    private boolean validatePayment(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 101);
        return randomNum < 70;
    }

    public boolean isValidate() {
        return validate;
    }
}
