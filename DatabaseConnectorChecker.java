package com.example.electricitybilling.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectorChecker {

    public static boolean isDatabaseConnected() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        if (isDatabaseConnected()) {
            System.out.println("Database is connected.");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
