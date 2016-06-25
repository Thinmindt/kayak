import java.util.*;
import java.sql.*;
import java.io.*;

abstract class Database {
	static final String url = "jdbc:mysql://localhost:3306/kayaktivity";
	static final String username = "greg";
	static final String password = "<notrealpassword>";
	
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
	
	
	// add riverToAdd to the database. returns 1 if it worked, -1 if failed.
	public static String addRiver(Connection conn, String riverToAdd) {
		Statement stmt = null;
		ResultSet rs = null;
		
		// increment ID's in database
		int lastId = getLastId(conn);
		if (lastId == -1) 
			return "-1";
		lastId = lastId + 1;
		
		String sqlCommand = "INSERT INTO rivers (id,name) VALUES (" + lastId + ", '" + riverToAdd + "')"; 
		
		try {
			stmt = conn.createStatement();
			stmt.execute(sqlCommand);
			return "1";
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
		return "-1";
	}
	
	//get sections by river. returns list of access points in JSON or -1 if it fails
	String getAccesses(Connection conn, String river) {
		Statement stmt = null;
		ResultSet rs = null;
		
		String sqlCommand = "SELECT * FROM rivers WHERE name = '" + river + "'"; 
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlCommand);
			rs.next();
			return rs.getString("access");
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
		System.out.println("getAccesses failed");
		return "-1";
	}
	
	// add an access point to a river. takes JSON input {"river": "<river_name>", "access": "<access_name>"}
	// returns 1 if it works, -1 if it doesn't, "section exists" if it's already added
	String addAccess(Connection conn, String toSQL) {
		Statement stmt = null;
		ResultSet rs = null;
		
		//parse json
		String river = null;
		String access = null;
		if (toSQL.contains("river\": ")) {
			int beginning = toSQL.indexOf("river\": ");
			beginning += 9;
			int end = toSQL.indexOf(',', beginning) - 1;
			river = toSQL.substring(beginning, end);
		}
		else {
			System.out.println("invalid input to addAccess");
			return "-1";
		}
		if (toSQL.contains("access\": ")) {
			int beginning = toSQL.indexOf("access\": ");
			beginning += 10;
			int end = toSQL.indexOf('"', beginning);
			access = toSQL.substring(beginning, end);
		}
		else {
			System.out.println("invalid input to addAccess");
			return "-1";
		}
		
		String newAccessList = null;
		//check if access already exists and append entry
		String accessList = getAccesses(conn, river);
		if (accessList != null) {
			if (accessList.contains(access))
				return "access exists";
			
			int indexInsert = accessList.indexOf('}');
			newAccessList = accessList.substring(0, indexInsert) + ", access: " + access + "}";
		}
		else 
			newAccessList = "{access: " + access + "}";
			
		// make SQL command
		String sqlCommand = "UPDATE rivers SET access=\"" + newAccessList + "\" WHERE name=\"" + river + "\""; 
		
		try {
			stmt = conn.createStatement();
			stmt.execute(sqlCommand);
			return "1";
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
		return "-1";
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
