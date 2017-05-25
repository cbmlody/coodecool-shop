package com.codecool.shop.controller;

import com.codecool.shop.App;
import com.codecool.shop.model.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Session;

import java.lang.ref.ReferenceQueue;
import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void testIfAddToBasketAddsCorrectValue() throws SQLException {
        when(this.req.queryParams("productid")).thenReturn("1");
        when(this.req.queryParams("quantity")).thenReturn("1");
        when(this.req.session()).thenReturn(mock(Session.class));
        when(req.session().attribute("basket")).thenReturn(new Cart());
        CartController.addToBasket(this.req, this.res);
        Cart maybeCart = req.session().attribute("basket");
        assertTrue(maybeCart.size() > 0);
    }

    @Test
    void testIfrenderCartPassCorrectValueOfItemCounterWhenCartisEmpty() throws SQLException {
        when(this.req.session()).thenReturn(mock(Session.class));
        when(req.session().attribute("basket")).thenReturn(null);
        HashMap<String, Object> testMap = (HashMap<String, Object>) CartController.renderCart(this.req, this.res).getModel();
        assertEquals(0, testMap.get("count"));
    }
//
//    @Test
//    void removeProduct() {
//    }

}