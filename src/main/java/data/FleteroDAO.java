package data;

import java.sql.*;
import java.util.LinkedList;

import entities.Fletero;

public class FleteroDAO {
	
	private final String LIST_QUERY = "SELECT * FROM fletero";
	private final String ADD_QUERY = "INSERT INTO fletero (nombre, apellido, telefono, mail) VALUES (?, ?, ?, ?)";
	private final String UPDATE_QUERY = "UPDATE fletero SET nombre = ?, apellido = ?, telefono = ?, mail = ? WHERE id_fletero = ?";
	private final String DELETE_QUERY = "DELETE FROM fletero WHERE id_fletero = ?";
	
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
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet keyRS = null;
		try {
			conn = DbConnector.getConnection();
			ps = conn.prepareStatement(ADD_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, f.getNombre());
			ps.setString(2, f.getApellido());
			ps.setString(3, f.getTelefono());
			ps.setString(4, f.getMail());
			ps.executeUpdate();
			keyRS = ps.getGeneratedKeys();
			if (keyRS != null && keyRS.next()) {
				f.setId(keyRS.getInt(1));
			}
		} catch (Exception e){
			e.printStackTrace(System.out);
		} finally {
			if (keyRS != null) {DbConnector.close(keyRS);};
			if (ps != null) {DbConnector.close(ps);};
			if (conn != null) {DbConnector.close(conn);};
		}
		return f;
	}
	
	public void update(Fletero f) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbConnector.getConnection();
			ps = conn.prepareStatement(UPDATE_QUERY);
			ps.setString(1, f.getNombre());
			ps.setString(2, f.getApellido());
			ps.setString(3, f.getTelefono());
			ps.setString(4, f.getMail());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			if (ps != null) {DbConnector.close(ps);};
			if (conn != null) {DbConnector.close(conn);};
		}
	}
	
	public void delete(Fletero f) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbConnector.getConnection();
			ps = conn.prepareStatement(DELETE_QUERY);
			ps.setInt(1, f.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			if (ps != null) {DbConnector.close(ps);};
			if (conn != null) {DbConnector.close(conn);};
		}
	}
}