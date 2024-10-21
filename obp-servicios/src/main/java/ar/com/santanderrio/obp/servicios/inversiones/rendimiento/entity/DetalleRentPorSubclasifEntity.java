/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DetalleRentPorSubclasifEntity.
 */
public class DetalleRentPorSubclasifEntity {

	/** The codigo periodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;
	
	/** The descripcion periodo. */
	@JsonProperty("DescripcionPeriodo")
	private String descripcionPeriodo;
	
	/** The periodo fecha inicial. */
	@JsonProperty("FechaInicio")
	private String periodoFechaInicial;
	
	/** The periodo fecha final. */
	@JsonProperty("FechaFin")
	private String periodoFechaFinal;
	
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
	@JsonProperty("CodigoSubClasificacion")
	private String codigoSubclasificacion;
	
	/** The descripcion subclasificacion. */
	@JsonProperty("DescripcionSubClasificacion")
	private String descripcionSubclasificacion;
	
	/** The rentabilidad neta. */
	@JsonProperty("RentabilidadNeta")
	private BigDecimal rentabilidadNeta;
	
	/** The rendimiento. */
	@JsonProperty("Rendimiento")
	private BigDecimal rendimiento;
	
	/** The tna. */
	@JsonProperty("TNA")
	private BigDecimal tna;
	
	/** The resultado agrupacion. */
	@JsonProperty("ResultadoAgrupacion")
	private List<DetallePorAgrupacionEntity> resultadoAgrupacion;

	
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
	 * Gets the rentabilidad neta.
	 *
	 * @return the rentabilidad neta
	 */
	public BigDecimal getRentabilidadNeta() {
		return rentabilidadNeta;
	}

	/**
	 * Sets the rentabilidad neta.
	 *
	 * @param rentabilidadNeta
	 *            the new rentabilidad neta
	 */
	public void setRentabilidadNeta(BigDecimal rentabilidadNeta) {
		this.rentabilidadNeta = rentabilidadNeta;
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
	 * Gets the resultado agrupacion.
	 *
	 * @return the resultado agrupacion
	 */
	public List<DetallePorAgrupacionEntity> getResultadoAgrupacion() {
		return resultadoAgrupacion;
	}

	/**
	 * Sets the resultado agrupacion.
	 *
	 * @param resultadoAgrupacion
	 *            the new resultado agrupacion
	 */
	public void setResultadoAgrupacion(List<DetallePorAgrupacionEntity> resultadoAgrupacion) {
		this.resultadoAgrupacion = resultadoAgrupacion;
	}
	
}
