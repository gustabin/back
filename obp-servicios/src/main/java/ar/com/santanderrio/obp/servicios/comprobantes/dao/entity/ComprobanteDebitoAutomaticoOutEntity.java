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
 * The Class ComprobanteDebitoAutomaticoOutEntity.
 */
@Record
public class ComprobanteDebitoAutomaticoOutEntity {
	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The indicador consulta debito DDI. */
	@Field
	private String indicadorConsultaDebitoDDI;

	/** The cantidad registros. */
	@Field
	private Long cantidadRegistros;

	/** The destinatarios. */
	@Segment(occursRef = "cantidadRegistros")
	private List<ComprobanteDebitoAutomatico> comprobantes = new ArrayList<ComprobanteDebitoAutomatico>();

	/**
	 * DDI_RELLAMADO.
	 */
	private static final String DDI_RELLAMADO = "200200";

	/**
	 * Indica si hay error de rellamada.
	 */
	private Boolean errorRellamada = Boolean.FALSE;

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
	 * Gets the indicador consulta debito DDI.
	 *
	 * @return the indicador consulta debito DDI
	 */
	public String getIndicadorConsultaDebitoDDI() {
		return indicadorConsultaDebitoDDI;
	}

	/**
	 * Sets the indicador consulta debito DDI.
	 *
	 * @param indicadorConsultaDebitoDDI
	 *            the new indicador consulta debito DDI
	 */
	public void setIndicadorConsultaDebitoDDI(String indicadorConsultaDebitoDDI) {
		this.indicadorConsultaDebitoDDI = indicadorConsultaDebitoDDI;
	}

	/**
	 * Gets the cantidad registros.
	 *
	 * @return the cantidad registros
	 */
	public Long getCantidadRegistros() {
		return cantidadRegistros;
	}

	/**
	 * Sets the cantidad registros.
	 *
	 * @param cantidadRegistros
	 *            the new cantidad registros
	 */
	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	/**
	 * Gets the comprobantes.
	 *
	 * @return the comprobantes
	 */
	public List<ComprobanteDebitoAutomatico> getComprobantes() {
		return comprobantes;
	}

	/**
	 * Sets the comprobantes.
	 *
	 * @param comprobantes
	 *            the new comprobantes
	 */
	public void setComprobantes(List<ComprobanteDebitoAutomatico> comprobantes) {
		this.comprobantes = comprobantes;
	}

	/**
	 * Retorna true si hay mas registros.
	 *
	 * @return the hay mas registros
	 */
	public Boolean getHayMasRegistros() {
		return DDI_RELLAMADO.equals(this.getIndicadorConsultaDebitoDDI());
	}

	/**
	 * Gets the error rellamada.
	 *
	 * @return the errorRellamada
	 */
	public Boolean getErrorRellamada() {
		return errorRellamada;
	}

	/**
	 * Sets the error rellamada.
	 *
	 * @param errorRellamada
	 *            the errorRellamada to set
	 */
	public void setErrorRellamada(Boolean errorRellamada) {
		this.errorRellamada = errorRellamada;
	}

}
