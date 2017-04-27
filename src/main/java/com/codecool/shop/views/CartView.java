package com.codecool.shop.views;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;

import java.util.List;

public class CartView implements View<CartItem> {
    public void displayOne(CartItem item) {
        System.out.format("%-3d | %-3d | %-15s | %-20s | %-9.2f | %-9.2f\n",
                item.getQuantity(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getProduct().getSupplier().getName(),
                item.getProduct().getDefaultPrice(),
                item.getConvertedCost());
    }

    public void displayAll(List<CartItem> cartItems) {
        if (cartItems.size() > 0) {
            for (CartItem item : cartItems) {
                System.out.format("%-3s | %-3s | %-15s | %-20s | %-9s | %-9s\n", "QTY", "ID", "NAME", "SUPPLIER", "PRICE", "TOTAL");
                displayOne(item);
            }
        } else {
            System.out.println("No items found...");
        }
    }

    public void displayCart(Cart cart){
        System.out.format("%-3s | %-3s | %-15s | %-20s | %-9s | %-9s\n", "QTY", "ID", "NAME", "SUPPLIER", "PRICE", "TOTAL");
        if (cart.size() > 0){
            cart.forEach(this::displayOne);
        } else {
            System.out.println("No items found...");
        }

    }
}
