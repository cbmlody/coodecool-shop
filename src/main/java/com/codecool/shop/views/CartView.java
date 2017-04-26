package com.codecool.shop.views;

import java.util.List;

public class CartView implements View<CartItem> {
    public void displayOne(CartItem item) {
        System.out.println(item);
    }

    public void displayAll(List<CartItem> cartItems) {
        for (CartItem item : cartItems) {
            System.out.println(item);
        }
    }
}
