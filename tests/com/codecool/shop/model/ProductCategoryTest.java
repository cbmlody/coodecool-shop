package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {
    private ProductCategory productCategoryWithoutId;
    private ProductCategory productCategoryWithId;

    @BeforeEach
    void setUp() {
        this.productCategoryWithoutId = new ProductCategory("name", "department", "description");
        this.productCategoryWithId = new ProductCategory(1,"name", "department", "description");
    }

    @Test
    void testProductCategoryConstructorWithoutId() {
        ProductCategory productCategory = new ProductCategory("name", "department", "description");
        assertEquals(this.productCategoryWithoutId.getClass(), productCategory.getClass());
    }

    @Test
    void testProductCategoryConstructorWithId() {
        ProductCategory productCategory = new ProductCategory(1,"name", "department", "description");
        assertEquals(this.productCategoryWithId.getClass(), productCategory.getClass());
    }

    @Test
    void testGetDepartment() {
        assertEquals("department", this.productCategoryWithoutId.getDepartment());
    }

    @Test
    void testSetDepartment() {
        String testDepartment = "testDepartment";
        this.productCategoryWithoutId.setDepartment(testDepartment);
        assertEquals(testDepartment, this.productCategoryWithoutId.getDepartment());
    }

    @Test
    void testSetProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product("name", 1.2f, "PLN", "name descr", this.productCategoryWithoutId, new Supplier("name", "descr"));
        products.add(product);
        productCategoryWithId.setProducts(products);
        assertEquals(1 , (int)this.productCategoryWithId.getProductsCount());
    }

    @Test
    void testAddProduct() {
        Product product = new Product("name", 1.2f, "PLN", "name descr", this.productCategoryWithoutId, new Supplier("name", "descr"));
        this.productCategoryWithoutId.addProduct(product);
        assertEquals(2, (int)this.productCategoryWithoutId.getProductsCount());
    }

    @Test
    void testGetProductsCount() {
        System.out.println(this.productCategoryWithoutId.getProductsCount());
        assertEquals(0, (int)this.productCategoryWithoutId.getProductsCount());
    }

    @Test
    void testToString() {
        ProductCategory productCategory = new ProductCategory("name", "department", "description");
        assertEquals(productCategory.toString(), this.productCategoryWithoutId.toString());
    }
}
