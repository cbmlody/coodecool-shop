package com.codecool.shop.dao;

import com.codecool.shop.App;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by michal on 25.05.17.
 */
public class ProductDaoSQLiteTestGetBySupplier {

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
	void testGetAllAfterApplicationInitBySupplier() throws SQLException {
		Supplier supplier = new Supplier(1, "Apotex Corp.", "cras non velit nec nisi vulputate nonummy " +
		 "maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque");
		List<Product> productList = productDaoSqlite.getBy(supplier);
		assertEquals(5, productList.size());
	}




}
