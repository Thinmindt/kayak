import java.util.*;
import java.sql.*;
import java.io.*;

class DbRmAccess extends Database{

	String rm(String toSQL) {
		return getConnection(toSQL);
	}

	String doIt(Connection conn, String river) {
		return rmAccess(conn, river);
	}
}