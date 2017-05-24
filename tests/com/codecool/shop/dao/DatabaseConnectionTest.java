package com.codecool.shop.dao;

import org.junit.AfterClass;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Array;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    private DatabaseConnection dbConnector;

    @Test
    public void testOpenConnectionThrowsSQLException() throws SQLException {
        dbConnector = new DatabaseConnection("");
        assertThrows(SQLException.class, () -> dbConnector.openConnection());
    }

    @Test
    public void testGetConnectionReturnOpenConnection() throws SQLException {
        dbConnector = new DatabaseConnection("jdbc:sqlite:tests/test_database.db");
        dbConnector.openConnection();
        assertFalse(dbConnector.getConnection().isClosed());
        dbConnector.closeConnection();
    }

    @Test
    public void testGetConnectionBeforeOpenConnectionReturnNull() {
        dbConnector = new DatabaseConnection("jdbc:sqlite:tests/test_database.db");
        assertEquals(null, dbConnector.getConnection());
    }

    @Test
    public void testCloseConnectionClosingConnection() throws SQLException {
        dbConnector = new DatabaseConnection("jdbc:sqlite:tests/test_database.db");
        dbConnector.openConnection();
        dbConnector.closeConnection();
        assertTrue(dbConnector.getConnection().isClosed());
    }

    @Nested
    @DisplayName("Database Tests")
    class databaseTests {

        @AfterEach
        public void dropTables() throws SQLException {
            dbConnector = new DatabaseConnection("jdbc:sqlite:tests/test_database.db");
            dbConnector.openConnection();
            String dropTablesQuery = "DROP TABLE IF EXISTS `products`;" +
                    "DROP TABLE IF EXISTS `suppliers`;" +
                    "DROP TABLE IF EXISTS `product_categories`;";
            dbConnector.getConnection().createStatement().executeUpdate(dropTablesQuery);
            dbConnector.closeConnection();
            System.out.println("Boo");
        }

        @Test
        public void testMigrateDBCreateDBFile() throws SQLException {
            dbConnector = new DatabaseConnection("jdbc:sqlite:tests/test_database.db");
            dbConnector.openConnection();
            dbConnector.migrateDb();
            List<String> tablesName = new ArrayList<>();
            DatabaseMetaData metaData = dbConnector.getConnection().getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", null);
            while (resultSet.next()) {
                tablesName.add(resultSet.getString(3));
            }
            assertTrue(tablesName.contains("product_categories"));
            assertTrue(tablesName.contains("suppliers"));
            assertTrue(tablesName.contains("products"));
            dbConnector.closeConnection();
        }

        @Test
        public void testResetDBCreateDBFile() throws SQLException {
            dbConnector = new DatabaseConnection("jdbc:sqlite:tests/test_database.db");
            dbConnector.openConnection();
            dbConnector.resetDatabase();
            String testQuery = "SELECT * FROM `products` WHERE id = 30";
            ResultSet resultSet = dbConnector.getConnection().createStatement().executeQuery(testQuery);
            assertEquals("Being Well stomach relief", resultSet.getString("name"));
            assertEquals(3, resultSet.getInt("categoryId"));
            assertEquals(6, resultSet.getInt("supplierId"));
            dbConnector.closeConnection();
        }
    }
}