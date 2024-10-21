/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto response, engloba una lista con los detalles de la frecuencia de cobro
 * de intereses.
 * 
 * @author B039920
 *
 */
public class DetalleCobroInteresesViewResponse {

	/** The lista detalles. */
	private List<ItemDetalleIntereses> listaDetalles = new ArrayList<ItemDetalleIntereses>();

	/**
	 * Gets the lista detalles.
	 *
	 * @return the lista detalles
	 */
	public List<ItemDetalleIntereses> getListaDetalles() {
		return listaDetalles;
	}

	/**
	 * Sets the lista detalles.
	 *
	 * @param listaDetalles
	 *            the new lista detalles
	 */
	public void setListaDetalles(List<ItemDetalleIntereses> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}
}
