package com.codecool.shop.controller;

import com.codecool.shop.App;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ProductCategoryControllerTest {
    private ProductCategoryController productCategoryController;
    private ProductCategoryDaoSqlite productCategoryDaoSqlite;

    @BeforeEach
    void setUp() throws SQLException {
        App.run();
        App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
        App.getApp().resetDb();
        this.productCategoryController = new ProductCategoryController();
        this.productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
    }

    @AfterEach
    void tearDown() throws SQLException {
        App.getApp().closeConnection();
    }

    @Test
    void testGetAllProductCategoriesThrowsSQLException() throws SQLException {
        App.getApp().closeConnection();
        assertThrows(SQLException.class, ()-> {
            ProductCategoryController.getAllProductCategories(mock(Request.class), mock(Response.class));
        });
    }

    @Test
    void testGetAllProductCategories() throws SQLException {
        ProductCategoryDaoSqlite testProductCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        Map testParams = new HashMap<>();
        testParams.put("productCategoriesList", testProductCategoryDaoSqlite.getAll());
        ModelAndView testModelAndView = new ModelAndView(testParams, "");

        Map params = new HashMap<>();
        params.put("productCategoriesList", this.productCategoryDaoSqlite.getAll());
        ModelAndView modelAndiew = ProductCategoryController.getAllProductCategories(mock(Request.class), mock(Response.class));
        assertEquals(testModelAndView.getModel().toString(), modelAndiew.getModel().toString());
        assertEquals(testModelAndView.getClass(), modelAndiew.getClass());
    }

    @Test
    void testFindCategoryThrowsSQLException() throws SQLException {
        App.getApp().closeConnection();
        assertThrows(SQLException.class, () -> {
            this.productCategoryDaoSqlite.find(1);
        });
    }

    @Test
    void testFindCategory() throws SQLException {
        ProductCategoryDaoSqlite testProductCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        ProductCategory productCategory = testProductCategoryDaoSqlite.find(1);
        ProductCategory testProductCategory = this.productCategoryDaoSqlite.find(1);
        assertEquals(productCategory.toString() ,testProductCategory.toString());
    }
}