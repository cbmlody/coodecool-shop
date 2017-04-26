package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSqlite implements SupplierDao {

    @Override
    public void add(Supplier supplier){}

    @Override
    public Supplier find(int id){
        Supplier supplier = null;
        Statement statement = DatabaseConnection.getInstance().getStatement();
        String query = "SELECT * FROM `suppliers` WHERE id = '" + id + "'";
        try {
            ResultSet result = statement.executeQuery(query);
            supplier = new Supplier(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description")
            );

            )
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }
    @Override
    public void remove(int id){}

    @Override
    public List<Supplier> getAll(){
        List<Supplier> supplierList = new ArrayList<Supplier>();
        Statement statement = DatabaseConnection.getInstance().getStatement();
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
