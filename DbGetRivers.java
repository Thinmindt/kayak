import java.util.*;
import java.sql.*;
import java.io.*;

class DbGetRivers extends Database{

	String get(String toSQL) {
		return getConnection(toSQL);
	}

	String doIt(Connection conn, String toSQL) {
		return getRivers(conn);
	}
}