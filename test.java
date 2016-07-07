import java.util.*;
import java.sql.*;
import java.io.*;

class Test {
	
	public static void main(String[] args) throws Exception {
//  		DbAddAccess add = new DbAddAccess();
//  		System.out.println(add.add("{\"river\": \"Mulberry\", \"access\": \"Indian Creek\"}"));
//  		System.out.println(add.add("{\"river\": \"Mulberry\", \"access\": \"Turner's Bend\"}"));
//  		System.out.println(add.add("{\"river\": \"Mulberry\", \"access\": \"Cambell's Cemetary\"}"));
//  		System.out.println(add.add("{\"river\": \"Mulberry\", \"access\": \"Bird's Outdoors\"}"));
		
// 		DbAddRiver add = new DbAddRiver();
// 		System.out.println(add.add("Mulberry"));
// 		System.out.println(add.add("Frog"));
// 		System.out.println(add.add("Illinois"));
// 		System.out.println(add.add("Richland"));
// 		System.out.println(add.add("Buffalo"));
		
		
	
		DbRmRiver rm = new DbRmRiver();
		System.out.println(rm.rm("Mulberry"));
		
	}
	
// 	public static String getSections(Connection conn, String riverToCheck) {
// 		Statement stmt = null;
// 		ResultSet rs = null;
// 		
// 		String sqlCommand = "SELECT * FROM rivers WHERE id = 1"; 
// 		
// 		try {
// 			stmt = conn.createStatement();
// 			rs = stmt.executeQuery(sqlCommand);
// 			rs.next();
// 			System.out.println("   The last id in list is " + rs.getNString("access"));
// 			return "1";
// 		}
// 		catch (SQLException ex){
// 			// handle any errors
// 			System.out.println("SQLException: " + ex.getMessage());
// 			System.out.println("SQLState: " + ex.getSQLState());
// 			System.out.println("VendorError: " + ex.getErrorCode());
// 		}
// 		finally {
// 			if (rs != null) {
// 				try {
// 					rs.close();
// 				} catch (SQLException sqlEx) { } // ignore
// 
// 				rs = null;
// 			}
// 
// 			if (stmt != null) {
// 				try {
// 					stmt.close();
// 				} catch (SQLException sqlEx) { } // ignore
// 
// 				stmt = null;
// 			}
// 		}
// 		return "-1";
// 	}
// 
// 	public static String getConnection(String riverToCheck) {
// 		String url = "jdbc:mysql://localhost:3306/kayaktivity";
// 		String username = "greg";
// 		String password = "(Critical)<Jack>";
// 		
// 		System.out.println("Loading driver...");
// 
// 		try {
// 			Class.forName("com.mysql.jdbc.Driver");
// 			System.out.println("Driver loaded!");
// 		} 
// 		catch (ClassNotFoundException e) {
// 			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
// 		}
// 		
// 		System.out.println("Connecting database...");
// 
// 		try (Connection connection = DriverManager.getConnection(url, username, password)) {
// 			System.out.println("Database connected!");
// 			return checkRiverForSection(connection, riverToCheck);
// 		} 
// 		catch (SQLException e) {
// 			throw new IllegalStateException("Cannot connect the database!", e);
// 		}
// 	}
}
	