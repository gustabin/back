/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.beanio.annotation.Field;

/**
 * The Class FondoEntity.
 */
public class FondoEntity {

	/** The Tipo fondo. */
	@Field()
	private String tipo_Fondo;

	/** The Descripción fondo. */
	@Field()
	private String descripcion_Fondo;

	/** The Valor a la fecha. */
	@Field()
	private String valor_a_la_fecha;

	/** The Ultimos 30 días. */
	@Field()
	private String ultimos_30_dias;

	/** The Ultimos 90 días. */
	@Field()
	private String ultimos_90_dias;

	/** The Renta 12 meses. */
	@Field()
	private String renta_12_meses;

	/** The Variación inicio. */
	@Field()
	private String variacion_Inicio;

	/** The Fecha inicio fondo. */
	@Field()
	private String fecha_Inicio_Fondo;

	/** The moneda. */
	@Field()
	private String moneda;

	/**
	 * Gets the tipo fondo.
	 *
	 * @return the tipo fondo
	 */
	public String getTipo_Fondo() {
		return tipo_Fondo;
	}

	/**
	 * Sets the tipo fondo.
	 *
	 * @param tipo_Fondo
	 *            the new tipo fondo
	 */
	public void setTipo_Fondo(String tipo_Fondo) {
		this.tipo_Fondo = tipo_Fondo;
	}

	/**
	 * Gets the descripción fondo.
	 *
	 * @return the descripción fondo
	 */
	public String getDescripcion_Fondo() {
		return descripcion_Fondo;
	}

	/**
	 * Sets the descripción fondo.
	 *
	 * @param descripcion_Fondo
	 *            the new descripcion fondo
	 */
	public void setDescripcion_Fondo(String descripcion_Fondo) {
		this.descripcion_Fondo = descripcion_Fondo;
	}

	/**
	 * Gets the valor a la fecha.
	 *
	 * @return the valor a la fecha
	 */
	public String getValor_a_la_fecha() {
		return valor_a_la_fecha;
	}

	/**
	 * Sets the valor a la fecha.
	 *
	 * @param valor_a_la_fecha
	 *            the new valor a la fecha
	 */
	public void setValor_a_la_fecha(String valor_a_la_fecha) {
		this.valor_a_la_fecha = valor_a_la_fecha;
	}

	/**
	 * Gets the ultimos 30 días.
	 *
	 * @return the ultimos 30 días
	 */
	public String getUltimos_30_dias() {
		return ultimos_30_dias;
	}

	/**
	 * Sets the ultimos 30 días.
	 *
	 * @param ultimos_30_dias
	 *            the new ultimos 30 días
	 */
	public void setUltimos_30_dias(String ultimos_30_dias) {
		this.ultimos_30_dias = ultimos_30_dias;
	}

	/**
	 * Gets the ultimos 90 días.
	 *
	 * @return the ultimos 90 días
	 */
	public String getUltimos_90_dias() {
		return ultimos_90_dias;
	}

	/**
	 * Sets the ultimos 90 días.
	 *
	 * @param ultimos_90_dias
	 *            the new ultimos 90 días
	 */
	public void setUltimos_90_dias(String ultimos_90_dias) {
		this.ultimos_90_dias = ultimos_90_dias;
	}

	/**
	 * Gets the renta 12 meses.
	 *
	 * @return the renta 12 meses
	 */
	public String getRenta_12_meses() {
		return renta_12_meses;
	}

	/**
	 * Sets the renta 12 meses.
	 *
	 * @param renta_12_meses
	 *            the new renta 12 meses
	 */
	public void setRenta_12_meses(String renta_12_meses) {
		this.renta_12_meses = renta_12_meses;
	}

	/**
	 * Gets the variación inicio.
	 *
	 * @return the variación inicio
	 */
	public String getVariacion_Inicio() {
		return variacion_Inicio;
	}

	/**
	 * Sets the variación inicio.
	 *
	 * @param variacion_Inicio
	 *            the new variación inicio
	 */
	public void setVariacion_Inicio(String variacion_Inicio) {
		this.variacion_Inicio = variacion_Inicio;
	}

	/**
	 * Gets the fecha inicio fondo.
	 *
	 * @return the fecha inicio fondo
	 */
	public String getFecha_Inicio_Fondo() {
		return fecha_Inicio_Fondo;
	}

	/**
	 * Sets the fecha inicio fondo.
	 *
	 * @param fecha_Inicio_Fondo
	 *            the new fecha inicio fondo
	 */
	public void setFecha_Inicio_Fondo(String fecha_Inicio_Fondo) {
		this.fecha_Inicio_Fondo = fecha_Inicio_Fondo;
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

}
