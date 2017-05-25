package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends BaseModel {
    private String department;
    private List<Product> products = new ArrayList<>();

    public ProductCategory(String name, String department, String description) {
        super(name);
        this.department = department;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public ProductCategory(int id, String name, String department, String description) {
        super(id, name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
            this.department = department;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Integer getProductsCount() {
        return this.products.size();
    }

    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}