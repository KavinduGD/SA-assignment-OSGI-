package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements IDatabaseConnection {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/student_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Shhjhrrr123";
    private Connection connection;
	   public static final String ANSI_RESET = "\u001B[0m";
	   public static final String ANSI_YELLOW = "\u001B[33m";
    
    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println(ANSI_YELLOW+"Database Connected Successfully"+ANSI_RESET);
        } catch (ClassNotFoundException | SQLException e) {
        	System.out.println(ANSI_YELLOW+"Could not connect to the database"+ANSI_RESET);
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    


}
