package com.codecool.shop.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseConnection {
    private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    private Connection connection = null;
    private Statement statement = null;

    private DatabaseConnection() {
        this.openConnection();
    }

    public static DatabaseConnection getInstance() {
        return INSTANCE;
    }

    public void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/database.db");
            System.out.println("Connection established...");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void resetDatabase() throws SQLException {
        String string;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(new File("src/main/resources/script.sql"));

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((string = bufferedReader.readLine()) != null) {
                stringBuilder.append(string);
            }
            bufferedReader.close();

            String[] queries = stringBuilder.toString().split(";");

            for (String query : queries) {
                if (!query.trim().equals("")) {
                    getStatement().executeUpdate(query);
                    System.out.println(">>" + query);
                }
            }

        } catch (Exception e) {
            System.out.println("*** Error : " + e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(stringBuilder.toString());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection terminated...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
}