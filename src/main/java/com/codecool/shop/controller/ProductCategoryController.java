package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.model.ProductCategory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class ProductCategoryController {

    public static ModelAndView getAllProductCategories(Request req, Response res) throws SQLException {
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        Map params = new HashMap<>();
        params.put("productCategoriesList", productCategoryDaoSqlite.getAll());
        return new ModelAndView(params, "product/index");
    }

    public void findCategory(int id) throws SQLException {
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        ProductCategory productCategory = productCategoryDaoSqlite.find(id);
    }
}
