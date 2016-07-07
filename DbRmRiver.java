import java.util.*;
import java.sql.*;
import java.io.*;

class DbRmRiver extends Database{

	String rm(String toSQL) {
		return getConnection(toSQL);
	}

	String doIt(Connection conn, String river) {
		// get info from last entry in list
		int lastId = getLastId(conn);
		String lastRiver = getRiverById(conn, lastId);
		String lastAccesses = getAccessesById(conn, lastId);
		
		int toDeleteId = getIdByName(conn, river);
		
		// delete last item in list
		if (rmRiver(conn, lastRiver) == "1") {
			if (toDeleteId != lastId) {
				// replace entry to delete with last in list
				String updateRet = updateById(conn, toDeleteId, lastRiver, lastAccesses);
				if (updateRet == "-1")
					return updateRet;
			}
			return "1";
		}
		
		return "-1";
	}
}