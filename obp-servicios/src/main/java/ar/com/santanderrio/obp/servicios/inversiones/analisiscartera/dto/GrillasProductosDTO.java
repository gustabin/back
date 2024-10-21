/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleProductoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoCircularView;

/**
 * The Class GrillasProductosDTO.
 */
public class GrillasProductosDTO {

	/** The moneda. */
	private String moneda;
	
	/** The lista productos. */
	private List<DetalleProductoView> listaProductos = new ArrayList<DetalleProductoView>();
	
	/** The lista grafico circular. */
	private List<GraficoCircularView> listaGraficoCircular = new ArrayList<GraficoCircularView>();
	
	/** The llamar rentabilidad. */
	private boolean llamarRentabilidad = false;
	
	/** The llamar rendimiento. */
	private boolean llamarRendimiento = false;
	
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	public List<DetalleProductoView> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos
	 *            the new lista productos
	 */
	public void setListaProductos(List<DetalleProductoView> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * Gets the lista grafico circular.
	 *
	 * @return the lista grafico circular
	 */
	public List<GraficoCircularView> getListaGraficoCircular() {
		return listaGraficoCircular;
	}

	/**
	 * Sets the lista grafico circular.
	 *
	 * @param listaGraficoCircular
	 *            the new lista grafico circular
	 */
	public void setListaGraficoCircular(List<GraficoCircularView> listaGraficoCircular) {
		this.listaGraficoCircular = listaGraficoCircular;
	}

	/**
	 * Checks if is llamar rentabilidad.
	 *
	 * @return true, if is llamar rentabilidad
	 */
	public boolean isLlamarRentabilidad() {
		return llamarRentabilidad;
	}

	/**
	 * Sets the llamar rentabilidad.
	 *
	 * @param llamarRentabilidad
	 *            the new llamar rentabilidad
	 */
	public void setLlamarRentabilidad(boolean llamarRentabilidad) {
		this.llamarRentabilidad = llamarRentabilidad;
	}

	/**
	 * Checks if is llamar rendimiento.
	 *
	 * @return true, if is llamar rendimiento
	 */
	public boolean isLlamarRendimiento() {
		return llamarRendimiento;
	}

	/**
	 * Sets the llamar rendimiento.
	 *
	 * @param llamarRendimiento
	 *            the new llamar rendimiento
	 */
	public void setLlamarRendimiento(boolean llamarRendimiento) {
		this.llamarRendimiento = llamarRendimiento;
	}

	
}
