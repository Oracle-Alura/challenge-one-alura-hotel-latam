package model;

import java.sql.Date;

public class Reserva {
	
	private Integer id;
	private Date fechaE;
	private Date fechaS;
	private String valor;
	private String formaPago;
	
	
	public Reserva(Date fechaE, Date fechaS, String valor, String formaPago) {		
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.valor = valor;
		this.formaPago = formaPago;
	}
		
	public Reserva(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
		this.id = id;
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getfechaE() {
		return fechaE;
	}

	public Date getfechaS() {
		return fechaS;
	}

	public String getvalor() {
		return valor;
	}

	public String getformaPago() {
		return formaPago;
	}
	
	

}
