package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategorySqlite;
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
        ProductCategorySqlite productCategorySqlite = new ProductCategorySqlite();
        List<ProductCategory> productCategoriesList = productCategorySqlite.getAll();
        productCategoryView.displayAll(productCategoriesList);
    }
    public void findCategory(int id){
        ProductCategoryView productCategoryView = new ProductCategoryView();
        ProductCategorySqlite productCategorySqlite = new ProductCategorySqlite();
        ProductCategory productCategory = productCategorySqlite.find(id);
        productCategoryView.displayOne(productCategory);
    }
}
