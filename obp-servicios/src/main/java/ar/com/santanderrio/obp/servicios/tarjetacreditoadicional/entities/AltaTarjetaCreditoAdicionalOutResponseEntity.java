/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class AltaTarjetaCreditoAdicionalOutResponseEntity.
 */
@Record
public class AltaTarjetaCreditoAdicionalOutResponseEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The nro solicitud. */
	@Field
	private String nroSolicitud;

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
