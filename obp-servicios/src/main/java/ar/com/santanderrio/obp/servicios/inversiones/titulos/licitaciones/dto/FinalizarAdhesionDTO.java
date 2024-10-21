/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class FinalizarAdhesionDTO.
 */
public class FinalizarAdhesionDTO {
	
	/** The map form campos. */
	private Map<String, String> mapFormCampos = new HashMap<String, String>();
	
	/** The legal. */
	private String legal;

	/**
	 * Gets the map form campos.
	 *
	 * @return the map form campos
	 */
	public Map<String, String> getMapFormCampos() {
		return mapFormCampos;
	}

	/**
	 * Sets the map form campos.
	 *
	 * @param mapFormCampos
	 *            the map form campos
	 */
	public void setMapFormCampos(Map<String, String> mapFormCampos) {
		this.mapFormCampos = mapFormCampos;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}
}
