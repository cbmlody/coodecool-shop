package com.codecool.shop.controller;

import com.codecool.shop.App;
import com.codecool.shop.dao.*;
import com.codecool.shop.model.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by michal on 25.05.17.
 */
class IndexControllerTest {
	private ProductController productController = new ProductController();
	private ProductDao productDaoSqlite = new ProductDaoSqlite();
	private SupplierDao supplierDaoSqlite = new SupplierDaoSqlite();
	private ProductCategoryDao productCategoryDaoSqlite = new ProductCategoryDaoSqlite();

	@Mock
	private Response res = mock(Response.class);
	@Mock
	private Request req = mock(Request.class);
	@Mock
	private Session session = mock(Session.class);

	@BeforeEach
	void runApp() throws SQLException {
		App.run();
		App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
		App.getApp().resetDb();
	}

	@AfterEach
	void closeDb() throws SQLException{
		App.getApp().closeConnection();
	}

//	@Test
//	void testGetAllInfo() throws SQLException {
//		Map params = new HashMap<>();
//		when(session.attribute("basket")).thenReturn(null);
//		params.put("productList", productDaoSqlite.getAll());
//		ModelAndView sampleModel = new ModelAndView(params, "product/index");
//		ModelAndView testModel = IndexController.getAllInfo(req, res);
//		assertTrue(sampleModel.getModel().toString().equals(testModel.getModel().toString()));
//	}


}

