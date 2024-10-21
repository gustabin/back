/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * CambioDomicilioOutEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class CambioDomicilioOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The nro comprobante. */
	@Field
	private String nroComprobante;

	/** The trama. */
	@Field
	private String trama;

	/** The fecha transaccion. */
	@Field
	private String fechaTransaccion;

	/** The nro solicitud. */
	@Field
	private String nroSolicitud;

	/**
	 * Gets the trama.
	 *
	 * @return the trama
	 */
	public String getTrama() {
		return trama;
	}

	/**
	 * Sets the trama.
	 *
	 * @param trama
	 *            the new trama
	 */
	public void setTrama(String trama) {
		this.trama = trama;
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
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the nro solicitud.
	 *
	 * @return the nro solicitud
	 */
	public String getNroSolicitud() {
		return nroSolicitud;
	}

	/**
	 * Sets the nro solicitud.
	 *
	 * @param nroSolicitud
	 *            the new nro solicitud
	 */
	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}

}
