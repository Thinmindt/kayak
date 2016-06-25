import java.util.*;
import java.sql.*;
import java.io.*;

class DbAddAccess extends Database{

	String add(String toSQL) {
		return getConnection(toSQL);
	}

	String doIt(Connection conn, String toSQL) {
		return addAccess(conn, toSQL);
	}
}