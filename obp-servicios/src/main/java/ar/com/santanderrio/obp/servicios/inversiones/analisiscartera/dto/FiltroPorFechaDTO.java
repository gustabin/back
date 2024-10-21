/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ListaFiltradaPorFecha;

/**
 * The Class FiltroPorFechaDTO.
 */
public class FiltroPorFechaDTO {

	/** The periodos. */
	private List<ListaFiltradaPorFecha> periodos;

	
	/**
	 * Gets the periodos.
	 *
	 * @return the periodos
	 */
	public List<ListaFiltradaPorFecha> getPeriodos() {
		return periodos;
	}

	/**
	 * Sets the periodos.
	 *
	 * @param periodos
	 *            the new periodos
	 */
	public void setPeriodos(List<ListaFiltradaPorFecha> periodos) {
		this.periodos = periodos;
	}
	
	

}
