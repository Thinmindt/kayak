import java.util.*;
import java.sql.*;
import java.io.*;

class DbGetAccesses extends Database{

	String get(String toSQL) {
		return getConnection(toSQL);
	}

	String doIt(Connection conn, String river) {
		return getAccesses(conn, river);
	}
}