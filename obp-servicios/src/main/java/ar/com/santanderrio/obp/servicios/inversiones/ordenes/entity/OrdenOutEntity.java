/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class OrdenOutEntity.
 */
public class OrdenOutEntity {

	/** The respuesta. */
	private List<Orden> ordenes = new ArrayList<Orden>();

	/**
	 * Gets the ordenes.
	 *
	 * @return the ordenes
	 */
	public List<Orden> getOrdenes() {
		return ordenes;
	}

	/**
	 * Sets the ordenes.
	 *
	 * @param ordenes
	 *            the new ordenes
	 */
	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}

}
