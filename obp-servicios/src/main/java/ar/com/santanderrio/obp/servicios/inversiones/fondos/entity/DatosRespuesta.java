/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosRespuesta.
 */
public class DatosRespuesta {

	/** lista de errores*. */
	@JsonProperty("ListaErrores")
	private List<ItemError> listaErrores;

	/** The resultado . */
	@JsonProperty("Resultado")
	private List<ResultadoTenenciaValuadaDetalleFondoOL> resultado;

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

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public List<ResultadoTenenciaValuadaDetalleFondoOL> getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the resultado to set
	 */
	public void setResultado(List<ResultadoTenenciaValuadaDetalleFondoOL> resultado) {
		this.resultado = resultado;
	}
}
