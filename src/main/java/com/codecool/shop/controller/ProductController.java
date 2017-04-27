package com.codecool.shop.controller;

import com.codecool.shop.model.Supplier;
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
    public void findProduct(int id){
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        ProductView productView = new ProductView();
        Product product = productDaoSqlite.find(id);
        productView.displayOne(product);
    }
    public void getBySupplier(Supplier supplier){
        ProductView productView = new ProductView();
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getBy(supplier);
        productView.displayAll(productList);
    }
    public void getByProductCategory(ProductCategory productCategory){
        ProductView productView = new ProductView();
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getBy(productCategory);
        productView.displayAll(productList);
    }
}
