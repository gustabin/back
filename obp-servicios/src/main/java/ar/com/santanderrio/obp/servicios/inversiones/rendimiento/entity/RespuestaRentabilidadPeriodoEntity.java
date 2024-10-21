/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class RespuestaRentabilidadPeriodoEntity.
 */
public class RespuestaRentabilidadPeriodoEntity {

	/** The GUID. */
	@JsonProperty("GUID")
	private int guid;
	
	/** The CodigoPeriodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;
	
	/** The DescripcionPeriodo. */
	@JsonProperty("DescripcionPeriodo")
	private String descripcionPeriodo;
	
	/** The FechaInicio. */
	@JsonProperty("FechaInicio")
	private String fechaInicio;
	
	/** The FechaFin. */
	@JsonProperty("FechaFin")
	private String fechaFin;
	
	/** The Moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	/** The RentabilidadRealizada. */
	@JsonProperty("RentabilidadRealizada")
	private BigDecimal rentabilidadRealizada;
	
	/** The RentabilidadNoRealizada. */
	@JsonProperty("RentabilidadNoRealizada")
	private BigDecimal rentabilidadNoRealizada;
	
	/** The RentabilidadTotal. */
	@JsonProperty("RentabilidadTotal")
	private BigDecimal rentabilidadTotal;
	
	/** The TNA. */
	@JsonProperty("TNA")
	private BigDecimal tna;
	
	/** The ResultadoPorClasificación. */
	@JsonProperty("ResultadoPorClasificacion")
	private List<RentabilidadPorClasificacion> resultadoPorClasificacion = new ArrayList<RentabilidadPorClasificacion>();


	/**
	 * Gets the guid.
	 *
	 * @return the guid
	 */
	public int getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 *
	 * @param guid
	 *            the new guid
	 */
	public void setGuid(int guid) {
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
	 * Gets the rentabilidad realizada.
	 *
	 * @return the rentabilidad realizada
	 */
	public BigDecimal getRentabilidadRealizada() {
		return rentabilidadRealizada;
	}

	/**
	 * Sets the rentabilidad realizada.
	 *
	 * @param rentabilidadRealizada
	 *            the new rentabilidad realizada
	 */
	public void setRentabilidadRealizada(BigDecimal rentabilidadRealizada) {
		this.rentabilidadRealizada = rentabilidadRealizada;
	}

	/**
	 * Gets the rentabilidad no realizada.
	 *
	 * @return the rentabilidad no realizada
	 */
	public BigDecimal getRentabilidadNoRealizada() {
		return rentabilidadNoRealizada;
	}

	/**
	 * Sets the rentabilidad no realizada.
	 *
	 * @param rentabilidadNoRealizada
	 *            the new rentabilidad no realizada
	 */
	public void setRentabilidadNoRealizada(BigDecimal rentabilidadNoRealizada) {
		this.rentabilidadNoRealizada = rentabilidadNoRealizada;
	}

	/**
	 * Gets the rentabilidad total.
	 *
	 * @return the rentabilidad total
	 */
	public BigDecimal getRentabilidadTotal() {
		return rentabilidadTotal;
	}

	/**
	 * Sets the rentabilidad total.
	 *
	 * @param rentabilidadTotal
	 *            the new rentabilidad total
	 */
	public void setRentabilidadTotal(BigDecimal rentabilidadTotal) {
		this.rentabilidadTotal = rentabilidadTotal;
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
	 * Gets the resultado por clasificacion.
	 *
	 * @return the resultado por clasificacion
	 */
	public List<RentabilidadPorClasificacion> getResultadoPorClasificacion() {
		return resultadoPorClasificacion;
	}

	/**
	 * Sets the resultado por clasificacion.
	 *
	 * @param resultadoPorClasificacion
	 *            the new resultado por clasificacion
	 */
	public void setResultadoPorClasificacion(List<RentabilidadPorClasificacion> resultadoPorClasificacion) {
		this.resultadoPorClasificacion = resultadoPorClasificacion;
	}
	
}
