package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by mercutio on 24.05.17.
 */
class SupplierTest {
    private Product mockProduct;
    private Supplier testSupplier;

    @BeforeEach
    void setUp() {
        mockProduct = mock(Product.class);
    }

    @Test
    void testConstructorCheckIfIdIsLesserThan0() {
        testSupplier = new Supplier("name", "description");
        assertThrows(IllegalArgumentException.class, () ->
                testSupplier.setId(-1));
    }

    @Test
    void testConstructor() {
        testSupplier = new Supplier("name", "surname");
        assertEquals("name", testSupplier.getName());
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
        ProductCategory testCategory = new ProductCategory("name", "dep", "desc");
        testSupplier = new Supplier("janusz", "description");
        Product test1 = mockProduct;
        Product test2 = mockProduct;
        Product test3 = new Product(2, "name", 12.00f, "PLN", "testDesc", testCategory, testSupplier);
        Product test4 = mockProduct;
        testSupplier.setProducts(new ArrayList<>(Arrays.asList(test1, test2, test3, test4)));
        assertEquals("id: 2, " +
                "name: name, " +
                "defaultPrice: 12.000000, " +
                "defaultCurrency: PLN, " +
                "productCategory: name, " +
                "supplier: janusz", testSupplier.getProducts().get(2).toString());

    }

    @Test
    void testGetEmptyListOfProducts() {
        testSupplier = new Supplier("name", "description");
        assertEquals(0, testSupplier.getProducts().size());


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
        testSupplier = new Supplier(1, "something", "something else");
        assertEquals("id: 1, " +
                "name: something, " +
                "description: something else", testSupplier.toString());

    }

    @Test
    void testToStringWithoutArgs() {
        testSupplier = new Supplier(1, null, null);
        assertEquals("id: 1, " +
                "name: null, " +
                "description: null", testSupplier.toString());
    }

}