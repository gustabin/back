/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RentabilidadRendimientoView.
 */
public class RentabilidadRendimientoView {

	/** The lista productos. */
	private List<ProductoFiltroRentabilidad> listaProductos;

	/** The lista grillas. */
	private List<DetalleProductoView> listaGrillas = new ArrayList<DetalleProductoView>();

	/** The lista grafico circular. */
	private List<GraficoCircularView> listaGraficoCircular = new ArrayList<GraficoCircularView>();
	
	/** The llamar rentabilidad. */
	private boolean llamarRentabilidad = false;
	
	/** The llamar rendimiento. */
	private boolean llamarRendimiento = false;

	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	public List<ProductoFiltroRentabilidad> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos
	 *            the new lista productos
	 */
	public void setListaProductos(List<ProductoFiltroRentabilidad> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * Gets the lista grillas.
	 *
	 * @return the lista grillas
	 */
	public List<DetalleProductoView> getListaGrillas() {
		return listaGrillas;
	}

	/**
	 * Sets the lista grillas.
	 *
	 * @param listaGrillas
	 *            the new lista grillas
	 */
	public void setListaGrillas(List<DetalleProductoView> listaGrillas) {
		this.listaGrillas = listaGrillas;
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
