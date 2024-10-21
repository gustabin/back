/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ResultadoDetalleRentabilidadEntity.
 */
public class ResultadoDetalleRentabilidadEntity {
	
	/** The guid error. */
	@JsonProperty("GUIDError")
	private String guidError;
	
	/** The Codigo Periodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;
	
	/** The Descripcion Periodo. */
	@JsonProperty("DescripcionPeriodo")
	private String descripcionPeriodo;
	
	/** The Periodo Fecha Inicial. */
	@JsonProperty("PeriodoFechaInicial")
	private String periodoFechaInicial;
	
	/** The Periodo Fecha Final. */
	@JsonProperty("PeriodoFechaFinal")
	private String periodoFechaFinal;
	
	/** The Moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	/** The Codigo Clasificacion. */
	@JsonProperty("CodigoClasificacion")
	private String codigoClasificacion;
	
	/** The DescripcionClasificacion. */
	@JsonProperty("DescripcionClasificacion")
	private String descripcionClasificacion;
	
	/** The CodigoSubclasificacion. */
	@JsonProperty("CodigoSubclasificacion")
	private String codigoSubclasificacion;
	
	/** The DescripcionSubclasificacion. */
	@JsonProperty("DescripcionSubclasificacion")
	private String descripcionSubclasificacion;
	
	/** The ResultadoCabecera. */
	@JsonProperty("ResultadoCabecera")
	private List<DetallePorCabecera> resultadoCabecera;
	
	/** The ResultadoSubCabecera. */
	@JsonProperty("ResultadoSubCabecera")
	private List<DetallePorSubCabecera> resultadoSubCabecera;

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
	 * Gets the periodo fecha inicial.
	 *
	 * @return the periodo fecha inicial
	 */
	public String getPeriodoFechaInicial() {
		return periodoFechaInicial;
	}

	/**
	 * Sets the periodo fecha inicial.
	 *
	 * @param periodoFechaInicial
	 *            the new periodo fecha inicial
	 */
	public void setPeriodoFechaInicial(String periodoFechaInicial) {
		this.periodoFechaInicial = periodoFechaInicial;
	}

	/**
	 * Gets the periodo fecha final.
	 *
	 * @return the periodo fecha final
	 */
	public String getPeriodoFechaFinal() {
		return periodoFechaFinal;
	}

	/**
	 * Sets the periodo fecha final.
	 *
	 * @param periodoFechaFinal
	 *            the new periodo fecha final
	 */
	public void setPeriodoFechaFinal(String periodoFechaFinal) {
		this.periodoFechaFinal = periodoFechaFinal;
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
	 * Gets the resultado cabecera.
	 *
	 * @return the resultado cabecera
	 */
	public List<DetallePorCabecera> getResultadoCabecera() {
		return resultadoCabecera;
	}

	/**
	 * Sets the resultado cabecera.
	 *
	 * @param resultadoCabecera
	 *            the new resultado cabecera
	 */
	public void setResultadoCabecera(List<DetallePorCabecera> resultadoCabecera) {
		this.resultadoCabecera = resultadoCabecera;
	}

	/**
	 * Gets the resultado sub cabecera.
	 *
	 * @return the resultado sub cabecera
	 */
	public List<DetallePorSubCabecera> getResultadoSubCabecera() {
		return resultadoSubCabecera;
	}

	/**
	 * Sets the resultado sub cabecera.
	 *
	 * @param resultadoSubCabecera
	 *            the new resultado sub cabecera
	 */
	public void setResultadoSubCabecera(List<DetallePorSubCabecera> resultadoSubCabecera) {
		this.resultadoSubCabecera = resultadoSubCabecera;
	}

	
}
