/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

/**
 * The Class PrestamoView.
 *
 * @author desa
 */
public class PrestamoView {

	/** The anio. */
	private String anio;

	/** The cuenta. */
	private String cuenta;

	/** The pecodofi. */
	private String pecodofi;

	/** The pecodpro. */
	private String pecodpro;

	/** The pecodsub. */
	private String pecodsub;

	/** The tipo producto. */
	private String tipoProducto;

	/** The divisa. */
	private String divisa;

	/** The destino fondos. */
	private String destinoFondos;

	/** The destino descripcion. */
	private String destinoDescripcion;

	/** The fecha formalizacion. */
	private String fechaFormalizacion;

	/** The situacion. */
	private String situacion;

	/** The saldo deuda. */
	private String saldoDeuda;

	/** The saldo vencido. */
	private String saldoVencido;

	/** The int devengado. */
	private String intDevengado;

	/** The int vencido. */
	private String intVencido;

	/** The int cobrado. */
	private String intCobrado;

	/** The plazo. */
	private String plazo;

	/** The firmantes. */
	private List<FirmantePrestamoView> firmantes;

	/**
	 * Gets the firmantes.
	 *
	 * @return the firmantes
	 */
	public List<FirmantePrestamoView> getFirmantes() {
		return firmantes;
	}

	/**
	 * Sets the firmantes.
	 *
	 * @param firmantes
	 *            the firmantes to set
	 */
	public void setFirmantes(List<FirmantePrestamoView> firmantes) {
		this.firmantes = firmantes;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the pecodofi.
	 *
	 * @return the pecodofi
	 */
	public String getPecodofi() {
		return pecodofi;
	}

	/**
	 * Sets the pecodofi.
	 *
	 * @param pecodofi
	 *            the pecodofi to set
	 */
	public void setPecodofi(String pecodofi) {
		this.pecodofi = pecodofi;
	}

	/**
	 * Gets the pecodpro.
	 *
	 * @return the pecodpro
	 */
	public String getPecodpro() {
		return pecodpro;
	}

	/**
	 * Sets the pecodpro.
	 *
	 * @param pecodpro
	 *            the pecodpro to set
	 */
	public void setPecodpro(String pecodpro) {
		this.pecodpro = pecodpro;
	}

	/**
	 * Gets the pecodsub.
	 *
	 * @return the pecodsub
	 */
	public String getPecodsub() {
		return pecodsub;
	}

	/**
	 * Sets the pecodsub.
	 *
	 * @param pecodsub
	 *            the pecodsub to set
	 */
	public void setPecodsub(String pecodsub) {
		this.pecodsub = pecodsub;
	}

	/**
	 * Gets the tipo producto.
	 *
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * Sets the tipo producto.
	 *
	 * @param tipoProducto
	 *            the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
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
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the destino fondos.
	 *
	 * @return the destinoFondos
	 */
	public String getDestinoFondos() {
		return destinoFondos;
	}

	/**
	 * Sets the destino fondos.
	 *
	 * @param destinoFondos
	 *            the destinoFondos to set
	 */
	public void setDestinoFondos(String destinoFondos) {
		this.destinoFondos = destinoFondos;
	}

	/**
	 * Gets the destino descripcion.
	 *
	 * @return the destinoDescripcion
	 */
	public String getDestinoDescripcion() {
		return destinoDescripcion;
	}

	/**
	 * Sets the destino descripcion.
	 *
	 * @param destinoDescripcion
	 *            the destinoDescripcion to set
	 */
	public void setDestinoDescripcion(String destinoDescripcion) {
		this.destinoDescripcion = destinoDescripcion;
	}

	/**
	 * Gets the fecha formalizacion.
	 *
	 * @return the fechaFormalizacion
	 */
	public String getFechaFormalizacion() {
		return fechaFormalizacion;
	}

	/**
	 * Sets the fecha formalizacion.
	 *
	 * @param fechaFormalizacion
	 *            the fechaFormalizacion to set
	 */
	public void setFechaFormalizacion(String fechaFormalizacion) {
		this.fechaFormalizacion = fechaFormalizacion;
	}

	/**
	 * Gets the situacion.
	 *
	 * @return the situacion
	 */
	public String getSituacion() {
		return situacion;
	}

	/**
	 * Sets the situacion.
	 *
	 * @param situacion
	 *            the situacion to set
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	/**
	 * Gets the saldo deuda.
	 *
	 * @return the saldoDeuda
	 */
	public String getSaldoDeuda() {
		return saldoDeuda;
	}

	/**
	 * Sets the saldo deuda.
	 *
	 * @param saldoDeuda
	 *            the saldoDeuda to set
	 */
	public void setSaldoDeuda(String saldoDeuda) {
		this.saldoDeuda = saldoDeuda;
	}

	/**
	 * Gets the saldo vencido.
	 *
	 * @return the saldoVencido
	 */
	public String getSaldoVencido() {
		return saldoVencido;
	}

	/**
	 * Sets the saldo vencido.
	 *
	 * @param saldoVencido
	 *            the saldoVencido to set
	 */
	public void setSaldoVencido(String saldoVencido) {
		this.saldoVencido = saldoVencido;
	}

	/**
	 * Gets the int devengado.
	 *
	 * @return the intDevengado
	 */
	public String getIntDevengado() {
		return intDevengado;
	}

	/**
	 * Sets the int devengado.
	 *
	 * @param intDevengado
	 *            the intDevengado to set
	 */
	public void setIntDevengado(String intDevengado) {
		this.intDevengado = intDevengado;
	}

	/**
	 * Gets the int vencido.
	 *
	 * @return the intVencido
	 */
	public String getIntVencido() {
		return intVencido;
	}

	/**
	 * Sets the int vencido.
	 *
	 * @param intVencido
	 *            the intVencido to set
	 */
	public void setIntVencido(String intVencido) {
		this.intVencido = intVencido;
	}

	/**
	 * Gets the int cobrado.
	 *
	 * @return the intCobrado
	 */
	public String getIntCobrado() {
		return intCobrado;
	}

	/**
	 * Sets the int cobrado.
	 *
	 * @param intCobrado
	 *            the intCobrado to set
	 */
	public void setIntCobrado(String intCobrado) {
		this.intCobrado = intCobrado;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

}
