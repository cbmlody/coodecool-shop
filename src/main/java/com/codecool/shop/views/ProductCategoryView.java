package com.codecool.shop.views;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryView implements View<ProductCategory> {
    public void displayOne(ProductCategory productCategory) {
        System.out.println(productCategory);
    }

    public void displayAll(List<ProductCategory> productCategoryList) {
        for (ProductCategory productCategory : productCategoryList) {
            System.out.println(productCategory);
        }
    }
}
