/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class DetalleGrillaRentabilidadView.
 */
public class DetalleGrillaRentabilidadView {

	/** The fechas. */
	private String fechas;
	
	/** The lista productos. */
	private List<OpcionDetalleMenuView> listaProductos;
	
	/** The rentabilidad neta. */
	private String rentabilidadNeta;
	
	/** The rendimiento txt. */
	private String rendimientoTxt;
	
	/** The rendimiento. */
	private BigDecimal rendimiento;
	
	/** The tna. */
	private String tna;
	
	/** The lista especies. */
	private List<TipoEspecieDetalleView> listaEspecies;

	
	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public String getFechas() {
		return fechas;
	}

	/**
	 * Sets the fechas.
	 *
	 * @param fechas
	 *            the new fechas
	 */
	public void setFechas(String fechas) {
		this.fechas = fechas;
	}

	/**
	 * Gets the lista productos.
	 *
	 * @return the lista productos
	 */
	public List<OpcionDetalleMenuView> getListaProductos() {
		return listaProductos;
	}

	/**
	 * Sets the lista productos.
	 *
	 * @param listaProductos
	 *            the new lista productos
	 */
	public void setListaProductos(List<OpcionDetalleMenuView> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * Gets the rentabilidad neta.
	 *
	 * @return the rentabilidad neta
	 */
	public String getRentabilidadNeta() {
		return rentabilidadNeta;
	}

	/**
	 * Sets the rentabilidad neta.
	 *
	 * @param rentabilidadNeta
	 *            the new rentabilidad neta
	 */
	public void setRentabilidadNeta(String rentabilidadNeta) {
		this.rentabilidadNeta = rentabilidadNeta;
	}

	/**
	 * Gets the rendimiento txt.
	 *
	 * @return the rendimiento txt
	 */
	public String getRendimientoTxt() {
		return rendimientoTxt;
	}

	/**
	 * Sets the rendimiento txt.
	 *
	 * @param rendimientoTxt
	 *            the new rendimiento txt
	 */
	public void setRendimientoTxt(String rendimientoTxt) {
		this.rendimientoTxt = rendimientoTxt;
	}

	/**
	 * Gets the rendimiento.
	 *
	 * @return the rendimiento
	 */
	public BigDecimal getRendimiento() {
		return rendimiento;
	}

	/**
	 * Sets the rendimiento.
	 *
	 * @param rendimiento
	 *            the new rendimiento
	 */
	public void setRendimiento(BigDecimal rendimiento) {
		this.rendimiento = rendimiento;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(String tna) {
		this.tna = tna;
	}

	/**
	 * Gets the lista especies.
	 *
	 * @return the lista especies
	 */
	public List<TipoEspecieDetalleView> getListaEspecies() {
		return listaEspecies;
	}

	/**
	 * Sets the lista especies.
	 *
	 * @param listaEspecies
	 *            the new lista especies
	 */
	public void setListaEspecies(List<TipoEspecieDetalleView> listaEspecies) {
		this.listaEspecies = listaEspecies;
	}
	
}