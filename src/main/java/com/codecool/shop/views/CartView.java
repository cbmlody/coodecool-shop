package com.codecool.shop.views;

import com.codecool.shop.model.CartItem;

import java.util.List;

public class CartView implements View<CartItem> {
    public void displayOne(CartItem item) {
        System.out.format("%-3d | %-15s | %-10s | %-9.2f",
                item.getQuantity(),
                item.getProduct().getName(),
                item.getProduct().getSupplier().getName(),
                item.getProduct().getDefaultPrice());
    }

    public void displayAll(List<CartItem> cartItems) {
        if (cartItems.size() > 0) {
            for (CartItem item : cartItems) {
                System.out.format("%-3s | %-15s | %-10s | %-9s", "ID", "NAME", "SUPPLIER", "PRICE");
                displayOne(item);
            }
        } else {
            System.out.println("No items found...");
        }
    }
}
