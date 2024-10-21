/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class PrestamosCanceladosOutEntity.
 */
@Record
public class PrestamosCanceladosOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The cantidad ocurrencias. */
	@Field
	private Long cantidadOcurrencias;

	/** The lista result. */
	@Segment(occursRef = "cantidadOcurrencias")
	private List<PrestamoCanceladoOutEntity> listaResult = new ArrayList<PrestamoCanceladoOutEntity>();

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
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidad ocurrencias
	 */
	public Long getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the new cantidad ocurrencias
	 */
	public void setCantidadOcurrencias(Long cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the lista result.
	 *
	 * @return the lista result
	 */
	public List<PrestamoCanceladoOutEntity> getListaResult() {
		return listaResult;
	}

	/**
	 * Sets the lista result.
	 *
	 * @param listaResult
	 *            the new lista result
	 */
	public void setListaResult(List<PrestamoCanceladoOutEntity> listaResult) {
		this.listaResult = listaResult;
	}

}
