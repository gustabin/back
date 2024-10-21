package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.Date;

public class SeleccionTarjetaBanelcoInEntity {

	private String funcion;
	
	private String idSesion;
	
	private String ip;
	
	private String numeroTarjeta;
	
	private String pinIngresado;
	
	private Date fechaNacimiento;
	
	private String dni;
	
	private String nup;

	
	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getPinIngresado() {
		return pinIngresado;
	}

	public void setPinIngresado(String pinIngresado) {
		this.pinIngresado = pinIngresado;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}
			
}
