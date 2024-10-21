package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;

import java.sql.Date;

//import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonProperty;

public class DatosPerfilInversorResponse {
	@JsonProperty("IdPerfil")
	private int idPerfil;

	@JsonProperty("Descripcion")
	private String descripcion;

	@JsonProperty("FechaDesde")
	private Date fechaDesde;

	@JsonProperty("FechaHasta")
	private Date fechaHasta;

	@JsonProperty("DiasVencimiento")
	private int diasVencimiento;

	@JsonProperty("TextoPerfil")
	private String descripcionLarga ;

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public int getDiasVencimiento() {
		return diasVencimiento;
	}

	public void setDiasVencimiento(int diasVencimiento) {
		this.diasVencimiento = diasVencimiento;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	
	
	

}
