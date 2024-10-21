/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResultadoCarteraCliente;

/**
 * The Class FiltroCarteraDTO.
 */
public class FiltroCarteraDTO {

	/** The multiseleccion. */
	private Boolean multiseleccion = Boolean.FALSE;
	
	/** The resultado cartera cliente. */
	private List<ResultadoCarteraCliente> resultadoCarteraCliente = new ArrayList<ResultadoCarteraCliente>();

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
