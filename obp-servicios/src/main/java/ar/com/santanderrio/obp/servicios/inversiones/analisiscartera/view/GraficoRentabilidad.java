/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class GraficoRentabilidad.
 */
public class GraficoRentabilidad {
	
	/** The periodo. */
	private String periodo;
	
	/** The datos. */
	List<DatosGraficoRentabilidadView> datos = new ArrayList<DatosGraficoRentabilidadView>();

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo
	 *            the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}



	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public List<DatosGraficoRentabilidadView> getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(List<DatosGraficoRentabilidadView> datos) {
		this.datos = datos;
	}

}
