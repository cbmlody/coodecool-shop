package com.codecool.shop.dao;

import com.codecool.shop.App;
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
    public void add(Product product) throws SQLException {
        String query = "INSERT INTO `products`(name, defaultPrice, currency, description, categoryId," +
                "supplierId) VALUES('"+product.getName()+"', '"+product.getDefaultPrice()+"', '"+product.getDefaultCurrency()+"'," +
                " '"+product.getDescription()+"', '"+product.getProductCategory().getId()+"', '"+product.getSupplier().getId()+"')";
        Statement statement = App.getApp().getConnection().createStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        String query = "DELETE FROM `products` WHERE id = '"+id+"'";
        Statement statement = App.getApp().getConnection().createStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) throws SQLException {
        Statement statement = App.getApp().getConnection().createStatement();
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        String query = "SELECT * FROM `products` WHERE id = '" + id + "'";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.isBeforeFirst()){
                return null;
            }
            return productFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        String query = "SELECT * FROM `products`";
        return getByHelper(query);
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws SQLException {
        String query = "SELECT *FROM `products` WHERE supplierId = '" + supplier.getId() + "'";
        return getByHelper(query);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        String query = "SELECT *FROM `products` WHERE categoryId = '"+productCategory.getId()+"'";
        return getByHelper(query);
    }

    public List<Product> getBy(String name) throws SQLException {
        String query = null;
        if (name.length() >= 3) {
            query = "SELECT *FROM `products` WHERE name LIKE '%"+name+"%'";
        } else {
            query = "SELECT *FROM `products`";
        }
        return getByHelper(query);
    }

    private List<Product> getByHelper(String query) throws SQLException {
        List<Product> productList = new ArrayList<>();
        Statement statement = App.getApp().getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                productList.add(productFromResultSet(resultSet));
            }

        return productList;
    }

    private Product productFromResultSet (ResultSet resultSet) throws SQLException {
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        return  new Product(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getFloat("defaultPrice"),
                resultSet.getString("currency"),
                resultSet.getString("description"),
                productCategoryDaoSqlite.find(resultSet.getInt("categoryId")),
                supplierDaoSqlite.find(resultSet.getInt("supplierId")));
    }
}
