package database.com.airamerica.interfaces;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

	



		public static final String url = "jdbc:mysql://cse.unl.edu/javery";
		public static final String username = "javery";
		public static final String password = "jellyfish";
		
		static public Connection getConnection()
		{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				System.out.println("InstantiationException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				System.out.println("IllegalAccessException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			Connection conn = null;

			try {
				conn = DriverManager.getConnection(DatabaseConnect.url, DatabaseConnect.username, DatabaseConnect.password);
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			return conn;
}
}
