package com.codecool.shop.dao;

import com.codecool.shop.App;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by michal on 25.05.17.
 */
public class ProductDaoSQLiteTestGetByCategory {
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
	void testGetAllAfterApplicationInitByCategory() throws SQLException {
		ProductCategory productCategory = new ProductCategory(1,"Skin Care", "Health",
		 "auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio");
		List<Product> productList = productDaoSqlite.getBy(productCategory);
		assertEquals(4, productList.size());
	}

}
