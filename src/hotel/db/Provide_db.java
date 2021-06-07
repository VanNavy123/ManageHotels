package hotel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Provide_db {
	private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
			+ "databaseName=ManageHotel;";
	private static String USER_NAME = "sa";
	private static String PASSWORD = "12345678";

    /**
     * Create connection
     * @return
     */
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		} catch (Exception ex) {
			System.out.println("connect failure!");
			ex.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * Close connection
	 * @param connect
	 */
	public static void closeConnection(Connection connect) {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				System.out.println("close connection failure!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Close prepare statement
	 * @param prStmt
	 */
	public static void closeStatment(PreparedStatement prStmt) {
		if (prStmt != null) {
			try {
				prStmt.close();
			} catch (SQLException e) {
				System.out.println("Close prepare statement failure!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Close result set
	 * @param prStmt
	 */
	public void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Close result set failure!");
				e.printStackTrace();
			}
		}
	}
}
