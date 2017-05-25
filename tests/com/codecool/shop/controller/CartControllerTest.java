package com.codecool.shop.controller;

import com.codecool.shop.App;
import com.codecool.shop.model.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Session;

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
    void testIfAddToBasketAddsCorrectQuantity() throws SQLException {
        createMockCart();
        requestProductMock(1, 2);
        CartController.addToBasket(this.req, this.res);
        Cart testCart = req.session().attribute("basket");
        assertTrue(testCart.numOfitemsInCart() > 1);
    }

    @Test
    void testIfrenderCartPassCorrectValueOfItemCounterWhenCartisEmpty() throws SQLException {
        when(this.req.session()).thenReturn(mock(Session.class));
        when(req.session().attribute("basket")).thenReturn(null);
        HashMap<String, Object> testMap = (HashMap<String, Object>) CartController.renderCart(this.req, this.res).getModel();
        assertEquals(0, testMap.get("count"));
    }

    @Test
    void testIfrenderCartPassCorrectValueOfItemCounterWhenCartisNotEmpty() throws SQLException {
        createMockCart();
        requestProductMock(2, 4);
        CartController.addToBasket(this.req, this.res);
        requestProductMock(3, 2);
        CartController.addToBasket(this.req, this.res);
        HashMap<String, Object> testMap = (HashMap<String, Object>) CartController.renderCart(this.req, this.res).getModel();
        Cart testCart = (Cart) testMap.get("basket");
        assertEquals(6, testCart.numOfitemsInCart());


    }

    @Test
    void testRemoveProduct() throws SQLException {
        createMockCart();
        requestProductMock(4, 1);
        CartController.addToBasket(this.req, this.res);
        requestProductMock(1, 2);
        CartController.addToBasket(this.req, this.res);
        requestProductMock(2, 1);
        CartController.addToBasket(this.req, this.res);
        when(req.params(":productid")).thenReturn("4");
        CartController.removeProduct(this.req, this.res);
        Cart testCart = req.session().attribute("basket");
        assertEquals(3, testCart.numOfitemsInCart());


    }

    @Test
    void testRemoveNonExistentProduct() throws SQLException {
        createMockCart();
        requestProductMock(5, 3);
        CartController.addToBasket(this.req, this.res);
        when(req.params(":productid")).thenReturn("4");
        assertThrows(SQLException.class, () -> CartController.removeProduct(this.req, this.res));
    }

    void createMockCart() {
        when(this.req.session()).thenReturn(mock(Session.class));
        when(req.session().attribute("basket")).thenReturn(new Cart());

    }

    void requestProductMock(Integer productId, Integer quantity) {
        when(this.req.queryParams("productid")).thenReturn(Integer.toString(productId));
        when(this.req.queryParams("quantity")).thenReturn(Integer.toString(quantity));
    }

}