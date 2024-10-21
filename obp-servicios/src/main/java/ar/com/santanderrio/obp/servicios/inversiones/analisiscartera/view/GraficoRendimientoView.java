/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class GraficoRendimientoView.
 */
public class GraficoRendimientoView {
	
	/** The lista productos. */
	List<ProductosGraficoRendimiento> listaProductos = new ArrayList<ProductosGraficoRendimiento>();
	
	/** The fechas. */
	List<String> fechas = new ArrayList<String>();

	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	public List<ProductosGraficoRendimiento> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos
	 *            the new lista productos
	 */
	public void setListaProductos(List<ProductosGraficoRendimiento> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public List<String> getFechas() {
		return fechas;
	}

	/**
	 * Sets the fechas.
	 *
	 * @param fechas
	 *            the new fechas
	 */
	public void setFechas(List<String> fechas) {
		this.fechas = fechas;
	}
	
}
