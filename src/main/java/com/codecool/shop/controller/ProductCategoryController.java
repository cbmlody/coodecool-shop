package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.views.ProductCategoryView;
import java.util.List;

public final class ProductCategoryController {

    private static final ProductCategoryController INSTANCE = new ProductCategoryController();

    private ProductCategoryController() {
    }

    public static ProductCategoryController getInstance() {
        return INSTANCE;
    }

    public void getAllProductCategories(){
        ProductCategoryView productCategoryView = new ProductCategoryView();
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        List<ProductCategory> productCategoriesList = productCategoryDaoSqlite.getAll();
        productCategoryView.displayAll(productCategoriesList);
    }
    public void findCategory(int id){
        ProductCategoryView productCategoryView = new ProductCategoryView();
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        ProductCategory productCategory = productCategoryDaoSqlite.find(id);
        productCategoryView.displayOne(productCategory);
    }
}
