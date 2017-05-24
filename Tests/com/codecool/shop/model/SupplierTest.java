package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import org.mockito.Mockito.*;
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
    void testConstructorWithoutName() {
        testSupplier = new Supplier(null, "description");
        assertEquals(null, testSupplier.getName());
    }


    @Test
    void testSetProducts() {

    }

    @Test
    void getProducts() {
    }

    @Test
    void testAddProduct() {
        ProductCategory category = new ProductCategory("something", "something", "something");
        testSupplier = new Supplier(null, "description");
        Product testProduct1 = new Product("name", 12.00f, "PLN", "dwadw", category, testSupplier);
        testSupplier.addProduct(testProduct1);
        assertEquals(Product.class, testSupplier.getProducts().get(0).getClass());
    }

    @Test
    void testToString() {
        testSupplier = new Supplier("something", "something else");
        testSupplier.setId(1);
        assertEquals("id: 1, " +
                "name: something, " +
                "description: something else", testSupplier.toString());

    }

}