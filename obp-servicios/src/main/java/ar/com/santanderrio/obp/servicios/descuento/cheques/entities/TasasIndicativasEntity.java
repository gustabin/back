/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class TasasIndicativasEntity.
 */
@Record
public class TasasIndicativasEntity {
    /** The header trama. */
    @Field
    private String headerTrama;

    /** Codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;
    
	/** Cantidad de registros. */
	@Field
	private Long cantidadRegistros;
	
	/** The fondos. */
	@Segment(occursRef = "cantidadRegistros")
	private List<TasaIndicativa> tasaIndicativa = new ArrayList<TasaIndicativa>();

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
	 * Gets the tasa indicativa.
	 *
	 * @return the tasa indicativa
	 */
	public List<TasaIndicativa> getTasaIndicativa() {
		return tasaIndicativa;
	}

	/**
	 * Sets the tasa indicativa.
	 *
	 * @param tasaIndicativa
	 *            the new tasa indicativa
	 */
	public void setTasaIndicativa(List<TasaIndicativa> tasaIndicativa) {
		this.tasaIndicativa = tasaIndicativa;
	}
	
	
}
