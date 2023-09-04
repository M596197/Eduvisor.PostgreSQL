package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        String postgreSQLUrl = "jdbc:postgresql://ider-database.westeurope.cloudapp.azure.com:5432/h596197";
        String username = "h596197";
        String password = "passpass";


        Connection connection= null;
        Statement stmt = null;

        try {
             connection = DriverManager.getConnection(postgreSQLUrl, username,password);
            System.out.println("ALL GOOD");

            stmt = connection.createStatement();
            String cas = "DROP TABLE IF EXISTS COMPANY CASCADE ";
            String sql =
                    "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";

            stmt.executeUpdate(cas);
            stmt.executeUpdate(sql);
            String insertDataSQL = "INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) VALUES " +
                    "(1, 'John Doe', 30, '123 Main St', 50000.0), " +
                    "(2, 'Jane Smith', 25, '456 Elm St', 60000.0), " +
                    "(3, 'Bob Johnson', 35, '789 Oak St', 75000.0)";

            stmt.executeUpdate(insertDataSQL);
            String query = "SELECT * FROM COMPANY";
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                int id = res.getInt("ID");
                String name = res.getString("NAME");
                int age = res.getInt("AGE");
                String address = res.getString("ADDRESS");
                double salary = res.getDouble("SALARY");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Address: " + address + ", Salary: " + salary);
            }
            res.close();
            stmt.close();
            connection.close();
        } catch(SQLException e){
            System.out.println("Error in connecting to PostgreSQL ");
            e.printStackTrace();
        }

    }
}