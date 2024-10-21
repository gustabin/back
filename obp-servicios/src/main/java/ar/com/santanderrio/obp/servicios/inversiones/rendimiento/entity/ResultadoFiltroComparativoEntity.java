/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ResultadoFiltroComparativoEntity.
 */
public class ResultadoFiltroComparativoEntity {
	
	/** The guid error. */
	@JsonProperty("GUIDError")
	private String guidError;
	
	/** The codigo comparacion. */
	@JsonProperty("CodigoComparacion")
	private String codigoComparacion;
	
	/** The guid error. */
	@JsonProperty("DescripcionComparacion")
	private String descripcionComparacion;

	/** The cuenta. */
	@JsonProperty("Cuenta")
	private SucNroCuenta cuenta;
	
	/** The codigo periodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;
	
	/** The descripcion periodo. */
	@JsonProperty("DescripcionPeriodo")
	private String descripcionPeriodo;
	
	/** The fecha inicio. */
	@JsonProperty("FechaInicio")
	private String fechaInicio;

	/** The decha fin. */
	@JsonProperty("FechaFin")
	private String dechaFin;
	
	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	/** The disponibilidad periodo. */
	@JsonProperty("DisponibilidadPeriodo")
	private String disponibilidadPeriodo;
	
	/** The disponibilidad rendimiento. */
	@JsonProperty("DisponibilidadRendimiento")
	private String disponibilidadRendimiento;

	/**
	 * Gets the guid error.
	 *
	 * @return the guid error
	 */
	public String getGuidError() {
		return guidError;
	}

	/**
	 * Sets the guid error.
	 *
	 * @param guidError
	 *            the new guid error
	 */
	public void setGuidError(String guidError) {
		this.guidError = guidError;
	}

	/**
	 * Gets the codigo comparacion.
	 *
	 * @return the codigo comparacion
	 */
	public String getCodigoComparacion() {
		return codigoComparacion;
	}

	/**
	 * Sets the codigo comparacion.
	 *
	 * @param codigoComparacion
	 *            the new codigo comparacion
	 */
	public void setCodigoComparacion(String codigoComparacion) {
		this.codigoComparacion = codigoComparacion;
	}

	/**
	 * Gets the descripcion comparacion.
	 *
	 * @return the descripcion comparacion
	 */
	public String getDescripcionComparacion() {
		return descripcionComparacion;
	}

	/**
	 * Sets the descripcion comparacion.
	 *
	 * @param descripcionComparacion
	 *            the new descripcion comparacion
	 */
	public void setDescripcionComparacion(String descripcionComparacion) {
		this.descripcionComparacion = descripcionComparacion;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public SucNroCuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(SucNroCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the codigo periodo.
	 *
	 * @return the codigo periodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * Sets the codigo periodo.
	 *
	 * @param codigoPeriodo
	 *            the new codigo periodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * Gets the descripcion periodo.
	 *
	 * @return the descripcion periodo
	 */
	public String getDescripcionPeriodo() {
		return descripcionPeriodo;
	}

	/**
	 * Sets the descripcion periodo.
	 *
	 * @param descripcionPeriodo
	 *            the new descripcion periodo
	 */
	public void setDescripcionPeriodo(String descripcionPeriodo) {
		this.descripcionPeriodo = descripcionPeriodo;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the decha fin.
	 *
	 * @return the decha fin
	 */
	public String getDechaFin() {
		return dechaFin;
	}

	/**
	 * Sets the decha fin.
	 *
	 * @param dechaFin
	 *            the new decha fin
	 */
	public void setDechaFin(String dechaFin) {
		this.dechaFin = dechaFin;
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

	/**
	 * Gets the disponibilidad periodo.
	 *
	 * @return the disponibilidad periodo
	 */
	public String getDisponibilidadPeriodo() {
		return disponibilidadPeriodo;
	}

	/**
	 * Sets the disponibilidad periodo.
	 *
	 * @param disponibilidadPeriodo
	 *            the new disponibilidad periodo
	 */
	public void setDisponibilidadPeriodo(String disponibilidadPeriodo) {
		this.disponibilidadPeriodo = disponibilidadPeriodo;
	}

	/**
	 * Gets the disponibilidad rendimiento.
	 *
	 * @return the disponibilidad rendimiento
	 */
	public String getDisponibilidadRendimiento() {
		return disponibilidadRendimiento;
	}

	/**
	 * Sets the disponibilidad rendimiento.
	 *
	 * @param disponibilidadRendimiento
	 *            the new disponibilidad rendimiento
	 */
	public void setDisponibilidadRendimiento(String disponibilidadRendimiento) {
		this.disponibilidadRendimiento = disponibilidadRendimiento;
	}
	
	
}
