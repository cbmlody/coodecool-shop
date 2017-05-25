package com.codecool.shop.controller;

import com.codecool.shop.App;
import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by michal on 25.05.17.
 */
class ProductControllerTest {

	private ProductController productController = new ProductController();
	private ProductDao productDaoSqlite = new ProductDaoSqlite();
	private SupplierDao supplierDaoSqlite = new SupplierDaoSqlite();
	private ProductCategoryDao productCategoryDaoSqlite = new ProductCategoryDaoSqlite();

	@Mock
	private Response res = mock(Response.class);
	@Mock
	private Request req = mock(Request.class);

	@Test
	void testGetAllProducts() throws SQLException {
		Map params = new HashMap<>();
		params.put("productList", productDaoSqlite.getAll());
		ModelAndView testModel = new ModelAndView(params, "product/index");
		ModelAndView model = ProductController.getAllProducts(req, res);
		assertTrue(model.getModel().toString().equals(testModel.getModel().toString()));
	}

	@Test
	void testGetAllProductsBySearchNotNull() throws SQLException {
		when(req.queryParams("search")).thenReturn("Atenolol");
		ModelAndView testModel = ProductController.getProductsBySearch(req, res);
		assertNotNull(testModel.getModel());
	}

	@Test
	void testRenderAddForm() throws SQLException {
		assertEquals(ModelAndView.class, ProductController.renderAddForm(req, res).getClass());
	}

	@Test
	void testAddProduct() throws SQLException {
		when(req.queryParams("name")).thenReturn("testName");
		when(req.queryParams("defaultprice")).thenReturn("100.00");
		when(req.queryParams("description")).thenReturn("testDesc");
		when(req.queryParams("chooseCategory")).thenReturn("1");
		when(req.queryParams("chooseSupplier")).thenReturn("1");
		ProductController.addProduct(req, res);
		assertNotNull(productDaoSqlite.find(31));
	}

	@Test
	void testGetBySupplier() throws SQLException {
		when(req.params(":supplierid")).thenReturn("1");
		Map params = new HashMap<>();
		Supplier supplier = supplierDaoSqlite.find(1);
		params.put("productsBySupplier", productDaoSqlite.getBy(supplier));
		ModelAndView testModel = new ModelAndView(params,"product/index");
		assertEquals(testModel.getModel(), ProductController.getBySupplier(req,res).getModel());
	}

	@Test
	void testGetByProductCategory() throws SQLException {
		when(req.params(":categoryid")).thenReturn("1");
		Map params = new HashMap<>();
		ProductCategory productCategory = productCategoryDaoSqlite.find(1);
		params.put("productsByCategory", productDaoSqlite.getBy(productCategory));
		ModelAndView testModel = new ModelAndView(params,"product/index");
		assertEquals(testModel.getModel(), ProductController.getByProductCategory(req,res).getModel());
	}

	@Test
	void testGetProductsByName() throws SQLException {
		when(req.queryParams("productName")).thenReturn("Atenolol");
		Map params = new HashMap<>();
		params.put("productsByName", productDaoSqlite.find(2));
		ModelAndView testModel = new ModelAndView(params, "product/index");
		assertTrue(ProductController.getProductsByName(req, res).getModel().toString().contains("Atenolol"));
	}

	@Test
	void testGetProductJsonById() throws SQLException {
		when(req.params(":id")).thenReturn("2");
		String json = ProductController.getProductJsonById(req);
		assertTrue(json.contains("Atenolol"));
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