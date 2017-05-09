package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public final class ProductCategoryController {

    private static final ProductCategoryController INSTANCE = new ProductCategoryController();

    private ProductCategoryController() {
    }

    public static ProductCategoryController getInstance() {
        return INSTANCE;
    }

    public void getAllProductCategories(){
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        List<ProductCategory> productCategoriesList = productCategoryDaoSqlite.getAll();
    }
    public void findCategory(int id){
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        ProductCategory productCategory = productCategoryDaoSqlite.find(id);
    }
}
