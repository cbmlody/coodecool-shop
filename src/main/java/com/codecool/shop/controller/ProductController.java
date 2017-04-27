package com.codecool.shop.controller;

public class ProductController {
    private static final ProductController INSTANCE = new ProductController();
    private  ProductController(){}
    public static ProductCategoryController getInstance() {
        return INSTANCE;
    }

}
