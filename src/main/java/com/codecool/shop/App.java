package com.codecool.shop;

import com.codecool.shop.controller.IndexController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.Request;
import spark.Response;
import spark.*;
import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    private static App INSTANCE;
    private DatabaseConnection dbcon;

    private App() {
        dbcon = new DatabaseConnection();
    }

    public void dispatchRoutes() {
        staticFileLocation("/public");
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

        get("/add", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderAddForm(req, res));
        });

        post("/add", (Request req, Response res) -> {
            try {
                return new ThymeleafTemplateEngine().render(ProductController.addProduct(req, res));
            } catch (NullPointerException e) {
                return null;
            }
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
