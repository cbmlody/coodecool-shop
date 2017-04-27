package com.codecool.shop.views;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryView implements View<ProductCategory> {
    public void displayOne(ProductCategory productCategory) {
        System.out.format("%-3d | %-15s | %-20s\n",
                productCategory.getId(),
                productCategory.getName(),
                productCategory.getDescription());
    }

    public void displayAll(List<ProductCategory> productCategories) {
        if (productCategories.size() > 0) {
            showTableHead();
            for (ProductCategory productCategory : productCategories) {
                displayOne(productCategory);
            }
        } else {
            System.out.println("No items found...");
        }
    }

    public void showTableHead(){
        System.out.format("%-3s | %-15s | %-20s\n", "ID", "NAME", "DESCRIPTION");
    }
}
