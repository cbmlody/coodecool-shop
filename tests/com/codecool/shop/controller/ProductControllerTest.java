package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by michal on 25.05.17.
 */
class ProductControllerTest {

	private ProductController productController = new ProductController();

	@Test
	void testGetAllProducts() throws SQLException {
		Request req = mock(Request.class);
		Response res = mock(Response.class);

		ModelAndView model = productController.getAllProducts(req, res);
		System.out.println(model.getModel());
		assertEquals(ModelAndView.class, model);
	}
//
//	public static ModelAndView getAllProducts(Request req, Response res) throws SQLException {
//		ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
//		Map params = new HashMap<>();
//		params.put("productList", productDaoSqlite.getAll());
//		return new ModelAndView(params, "product/index");
//	}


}