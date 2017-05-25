package com.codecool.shop.dao;

import com.codecool.shop.App;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by michal on 23.05.17.
 */


class ProductDaoSqliteTest {

	private Product generateProductWithoutId() throws SQLException {
		SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
		ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
		Supplier supplier = supplierDaoSqlite.find(1);
		ProductCategory productCategory = productCategoryDaoSqlite.find(1);
		return new Product("testName", 100f, "PLN", "testDesc", productCategory, supplier);
	}

	@BeforeEach
	private void runApp() throws SQLException{
		App app = App.getApp();
		String testDbPath = "jdbc:sqlite:src/tests/testdb/testdatabase";
		App.run();
		app.setConnection(testDbPath);
	}

	private void closeDb() throws SQLException{
		App app = App.getApp();
		app.closeConnection();
	}

	@Test
	void testGetAllBeforeApplicationInitialization(){
		assertThrows(SQLException.class, () -> {
			new ProductDaoSqlite().getAll();
		});
	}

	@Test
	void testGetAllAfterApplicationInitializationComponentType() throws SQLException{
		ArrayList productList = new ArrayList<Product>();
		assertEquals(productList.getClass().getComponentType(), new ProductDaoSqlite().getAll().getClass().getComponentType());

	}

	@Test
	void testAddProduct() throws SQLException {
		ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
		Product testProduct = generateProductWithoutId();
		productDaoSqlite.add(testProduct);
		System.out.println(productDaoSqlite.find(31));
		assertEquals(31, productDaoSqlite.getAll().size());
	}

}