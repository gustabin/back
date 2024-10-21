/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaConsolidadaPorProductoView.
 *
 * @author juan.pablo.picate
 */
public class TenenciaConsolidadaPorProductoView {

	/** The lista tenencia. */
	List<TenenciaPorProductoView> listaTenencia = new ArrayList<TenenciaPorProductoView>();

	/** The total tenencia valuada costo pesos. */
	private String totalTenenciaValuadaCostoPesos;

	/** The total tenencia valuada costo dolares. */
	private String totalTenenciaValuadaCostoDolares;

	/** The total tenencia valuada hoy pesos. */
	private String totalTenenciaValuadaHoyPesos;

	/** The total tenencia valuada hoy dolares. */
	private String totalTenenciaValuadaHoyDolares;

	/** The resultado pesos. */
	private String resultadoPesos;

	/** The resultado dolares. */
	private String resultadoDolares;

	/** The tenencia reexpresada pesos. */
	private String tenenciaReexpresadaPesos;

	/** The tenencia reexpresada dolares. */
	private String tenenciaReexpresadaDolares;

	/** The total tenencia reexpresada pesos. */
	private String totalTenenciaReexpresadaPesos;

	/** The total tenencia reexpresada dolares. */
	private String totalTenenciaReexpresadaDolares;

	/** The mostrat totales. */
	private boolean mostratTotales = true;

	/** The mensaje warning. */
	private String mensajeWarning;

	/**
	 * Gets the mensaje warning.
	 *
	 * @return the mensajeWarning
	 */
	public String getMensajeWarning() {
		return mensajeWarning;
	}

	/**
	 * Sets the mensaje warning.
	 *
	 * @param mensajeWarning
	 *            the mensajeWarning to set
	 */
	public void setMensajeWarning(String mensajeWarning) {
		this.mensajeWarning = mensajeWarning;
	}

	/**
	 * Gets the lista tenencia.
	 *
	 * @return the listaTenencia
	 */
	public List<TenenciaPorProductoView> getListaTenencia() {
		return listaTenencia;
	}

	/**
	 * Sets the lista tenencia.
	 *
	 * @param listaTenencia
	 *            the listaTenencia to set
	 */
	public void setListaTenencia(List<TenenciaPorProductoView> listaTenencia) {
		this.listaTenencia = listaTenencia;
	}

	/**
	 * Gets the total tenencia valuada costo pesos.
	 *
	 * @return the totalTenenciaValuadaCostoPesos
	 */
	public String getTotalTenenciaValuadaCostoPesos() {
		return totalTenenciaValuadaCostoPesos;
	}

	/**
	 * Sets the total tenencia valuada costo pesos.
	 *
	 * @param totalTenenciaValuadaCostoPesos
	 *            the totalTenenciaValuadaCostoPesos to set
	 */
	public void setTotalTenenciaValuadaCostoPesos(String totalTenenciaValuadaCostoPesos) {
		this.totalTenenciaValuadaCostoPesos = totalTenenciaValuadaCostoPesos;
	}

	/**
	 * Gets the total tenencia valuada costo dolares.
	 *
	 * @return the totalTenenciaValuadaCostoDolares
	 */
	public String getTotalTenenciaValuadaCostoDolares() {
		return totalTenenciaValuadaCostoDolares;
	}

	/**
	 * Sets the total tenencia valuada costo dolares.
	 *
	 * @param totalTenenciaValuadaCostoDolares
	 *            the totalTenenciaValuadaCostoDolares to set
	 */
	public void setTotalTenenciaValuadaCostoDolares(String totalTenenciaValuadaCostoDolares) {
		this.totalTenenciaValuadaCostoDolares = totalTenenciaValuadaCostoDolares;
	}

	/**
	 * Gets the total tenencia valuada hoy pesos.
	 *
	 * @return the totalTenenciaValuadaHoyPesos
	 */
	public String getTotalTenenciaValuadaHoyPesos() {
		return totalTenenciaValuadaHoyPesos;
	}

	/**
	 * Sets the total tenencia valuada hoy pesos.
	 *
	 * @param totalTenenciaValuadaHoyPesos
	 *            the totalTenenciaValuadaHoyPesos to set
	 */
	public void setTotalTenenciaValuadaHoyPesos(String totalTenenciaValuadaHoyPesos) {
		this.totalTenenciaValuadaHoyPesos = totalTenenciaValuadaHoyPesos;
	}

	/**
	 * Gets the total tenencia valuada hoy dolares.
	 *
	 * @return the totalTenenciaValuadaHoyDolares
	 */
	public String getTotalTenenciaValuadaHoyDolares() {
		return totalTenenciaValuadaHoyDolares;
	}

	/**
	 * Sets the total tenencia valuada hoy dolares.
	 *
	 * @param totalTenenciaValuadaHoyDolares
	 *            the totalTenenciaValuadaHoyDolares to set
	 */
	public void setTotalTenenciaValuadaHoyDolares(String totalTenenciaValuadaHoyDolares) {
		this.totalTenenciaValuadaHoyDolares = totalTenenciaValuadaHoyDolares;
	}

	/**
	 * Gets the resultado pesos.
	 *
	 * @return the resultadoPesos
	 */
	public String getResultadoPesos() {
		return resultadoPesos;
	}

	/**
	 * Sets the resultado pesos.
	 *
	 * @param resultadoPesos
	 *            the resultadoPesos to set
	 */
	public void setResultadoPesos(String resultadoPesos) {
		this.resultadoPesos = resultadoPesos;
	}

	/**
	 * Gets the resultado dolares.
	 *
	 * @return the resultadoDolares
	 */
	public String getResultadoDolares() {
		return resultadoDolares;
	}

	/**
	 * Sets the resultado dolares.
	 *
	 * @param resultadoDolares
	 *            the resultadoDolares to set
	 */
	public void setResultadoDolares(String resultadoDolares) {
		this.resultadoDolares = resultadoDolares;
	}

	/**
	 * Gets the tenencia reexpresada pesos.
	 *
	 * @return the tenenciaReexpresadaPesos
	 */
	public String getTenenciaReexpresadaPesos() {
		return tenenciaReexpresadaPesos;
	}

	/**
	 * Sets the tenencia reexpresada pesos.
	 *
	 * @param tenenciaReexpresadaPesos
	 *            the tenenciaReexpresadaPesos to set
	 */
	public void setTenenciaReexpresadaPesos(String tenenciaReexpresadaPesos) {
		this.tenenciaReexpresadaPesos = tenenciaReexpresadaPesos;
	}

	/**
	 * Gets the tenencia reexpresada dolares.
	 *
	 * @return the tenenciaReexpresadaDolares
	 */
	public String getTenenciaReexpresadaDolares() {
		return tenenciaReexpresadaDolares;
	}

	/**
	 * Sets the tenencia reexpresada dolares.
	 *
	 * @param tenenciaReexpresadaDolares
	 *            the tenenciaReexpresadaDolares to set
	 */
	public void setTenenciaReexpresadaDolares(String tenenciaReexpresadaDolares) {
		this.tenenciaReexpresadaDolares = tenenciaReexpresadaDolares;
	}

	/**
	 * Gets the total tenencia reexpresada pesos.
	 *
	 * @return the totalTenenciaReexpresadaPesos
	 */
	public String getTotalTenenciaReexpresadaPesos() {
		return totalTenenciaReexpresadaPesos;
	}

	/**
	 * Sets the total tenencia reexpresada pesos.
	 *
	 * @param totalTenenciaReexpresadaPesos
	 *            the totalTenenciaReexpresadaPesos to set
	 */
	public void setTotalTenenciaReexpresadaPesos(String totalTenenciaReexpresadaPesos) {
		this.totalTenenciaReexpresadaPesos = totalTenenciaReexpresadaPesos;
	}

	/**
	 * Gets the total tenencia reexpresada dolares.
	 *
	 * @return the totalTenenciaReexpresadaDolares
	 */
	public String getTotalTenenciaReexpresadaDolares() {
		return totalTenenciaReexpresadaDolares;
	}

	/**
	 * Sets the total tenencia reexpresada dolares.
	 *
	 * @param totalTenenciaReexpresadaDolares
	 *            the totalTenenciaReexpresadaDolares to set
	 */
	public void setTotalTenenciaReexpresadaDolares(String totalTenenciaReexpresadaDolares) {
		this.totalTenenciaReexpresadaDolares = totalTenenciaReexpresadaDolares;
	}

	/**
	 * Checks if is mostrat totales.
	 *
	 * @return the mostratTotales
	 */
	public boolean isMostratTotales() {
		return mostratTotales;
	}

	/**
	 * Sets the mostrat totales.
	 *
	 * @param mostratTotales
	 *            the mostratTotales to set
	 */
	public void setMostratTotales(boolean mostratTotales) {
		this.mostratTotales = mostratTotales;
	}

}
