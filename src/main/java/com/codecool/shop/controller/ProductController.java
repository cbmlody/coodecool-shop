package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public class ProductController {

    public void getAllProducts() throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getAll();
    }

    public void findProduct(int id) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        Product product = productDaoSqlite.find(id);
    }

    public void getBySupplier(Supplier supplier) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getBy(supplier);
    }

    public void getByProductCategory(ProductCategory productCategory) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getBy(productCategory);
    }

    public void getProductsByName(String name) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        List<Product> productList = productDaoSqlite.getBy(name);
    }
}
