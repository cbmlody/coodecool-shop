package com.codecool.shop.dao;

import static com.codecool.shop.App.run;
import static org.junit.jupiter.api.Assertions.*;


import com.codecool.shop.App;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;

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

//	@Test
//	@Tag("runApp")
//	public void testFindProduct() throws SQLException {
//		Product
//	}
//
	@Test
	void testAddProduct() throws SQLException {
		ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
		Product testProduct = generateProductWithoutId();
		productDaoSqlite.add(testProduct);
		System.out.println(productDaoSqlite.find(31));
		assertEquals(31, productDaoSqlite.getAll().size());
	}



//	public void add(Product product) throws SQLException {
//		String query = "INSERT INTO `products`(name, defaultPrice, currency, description, categoryId," +
//		 "supplierId) VALUES('"+product.getName()+"', '"+product.getDefaultPrice()+"', '"+product.getDefaultCurrency()+"'," +
//		 " '"+product.getDescription()+"', '"+product.getProductCategory().getId()+"', '"+product.getSupplier().getId()+"')";
//		Statement statement = App.getApp().getConnection().createStatement();
//		try {
//			statement.execute(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void remove(int id) throws SQLException {
//		String query = "DELETE FROM `products` WHERE id = '"+id+"'";
//		Statement statement = App.getApp().getConnection().createStatement();
//		try {
//			statement.execute(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public Product find(int id) throws SQLException {
//		Statement statement = App.getApp().getConnection().createStatement();
//		ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
//		SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
//		String query = "SELECT * FROM `products` WHERE id = '" + id + "'";
//		try {
//			ResultSet resultSet = statement.executeQuery(query);
//			if (!resultSet.isBeforeFirst()){
//				return null;
//			}
//			return productFromResultSet(resultSet);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public List<Product> getAll() throws SQLException {
//		String query = "SELECT * FROM `products`";
//		return getByHelper(query);
//	}
//
//	@Override
//	public List<Product> getBy(Supplier supplier) throws SQLException {
//		String query = "SELECT *FROM `products` WHERE supplierId = '" + supplier.getId() + "'";
//		return getByHelper(query);
//	}
//
//	@Override
//	public List<Product> getBy(ProductCategory productCategory) throws SQLException {
//		String query = "SELECT *FROM `products` WHERE categoryId = '"+productCategory.getId()+"'";
//		return getByHelper(query);
//	}
//
//	public List<Product> getBy(String name) throws SQLException {
//		String query = null;
//		if (name.length() >= 3) {
//			query = "SELECT *FROM `products` WHERE name LIKE '%"+name+"%'";
//		} else {
//			query = "SELECT *FROM `products`";
//		}
//		return getByHelper(query);
//	}
//
//	private List<Product> getByHelper(String query) throws SQLException {
//		List<Product> productList = new ArrayList<>();
//		Statement statement = App.getApp().getConnection().createStatement();
//
//		ResultSet resultSet = statement.executeQuery(query);
//		while (resultSet.next()) {
//			productList.add(productFromResultSet(resultSet));
//		}
//
//		return productList;
//	}
//
//	private Product productFromResultSet (ResultSet resultSet) throws SQLException {
//		ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
//		SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
//		return  new Product(
//		 resultSet.getInt("id"),
//		 resultSet.getString("name"),
//		 resultSet.getFloat("defaultPrice"),
//		 resultSet.getString("currency"),
//		 resultSet.getString("description"),
//		 productCategoryDaoSqlite.find(resultSet.getInt("categoryId")),
//		 supplierDaoSqlite.find(resultSet.getInt("supplierId")));
//	}
//


}