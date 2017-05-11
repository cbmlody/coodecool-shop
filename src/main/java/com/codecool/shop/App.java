package com.codecool.shop;

import com.codecool.shop.controller.CartController;
import com.codecool.shop.controller.IndexController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.Request;
import spark.Response;
import spark.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

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
    }

    public void dispatchRoutes() {
        staticFileLocation("/public");

        post("/basket/add/:basketid", (Request req, Response res) ->{
            return new ThymeleafTemplateEngine().render(CartController.addToBasket(req, res));
        });

        get("/product/supplier/:supplierid", (Request req, Response res) ->{
            return new ThymeleafTemplateEngine().render(ProductController.getBySupplier(req, res));
        });

        get("/product/category/:categoryid", (Request req, Response res) ->{
            return new ThymeleafTemplateEngine().render(ProductController.getByProductCategory(req, res));
        });

        get("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(IndexController.getAllInfo(req, res));
        });

        post("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.getProductsBySearch(req, res));
        });
    }

    public static App getApp(){
        return INSTANCE;
    }

    public Connection getConnection(){
        return dbcon.getConnection();
    }

    public void resetDb() throws SQLException {
        dbcon.resetDatabase();
    }

    public void migrateDb() throws SQLException{
        dbcon.migrateDb();
    }

    public void closeConnection(){
        dbcon.closeConnection();
    }

    public static void run() throws SQLException {
        if (INSTANCE == null){
            INSTANCE = new App();
        }
    }


}
