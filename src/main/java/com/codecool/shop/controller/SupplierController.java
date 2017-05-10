package com.codecool.shop.controller;

import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public final class SupplierController {

    public void getAllSuppliers() throws SQLException {
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        List<Supplier> supplierList = supplierDaoSqlite.getAll();
    }
    public void findSupplier(int id) throws SQLException {
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        Supplier supplier = supplierDaoSqlite.find(id);
    }
}
