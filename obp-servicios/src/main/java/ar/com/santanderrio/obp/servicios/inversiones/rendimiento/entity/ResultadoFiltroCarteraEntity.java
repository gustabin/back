/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ResultadoFiltroCarteraEntity.
 */
public class ResultadoFiltroCarteraEntity {

	/** The multiseleccion. */
	@JsonProperty("Multiseleccion")
	private String multiseleccion;
	
	/** The resultado cartera cliente. */
	@JsonProperty("ResultadoCarteraCliente")
	private List<CarteraAConsultar> resultadoCarteraCliente;

	/**
	 * Gets the multiseleccion.
	 *
	 * @return the multiseleccion
	 */
	public String getMultiseleccion() {
		return multiseleccion;
	}

	/**
	 * Sets the multiseleccion.
	 *
	 * @param multiseleccion
	 *            the new multiseleccion
	 */
	public void setMultiseleccion(String multiseleccion) {
		this.multiseleccion = multiseleccion;
	}

	/**
	 * Gets the resultado cartera cliente.
	 *
	 * @return the resultado cartera cliente
	 */
	public List<CarteraAConsultar> getResultadoCarteraCliente() {
		return resultadoCarteraCliente;
	}

	/**
	 * Sets the resultado cartera cliente.
	 *
	 * @param resultadoCarteraCliente
	 *            the new resultado cartera cliente
	 */
	public void setResultadoCarteraCliente(List<CarteraAConsultar> resultadoCarteraCliente) {
		this.resultadoCarteraCliente = resultadoCarteraCliente;
	}
	
	
	
}
