package com.codecool.shop.controller;

import com.codecool.shop.App;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mercutio on 25.05.17.
 */
class SupplierControllerTest {
    private SupplierController supplierController;

    @BeforeEach
    void setUp() throws SQLException {
//        App.run();
//        App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
//        App.getApp().resetDb();
        this.supplierController = new SupplierController();
    }

    @AfterEach
    void tearDown() throws SQLException {
        App.getApp().closeConnection();
    }

    @Test
    void testIfGetAllSuppliersTakesParameters() {

    }



}