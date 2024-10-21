/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class ComprobanteVisaAmexIATXOutEntity.
 */
@Record
public class ComprobanteVisaAmexIATXOutEntity {
	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** Cantidad de registros. */
	@Field
	private Long cantidadPagos;

	/** The comprobantes. */
	@Segment(occursRef = "cantidadPagos")
	private List<ComprobanteVisaIATXEntity> comprobantes = new ArrayList<ComprobanteVisaIATXEntity>();

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
	 * Gets the cantidad pagos.
	 *
	 * @return the cantidad pagos
	 */
	public Long getCantidadPagos() {
		return cantidadPagos;
	}

	/**
	 * Sets the cantidad pagos.
	 *
	 * @param cantidadPagos
	 *            the new cantidad pagos
	 */
	public void setCantidadPagos(Long cantidadPagos) {
		this.cantidadPagos = cantidadPagos;
	}

	/**
	 * Gets the comprobantes.
	 *
	 * @return the comprobantes
	 */
	public List<ComprobanteVisaIATXEntity> getComprobantes() {
		return comprobantes;
	}

	/**
	 * Sets the comprobantes.
	 *
	 * @param comprobantes
	 *            the new comprobantes
	 */
	public void setComprobantes(List<ComprobanteVisaIATXEntity> comprobantes) {
		this.comprobantes = comprobantes;
	}

}
