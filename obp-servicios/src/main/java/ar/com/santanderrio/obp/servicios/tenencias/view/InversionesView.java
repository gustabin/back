/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

/**
 * The Class InversionesView.
 */
public class InversionesView {

	/** The moneda extranjera. */
	private List<CustodiaResumenView> monedaExtranjera;

	/** The bonos. */
	private List<CustodiaResumenView> bonos;

	/** The acciones. */
	private List<CustodiaResumenView> acciones;

	/** The fondos extranjeros. */
	private List<CustodiaResumenView> fondosExtranjeros;

	/** The fondos. */
	private List<FondoResumenView> fondos;

	/** The fondos pendientes. */
	private List<FondoPendienteView> fondosPendientes;

	/** The plazo fijos. */
	private List<PlazoFijoView> plazoFijos;

	/** The total tenencias plazos fijos. */
	private String totalTenenciasPlazosFijos = "0.00";

	/** The total tenencias plazos fijos US. */
	private String totalTenenciasPlazosFijosUS = "0.00";

	/**
	 * Gets the moneda extranjera.
	 *
	 * @return the monedaExtranjera
	 */
	public List<CustodiaResumenView> getMonedaExtranjera() {
		return monedaExtranjera;
	}

	/**
	 * Sets the moneda extranjera.
	 *
	 * @param monedaExtranjera
	 *            the monedaExtranjera to set
	 */
	public void setMonedaExtranjera(List<CustodiaResumenView> monedaExtranjera) {
		this.monedaExtranjera = monedaExtranjera;
	}

	/**
	 * Gets the bonos.
	 *
	 * @return the bonos
	 */
	public List<CustodiaResumenView> getBonos() {
		return bonos;
	}

	/**
	 * Sets the bonos.
	 *
	 * @param bonos
	 *            the bonos to set
	 */
	public void setBonos(List<CustodiaResumenView> bonos) {
		this.bonos = bonos;
	}

	/**
	 * Gets the acciones.
	 *
	 * @return the acciones
	 */
	public List<CustodiaResumenView> getAcciones() {
		return acciones;
	}

	/**
	 * Sets the acciones.
	 *
	 * @param acciones
	 *            the acciones to set
	 */
	public void setAcciones(List<CustodiaResumenView> acciones) {
		this.acciones = acciones;
	}

	/**
	 * Gets the fondos extranjeros.
	 *
	 * @return the fondosExtranjeros
	 */
	public List<CustodiaResumenView> getFondosExtranjeros() {
		return fondosExtranjeros;
	}

	/**
	 * Sets the fondos extranjeros.
	 *
	 * @param fondosExtranjeros
	 *            the fondosExtranjeros to set
	 */
	public void setFondosExtranjeros(List<CustodiaResumenView> fondosExtranjeros) {
		this.fondosExtranjeros = fondosExtranjeros;
	}

	/**
	 * Gets the fondos.
	 *
	 * @return the fondos
	 */
	public List<FondoResumenView> getFondos() {
		return fondos;
	}

	/**
	 * Sets the fondos.
	 *
	 * @param fondos
	 *            the fondos to set
	 */
	public void setFondos(List<FondoResumenView> fondos) {
		this.fondos = fondos;
	}

	/**
	 * Gets the fondos pendientes.
	 *
	 * @return the fondosPendientes
	 */
	public List<FondoPendienteView> getFondosPendientes() {
		return fondosPendientes;
	}

	/**
	 * Sets the fondos pendientes.
	 *
	 * @param fondosPendientes
	 *            the fondosPendientes to set
	 */
	public void setFondosPendientes(List<FondoPendienteView> fondosPendientes) {
		this.fondosPendientes = fondosPendientes;
	}

	/**
	 * Gets the plazo fijos.
	 *
	 * @return the plazoFijos
	 */
	public List<PlazoFijoView> getPlazoFijos() {
		return plazoFijos;
	}

	/**
	 * Sets the plazo fijos.
	 *
	 * @param plazoFijos
	 *            the plazoFijos to set
	 */
	public void setPlazoFijos(List<PlazoFijoView> plazoFijos) {
		this.plazoFijos = plazoFijos;
	}

	/**
	 * Gets the total tenencias plazos fijos.
	 *
	 * @return the totalTenenciasPlazosFijos
	 */
	public String getTotalTenenciasPlazosFijos() {
		return totalTenenciasPlazosFijos;
	}

	/**
	 * Sets the total tenencias plazos fijos.
	 *
	 * @param totalTenenciasPlazosFijos
	 *            the totalTenenciasPlazosFijos to set
	 */
	public void setTotalTenenciasPlazosFijos(String totalTenenciasPlazosFijos) {
		this.totalTenenciasPlazosFijos = totalTenenciasPlazosFijos;
	}

	/**
	 * Gets the total tenencias plazos fijos US.
	 *
	 * @return the totalTenenciasPlazosFijosUS
	 */
	public String getTotalTenenciasPlazosFijosUS() {
		return totalTenenciasPlazosFijosUS;
	}

	/**
	 * Sets the total tenencias plazos fijos US.
	 *
	 * @param totalTenenciasPlazosFijosUS
	 *            the totalTenenciasPlazosFijosUS to set
	 */
	public void setTotalTenenciasPlazosFijosUS(String totalTenenciasPlazosFijosUS) {
		this.totalTenenciasPlazosFijosUS = totalTenenciasPlazosFijosUS;
	}

}
