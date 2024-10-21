/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Class SimularLicitacionViewResponse.
 */
public class SimularLicitacionViewResponse {

	/** The importe debitar. */
	private String importeDebitar;

	/** The fecha debito cuenta. */
	private String fechaDebitoCuenta;

	/** The comisiones. */
	private String comisiones;

	/** The impuestos. */
	private String impuestos;

	/** The cabecera. */
	private String cabecera;

	/** The pie. */
	private String pie;

	/** The numero orden. */
	private String numeroOrden;

	/** The legal. */
	private String legal;

	/** The legal canal. */
	private String legalCanal;
	
	/**
	 * The Disclaimer.
	 */
	private String Disclaimer;

	/** The tipo precio. */
	private String tipoPrecio;

	/** The archivo condiciones. */
	private ReporteView archivoCondiciones;

	/**
	 * Gets the importe debitar.
	 *
	 * @return the importe debitar
	 */
	public String getImporteDebitar() {
		return importeDebitar;
	}

	/**
	 * Sets the importe debitar.
	 *
	 * @param importeDebitar
	 *            the new importe debitar
	 */
	public void setImporteDebitar(String importeDebitar) {
		this.importeDebitar = importeDebitar;
	}

	/**
	 * Gets the fecha debito cuenta.
	 *
	 * @return the fecha debito cuenta
	 */
	public String getFechaDebitoCuenta() {
		return fechaDebitoCuenta;
	}

	/**
	 * Gets the comisiones.
	 *
	 * @return the comisiones
	 */
	public String getComisiones() {
		return comisiones;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the fecha debito cuenta.
	 *
	 * @param fechaDebitoCuenta
	 *            the new fecha debito cuenta
	 */
	public void setFechaDebitoCuenta(String fechaDebitoCuenta) {
		this.fechaDebitoCuenta = fechaDebitoCuenta;
	}

	/**
	 * Sets the comisiones.
	 *
	 * @param comisiones
	 *            the new comisiones
	 */
	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}

	/**
	 * Gets the pie.
	 *
	 * @return the pie
	 */
	public String getPie() {
		return pie;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Sets the pie.
	 *
	 * @param pie
	 *            the new pie
	 */
	public void setPie(String pie) {
		this.pie = pie;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Gets the legal canal.
	 *
	 * @return the legal canal
	 */
	public String getLegalCanal() {
		return legalCanal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Sets the legal canal.
	 *
	 * @param legalCanal
	 *            the new legal canal
	 */
	public void setLegalCanal(String legalCanal) {
		this.legalCanal = legalCanal;
	}

	/**
	 * Gets the archivo condiciones.
	 *
	 * @return the archivo condiciones
	 */
	public ReporteView getArchivoCondiciones() {
		return archivoCondiciones;
	}

	/**
	 * Sets the archivo condiciones.
	 *
	 * @param archivoCondiciones
	 *            the new archivo condiciones
	 */
	public void setArchivoCondiciones(ReporteView archivoCondiciones) {
		this.archivoCondiciones = archivoCondiciones;
	}

	/**
	 * Gets the disclaimer.
	 *
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return Disclaimer;
	}

	/**
	 * Sets the disclaimer.
	 *
	 * @param disclaimer
	 *            the new disclaimer
	 */
	public void setDisclaimer(String disclaimer) {
		Disclaimer = disclaimer;
	}

	/**
	 * Gets the tipo precio.
	 *
	 * @return the tipoPrecio
	 */
	public String getTipoPrecio() {
		return tipoPrecio;
	}

	/**
	 * Sets the tipo precio.
	 *
	 * @param tipoPrecio
	 *            the tipoPrecio to set
	 */
	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}
	
	
}
