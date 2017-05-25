package com.codecool.shop.dao;
import com.codecool.shop.App;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static java.lang.NullPointerException.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ProductCategoryDaoSqliteTest {
    private ProductCategoryDaoSqlite productCategoryDaoSqlite;

    @BeforeEach
    void setUp() throws SQLException {
        App.run();
        App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
        App.getApp().resetDb();
        this.productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
    }

    @AfterEach
    void tearDown() throws SQLException {
        App.getApp().closeConnection();
    }

    @Test
    void testAddProductCategoryToDatabase() throws SQLException {
        ProductCategory productCategory = new ProductCategory("testName", "testDepartment", "testDescription");
        this.productCategoryDaoSqlite.add(productCategory);
        String query = "SELECT name, department, description FROM `product_categories` WHERE id = 11;";
        ResultSet result = App.getApp().getConnection().createStatement().executeQuery(query);
        assertEquals("testName", result.getString("name"));
        assertEquals("testDepartment", result.getString("department"));
        assertEquals("testDescription", result.getString("description"));
    }

    @Test
    void testAddProductCategoryToDatabaseThrowsSQLException() throws SQLException {
        App.getApp().closeConnection();
        assertThrows(SQLException.class, () -> {
            this.productCategoryDaoSqlite.add(mock(ProductCategory.class));
        });
    }


    @Test
    void testRemoveProductCategoryFromDatabase() throws SQLException {
        this.productCategoryDaoSqlite.remove(1);
        assertEquals(null, this.productCategoryDaoSqlite.find(-1));
    }

    @Test
    void testFindByIdProductCategoryInDataBase() throws SQLException {
        ProductCategory productCategory = this.productCategoryDaoSqlite.find(10);
        assertEquals("Hair Products", productCategory.getName());
    }

    @Test
    void testFindByIdProductCategoryReturnNull() throws SQLException {
        ProductCategory productCategory = this.productCategoryDaoSqlite.find(-100000);
        assertEquals(null, productCategory);
    }

    @Test
    void testGetAllProductCategoriesFromDataBase() throws SQLException {
        List<ProductCategory> productCategoryList = this.productCategoryDaoSqlite.getAll();
        assertEquals(10, productCategoryList.size());
        assertEquals(1, productCategoryList.get(0).getId());
        assertEquals(10, productCategoryList.get(9).getId());
    }
}