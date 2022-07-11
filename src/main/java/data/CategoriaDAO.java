package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Categoria;

public class CategoriaDAO {

	private final String LIST_QUERY = "SELECT * FROM categoria";
	private final String ADD_QUERY = "INSERT INTO categoria (descripcion) VALUES (?)";
	private final String UPDATE_QUERY = "UPDATE categoria SET descripcion = ? WHERE id_categoria = ?";
	private final String DELETE_QUERY = "DELETE FROM categoria WHERE id_categoria = ?";
	
	public LinkedList<Categoria> list() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		Categoria c = null;
		LinkedList<Categoria> categorias = new LinkedList<>();
		try {
			conn = DbConnector.getConnection();
			s = conn.createStatement();
			rs = s.executeQuery(LIST_QUERY);
			if (rs != null) {
				while (rs.next()) {
					c = new Categoria(rs.getInt("id_categoria"), rs.getString("descripcion"));
					categorias.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			if (rs != null) {DbConnector.close(rs);};
			if (s != null) {DbConnector.close(s);};
			if (conn != null) {DbConnector.close(conn);};
		}
		return categorias;
	}
	
	public Categoria add(Categoria c) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet keyRS = null;
		try {
			conn = DbConnector.getConnection();
			ps = conn.prepareStatement(ADD_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getDescripcion());
			ps.executeUpdate();
			keyRS = ps.getGeneratedKeys();
			if (keyRS != null && keyRS.next()) {
				c.setId(keyRS.getInt(1));
			}
		} catch (SQLException e){
			e.printStackTrace(System.out);
		} finally {
			if (keyRS != null) {DbConnector.close(keyRS);};
			if (ps != null) {DbConnector.close(ps);};
			if (conn != null) {DbConnector.close(conn);};
		}
		return c;
	}
	
	public void update(Categoria c) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbConnector.getConnection();
			ps = conn.prepareStatement(UPDATE_QUERY);
			ps.setString(1, c.getDescripcion());
			ps.setInt(2, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			if (ps != null) {DbConnector.close(ps);};
			if (conn != null) {DbConnector.close(conn);};
		}
	}
	
	public void delete(Categoria c) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbConnector.getConnection();
			ps = conn.prepareStatement(DELETE_QUERY);
			ps.setInt(1, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			if (ps != null) {DbConnector.close(ps);};
			if (conn != null) {DbConnector.close(conn);};
		}
	}
}