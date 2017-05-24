package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by mercutio on 24.05.17.
 */
class CartItemTest {
    CartItem testItem;
    Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product("Test Product", 12.00f, "PLN", "any description", mock(ProductCategory.class), mock(Supplier.class));
        testItem = new CartItem(testProduct, 1);
    }

    @Test
    void testGetProduct() {
        assertEquals(Product.class, testItem.getProduct().getClass());
    }

    @Test
    void testGetQuantity() {
        assertEquals((Integer) 1, testItem.getQuantity());
    }

//    @Disabled
    @Test
    void testIsQuantityCanBeSmallerThanOne() {
        assertThrows(Exception.class, () -> testItem.setQuantity(0));
        assertThrows(Exception.class, () -> testItem.setQuantity(-1));


    }

    @Test
    void testSetQuantity() {
        testItem.setQuantity(3);
        assertEquals((Integer) 3, testItem.getQuantity());
    }

    @Test
    void testConvertCosts() {
        testItem.setQuantity(5);
        assertEquals(60.00f, testItem.getConvertedCost(), "Check with 5 products");
        testItem.setQuantity(3);
        assertEquals(36.00f, testItem.getConvertedCost(), "Check with 3 products");


    }

}