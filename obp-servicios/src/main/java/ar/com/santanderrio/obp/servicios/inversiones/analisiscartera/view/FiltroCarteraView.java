/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.List;

/**
 * The Class FiltroCarteraView.
 */
public class FiltroCarteraView {
	
	/** The multiseleccion. */
	private Boolean multiseleccion;
	
	/** The resultado cartera cliente. */
	private List<ResultadoCarteraCliente> resultadoCarteraCliente;

	/**
	 * Gets the multiseleccion.
	 *
	 * @return the multiseleccion
	 */
	public Boolean getMultiseleccion() {
		return multiseleccion;
	}

	/**
	 * Sets the multiseleccion.
	 *
	 * @param multiseleccion
	 *            the new multiseleccion
	 */
	public void setMultiseleccion(Boolean multiseleccion) {
		this.multiseleccion = multiseleccion;
	}

	/**
	 * Gets the resultado cartera cliente.
	 *
	 * @return the resultado cartera cliente
	 */
	public List<ResultadoCarteraCliente> getResultadoCarteraCliente() {
		return resultadoCarteraCliente;
	}

	/**
	 * Sets the resultado cartera cliente.
	 *
	 * @param resultadoCarteraCliente
	 *            the new resultado cartera cliente
	 */
	public void setResultadoCarteraCliente(List<ResultadoCarteraCliente> resultadoCarteraCliente) {
		this.resultadoCarteraCliente = resultadoCarteraCliente;
	}

}
