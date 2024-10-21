/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class AperturaGraficaResultado.
 */
public class AperturaGraficaResultado {
	
	/** The guid. */
	@JsonProperty("GUID")
	private String guid;
	
	/** The codigo periodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;
	
	/** The descripcion periodo. */
	@JsonProperty("DescripcionPeriodo")
	private String descripcionPeriodo;
	
	/** The fecha inicio. */
	@JsonProperty("FechaInicio")
	private String fechaInicio;
	
	/** The fecha fin. */
	@JsonProperty("FechaFin")
	private String fechaFin;
	
	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	/** The codigo clasificacion. */
	@JsonProperty("CodigoClasificacion")
	private String codigoClasificacion;
	
	/** The descripcion clasificacion. */
	@JsonProperty("DescripcionClasificacion")
	private String descripcionClasificacion;
	
	/** The codigo subclasificacion. */
	@JsonProperty("CodigoSubclasificacion")
	private String codigoSubclasificacion;

	/** The descripcion subclasificacion. */
	@JsonProperty("DescripcionSubclasificacion")
	private String descripcionSubclasificacion;
	
	/** The por defecto. */
	@JsonProperty("PorDefecto")
	private String porDefecto;
	
	/** The rentabilidad disponible. */
	@JsonProperty("RentabilidadDisponible")
	private String rentabilidadDisponible;
	
	/** The rendimiento disponible. */
	@JsonProperty("RendimientoDisponible")
	private String rendimientoDisponible;

	
	/**
	 * Gets the guid.
	 *
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 *
	 * @param guid
	 *            the new guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin
	 *            the new fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
	 * Gets the codigo clasificacion.
	 *
	 * @return the codigo clasificacion
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}

	/**
	 * Sets the codigo clasificacion.
	 *
	 * @param codigoClasificacion
	 *            the new codigo clasificacion
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * Gets the descripcion clasificacion.
	 *
	 * @return the descripcion clasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}

	/**
	 * Sets the descripcion clasificacion.
	 *
	 * @param descripcionClasificacion
	 *            the new descripcion clasificacion
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}

	/**
	 * Gets the codigo subclasificacion.
	 *
	 * @return the codigo subclasificacion
	 */
	public String getCodigoSubclasificacion() {
		return codigoSubclasificacion;
	}

	/**
	 * Sets the codigo subclasificacion.
	 *
	 * @param codigoSubclasificacion
	 *            the new codigo subclasificacion
	 */
	public void setCodigoSubclasificacion(String codigoSubclasificacion) {
		this.codigoSubclasificacion = codigoSubclasificacion;
	}

	/**
	 * Gets the descripcion subclasificacion.
	 *
	 * @return the descripcion subclasificacion
	 */
	public String getDescripcionSubclasificacion() {
		return descripcionSubclasificacion;
	}

	/**
	 * Sets the descripcion subclasificacion.
	 *
	 * @param descripcionSubclasificacion
	 *            the new descripcion subclasificacion
	 */
	public void setDescripcionSubclasificacion(String descripcionSubclasificacion) {
		this.descripcionSubclasificacion = descripcionSubclasificacion;
	}

	/**
	 * Gets the por defecto.
	 *
	 * @return the por defecto
	 */
	public String getPorDefecto() {
		return porDefecto;
	}

	/**
	 * Sets the por defecto.
	 *
	 * @param porDefecto
	 *            the new por defecto
	 */
	public void setPorDefecto(String porDefecto) {
		this.porDefecto = porDefecto;
	}

	/**
	 * Gets the rentabilidad disponible.
	 *
	 * @return the rentabilidad disponible
	 */
	public String getRentabilidadDisponible() {
		return rentabilidadDisponible;
	}

	/**
	 * Sets the rentabilidad disponible.
	 *
	 * @param rentabilidadDisponible
	 *            the new rentabilidad disponible
	 */
	public void setRentabilidadDisponible(String rentabilidadDisponible) {
		this.rentabilidadDisponible = rentabilidadDisponible;
	}

	/**
	 * Gets the rendimiento disponible.
	 *
	 * @return the rendimiento disponible
	 */
	public String getRendimientoDisponible() {
		return rendimientoDisponible;
	}

	/**
	 * Sets the rendimiento disponible.
	 *
	 * @param rendimientoDisponible
	 *            the new rendimiento disponible
	 */
	public void setRendimientoDisponible(String rendimientoDisponible) {
		this.rendimientoDisponible = rendimientoDisponible;
	}
	
}
