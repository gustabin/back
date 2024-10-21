/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.LicitacionVigenteDTO;

/**
 * The Class LicitacionesVigentesOutView.
 *
 * @author b039920 View con las licitaciones vigentes
 */
public class LicitacionesVigentesOutView {

	/** The licitaciones vigentes list. */
	List<LicitacionVigenteDTO> licitacionesVigentesList = new ArrayList<LicitacionVigenteDTO>();
	
	/** The legales. */
	private String legales;

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
	 * Gets the legales.
	 *
	 * @return the legales
	 */
    public String getLegales() {
        return legales;
    }

    /**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
    public void setLegales(String legales) {
        this.legales = legales;
    }

}
