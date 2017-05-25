package com.codecool.shop.controller;

import com.codecool.shop.App;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.Request;
import spark.ModelAndView;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by mercutio on 25.05.17.
 */
class SupplierControllerTest {
    private SupplierController supplierController;
    spark.Request req;
    spark.Response res;

    @BeforeEach
    void setUp() throws SQLException {
        App.run();
        App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
        App.getApp().resetDb();
        this.supplierController = new SupplierController();
        this.req = mock(spark.Request.class);
        this.res = mock(spark.Response.class);
    }

    @AfterEach
    void tearDown() throws SQLException {
        App.getApp().closeConnection();
    }

    @Test
    void testIfGetAllSuppliersThrowsSQLException() throws SQLException {
        App.getApp().closeConnection();
        assertThrows(SQLException.class, () -> SupplierController.getAllSuppliers(this.req, this.res));

    }

    @Test
    void testIfGetAllSuppliersTakesParametersFromSQLITE() throws SQLException {
        HashMap testMap = (HashMap) SupplierController.getAllSuppliers(this.req, this.res).getModel();
        assertTrue(testMap.size() > 0);

    }

    @Test
    void testIfFindThrowsSQLException() throws SQLException {
        App.getApp().closeConnection();
        assertThrows(SQLException.class, () -> this.supplierController.findSupplier(1));
    }

}

