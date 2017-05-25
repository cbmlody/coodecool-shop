package com.codecool.shop.dao;

import com.codecool.shop.App;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) throws SQLException {
        String query = "INSERT INTO `product_categories`(name, department, description) VALUES('" + category.getName() + "'," +
                " '" + category.getDepartment() + "', '" + category.getDescription() + "')";
        Statement statement = App.getApp().getConnection().createStatement();
        statement.execute(query);
    }
    
    @Override
    public void remove(int id) throws SQLException {
        String query = "DELETE FROM `product_categories` WHERE id = '" + id + "'";
        Statement statement = App.getApp().getConnection().createStatement();
        statement.execute(query);
    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        ProductCategory productCategory = null;
        Statement statement = App.getApp().getConnection().createStatement();
        String query = "SELECT * FROM `product_categories` WHERE id = '" + id + "'";
        ResultSet result = statement.executeQuery(query);
        if (!result.isBeforeFirst()) {
            return null;
        }
        productCategory = new ProductCategory(
                result.getInt("id"),
                result.getString("name"),
                result.getString("department"),
                result.getString("description"));

        return productCategory;
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        Statement statement = App.getApp().getConnection().createStatement();
        String query = "SELECT * FROM `product_categories`";
        List<ProductCategory> categories = new ArrayList<>();

        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            ProductCategory category = new ProductCategory(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("department"),
                    result.getString("description"));
            categories.add(category);
            for (ProductCategory productCategory : categories) {
                productCategory.setProducts(new ProductDaoSqlite().getBy(productCategory));
            }
        }
        return categories;
    }
}