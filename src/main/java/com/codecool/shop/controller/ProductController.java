package com.codecool.shop.controller;

import java.util.List;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.views.ProductView;

public class ProductController {
    private static final ProductController INSTANCE = new ProductController();
    private  ProductController(){}
    public static ProductCategoryController getInstance() {
        return INSTANCE;
    }

    public void getAllProducts(){
        ProductView productView = new ProductView();
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getAll();
        productView.displayAll(productList);
    }
}
