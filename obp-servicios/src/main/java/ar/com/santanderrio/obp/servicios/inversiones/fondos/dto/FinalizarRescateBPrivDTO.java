/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * The Class FinalizarRescateBPrivDTO.
 */
public class FinalizarRescateBPrivDTO extends BaseFondoAbstractDTO {

	/** The nro orden. */
	private String nroOrden;

	/** The nro certificado. */
	private String nroCertificado;

	/** The cuota partes. */
	private String cuotaPartes;

	/** The importe. */
	private String importe;

	/** The disclaimer. */
	private String disclaimer;

	/** The mensaje suscripcion. */
	private String mensajeSuscripcion;

	/**
	 * Gets the nro orden.
	 *
	 * @return the nroOrden
	 */
	public String getNroOrden() {
		return nroOrden;
	}

	/**
	 * Sets the nro orden.
	 *
	 * @param nroOrden
	 *            the nroOrden to set
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}

	/**
	 * Gets the nro certificado.
	 *
	 * @return the nroCertificado
	 */
	public String getNroCertificado() {
		return nroCertificado;
	}

	/**
	 * Sets the nro certificado.
	 *
	 * @param nroCertificado
	 *            the nroCertificado to set
	 */
	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	/**
	 * Gets the cuota partes.
	 *
	 * @return the cuotaPartes
	 */
	public String getCuotaPartes() {
		return cuotaPartes;
	}

	/**
	 * Sets the cuota partes.
	 *
	 * @param cuotaPartes
	 *            the cuotaPartes to set
	 */
	public void setCuotaPartes(String cuotaPartes) {
		this.cuotaPartes = cuotaPartes;
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
	 *            the disclaimer to set
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
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
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

}
