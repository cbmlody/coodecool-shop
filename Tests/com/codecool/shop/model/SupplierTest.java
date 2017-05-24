package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        testCategory = new ProductCategory("name", "dep", "desc");
        testSupplier = new Supplier("janusz", "description");
        Product test1 = mock(Product.class);
        Product test2 = mock(Product.class);
        Product test3 = new Product(2, "name", 12.00f, "PLN", "testDesc", testCategory, testSupplier);
        Product test4 = mock(Product.class);
        ArrayList<Product> list = new ArrayList<>(Arrays.asList(test1, test2, test3, test4));
        testSupplier.setProducts(list);
        System.out.println(list.size());
        System.out.println(testSupplier.getProducts().size());
        assertEquals("id: 2, " +
                "name: name, " +
                "description: testDesc", testSupplier.getProducts().get(2).toString());


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
        testSupplier = new Supplier(1,"something", "something else");
        assertEquals("id: 1, " +
                "name: something, " +
                "description: something else", testSupplier.toString());

    }

    @Test
    void testToStringWithoutArgs() {
        testSupplier = new Supplier(1, null, null);
        assertEquals("id: 1, " +
                "name: , " +
                "description: ", testSupplier.toString());
    }

}