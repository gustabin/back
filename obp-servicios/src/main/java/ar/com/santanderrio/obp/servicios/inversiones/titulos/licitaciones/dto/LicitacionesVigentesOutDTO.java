/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class LicitacionesVigentesOutDTO.
 */
public class LicitacionesVigentesOutDTO {

	/** The licitaciones vigentes list. */
	private List<LicitacionVigenteDTO> licitacionesVigentesList = new ArrayList<LicitacionVigenteDTO>();
	
	/** The licitaciones vigentes list. */
	private List<LicitacionVigenteDTO> licitacionesVigentesCustodiaList = new ArrayList<LicitacionVigenteDTO>();

	/**
	 * Gets the licitaciones vigentes list.
	 *
	 * @return the licitaciones vigentes list
	 */
	public List<LicitacionVigenteDTO> getLicitacionesVigentesList() {
		return licitacionesVigentesList;
	}

	/**
	 * Sets the licitaciones vigentes list.
	 *
	 * @param licitacionesVigentesList
	 *            the new licitaciones vigentes list
	 */
	public void setLicitacionesVigentesList(List<LicitacionVigenteDTO> licitacionesVigentesList) {
		this.licitacionesVigentesList = licitacionesVigentesList;
	}

	/**
	 * Gets the licitaciones vigentes custodia list.
	 *
	 * @return the licitacionesVigentesCustodiaList
	 */
	public List<LicitacionVigenteDTO> getLicitacionesVigentesCustodiaList() {
		return licitacionesVigentesCustodiaList;
	}

	/**
	 * Sets the licitaciones vigentes custodia list.
	 *
	 * @param licitacionesVigentesCustodiaList
	 *            the licitacionesVigentesCustodiaList to set
	 */
	public void setLicitacionesVigentesCustodiaList(List<LicitacionVigenteDTO> licitacionesVigentesCustodiaList) {
		this.licitacionesVigentesCustodiaList = licitacionesVigentesCustodiaList;
	}
}
