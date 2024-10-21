/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.math.BigDecimal;

/**
 * The Class GraficoCircularView.
 */
public class GraficoCircularView {
	
	/** The id producto. */
	private String idProducto;
	
	/** The descripcion producto. */
	private String descripcionProducto;
	
	/** The rendimiento txt. */
	private String rendimientoTxt;
	
	/** The rendimiento. */
	private BigDecimal rendimiento;
	
	/** The rentabilidad. */
	private String rentabilidad;
	
	/** The porcentaje grafico. */
	private BigDecimal porcentajeGrafico;
	
	/**
	 * Gets the id producto.
	 *
	 * @return the id producto
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * Sets the id producto.
	 *
	 * @param idProducto
	 *            the new id producto
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * Gets the descripcion producto.
	 *
	 * @return the descripcion producto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * Sets the descripcion producto.
	 *
	 * @param descripcionProducto
	 *            the new descripcion producto
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
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
	 * Gets the rentabilidad.
	 *
	 * @return the rentabilidad
	 */
	public String getRentabilidad() {
		return rentabilidad;
	}

	/**
	 * Sets the rentabilidad.
	 *
	 * @param rentabilidad
	 *            the new rentabilidad
	 */
	public void setRentabilidad(String rentabilidad) {
		this.rentabilidad = rentabilidad;
	}

	/**
	 * Gets the porcentaje grafico.
	 *
	 * @return the porcentaje grafico
	 */
	public BigDecimal getPorcentajeGrafico() {
		return porcentajeGrafico;
	}

	/**
	 * Sets the porcentaje grafico.
	 *
	 * @param porcentajeGrafico
	 *            the new porcentaje grafico
	 */
	public void setPorcentajeGrafico(BigDecimal porcentajeGrafico) {
		this.porcentajeGrafico = porcentajeGrafico;
	}
	
}
