package com.codecool.shop;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.get;

public class App {
    private static App INSTANCE;
    private DatabaseConnection dbcon;

    private App() {
        dbcon = new DatabaseConnection();

        try {
            dbcon.resetDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        get("/", (Request req, Response res) -> {
            System.out.println();
            return new ProductDaoSqlite().getAll();
        });
    }

    public static App getApp(){
        return INSTANCE;
    }

    public Connection getConnection(){
        return dbcon.getConnection();
    }

    public void closeConnection(){
        dbcon.closeConnection();
    }

    public static void run(){
        if (INSTANCE == null){
            INSTANCE = new App();
        }
    }

}
