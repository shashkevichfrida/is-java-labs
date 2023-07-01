package org.example.Sql;

import java.sql.*;

public class Util {
    private Connection connection;

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;encrypt=true;TrustServerCertificate=true;databaseName=HR_System;user=SA;password=FridaFrida1337;");
            //createTableCarBrand();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void createTableCarBrand() {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "CarBrand", null);
            if (tables.next()) {
                System.out.println("CarBrand exists");
            } else {
                String sql = "CREATE TABLE CarBrand " +
                        "(Id bigint not NULL, " +
                        " Name nvarchar(max), " +
                        " Date date, " +
                        " PRIMARY KEY ( ID ))";

                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createTableCarModel() {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "CarModel", null);
            if (tables.next()) {
                System.out.println("CarModel exists");
            } else {
                String sql = "CREATE TABLE CarModel " +
                       " (Id bigint not NULL, " +
                        " Name nvarchar(max), " +
                        " Length float, " +
                        " Width float, " +
                        " BodyType nvarchar(255), " +
                        " CarBrandId bigint, " +
                       " FOREIGN KEY(CarBrandId) REFERENCES CarBrand( Id ), " +
                        " PRIMARY KEY ( Id ))";

                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
