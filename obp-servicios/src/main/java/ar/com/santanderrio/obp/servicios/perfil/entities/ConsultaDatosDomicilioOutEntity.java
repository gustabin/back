/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * ConsultaDatosDomicilioOutEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class ConsultaDatosDomicilioOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The cantidad repeticiones. */
	@Field
	private Long cantidadRepeticiones;

	/** The lista datos domicilio. */
	@Segment(occursRef = "cantidadRepeticiones")
	private List<DatosDomicilioEntity> listaDatosDomicilio;

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
	 * Gets the cantidad repeticiones.
	 *
	 * @return the cantidad repeticiones
	 */
	public Long getCantidadRepeticiones() {
		return cantidadRepeticiones;
	}

	/**
	 * Sets the cantidad repeticiones.
	 *
	 * @param cantidadRepeticiones
	 *            the new cantidad repeticiones
	 */
	public void setCantidadRepeticiones(Long cantidadRepeticiones) {
		this.cantidadRepeticiones = cantidadRepeticiones;
	}

	/**
	 * Gets the lista datos domicilio.
	 *
	 * @return the lista datos domicilio
	 */
	public List<DatosDomicilioEntity> getListaDatosDomicilio() {
		return listaDatosDomicilio;
	}

	/**
	 * Sets the lista datos domicilio.
	 *
	 * @param listaDatosDomicilio
	 *            the new lista datos domicilio
	 */
	public void setListaDatosDomicilio(List<DatosDomicilioEntity> listaDatosDomicilio) {
		this.listaDatosDomicilio = listaDatosDomicilio;
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

}
