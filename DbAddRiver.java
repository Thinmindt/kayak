import java.util.*;
import java.sql.*;
import java.io.*;

class DbAddRiver extends Database{

	String add(String toSQL) {
		return getConnection(toSQL);
	}

	String doIt(Connection conn, String toSQL) {
		Statement stmt = null;
		ResultSet rs = null;
		
		int lastId = getLastId(conn);
		if (lastId == -1) 
			return "-1";
			
		lastId = lastId + 1;
		
		String sqlCommand = "INSERT INTO rivers VALUES (" + lastId + ", \"Mulberry\", \"Turner's Bend\")"; 
		
		try {
			stmt = conn.createStatement();
			stmt.execute(sqlCommand);
			return "0";
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
}