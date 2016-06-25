import java.util.*;
import java.sql.*;
import java.io.*;

class DbAddRiver extends Database{

	String add(String riverToAdd) {
		return getConnection(riverToAdd);
	}

	String doIt(Connection conn, String riverToAdd) {
		return addRiver(conn, riverToAdd);
	}
}