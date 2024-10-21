/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCanalTramoResponse;

/**
 * The Class ListasPliegosFiltradasBPriv.
 */
public class ListasPliegosFiltradasBPriv {

	/** The list pliegos. */
	List<DatosObtenerCanalTramoResponse> listPliegos = new ArrayList<DatosObtenerCanalTramoResponse>();
	
	/** The list pliegos custodia. */
	List<DatosObtenerCanalTramoResponse> listPliegosCustodia = new ArrayList<DatosObtenerCanalTramoResponse>();

	/**
	 * Gets the list pliegos.
	 *
	 * @return the listPliegos
	 */
	public List<DatosObtenerCanalTramoResponse> getListPliegos() {
		return listPliegos;
	}

	/**
	 * Sets the list pliegos.
	 *
	 * @param listPliegos
	 *            the listPliegos to set
	 */
	public void setListPliegos(List<DatosObtenerCanalTramoResponse> listPliegos) {
		this.listPliegos = listPliegos;
	}

	/**
	 * Gets the list pliegos custodia.
	 *
	 * @return the listPliegosCustodia
	 */
	public List<DatosObtenerCanalTramoResponse> getListPliegosCustodia() {
		return listPliegosCustodia;
	}

	/**
	 * Sets the list pliegos custodia.
	 *
	 * @param listPliegosCustodia
	 *            the listPliegosCustodia to set
	 */
	public void setListPliegosCustodia(List<DatosObtenerCanalTramoResponse> listPliegosCustodia) {
		this.listPliegosCustodia = listPliegosCustodia;
	}
}
