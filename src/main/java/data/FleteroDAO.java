package data;

import java.sql.*;
import java.util.LinkedList;

import entities.Fletero;

public class FleteroDAO {
	
	private final String LIST_QUERY = "SELECT * FROM fletero";

	public LinkedList<Fletero> list() throws SQLException {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		Fletero f = null;
		LinkedList<Fletero> fleteros = new LinkedList<>();
		try {
			conn = DbConnector.getConnection();
			s = conn.createStatement();
			rs = s.executeQuery(LIST_QUERY);
			if (rs != null) {
				while (rs.next()) {
					f = new Fletero(rs.getInt("id_fletero"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("mail"));
					fleteros.add(f);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			if (rs != null) {DbConnector.close(rs);};
			if (s != null) {DbConnector.close(s);};
			if (conn != null) {DbConnector.close(conn);};
		}
		return fleteros;
	}
	
	public Fletero add(Fletero f) throws SQLException {
		return null;
	}
	
	public void update(Fletero f) throws SQLException {
		
	}
	
	public void delete(Fletero f) throws SQLException {
		
	}
}