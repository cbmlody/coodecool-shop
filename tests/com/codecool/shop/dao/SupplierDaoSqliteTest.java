package com.codecool.shop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.codecool.shop.App;
import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class SupplierDaoSqliteTest {

    private SupplierDao supplierDao = new SupplierDaoSqlite();

    @BeforeEach
    public void mockDatabaseConnection() throws SQLException {
        App.run();
        App.getApp().setConnection("jdbc:sqlite:tests/test_database.db");
        App.getApp().resetDb();
    }

    @AfterEach
    public void dropDatabase() throws SQLException {
        App.getApp().closeConnection();
    }

    @Test
    public void testAddNewSupplierToDatabase() throws SQLException {
        Supplier testSupplier = new Supplier("testName", "testDescription");
        supplierDao.add(testSupplier);
        String testQuery = "SELECT * FROM `suppliers` WHERE id = 11";
        ResultSet resultSet = App.getApp().getConnection().createStatement().executeQuery(testQuery);
        assertEquals("testName", resultSet.getString("name"));
        assertEquals("testDescription", resultSet.getString("description"));
    }

    @Test
    public void testFindReturnsCorrectSupplier() throws SQLException {
        Supplier supplierFromTestDB = supplierDao.find(1);
        assertEquals("Apotex Corp.", supplierFromTestDB.getName());
    }

    @Test
    public void testFindReturnNullForIncorrectId() throws SQLException {
        Supplier supplierFromTestDB = supplierDao.find(100);
        assertEquals(null, supplierFromTestDB);
    }

    @Test
    public void testRemoveSupplierFromTestDB() throws SQLException {
        supplierDao.remove(6);
        assertEquals(null, supplierDao.find(6));
    }

    @Test
    public void testGetAllSuupliersFromTestDB() throws SQLException {
        List<Supplier> suppliersFromTestDB = supplierDao.getAll();
        assertEquals(10, suppliersFromTestDB.size());
        assertEquals("Apotex Corp.", suppliersFromTestDB.get(0).getName());
        assertEquals("Aloe Care International, LLC", suppliersFromTestDB.get(9).getName());
    }
}