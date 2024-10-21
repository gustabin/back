/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class FiltroPorFechaView.
 */
public class FiltroPorFechaView {
	
	/** The periodos. */
	private List<ListaFiltradaPorFecha> periodos = new ArrayList<ListaFiltradaPorFecha>();

	
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
