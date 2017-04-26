package com.codecool.shop.views;

import com.codecool.shop.model.Product;

import java.util.List;

public class ProductView implements View<Product> {

    public void displayOne(Product product) {
        System.out.println(product);
    }

    @Override
    public void displayAll(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
