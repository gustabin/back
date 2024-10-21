/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class DistribucionRentabilidadView.
 */
public class DistribucionRentabilidadView {

	/** The total rentabilidad. */
	private String totalRentabilidad;
	
	/** The tna. */
	private BigDecimal tna;
	
	/** The tna txt. */
	private String tnaTxt;
	
	/** The lista escala txt. */
	private List<String> listaEscalaTxt;
	
	/** The escala. */
	private BigDecimal escala;
	
	/** The lista productos. */
	private List<ProductoGraficoView> listaProductos;

	
	/**
	 * Gets the total rentabilidad.
	 *
	 * @return the total rentabilidad
	 */
	public String getTotalRentabilidad() {
		return totalRentabilidad;
	}

	/**
	 * Sets the total rentabilidad.
	 *
	 * @param totalRentabilidad
	 *            the new total rentabilidad
	 */
	public void setTotalRentabilidad(String totalRentabilidad) {
		this.totalRentabilidad = totalRentabilidad;
	}
	
	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public BigDecimal getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(BigDecimal tna) {
		this.tna = tna;
	}

	/**
	 * Gets the tna txt.
	 *
	 * @return the tna txt
	 */
	public String getTnaTxt() {
		return tnaTxt;
	}

	/**
	 * Sets the tna txt.
	 *
	 * @param tnaTxt
	 *            the new tna txt
	 */
	public void setTnaTxt(String tnaTxt) {
		this.tnaTxt = tnaTxt;
	}

	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	public List<ProductoGraficoView> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos
	 *            the new lista productos
	 */
	public void setListaProductos(List<ProductoGraficoView> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * Gets the lista escala txt.
	 *
	 * @return the lista escala txt
	 */
	public List<String> getListaEscalaTxt() {
		return listaEscalaTxt;
	}

	/**
	 * Sets the lista escala txt.
	 *
	 * @param listaEscalaTxt
	 *            the new lista escala txt
	 */
	public void setListaEscalaTxt(List<String> listaEscalaTxt) {
		this.listaEscalaTxt = listaEscalaTxt;
	}

	/**
	 * Gets the escala.
	 *
	 * @return the escala
	 */
	public BigDecimal getEscala() {
		return escala;
	}

	/**
	 * Sets the escala.
	 *
	 * @param escala
	 *            the new escala
	 */
	public void setEscala(BigDecimal escala) {
		this.escala = escala;
	}	

}
