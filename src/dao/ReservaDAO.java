package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Huespedes;
import model.Reserva;

public class ReservaDAO {
	
private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setDate(1, reserva.getfechaE());
				pstm.setDate(2, reserva.getfechaS());
				pstm.setString(3, reserva.getvalor());
				pstm.setString(4, reserva.getformaPago());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Reserva> buscar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {

			String sql = "SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Metodo para buscar por APELLIDO (Alejandra)	
	public List<Huespedes> buscarApellido(String apellido) {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {

			String sql = "SELECT id, nombre, apellido, valor, fechaNac, nacionalidad, telefono FROM huespedes WHERE apellido = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(3, apellido);
				pstm.execute();

				transformarResultSetEnHuespedes(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	private void transformarResultSetEnHuespedes(List<Huespedes> huespedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Huespedes huesped = new Huespedes(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
								
				huespedes.add(huesped);
			}
		}
		
	}

	public void Eliminar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE reservas SET fechaEntrada = ?, fechaSalida = ?, valor = ?, formaPago = ? WHERE id = ?")) {
			stm.setDate(1, fechaE);
			stm.setDate(2, fechaS);
			stm.setString(3, valor);
			stm.setString(4, formaPago);
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
						
	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));

				reservas.add(reserva);
			}
		}
	}
	

}
