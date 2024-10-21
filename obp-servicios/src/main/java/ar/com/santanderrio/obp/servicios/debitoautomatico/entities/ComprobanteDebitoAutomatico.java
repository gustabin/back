/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.entities;


import com.sun.istack.NotNull;

/**
 * The Class ComprobanteDebitoAutomatico.
 *
 * @author marcelo.ruiz
 */

public class ComprobanteDebitoAutomatico {

	private String comprobante;
	 
	/** The empresa. */
	private String empresa;

	/** The numero medio de pago. */
	private String numeroMedioDePago;

	/** The nombre medio de pago. */
	private String nombreMedioDePago;

	/** The pes identificacion. */
	private String pesIdentificacion;

	/** The codigo pago electronico. */
	private String codigoPagoElectronico;

	/** The limite. */
	@NotNull
	private String limite;

	/** The nro de comprobante. */
	private String nroDeComprobante;

	/** The fecha hora. */
	private String fechaHora;

	private String labelFantasia;

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the new empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the numero medio de pago.
	 *
	 * @return the numero medio de pago
	 */
	public String getNumeroMedioDePago() {
		return numeroMedioDePago;
	}

	/**
	 * Sets the numero medio de pago.
	 *
	 * @param numeroMedioDePago
	 *            the new numero medio de pago
	 */
	public void setNumeroMedioDePago(String numeroMedioDePago) {
		this.numeroMedioDePago = numeroMedioDePago;
	}

	/**
	 * Gets the nombre medio de pago.
	 *
	 * @return the nombre medio de pago
	 */
	public String getNombreMedioDePago() {
		return nombreMedioDePago;
	}

	/**
	 * Sets the nombre medio de pago.
	 *
	 * @param nombreMedioDePago
	 *            the new nombre medio de pago
	 */
	public void setNombreMedioDePago(String nombreMedioDePago) {
		this.nombreMedioDePago = nombreMedioDePago;
	}

	/**
	 * Gets the pes identificacion.
	 *
	 * @return the pes identificacion
	 */
	public String getPesIdentificacion() {
		return pesIdentificacion;
	}

	/**
	 * Sets the pes identificacion.
	 *
	 * @param pesIdentificacion
	 *            the new pes identificacion
	 */
	public void setPesIdentificacion(String pesIdentificacion) {
		this.pesIdentificacion = pesIdentificacion;
	}

	/**
	 * Gets the codigo pago electronico.
	 *
	 * @return the codigo pago electronico
	 */
	public String getCodigoPagoElectronico() {
		return codigoPagoElectronico;
	}

	/**
	 * Sets the codigo pago electronico.
	 *
	 * @param codigoPagoElectronico
	 *            the new codigo pago electronico
	 */
	public void setCodigoPagoElectronico(String codigoPagoElectronico) {
		this.codigoPagoElectronico = codigoPagoElectronico;
	}

	/**
	 * Gets the limite.
	 *
	 * @return the limite
	 */
	public String getLimite() {
		return limite;
	}

	/**
	 * Sets the limite.
	 *
	 * @param limite
	 *            the new limite
	 */
	public void setLimite(String limite) {
		this.limite = limite;
	}

	/**
	 * Gets the nro de comprobante.
	 *
	 * @return the nro de comprobante
	 */
	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	/**
	 * Sets the nro de comprobante.
	 *
	 * @param nroDeComprobante
	 *            the new nro de comprobante
	 */
	public void setNroDeComprobante(String nroDeComprobante) {
		this.nroDeComprobante = nroDeComprobante;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	
	public String getLabelFantasia() {
		return labelFantasia;
	}

	public void setLabelFantasia(String labelFantasia) {
		this.labelFantasia = labelFantasia;
	}
}