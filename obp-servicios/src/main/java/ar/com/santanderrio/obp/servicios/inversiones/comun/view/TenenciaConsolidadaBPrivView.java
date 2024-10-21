/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaConsolidadaBPrivView.
 */
public class TenenciaConsolidadaBPrivView {

	/** The lista cuentas. */
	private List<TenenciaPorCuentaBPriv> listaTenenciaPorCuenta = new ArrayList<TenenciaPorCuentaBPriv>();

	/** The legales. */
	private String legales;

	private boolean habilitadaCtaTitRep;
	
	/**
	 * Gets the lista tenencia por cuenta.
	 *
	 * @return the lista tenencia por cuenta
	 */
	public List<TenenciaPorCuentaBPriv> getListaTenenciaPorCuenta() {
		return listaTenenciaPorCuenta;
	}

	/**
	 * Sets the lista tenencia por cuenta.
	 *
	 * @param listaTenenciaPorCuenta
	 *            the new lista tenencia por cuenta
	 */
	public void setListaTenenciaPorCuenta(List<TenenciaPorCuentaBPriv> listaTenenciaPorCuenta) {
		this.listaTenenciaPorCuenta = listaTenenciaPorCuenta;
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

	public boolean isHabilitadaCtaTitRep() {
		return habilitadaCtaTitRep;
	}

	public void setHabilitadaCtaTitRep(boolean habilitadaCtaTitRep) {
		this.habilitadaCtaTitRep = habilitadaCtaTitRep;
	}
	
	

}
