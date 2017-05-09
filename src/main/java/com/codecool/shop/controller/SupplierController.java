package com.codecool.shop.controller;

import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Supplier;
import java.util.List;

public final class SupplierController {
    private static final SupplierController INSTANCE = new SupplierController();
    private SupplierController() {}
    public static SupplierController getInstance() {
        return INSTANCE;
    }

    public void getAllSuppliers(){
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        List<Supplier> supplierList = supplierDaoSqlite.getAll();
    }
    public void findSupplier(int id){
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        Supplier supplier = supplierDaoSqlite.find(id);
    }
}
