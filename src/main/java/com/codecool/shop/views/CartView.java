package com.codecool.shop.views;

import java.util.List;

public class CartView implements View<CartItem> {
    public void displayOne(CartItem item) {
        System.out.println(item);
    }

    public void displayAll(List<CartItem> cartItems) {
        if (cartItems.size() > 0) {
            for (CartItem item : cartItems) {
                System.out.println(item);
            }
        } else {
            System.out.println("No items found...");
        }
    }
}
