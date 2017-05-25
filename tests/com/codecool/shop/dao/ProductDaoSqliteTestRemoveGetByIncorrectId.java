package com.codecool.shop.dao;

import com.codecool.shop.App;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by michal on 25.05.17.
 */
public class ProductDaoSqliteTestRemoveGetByIncorrectId {
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
	public void testRemoveProductFromTestDB() throws SQLException {
		productDaoSqlite.remove(6);
		Product product = productDaoSqlite.find(6);
		assertEquals(null, product);
	}

	@Test
	void testFindProductWithIncorrectId() throws SQLException {
		Product product = productDaoSqlite.find(300 );
		assertEquals(null, product);
	}
}
