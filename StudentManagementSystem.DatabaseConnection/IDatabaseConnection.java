package databaseconnection;

import java.sql.Connection;

public interface IDatabaseConnection {
	  Connection getConnection();
	    void closeConnection();

}
