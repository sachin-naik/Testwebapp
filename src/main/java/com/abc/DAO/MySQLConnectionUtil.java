package com.abc.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLConnectionUtil {
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    
    
	public static Connection getConnection() {
		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "mydb12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;

	}

    // You need to close the resultSet
    public static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}