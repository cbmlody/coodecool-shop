package com.codecool.shop.controller;

import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Supplier;
import java.util.List;
import com.codecool.shop.views.SupplierView;

public final class SupplierController {
    private static final SupplierController INSTANCE = new SupplierController();
    private SupplierController() {}
    public static SupplierController getInstance() {
        return INSTANCE;
    }

    public void getAllSuppliers(){
        SupplierView supplierView = new SupplierView();
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        List<Supplier> supplierList = supplierDaoSqlite.getAll();
        supplierView.displayAll(supplierList);
    }
    public void findSupplier(int id){
        SupplierView supplierView = new SupplierView();
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        Supplier supplier = supplierDaoSqlite.find(id);
        supplierView.displayOne(supplier);
    }


}
