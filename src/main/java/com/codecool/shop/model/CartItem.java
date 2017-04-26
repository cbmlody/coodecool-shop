package com.codecool.shop.model;

public class CartItem {
    private Product product;
    private Integer quantity;

    CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    Product getProduct() {
        return product;
    }
    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
