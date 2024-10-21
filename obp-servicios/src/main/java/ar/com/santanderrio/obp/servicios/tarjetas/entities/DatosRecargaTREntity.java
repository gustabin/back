/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import javax.validation.constraints.Pattern;

/**
 * The Class DatosRecargaTREntity.
 */
public class DatosRecargaTREntity {

	/** The importe. */
	private String importe;

	/** The sucursal cuenta destino. */
	@Pattern(regexp = "^[a-zA-Z0-9]{4}$")
	private String sucursalCuentaDestino;

	/** The tipo cuenta destino. */
	@Pattern(regexp = "[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+")
	private String tipoCuentaDestino;

	/** The nro cuenta destino. */
	private String nroCuentaDestino;

	/** The sucursal cuenta origen. */
	@Pattern(regexp = "^[a-zA-Z0-9]{4}$")
	private String sucursalCuentaOrigen;

	/** The tipo cuenta origen abreviatura. */
	private String tipoCuentaOrigenAbreviatura;

	/** The tipo cuenta origen. */
	@Pattern(regexp = "^[a-zA-Z0-9]{2}$")
	private String tipoCuentaOrigen;

	/** The nro cuenta origen. */
	@Pattern(regexp = "^[a-zA-Z0-9]{12}$")
	private String nroCuentaOrigen;

	/** The nro cuenta origen sin formato. */
	private String nroCuentaOrigenSinFormato;

	/** The divisa. */
	@Pattern(regexp = "^[a-zA-Z0-9]{3}$")
	private String divisa;

	/** The nro tarjeta. */
	@Pattern(regexp = "\\d{16}")
	private String nroTarjeta;

	/** The fecha de recarga. */
	private String fechaDeRecarga;

	/** The fecha inicio. */
	private String fechaInicio;

	/** The fecha de prox recarga. */
	private String fechaDeProxRecarga;

	/** The frecuencia. */
	private String frecuencia;

	/** The nro orden firmante. */
	private String nroOrdenFirmante;

	/** The es recarga programada. */
	private Boolean esRecargaProgramada;

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the sucursal cuenta destino.
	 *
	 * @return the sucursal cuenta destino
	 */
	public String getSucursalCuentaDestino() {
		return sucursalCuentaDestino;
	}

	/**
	 * Sets the sucursal cuenta destino.
	 *
	 * @param sucursalCuentaDestino
	 *            the new sucursal cuenta destino
	 */
	public void setSucursalCuentaDestino(String sucursalCuentaDestino) {
		this.sucursalCuentaDestino = sucursalCuentaDestino;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the nro cuenta destino.
	 *
	 * @return the nro cuenta destino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * Sets the nro cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the new nro cuenta destino
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	/**
	 * Gets the sucursal cuenta origen.
	 *
	 * @return the sucursal cuenta origen
	 */
	public String getSucursalCuentaOrigen() {
		return sucursalCuentaOrigen;
	}

	/**
	 * Sets the sucursal cuenta origen.
	 *
	 * @param sucursalCuentaOrigen
	 *            the new sucursal cuenta origen
	 */
	public void setSucursalCuentaOrigen(String sucursalCuentaOrigen) {
		this.sucursalCuentaOrigen = sucursalCuentaOrigen;
	}

	/**
	 * Gets the tipo cuenta origen abreviatura.
	 *
	 * @return the tipo cuenta origen abreviatura
	 */
	public String getTipoCuentaOrigenAbreviatura() {
		return tipoCuentaOrigenAbreviatura;
	}

	/**
	 * Sets the tipo cuenta origen abreviatura.
	 *
	 * @param tipoCuentaOrigenAbreviatura
	 *            the new tipo cuenta origen abreviatura
	 */
	public void setTipoCuentaOrigenAbreviatura(String tipoCuentaOrigenAbreviatura) {
		this.tipoCuentaOrigenAbreviatura = tipoCuentaOrigenAbreviatura;
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipo cuenta origen
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the new tipo cuenta origen
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
	}

	/**
	 * Gets the nro cuenta origen.
	 *
	 * @return the nro cuenta origen
	 */
	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	/**
	 * Sets the nro cuenta origen.
	 *
	 * @param nroCuentaOrigen
	 *            the new nro cuenta origen
	 */
	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the fecha de recarga.
	 *
	 * @return the fecha de recarga
	 */
	public String getFechaDeRecarga() {
		return fechaDeRecarga;
	}

	/**
	 * Sets the fecha de recarga.
	 *
	 * @param fechaDeRecarga
	 *            the new fecha de recarga
	 */
	public void setFechaDeRecarga(String fechaDeRecarga) {
		this.fechaDeRecarga = fechaDeRecarga;
	}

	/**
	 * Gets the es recarga programada.
	 *
	 * @return the es recarga programada
	 */
	public Boolean getEsRecargaProgramada() {
		return esRecargaProgramada;
	}

	/**
	 * Sets the es recarga programada.
	 *
	 * @param esRecargaProgramada
	 *            the new es recarga programada
	 */
	public void setEsRecargaProgramada(Boolean esRecargaProgramada) {
		this.esRecargaProgramada = esRecargaProgramada;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the fecha de prox recarga.
	 *
	 * @return the fecha de prox recarga
	 */
	public String getFechaDeProxRecarga() {
		return fechaDeProxRecarga;
	}

	/**
	 * Sets the fecha de prox recarga.
	 *
	 * @param fechaDeProxRecarga
	 *            the new fecha de prox recarga
	 */
	public void setFechaDeProxRecarga(String fechaDeProxRecarga) {
		this.fechaDeProxRecarga = fechaDeProxRecarga;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the new frecuencia
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * Gets the nro cuenta origen sin formato.
	 *
	 * @return the nro cuenta origen sin formato
	 */
	public String getNroCuentaOrigenSinFormato() {
		return nroCuentaOrigenSinFormato;
	}

	/**
	 * Sets the nro cuenta origen sin formato.
	 *
	 * @param nroCuentaOrigenSinFormato
	 *            the new nro cuenta origen sin formato
	 */
	public void setNroCuentaOrigenSinFormato(String nroCuentaOrigenSinFormato) {
		this.nroCuentaOrigenSinFormato = nroCuentaOrigenSinFormato;
	}

	/**
	 * Gets the nro orden firmante.
	 *
	 * @return the nro orden firmante
	 */
	public String getNroOrdenFirmante() {
		return nroOrdenFirmante;
	}

	/**
	 * Sets the nro orden firmante.
	 *
	 * @param nroOrdenFirmante
	 *            the new nro orden firmante
	 */
	public void setNroOrdenFirmante(String nroOrdenFirmante) {
		this.nroOrdenFirmante = nroOrdenFirmante;
	}

}