package com.codecool.shop;

import com.codecool.shop.controller.CartController;
import com.codecool.shop.controller.IndexController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.DatabaseConnection;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.*;

public class App {
    private static App INSTANCE;
    private DatabaseConnection dbcon;

    private App() {
        dbcon = new DatabaseConnection("jdbc:sqlite:src/main/database.db");
    }

    public void dispatchRoutes() {
        staticFileLocation("/public");

        get("/cart", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(CartController.renderCart(req, res));
        });

        post("/basket/add/:basketid", (Request req, Response res) ->{
            return new ThymeleafTemplateEngine().render(CartController.addToBasket(req, res));
        });

        get("/supplier/:supplierid", (Request req, Response res) ->{
            return new ThymeleafTemplateEngine().render(IndexController.getBySupplier(req, res));
        });

        get("/category/:categoryid", (Request req, Response res) ->{
            return new ThymeleafTemplateEngine().render(IndexController.getByCategory(req, res));
        });

        get("/product/:id", "application/json", (req, res) -> {
            return ProductController.getProductJsonById(req);
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

        get("/product/remove/:productid", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(CartController.removeProduct(req, res));
        });
    }

    public static App getApp(){
        return INSTANCE;
    }

    public void setConnection(String pathToDatabase) throws SQLException {
        dbcon = new DatabaseConnection(pathToDatabase);
    }

    public Connection getConnection() throws SQLException {
        dbcon.openConnection();
        return dbcon.getConnection();
    }

    public void resetDb() throws SQLException {
        dbcon.resetDatabase();
    }

    public void migrateDb() throws SQLException {
        dbcon.migrateDb();
    }

    public void closeConnection() throws SQLException {
        dbcon.closeConnection();
    }

    public static void run() throws SQLException {
        if (INSTANCE == null){
            INSTANCE = new App();
        }
    }


}