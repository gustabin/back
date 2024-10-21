/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class AccionesAlVencimientoOutView.
 */
public class AccionesAlVencimientoOutView {

	/** The lista acciones. */
	private List<AccionAlVencimientoView> listaAcciones = new ArrayList<AccionAlVencimientoView>();

	/**
	 * Gets the lista acciones.
	 *
	 * @return the lista acciones
	 */
	public List<AccionAlVencimientoView> getListaAcciones() {
		return listaAcciones;
	}

	/**
	 * Sets the lista acciones.
	 *
	 * @param listaAcciones
	 *            the new lista acciones
	 */
	public void setListaAcciones(List<AccionAlVencimientoView> listaAcciones) {
		this.listaAcciones = listaAcciones;
	}

}
