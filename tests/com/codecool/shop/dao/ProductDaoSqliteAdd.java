package com.codecool.shop.dao;

import com.codecool.shop.App;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by michal on 23.05.17.
 */


class ProductDaoSqliteAdd {
	private ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();

	@BeforeEach
	private void runApp() throws SQLException{
		App.run();
		App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
		App.getApp().resetDb();
	}

	@AfterEach
	private void closeDb() throws SQLException{
		App.getApp().closeConnection();
	}


	@Test
	void testAddProduct() throws SQLException {
		Product testProduct = generateProductWithId();
		productDaoSqlite.add(testProduct);
		String testQuery = "SELECT * FROM `products` WHERE id = 31";
		ResultSet resultSet = App.getApp().getConnection().createStatement().executeQuery(testQuery);
		assertEquals("testName", resultSet.getString("name"));
		assertEquals("testDesc", resultSet.getString("description"));
	}

	@Test
	void testGetAllAfterApplicationInit() throws SQLException {
		List <Product> productList = productDaoSqlite.getAll();
		assertEquals(30, productList.size());
	}

	private Product generateProductWithId() throws SQLException {
		SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
		ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
		Supplier supplier = new Supplier(10, "testName", "testDescr");
		ProductCategory productCategory = new ProductCategory(10, "testName", "testDep", "testDescr");
		return new Product(31,"testName", 100f, "PLN", "testDesc", productCategory, supplier);
	}
}