/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class TenenciaValuadaCarteraListaResultadosEntity.
 */
public class TenenciaValuadaCarteraListaResultadosEntity {

	/** The codigo producto. */
	@JsonProperty("CodigoProducto")
	private String codigoProducto;

	/** The Resultado. */
	@JsonProperty("Resultado")
	private int Resultado;

	/** The Segmento. */
	@JsonProperty("Segmento")
	private String Segmento;

	
	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the new codigo producto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public int getResultado() {
		return Resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(int resultado) {
		Resultado = resultado;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return Segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the new segmento
	 */
	public void setSegmento(String segmento) {
		Segmento = segmento;
	}
	
	
}
