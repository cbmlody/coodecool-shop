package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoSqlite implements ProductDao {

    @Override
    public void add(Product product) {}

    @Override
    public void remove(int id) {}

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        Statement statement = DatabaseConnection.getInstance().getStatement();
        String query = "SELECT *FROM `products`";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("defaulPrice"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategoryDaoSqlite.find(resultSet.getInt("categoryId")),
                       supplierDaoSqlite.find(resultSet.getInt("supplierId")));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
