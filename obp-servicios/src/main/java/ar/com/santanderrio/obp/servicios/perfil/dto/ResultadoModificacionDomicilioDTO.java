/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dto;

import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioOutEntity;

/**
 * DatosModificacionDomicilioDTO.
 *
 * @author Silvina_Luque
 */
public class ResultadoModificacionDomicilioDTO {

	/** The nro comprobante. */
	private String nroComprobante;

	/** The fecha transaccion. */
	private String fechaTransaccion;

	/** The numero solicitud. */
	private String numeroSolicitud;

	/**
	 * Gets the numero solicitud.
	 *
	 * @return the numero solicitud
	 */
	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}

	/**
	 * Sets the numero solicitud.
	 *
	 * @param numeroSolicitud
	 *            the new numero solicitud
	 */
	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the fecha transaccion.
	 *
	 * @return the fecha transaccion
	 */
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}

	/**
	 * Sets the fecha transaccion.
	 *
	 * @param fechaTransaccion
	 *            the new fecha transaccion
	 */
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	/**
	 * Instantiates a new resultado modificacion domicilio DTO.
	 */
	public ResultadoModificacionDomicilioDTO() {

	}

	/**
	 * Instantiates a new resultado modificacion domicilio DTO.
	 *
	 * @param cambioDomicilioOutEntity
	 *            the cambio domicilio out entity
	 */
	public ResultadoModificacionDomicilioDTO(CambioDomicilioOutEntity cambioDomicilioOutEntity) {
		nroComprobante = cambioDomicilioOutEntity.getNroComprobante();
		fechaTransaccion = cambioDomicilioOutEntity.getFechaTransaccion();
	}

}
