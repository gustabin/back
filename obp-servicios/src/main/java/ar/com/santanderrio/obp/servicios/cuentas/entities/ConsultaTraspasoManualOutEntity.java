/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import javax.validation.constraints.Pattern;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class DetalleCuenta.
 */
@Record
public class ConsultaTraspasoManualOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The NIO. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{24}|[ ]{24}$")
	private String nio;

	/** The Centro Origen. */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|[ ]{4}$")
	private String centroOrigen;

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the nio.
	 *
	 * @return the nio
	 */
	public String getNio() {
		return nio;
	}

	/**
	 * Sets the nio.
	 *
	 * @param nio
	 *            the nio to set
	 */
	public void setNio(String nio) {
		this.nio = nio;
	}

	/**
	 * Gets the centro origen.
	 *
	 * @return the centroOrigen
	 */
	public String getCentroOrigen() {
		return centroOrigen;
	}

	/**
	 * Sets the centro origen.
	 *
	 * @param centroOrigen
	 *            the centroOrigen to set
	 */
	public void setCentroOrigen(String centroOrigen) {
		this.centroOrigen = centroOrigen;
	}

}
