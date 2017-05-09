package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductController {
    private static final ProductController INSTANCE = new ProductController();
    private  ProductController(){}
    public static ProductController getInstance() {
        return INSTANCE;
    }

    public void getAllProducts(){
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getAll();
    }

    public void findProduct(int id){
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        Product product = productDaoSqlite.find(id);
    }

    public void getBySupplier(Supplier supplier){
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getBy(supplier);
    }

    public void getByProductCategory(ProductCategory productCategory){
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getBy(productCategory);
    }

    public void getProductsByName(String name) {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getBy(name);
    }
}
