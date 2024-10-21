/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class LicitacionesVIgentesBprivOutView.
 */
public class LicitacionesVIgentesBprivOutView {

	/** The licitaciones vigentes list. */
	List<LicitacionVigente> licitacionesVigentesList = new ArrayList<LicitacionVigente>();
	
	/** The legales. */
	private String legales;

	/**
	 * Gets the licitaciones vigentes list.
	 *
	 * @return the licitaciones vigentes list
	 */
	public List<LicitacionVigente> getLicitacionesVigentesList() {
		return licitacionesVigentesList;
	}

	/**
	 * Sets the licitaciones vigentes list.
	 *
	 * @param licitacionesVigentesList
	 *            the new licitaciones vigentes list
	 */
	public void setLicitacionesVigentesList(List<LicitacionVigente> licitacionesVigentesList) {
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
