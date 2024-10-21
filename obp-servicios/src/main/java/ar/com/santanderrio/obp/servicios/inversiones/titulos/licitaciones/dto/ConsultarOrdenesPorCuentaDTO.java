/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class ConsultarOrdenesPorCuentaDTO.
 */
public class ConsultarOrdenesPorCuentaDTO {

	/** The cuenta titulo. */
	private String cuentaTitulo;

	/** The intervinientes. */
	private List<Interviniente> intervinientes = null;

	/** The ordenes. */
	List<ConsultarOrdenesDTO> ordenes;

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the ordenes.
	 *
	 * @return the ordenes
	 */
	public List<ConsultarOrdenesDTO> getOrdenes() {
		return ordenes;
	}

	/**
	 * Sets the ordenes.
	 *
	 * @param list
	 *            the new ordenes
	 */
	public void setOrdenes(List<ConsultarOrdenesDTO> list) {
		this.ordenes = list;
	}

	/**
	 * Gets the intervinientes.
	 *
	 * @return the intervinientes
	 */
	public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	/**
	 * Sets the intervinientes.
	 *
	 * @param intervinientes
	 *            the new intervinientes
	 */
	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

}
