/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * DTO de salida el BO para suscripcion de fondo.
 *
 * @author marcelo.ruiz
 */
public class SuscripcionOutDTO extends BaseFondoAbstractDTO {

	/** The nro orden. */
	private String nroOrden;

	/** The nro certificado. */
	private String nroCertificado;

	/** The cuota partes. */
	private String cuotaPartes;

	/** Importe a suscribir. */
	private String importe;

	/** The disclaimer. */
	private String disclaimer;

	/** The retorno. */
	private String retorno;

	/** The codigo retorno. */
	private String codigoRetorno;

	/** The descripcion. */
	private String descripcion;

	/** The nombre fondo. */
	private String nombreFondo;

	/** The mensaje suscripcion. */
	private String mensajeSuscripcion;

	/**
	 * Gets the nro orden.
	 *
	 * @return the nro orden
	 */
	public String getNroOrden() {
		return nroOrden;
	}

	/**
	 * Sets the nro orden.
	 *
	 * @param nroOrden
	 *            the new nro orden
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}

	/**
	 * Gets the nro certificado.
	 *
	 * @return the nro certificado
	 */
	public String getNroCertificado() {
		return nroCertificado;
	}

	/**
	 * Sets the nro certificado.
	 *
	 * @param nroCertificado
	 *            the new nro certificado
	 */
	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	/**
	 * Gets the cuota partes.
	 *
	 * @return the cuota partes
	 */
	public String getCuotaPartes() {
		return cuotaPartes;
	}

	/**
	 * Sets the cuota partes.
	 *
	 * @param cuotaPartes
	 *            the new cuota partes
	 */
	public void setCuotaPartes(String cuotaPartes) {
		this.cuotaPartes = cuotaPartes;
	}

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
	 * Gets the disclaimer.
	 *
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return disclaimer;
	}

	/**
	 * Sets the disclaimer.
	 *
	 * @param disclaimer
	 *            the new disclaimer
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	/**
	 * Gets the retorno.
	 *
	 * @return the retorno
	 */
	public String getRetorno() {
		return retorno;
	}

	/**
	 * Sets the retorno.
	 *
	 * @param retorno
	 *            the new retorno
	 */
	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	/**
	 * Gets the codigo retorno.
	 *
	 * @return the codigo retorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Sets the codigo retorno.
	 *
	 * @param codigoRetorno
	 *            the new codigo retorno
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the mensaje suscripcion.
	 *
	 * @return the mensaje suscripcion
	 */
	public String getMensajeSuscripcion() {
		return mensajeSuscripcion;
	}

	/**
	 * Sets the mensaje suscripcion.
	 *
	 * @param mensajeSuscripcion
	 *            the new mensaje suscripcion
	 */
	public void setMensajeSuscripcion(String mensajeSuscripcion) {
		this.mensajeSuscripcion = mensajeSuscripcion;
	}

	/**
	 * Gets the nombre fondo.
	 *
	 * @return the nombre fondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the new nombre fondo
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

}
