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

/**
 * Created by michal on 25.05.17.
 */
public class ProductDaoSQliteTestFindByCorrectId {
	private ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();

	@BeforeEach
	private void runApp() throws SQLException {
		App.run();
		App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
		App.getApp().resetDb();
	}

	@AfterEach
	private void closeDb() throws SQLException{
		App.getApp().closeConnection();
	}

	@Test
	void testFindProductWithCorrectId() throws SQLException {
		Product product = productDaoSqlite.find(1 );
		assertEquals("Dr.Jart CC Essence Balm 02 Medium - Deep", product.getName());
	}


	private Product generateProductWithId() throws SQLException {
		Supplier supplier = new Supplier(10, "testName", "testDescr");
		ProductCategory productCategory = new ProductCategory(10, "testName", "testDep", "testDescr");
		return new Product(31,"testName", 100f, "PLN", "testDesc", productCategory, supplier);
	}
}
