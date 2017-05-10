package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public static ModelAndView getAllProducts(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        Map params = new HashMap<>();
        params.put("productList", productDaoSqlite.getAll());
        return new ModelAndView(params, "product/index");
    }

    public void findProduct(int id) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        Product product = productDaoSqlite.find(id);
    }

    public static ModelAndView getBySupplier(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        Integer supplierId = Integer.parseInt(req.params(":supplierid"));
        Map params = new HashMap<>();
        params.put("productsBySupplier", productDaoSqlite.getBy(supplierDaoSqlite.find(supplierId)));
        return new ModelAndView(params, "product/index");
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
