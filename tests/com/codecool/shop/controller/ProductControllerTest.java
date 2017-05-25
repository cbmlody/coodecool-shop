package com.codecool.shop.controller;

import com.codecool.shop.App;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by michal on 25.05.17.
 */
class ProductControllerTest {

	private ProductController productController = new ProductController();
	private ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();

	@Test
	void testGetAllProducts() throws SQLException {
		Request req = mock(Request.class);
		Response res = mock(Response.class);
		Map params = new HashMap<>();
		params.put("productList", productDaoSqlite.getAll());
		ModelAndView testModel = new ModelAndView(params, "product/index");
		ModelAndView model = productController.getAllProducts(req, res);
		assertTrue(model.getModel().toString().equals(testModel.getModel().toString()));
	}


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

}