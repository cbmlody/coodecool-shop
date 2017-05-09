package com.codecool.shop.dao;

import com.codecool.shop.App;
import com.codecool.shop.model.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSqlite implements SupplierDao {

    @Override
    public void add(Supplier supplier) throws SQLException {
        String query = "INSERT INTO `suppliers`(name, description) VALUES('"+supplier.getName()+"', '"+supplier.getDescription()+"')";
        Statement statement = App.getApp().getConnection().createStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) throws SQLException {
        Supplier supplier = null;
        Statement statement = App.getApp().getConnection().createStatement();
        String query = "SELECT * FROM `suppliers` WHERE id = '" + id + "'";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.isBeforeFirst()){
                return null;
            }
            supplier = new Supplier(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }
    
    @Override
    public void remove(int id) throws SQLException {
        String query = "DELETE FROM `suppliers` WHERE id = '"+id+"'";
        Statement statement = App.getApp().getConnection().createStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> supplierList = new ArrayList<Supplier>();
        Statement statement = App.getApp().getConnection().createStatement();
        String query = "SELECT * FROM `suppliers`";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }
}
