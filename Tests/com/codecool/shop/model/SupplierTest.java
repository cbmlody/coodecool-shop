package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mercutio on 24.05.17.
 */
class SupplierTest {
    private Supplier testSupplier;
    private ArrayList<Product> testProductList;
    private ProductCategory testCategory;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testConstructorCheckIfIdIsGreaterThan0() {
        assertThrows(NullPointerException.class, () ->
        testSupplier.setId(-1));
    }

    @Test
    void testConstructorWithId() {
        testSupplier = new Supplier(1, "name", "description");
        assertEquals(1, testSupplier.getId());
    }

    @Test



    @Test
    void testSetProducts() {

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