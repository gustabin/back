/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * ConsultaDatosTelefonoOutEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class ConsultaDatosTelefonoOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido handler. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendidoHandler;

	/** The cantidad repeticiones. */
	@Field
	private Long cantidadRepeticiones;

	/** The lista telefonos. */
	@Segment(occursRef = "cantidadRepeticiones")
	private List<DatosTelefonoEntity> listaTelefonos;

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
	 * Gets the lista telefonos.
	 *
	 * @return the lista telefonos
	 */
	public List<DatosTelefonoEntity> getListaTelefonos() {
		return listaTelefonos;
	}

	/**
	 * Sets the lista telefonos.
	 *
	 * @param listaTelefonos
	 *            the new lista telefonos
	 */
	public void setListaTelefonos(List<DatosTelefonoEntity> listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
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
	 * Gets the codigo retorno extendido handler.
	 *
	 * @return the codigo retorno extendido handler
	 */
	public String getCodigoRetornoExtendidoHandler() {
		return codigoRetornoExtendidoHandler;
	}

	/**
	 * Sets the codigo retorno extendido handler.
	 *
	 * @param codigoRetornoExtendidoHandler
	 *            the new codigo retorno extendido handler
	 */
	public void setCodigoRetornoExtendidoHandler(String codigoRetornoExtendidoHandler) {
		this.codigoRetornoExtendidoHandler = codigoRetornoExtendidoHandler;
	}

}
