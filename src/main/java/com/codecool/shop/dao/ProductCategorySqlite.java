package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategorySqlite implements ProductCategoryDao{

    @Override
    public void add(ProductCategory category) {}

    @Override
    public void remove(int id) {}

    @Override
    public ProductCategory find(int id) {
        ProductCategory productCategory = null;
        Statement statement = DatabaseConnection.getInstance().getStatement();
        String query = "SELECT * FROM `product_categories` WHERE id = '" + id + "'";
        try {
            ResultSet result = statement.executeQuery(query);
            productCategory = new ProductCategory(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("department"),
                    result.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCategory;
    }

    @Override
    public List<ProductCategory> getAll() {
        Statement statement = DatabaseConnection.getInstance().getStatement();
        String query = "SELECT * FROM `product_categories`";
        ArrayList<ProductCategory> categories = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                ProductCategory category = new ProductCategory(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("department"),
                        result.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
