package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by mercutio on 24.05.17.
 */
class CartItemTest {
    CartItem testItem;

    @BeforeEach
    void setUp() {
        testItem = new CartItem(mock(Product.class), 1);
    }

    @Test
    void

}