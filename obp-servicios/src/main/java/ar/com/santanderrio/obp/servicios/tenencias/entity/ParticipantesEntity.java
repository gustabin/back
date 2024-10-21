package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The class Participantes.
 */

public class ParticipantesEntity {

	@JsonProperty("NumeroContrato")
	private String numeroContrato;

	@JsonProperty("TipoDocumento")
	private String tipoDocumento;

	@JsonProperty("NumeroPersona")
	private String numeroPersona;

	@JsonProperty("Nombre")
	private String nombre;

	@JsonProperty("Apellido")
	private String apellido;

	@JsonProperty("FechaAlta")
	private String fechaAlta;

	@JsonProperty("FechaBaja")
	private String fechaBaja;

	@JsonProperty("EstadoRelacion")
	private String estado;
	
	@JsonProperty("TipoCuenta")
	private String tipoCuenta;
	

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroPersona() {
		return numeroPersona;
	}

	public void setNumeroPersona(String numeroPersona) {
		this.numeroPersona = numeroPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	/**
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta.substring(0,10);
	}

	/**
	 * @return the fechaBaja
	 */
	public String getFechaBaja() {
		return fechaBaja;
	}

	/**
	 * @param fechaBaja the fechaBaja to set
	 */
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja.substring(0,10);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
