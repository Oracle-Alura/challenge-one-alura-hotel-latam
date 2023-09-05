package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import conexion.ConnectionFactory;
import dao.ReservaDAO;
import model.Huespedes;
import model.Reserva;

public class ReservasController {
	
	private ReservaDAO reservaDAO;
	 
	 public ReservasController() {
			Connection connection = new ConnectionFactory().recuperarConexion();
			this.reservaDAO = new ReservaDAO(connection);
		}
	 
		public void guardar(Reserva reserva) {
			this.reservaDAO.guardar(reserva);
		}
			
		public List<Reserva> buscar() {
			return this.reservaDAO.buscar();
		}
		
		public List<Reserva> buscarId(String id) {
			return this.reservaDAO.buscarId(id);
		}
		
		// Listar por APELLIDO
		public List<Huespedes> buscarApellido(String apellido) {
			return this.reservaDAO.buscarApellido(apellido);
		}
		
		public void actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
			this.reservaDAO.Actualizar(fechaE, fechaS, valor, formaPago, id);
		}
		
		public void Eliminar(Integer id) {
			this.reservaDAO.Eliminar(id);
		}
	

}
