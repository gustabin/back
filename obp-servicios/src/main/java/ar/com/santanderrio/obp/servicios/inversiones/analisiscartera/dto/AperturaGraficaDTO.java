/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaResultado;

/**
 * The Class AperturaGraficaDTO.
 */
public class AperturaGraficaDTO {

	/** The lista resultado. */
	private List<AperturaGraficaResultado> listaResultado;

	
	/**
	 * Gets the lista resultado.
	 *
	 * @return the lista resultado
	 */
	public List<AperturaGraficaResultado> getListaResultado() {
		return listaResultado;
	}

	/**
	 * Sets the lista resultado.
	 *
	 * @param listaResultado
	 *            the new lista resultado
	 */
	public void setListaResultado(List<AperturaGraficaResultado> listaResultado) {
		this.listaResultado = listaResultado;
	}
	
}
