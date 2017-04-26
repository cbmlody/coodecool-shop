package com.codecool.shop.views;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryView implements View<ProductCategory> {
    public void displayOne(ProductCategory productCategory) {
        System.out.println(productCategory);
    }

    public void displayAll(List<ProductCategory> productCategories) {
        if (productCategories.size() > 0) {
            for (ProductCategory productCategory : productCategories) {
                System.out.println(productCategory);
            }
        } else {
            System.out.println("No items found...");
        }
    }
}