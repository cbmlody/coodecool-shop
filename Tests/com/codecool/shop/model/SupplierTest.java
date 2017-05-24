package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mercutio on 24.05.17.
 */
class SupplierTest {
    private Supplier testSupplier;
    private ArrayList<Product> testProductList;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCheckIfIDisGreaterThan0() {
        assertThrows(NullPointerException.class, () ->
        testSupplier.setId(-1));
    }
    @Test
    void setProducts() {
    }

    @Test
    void getProducts() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void testtoString() {
    }

}