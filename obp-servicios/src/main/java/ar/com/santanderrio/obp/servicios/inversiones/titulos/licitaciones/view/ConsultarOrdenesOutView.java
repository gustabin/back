/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesDTO;

/**
 * The Class ConsultarOrdenesOutView.
 *
 * @author juan.pablo.picate
 */
public class ConsultarOrdenesOutView {

	/** The list. */
	List<ConsultarOrdenesDTO> list;

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<ConsultarOrdenesDTO> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list
	 *            the new list
	 */
	public void setList(List<ConsultarOrdenesDTO> list) {
		this.list = list;
	}
}
