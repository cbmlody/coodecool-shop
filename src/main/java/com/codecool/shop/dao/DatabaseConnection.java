package com.codecool.shop.dao;

import com.codecool.shop.utils.FileReader;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import org.sqlite.JDBC;
import java.sql.*;

public final class DatabaseConnection {
    private Connection connection;
    private FileReader reader;
    private String pathToDatabase;

    public DatabaseConnection(String pathToDatabase) {
        this.reader =  new FileReader();
        this.pathToDatabase = pathToDatabase;
    }

    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection(this.pathToDatabase);
        System.out.println("Connection established...");
    }

    public void resetDatabase() throws SQLException {
        String[] dropTables= reader.getStringFromFile("/sqls/dropTables.sql").split(";");
        String[] createTables= reader.getStringFromFile("/sqls/createTables.sql").split(";");
        String[] insertData = reader.getStringFromFile("/sqls/insertData.sql").split(";");
        String[][] queries = {dropTables, createTables, insertData};
        String[] infos = {"Dropping Tables...", "Creating Tables...", "Inserting data..."};
        Statement statement = connection.createStatement();
        System.out.println("Resetting database...");
        for (int i = 0; i<queries.length; i++) {
            System.out.println(infos[i]);
            for (String query: queries[i])
                if (!query.trim().equals("")) {
                    statement.executeUpdate(query);
                }
        }
        System.out.println("DONE!");
    }

    public void migrateDb() throws SQLException {
        String[] createTables= reader.getStringFromFile("/sqls/createTables.sql").split(";");
        Statement statement = connection.createStatement();
        System.out.println("Creating Tables...");
        for (String query: createTables)
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        System.out.println("Done!");
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
        System.out.println("Connection terminated...");
    }
}
