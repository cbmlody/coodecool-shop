package com.codecool.shop.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseConnection {
    private Connection connection = null;

    public void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/database.db");
            System.out.println("Connection established...");

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS `products`");
            stmt.executeUpdate("DROP TABLE IF EXISTS `suppliers`");
            stmt.executeUpdate("DROP TABLE IF EXISTS `product_categories`");
            stmt.executeUpdate("DROP TABLE IF EXISTS `cart_products`");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    };

    public static void resetDatabase(Connection connection) throws SQLException
    {
        String s            = new String();
        StringBuffer sb = new StringBuffer();
        Statement state = connection.createStatement();

        try
        {
            String filePath = "script.sql"
;           File scriptSql = new File(filePath);
            FileReader fr = new FileReader(scriptSql.getAbsolutePath());
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

            BufferedReader br = new BufferedReader(fr);

            while((s = br.readLine()) != null)
            {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");


            for (String anInst : inst) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if (!anInst.trim().equals("")) {
                    state.executeUpdate(anInst);
                    System.out.println(">>" + anInst);
                }
            }

        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
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

    public Connection getConnection() {
        return connection;
    }
}
