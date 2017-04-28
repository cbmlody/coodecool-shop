package com.codecool.shop.model;

import java.util.concurrent.ThreadLocalRandom;

public class Payment {
    private String name;
    private Integer cardNumber;
    private boolean validate;

    public Payment(String name, Integer cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
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
