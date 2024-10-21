/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ItemError;

/**
 * The Class DatosRespuestaFiltroPorFecha.
 */
public class DatosRespuestaFiltroPorFecha {

	/** lista de errores*. */
	@JsonProperty("ListaErrores")
	private List<ItemError> listaErrores;

	/** The resultado . */
	@JsonProperty("Resultado")
	private List<ResultadoFiltroPorFechaEntity> resultado;

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
	public List<ResultadoFiltroPorFechaEntity> getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(List<ResultadoFiltroPorFechaEntity> resultado) {
		this.resultado = resultado;
	}
	
}
