package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
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

    public static ModelAndView getByProductCategory(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        Integer categoryId = Integer.parseInt(req.params(":categoryid"));
        Map params = new HashMap<>();
        params.put("productsByCategory", productDaoSqlite.getBy(productCategoryDaoSqlite.find(categoryId)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView getProductsByName(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        String name = req.queryParams("productName");
        Map params = new HashMap<>();
        params.put("productsByName", productDaoSqlite.getBy(name));
        return new ModelAndView(params, "product/index");
    }
}
