package com.codecool.shop.model;

import com.codecool.shop.dao.ProductDaoSqlite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



/**
 * Created by michal on 23.05.17.
 */
class ProductTest {

	@Test
	public void testProductConstructorWithoutIdProductCategoryAndSupplier(){
		Product product = new Product("testName", 100f, "PLN", "testDesc", null, null);
		assertEquals(Product.class, product.getClass());
	}

	@Test
	public void testProductConstructorWithIncorrectCurrencyCode(){
		Supplier supplier = new Supplier("testName","testDescription");
		ProductCategory productCategory = new ProductCategory("testName", "testDepartment", "testDescription");
		Product product = new Product(1,"testName", 100f, "ABC", "testDesc", productCategory, supplier);
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
	public void testSetMinusDefaultPrice(){
		Product product = generateProductWithId();
		product.setDefaultPrice(-20f);
		assertThrows(IllegalArgumentException.class, () ->{
			product.getDefaultPrice();
		});
	}

	@Test
	public void testGetPrice(){
		Product product = generateProductWithId();
		assertEquals("100.0 PLN", product.getPrice());
	}

	@Test
	public void testSetPrice(){
		Product product = generateProductWithId();
		product.setPrice(200f, "PLN");
		assertEquals("200.0 PLN", product.getPrice());
	}

	@Test
	public void testSetMinusPrice(){
		Product product = generateProductWithId();
		assertThrows(IllegalArgumentException.class, () -> {
			 product.setPrice(-200f, "PLN");
		});
	}

	@Test
	public void testGetConvertedFloatPrice(){
		Product product = generateProductWithId();
		assertEquals(100f,product.getConvertedFloatPrice());
	}

	@Test
	public void testGetDefaultCurrencyReturnedClass(){
		Product product = generateProductWithId();
		assertEquals(Currency.class, product.getDefaultCurrency().getClass());
	}

	@Test
	public void testGetDefaultCurrencyReturnedValue(){
		Product product = generateProductWithId();
		assertEquals(Currency.getInstance("PLN"),product.getDefaultCurrency());
	}

	@Test
	public void testChangeDefaultCurrency(){
		Product product = generateProductWithId();
		product.setDefaultCurrency(Currency.getInstance("PHP"));
		assertEquals(Currency.getInstance("PHP"), product.getDefaultCurrency());
	}

	@Test
	public void testGetBaseCurrencyReturnedValue(){
		Product.changeBaseCurrency(Currency.getInstance("PHP"));
		assertEquals(Currency.getInstance("PHP"), Product.getBaseCurrency());
	}

	@Test
	public void testChangeBaseCurrency(){
		Currency beforeChangeCurrency = Product.getBaseCurrency();
		Product.changeBaseCurrency(Currency.getInstance("USD"));
		Currency afterChangeCurrency = Product.getBaseCurrency();
		assertNotEquals(beforeChangeCurrency,afterChangeCurrency);
	}

	@Test
	public void testGetProductCategoryNotNull(){
		Product product = generateProductWithId();
		assertNotNull(product.getProductCategory());
	}

	@Test
	public void testGetProductCategoryReturnClass(){
		Product product = generateProductWithId();
		assertEquals(ProductCategory.class, product.getProductCategory().getClass());
	}

	@Test
	public void testSetProductCategory(){
		Product product = generateProductWithId();
		ProductCategory productCategory = new ProductCategory("testSetter", "testDepartment", "testDescription");
		product.setProductCategory(productCategory);
		assertEquals(productCategory.getName(), product.getProductCategory().getName());
	}

	@Test
	public void testGetSupplierReturnClass(){
		Product product = generateProductWithId();
		assertEquals(Supplier.class, product.getSupplier().getClass());
	}

	@Test
	public void testGetSupplierNotNull() {
		Product product = generateProductWithId();
		assertNotNull(product.getSupplier());
	}

	@Test
	public void testSetSupplier() {
		Product product = generateProductWithId();
		Supplier supplier = new Supplier("testSetSupplier", "testDescription");
		product.setSupplier(supplier);
		assertEquals(supplier.getName(), product.getSupplier().getName());
	}

	@Test
	public void testEqualsObjNull(){
		Product product = generateProductWithId();
		Product product2 = null;
		assertEquals(false, product.equals(product2));
	}

	@Test
	public  void testEqualsObjDiffClass(){
		Product product = generateProductWithId();
		Supplier supplier = new Supplier("testSetSupplier", "testDescription");
		assertEquals(false, product.equals(supplier));
	}

	@Test
	public  void testEqualsObjSameClassDiffValues(){
		Product product1 = generateProductWithId();
		Supplier supplier = new Supplier("testEqualsProduct","testEqualsDescription");
		ProductCategory productCategory = new ProductCategory("testEqualsProduct", "testEqualsDept", "testEqualsDescription");
		Product product2 = new Product(2,"testEquals", 200f, "PHP", "testEqualsDesc", productCategory, supplier);
		assertEquals(false, product1.equals(product2));
	}

	@Test
	public void testEqualsObjSameClassSameObj(){
		Product product = generateProductWithId();
		assertEquals(true, product.equals(generateProductWithId()));
	}

	@Test
	public void testToString(){
		Product product = generateProductWithId();
		assertEquals("id: 1, name: testName, defaultPrice: 100.000000, defaultCurrency: PLN, " +
		 "productCategory: testName, supplier: testName", product.toString());
	}


	private Product generateProductWithId(){
		Supplier supplier = new Supplier("testName","testDescription");
		ProductCategory productCategory = new ProductCategory("testName", "testDepartment", "testDescription");
		return new Product(1,"testName", 100f, "PLN", "testDesc", productCategory, supplier);
	}


}