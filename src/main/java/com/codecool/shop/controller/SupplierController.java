package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SupplierController {

    public static ModelAndView getAllSuppliers(Request req, Response res) throws SQLException {
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        Map params = new HashMap<>();
        params.put("suppliersList", supplierDaoSqlite.getAll());
        return new ModelAndView(params, "product/index");
    }

    public void findSupplier(int id) throws SQLException {
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        Supplier supplier = supplierDaoSqlite.find(id);
    }
}
