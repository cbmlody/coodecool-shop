package com.codecool.shop.views;

import com.codecool.shop.model.Product;

import java.util.List;

public class ProductView implements View<Product> {

    public void displayOne(Product product) {
        System.out.format("%-3d | %-75s | %-9.2f | %-3s | %-20s\n",
                product.getId(),
                product.getName(),
                product.getConvertedFloatPrice(),
                Product.getBaseCurrency(),
                product.getSupplier().getName());
    }

    @Override
    public void displayAll(List<Product> products) {
        if (products.size() > 0) {
            System.out.format("%-3s | %-75s | %-9s | %-3s | %-20s\n", "ID", "NAME", "PRICE", "CUR", "SUPPLIER");
            for (Product product : products) {
                displayOne(product);
            }
        } else {
            System.out.println("No items found...");
        }
    }
}
