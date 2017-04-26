package com.codecool.shop.views;

import com.codecool.shop.model.Supplier;

import java.util.List;

public class SupplierView implements View<Supplier> {
    public void displayOne(Supplier supplier) {
        System.out.println(supplier);
    }

    public void displayAll(List<Supplier> suppliers) {
        if (suppliers.size() > 0) {
            for (Supplier supplier : suppliers) {
                System.out.println(supplier);
            }
        } else {
            System.out.println("No items found...");
        }
    }
}
