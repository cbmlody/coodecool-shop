package com.codecool.shop.views;

import com.codecool.shop.model.Supplier;

import java.util.List;

public class SupplierView implements View<Supplier> {
    public void displayOne(Supplier supplier) {
        System.out.format("%-3d | %-25s | %-40s",
                supplier.getId(),
                supplier.getName(),
                supplier.getDescription());
    }

    public void displayAll(List<Supplier> suppliers) {
        if (suppliers.size() > 0) {
            System.out.format("%-3s | %-25s | %-40s", "ID", "NAME", "DESCRIPTION");
            for (Supplier supplier : suppliers) {
                displayOne(supplier);
            }
        } else {
            System.out.println("No items found...");
        }
    }
}
