package com.codecool.shop.controller;

import com.codecool.shop.App;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by mercutio on 25.05.17.
 */
class CartControllerTest {

    CartController testController;
    spark.Request req;
    spark.Response res;

    @BeforeEach
    void setUp() throws SQLException {
        App.run();
        App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
        App.getApp().resetDb();
        this.testController = new CartController();
        this.req = mock(spark.Request.class);
        this.res = mock(spark.Response.class);
    }

    @AfterEach
    void tearDown() throws SQLException {
        App.getApp().closeConnection();
    }

    @Test
    void addToBasket() {
    }

    @Test
    void renderCart() {
    }

    @Test
    void removeProduct() {
    }

}