/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaProductosPorMonedaView.
 *
 * @author juan.pablo.picate
 */
public class TenenciaProductosPorMonedaView {

	/** The listaTenenciaProductos. */
	List<TenenciaPorProductoView> listaTenenciaProductos = new ArrayList<TenenciaPorProductoView>();

	/** The totalTenenciaValuadaHoy. */
	private String totalTenenciaValuadaHoy;

	/** The totalTenenciaValuadaCosto. */
	private String totalTenenciaValuadaCosto;

	/** The totalResultado. */
	private String totalResultado;

	/** The moneda. */
	private String moneda;
	
	/** The monedaReexpresada. 
	 * Es el total de la tenencia en la otra moneda, expresada en la moneda actual.*/
	private String monedaReexpresada;

	/** The totalMonedaReexpresada. 
	 * Es la suma de la tenencia total en la moneda actual y la tenencia de la otra moneda
	 * pero expresada en la moneda actual.*/
	private String totalMonedaReexpresada;
	
	/** The mostrarGraficos. */
	private boolean mostrarGraficos = true;
	
	/**
	 * Gets the lista tenencia productos.
	 *
	 * @return the listaTenenciaProductos
	 */
	public List<TenenciaPorProductoView> getListaTenenciaProductos() {
		return listaTenenciaProductos;
	}

	/**
	 * Sets the lista tenencia productos.
	 *
	 * @param listaTenenciaProductos
	 *            the listaTenenciaProductos to set
	 */
	public void setListaTenenciaProductos(List<TenenciaPorProductoView> listaTenenciaProductos) {
		this.listaTenenciaProductos = listaTenenciaProductos;
	}

	/**
	 * Gets the total tenencia valuada hoy.
	 *
	 * @return the totalTenenciaValuadaHoy
	 */
	public String getTotalTenenciaValuadaHoy() {
		return totalTenenciaValuadaHoy;
	}

	/**
	 * Sets the total tenencia valuada hoy.
	 *
	 * @param totalTenenciaValuadaHoy
	 *            the totalTenenciaValuadaHoy to set
	 */
	public void setTotalTenenciaValuadaHoy(String totalTenenciaValuadaHoy) {
		this.totalTenenciaValuadaHoy = totalTenenciaValuadaHoy;
	}

	/**
	 * Gets the total tenencia valuada costo.
	 *
	 * @return the totalTenenciaValuadaCosto
	 */
	public String getTotalTenenciaValuadaCosto() {
		return totalTenenciaValuadaCosto;
	}

	/**
	 * Sets the total tenencia valuada costo.
	 *
	 * @param totalTenenciaValuadaCosto
	 *            the totalTenenciaValuadaCosto to set
	 */
	public void setTotalTenenciaValuadaCosto(String totalTenenciaValuadaCosto) {
		this.totalTenenciaValuadaCosto = totalTenenciaValuadaCosto;
	}

	/**
	 * Gets the total resultado.
	 *
	 * @return the totalResultado
	 */
	public String getTotalResultado() {
		return totalResultado;
	}

	/**
	 * Sets the total resultado.
	 *
	 * @param totalResultado
	 *            the totalResultado to set
	 */
	public void setTotalResultado(String totalResultado) {
		this.totalResultado = totalResultado;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the moneda reexpresada.
	 *
	 * @return the moneda reexpresada
	 */
	public String getMonedaReexpresada() {
		return monedaReexpresada;
	}

	/**
	 * Sets the moneda reexpresada.
	 *
	 * @param monedaReexpresada
	 *            the new moneda reexpresada
	 */
	public void setMonedaReexpresada(String monedaReexpresada) {
		this.monedaReexpresada = monedaReexpresada;
	}

	/**
	 * Gets the total moneda reexpresada.
	 *
	 * @return the total moneda reexpresada
	 */
	public String getTotalMonedaReexpresada() {
		return totalMonedaReexpresada;
	}

	/**
	 * Sets the total moneda reexpresada.
	 *
	 * @param totalMonedaReexpresada
	 *            the new total moneda reexpresada
	 */
	public void setTotalMonedaReexpresada(String totalMonedaReexpresada) {
		this.totalMonedaReexpresada = totalMonedaReexpresada;
	}

	/**
	 * Checks if is mostrar graficos.
	 *
	 * @return true, if is mostrar graficos
	 */
	public boolean isMostrarGraficos() {
		return mostrarGraficos;
	}

	/**
	 * Sets the mostrar graficos.
	 *
	 * @param mostrarGraficos
	 *            the new mostrar graficos
	 */
	public void setMostrarGraficos(boolean mostrarGraficos) {
		this.mostrarGraficos = mostrarGraficos;
	}
	
	
	
	
}
