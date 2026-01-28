
package com.revplay.util;
import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBConnection {
    private static final Logger logger = LogManager.getLogger(DBConnection.class);


    public static Connection getConnection() {
         String url = "jdbc:mysql://localhost:3306/revplay_db";
         String username = "root";
         String password = "0609";

        Connection connection = null;

        try {

            // Step 2: Create Connection
            connection = DriverManager.getConnection(url,username,password);
            logger.info("Database connection established successfully");
        }
        catch (SQLException e) {
//            System.out.println("Database connection failed");
            logger.error("Database connection failed", e);

        }

        return connection;
    }
}
