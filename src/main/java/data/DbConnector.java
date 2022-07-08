package data;

import java.sql.*;

public class DbConnector {

	private static final String HOST ="localhost";
	private static final String PORT ="3306";
	private static final String USER ="root";
	private static final String PASSWORD ="leonguruciaga162";
	private static final String DB ="javatpdb";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DB, USER, PASSWORD);
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
}
