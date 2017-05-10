package com.codecool.shop.dao;

import com.codecool.shop.App;
import com.codecool.shop.model.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite implements ProductCategoryDao{

    @Override
    public void add(ProductCategory category) throws SQLException {
        String query = "INSERT INTO `product_categories`(name, department, description) VALUES('"+category.getName()+"'," +
                " '"+category.getDepartment()+"', '"+category.getDescription()+"')";
        Statement statement = App.getApp().getConnection().createStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        String query = "DELETE FROM `product_categories` WHERE id = '"+id+"'";
        Statement statement = App.getApp().getConnection().createStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        ProductCategory productCategory = null;
        Statement statement = App.getApp().getConnection().createStatement();
        String query = "SELECT * FROM `product_categories` WHERE id = '" + id + "'";
        try {
            ResultSet result = statement.executeQuery(query);
            if (!result.isBeforeFirst()){
                return null;
            }
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
    public List<ProductCategory> getAll() throws SQLException{
        Statement statement = App.getApp().getConnection().createStatement();
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
    public static Integer getProductsCount(ProductCategory productCategory) throws SQLException {
        Integer productsCount = new ProductDaoSqlite().getBy(productCategory).size();
        return productsCount;
    }
}
