/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.math.BigDecimal;

import org.beanio.annotation.Record;

/**
 * ConsultaPrestamoOpenCreditOutEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class ConsultaPrestamoOpenCreditOutEntity {

	/** CAPSOLI capitalSolicitado. */
	private BigDecimal capitalSolicitado;

	/** IMPDISPO capitalDispuesto. */
	private BigDecimal capitalDispuesto;

	/** fechasInicio. */
	private String fechasInicio;

	/**
	 * Gets the capital solicitado.
	 *
	 * @return the capital solicitado
	 */
	public BigDecimal getCapitalSolicitado() {
		return capitalSolicitado;
	}

	/**
	 * Sets the capital solicitado.
	 *
	 * @param capitalSolicitado
	 *            the new capital solicitado
	 */
	public void setCapitalSolicitado(BigDecimal capitalSolicitado) {
		this.capitalSolicitado = capitalSolicitado;
	}

	/**
	 * Gets the capital dispuesto.
	 *
	 * @return the capital dispuesto
	 */
	public BigDecimal getCapitalDispuesto() {
		return capitalDispuesto;
	}

	/**
	 * Sets the capital dispuesto.
	 *
	 * @param capitalDispuesto
	 *            the new capital dispuesto
	 */
	public void setCapitalDispuesto(BigDecimal capitalDispuesto) {
		this.capitalDispuesto = capitalDispuesto;
	}

	/**
	 * Gets the fechas inicio.
	 *
	 * @return the fechas inicio
	 */
	public String getFechasInicio() {
		return fechasInicio;
	}

	/**
	 * Sets the fechas inicio.
	 *
	 * @param fechasInicio
	 *            the new fechas inicio
	 */
	public void setFechasInicio(String fechasInicio) {
		this.fechasInicio = fechasInicio;
	}

}
