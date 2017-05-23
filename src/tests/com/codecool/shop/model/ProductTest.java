package com.codecool.shop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by michal on 23.05.17.
 */
class ProductTest {

	@Test
	public void testProductConstructorWithoutIdProductCategorySupplier(){
		Product product = new Product("testName", 100f, "PLN", "testDesc", null, null);
		assertEquals(Product.class, product.getClass());
	}

	@Test
	public void testProductConstructorWithProductCategorySupplierWithoutId(){
		Supplier supplier = new Supplier("testName","testDescription");
		ProductCategory productCategory = new ProductCategory("testName", "testDepartment", "testDescription");
		Product product = new Product("testName", 100f, "PLN", "testDesc", productCategory, supplier);
		assertEquals(Product.class, product.getClass());
	}

	@Test
	public void testProductConstructorWithProductCategorySupplierId(){
		Product product = generateProductWithId();
		assertEquals(Product.class, product.getClass());
	}

	@Test
	public void testGetDefaultPrice(){
		Product product = generateProductWithId();
		assertEquals(100.0, product.getDefaultPrice());
	}

	@Test
	public void testSetDefaultPrice(){
		Product product = generateProductWithId();
		product.setDefaultPrice(20f);
		assertEquals(20.0, product.getDefaultPrice());
	}

	@Test
	public void testGetPrice(){
		Product product = generateProductWithId();
		assertEquals("100.0 PLN", product.getPrice());
	}

	private Product generateProductWithId(){
		Supplier supplier = new Supplier("testName","testDescription");
		ProductCategory productCategory = new ProductCategory("testName", "testDepartment", "testDescription");
		return new Product(1,"testName", 100f, "PLN", "testDesc", productCategory, supplier);
	}


}