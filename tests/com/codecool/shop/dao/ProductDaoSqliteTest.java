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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by michal on 23.05.17.
 */


class ProductDaoSqliteTest {

	private ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();

	@Test
	void testGetAllByCategory() throws SQLException {
		ProductCategory productCategory = new ProductCategory(1,"Skin Care", "Health",
				"auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio");
		List<Product> productList = productDaoSqlite.getBy(productCategory);
		assertEquals(4, productList.size());
	}

    @Test
    void testGetAllBySupplier() throws SQLException {
        Supplier supplier = new Supplier(1, "Apotex Corp.", "cras non velit nec nisi vulputate nonummy " +
                "maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque");
        List<Product> productList = productDaoSqlite.getBy(supplier);
        assertEquals(5, productList.size());
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
    void testGetAll() throws SQLException {
        List<Product> productList = productDaoSqlite.getAll();
        assertEquals(30, productList.size());
    }

    @Test
    void testGetAllAfterApplicationInitializationComponentType() throws SQLException {
        ArrayList productList = new ArrayList<Product>();
        assertEquals(productList.getClass().getComponentType(), new ProductDaoSqlite().getAll().getClass().getComponentType());
    }

    @Test
    void testGetByName() throws SQLException {
        List<Product> product = productDaoSqlite.getBy("Atenolol");
        assertEquals("Atenolol", product.get(0).getName());
    }
    @Test
    void testRemoveProductFromTestDB() throws SQLException {
        productDaoSqlite.remove(6);
        Product product = productDaoSqlite.find(6);
        assertEquals(null, product);
    }

	@Test
	void testFindProductWithCorrectId() throws SQLException {
		Product product = productDaoSqlite.find(1 );
		assertEquals("Dr.Jart CC Essence Balm 02 Medium - Deep", product.getName());
	}

    @Test
    void testFindProductWithIncorrectId() throws SQLException {
        Product product = productDaoSqlite.find(300 );
        assertEquals(null, product);
    }

	private Product generateProductWithId() throws SQLException {
		Supplier supplier = new Supplier(10, "testName", "testDescr");
		ProductCategory productCategory = new ProductCategory(10, "testName", "testDep", "testDescr");
		return new Product(31,"testName", 100f, "PLN", "testDesc", productCategory, supplier);
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