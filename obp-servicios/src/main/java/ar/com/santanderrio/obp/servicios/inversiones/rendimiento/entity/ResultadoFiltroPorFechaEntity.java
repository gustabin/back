/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ResultadoFiltroPorFechaEntity.
 */
public class ResultadoFiltroPorFechaEntity {
	
	/** The guide error. */
	@JsonProperty("GUIDError")
	private String guideError;
	
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

	/** The moneda defecto. */
	@JsonProperty("MonedaDefecto")
	private String monedaDefecto;
	
	/** The disponibilidad periodo. */
	@JsonProperty("DisponibilidadPeriodo")
	private int disponibilidadPeriodo;
	
	/** The disponibilidad informacion. */
	@JsonProperty("DisponibilidadInformacion")
	private String disponibilidadInformacion;
	
	/** The por defecto. */
	@JsonProperty("PorDefecto")
	private String porDefecto;
	
	/** The leyenda. */
	@JsonProperty("Leyenda")
	private String leyenda;
	
	/** The lista monedas. */
	@JsonProperty("ListaMonedas")
	private List<String> listaMonedas;
	
	/** The fecha info disponible. */
	@JsonProperty("FechaInfoDisponible")
	private String fechaInfoDisponible;
	

	/**
	 * Gets the guide error.
	 *
	 * @return the guide error
	 */
	public String getGuideError() {
		return guideError;
	}

	/**
	 * Sets the guide error.
	 *
	 * @param guideError
	 *            the new guide error
	 */
	public void setGuideError(String guideError) {
		this.guideError = guideError;
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
	 * Gets the moneda defecto.
	 *
	 * @return the moneda defecto
	 */
	public String getMonedaDefecto() {
		return monedaDefecto;
	}

	/**
	 * Sets the moneda defecto.
	 *
	 * @param monedaDefecto
	 *            the new moneda defecto
	 */
	public void setMonedaDefecto(String monedaDefecto) {
		this.monedaDefecto = monedaDefecto;
	}

	/**
	 * Gets the disponibilidad periodo.
	 *
	 * @return the disponibilidad periodo
	 */
	public int getDisponibilidadPeriodo() {
		return disponibilidadPeriodo;
	}

	/**
	 * Sets the disponibilidad periodo.
	 *
	 * @param disponibilidadPeriodo
	 *            the new disponibilidad periodo
	 */
	public void setDisponibilidadPeriodo(int disponibilidadPeriodo) {
		this.disponibilidadPeriodo = disponibilidadPeriodo;
	}

	/**
	 * Gets the disponibilidad informacion.
	 *
	 * @return the disponibilidad informacion
	 */
	public String getDisponibilidadInformacion() {
		return disponibilidadInformacion;
	}

	/**
	 * Sets the disponibilidad informacion.
	 *
	 * @param disponibilidadInformacion
	 *            the new disponibilidad informacion
	 */
	public void setDisponibilidadInformacion(String disponibilidadInformacion) {
		this.disponibilidadInformacion = disponibilidadInformacion;
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
	 * Gets the leyenda.
	 *
	 * @return the leyenda
	 */
	public String getLeyenda() {
		return leyenda;
	}

	/**
	 * Sets the leyenda.
	 *
	 * @param leyenda
	 *            the new leyenda
	 */
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	/**
	 * Gets the lista monedas.
	 *
	 * @return the lista monedas
	 */
	public List<String> getListaMonedas() {
		return listaMonedas;
	}

	/**
	 * Sets the lista monedas.
	 *
	 * @param listaMonedas
	 *            the new lista monedas
	 */
	public void setListaMonedas(List<String> listaMonedas) {
		this.listaMonedas = listaMonedas;
	}

	/**
	 * Gets the fecha info disponible.
	 *
	 * @return the fecha info disponible
	 */
	public String getFechaInfoDisponible() {
		return fechaInfoDisponible;
	}

	/**
	 * Sets the fecha info disponible.
	 *
	 * @param fechaInfoDisponible
	 *            the new fecha info disponible
	 */
	public void setFechaInfoDisponible(String fechaInfoDisponible) {
		this.fechaInfoDisponible = fechaInfoDisponible;
	}
	
	
}
