/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosRespuestaTitulos.
 */
public class DatosRespuestaTitulos {

	/** lista tenencia cuenta tit moneda*. */
	@JsonProperty("Resultado")
	List<ListaTenenciaCuentaTitMoneda> resultado;

	/** lista de errores*. */
	@JsonProperty("ListaErrores")
	private List<ItemError> listaErrores;

	
	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public List<ListaTenenciaCuentaTitMoneda> getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(List<ListaTenenciaCuentaTitMoneda> resultado) {
		this.resultado = resultado;
	}

	/**
	 * Gets the lista errores.
	 *
	 * @return the lista errores
	 */
	public List<ItemError> getListaErrores() {
		return listaErrores;
	}

	/**
	 * Sets the lista errores.
	 *
	 * @param listaErrores
	 *            the new lista errores
	 */
	public void setListaErrores(List<ItemError> listaErrores) {
		this.listaErrores = listaErrores;
	}

}
