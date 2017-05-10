package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class IndexController {
    public static ModelAndView getAllInfo(Request req, Response res) throws SQLException{
        Map params = new HashMap<>();
        params.put("productList", new ProductDaoSqlite().getAll());
        params.put("productCategoryList", new ProductCategoryDaoSqlite().getAll());
        params.put("productSupplierList", new SupplierDaoSqlite().getAll());
        return new ModelAndView(params, "product/index");
    }
}
