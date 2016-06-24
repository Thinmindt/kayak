import java.util.*;
import java.sql.*;
import java.io.*;

abstract class Database {
	static final String url = "jdbc:mysql://localhost:3306/kayaktivity";
	static final String username = "greg";
	static final String password = "(Critical)<Jack>";
	
	Database() {
	
	}
	
	abstract String doIt(Connection conn, String toSQL);
	
	String getConnection(String toSQL) {
		System.out.println("Loading driver...");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} 
		catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			return doIt(connection, toSQL);
			
		} 
		catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
		
	public static int getLastId(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		
		String sqlCommand = "SELECT id FROM rivers ORDER BY id DESC LIMIT 1"; 
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlCommand);
			rs.next();
			return (rs.getInt("id"));
		}
		catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) { } // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) { } // ignore

				stmt = null;
			}
		}
		return -1;
	}
	
	
// 	boolean addSection(int id, String river, String putIn, String takeOut, int distance, int optLevel, String guage, 
// 											int rapidClass, String description) {
// 		Connection conn = getConnecton();
// 		Statement stmt = null;
// 		
// 		
// 		
// 		String sqlCommand = "INSERT INTO " ;
// 		
// 		try {
// 			stmt = conn.createStatement(sqlCommand);
// 			
// 		
// 		return true;
// 	}
}
