/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.List;

/**
 * The Class ListaFiltradaPorFecha.
 */
public class ListaFiltradaPorFecha{
	
    /** The codigo periodo. */
    private String codigoPeriodo;
    
    /** The descripcion periodo. */
    private String descripcionPeriodo;
    
    /** The etiqueta periodo combo. */
    private String etiquetaPeriodoCombo;
    
    /** The fecha inicio. */
    private String fechaInicio;
    
    /** The fecha fin. */
    private String fechaFin;
    
    /** The disponibilidad periodo. */
    private int disponibilidadPeriodo;
    
    /** The disponibilidad informacion. */
    private Boolean disponibilidadInformacion = false;
    
    /** The por defecto. */
    private Boolean porDefecto = false;
    
    /** The leyenda. */
    private String leyenda;
    
    /** The lista monedas. */
    private List<MonedaACView> listaMonedas;
    
    /** The fecha rentabilidad. */
    private String fechaRentabilidad;
    
    
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

//	public MonedaACView getMonedaDefecto() {
//		return monedaDefecto;
//	}
//
//	public void setMonedaDefecto(MonedaACView monedaDefecto) {
//		this.monedaDefecto = monedaDefecto;
//	}

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
	public Boolean getDisponibilidadInformacion() {
		return disponibilidadInformacion;
	}

	/**
	 * Sets the disponibilidad informacion.
	 *
	 * @param disponibilidadInformacion
	 *            the new disponibilidad informacion
	 */
	public void setDisponibilidadInformacion(Boolean disponibilidadInformacion) {
		this.disponibilidadInformacion = disponibilidadInformacion;
	}

	/**
	 * Gets the por defecto.
	 *
	 * @return the por defecto
	 */
	public Boolean getPorDefecto() {
		return porDefecto;
	}

	/**
	 * Sets the por defecto.
	 *
	 * @param porDefecto
	 *            the new por defecto
	 */
	public void setPorDefecto(Boolean porDefecto) {
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
	public List<MonedaACView> getListaMonedas() {
		return listaMonedas;
	}

	/**
	 * Sets the lista monedas.
	 *
	 * @param listaMonedas
	 *            the new lista monedas
	 */
	public void setListaMonedas(List<MonedaACView> listaMonedas) {
		this.listaMonedas = listaMonedas;
	}

	/**
	 * Gets the etiqueta periodo combo.
	 *
	 * @return the etiqueta periodo combo
	 */
	public String getEtiquetaPeriodoCombo() {
		return etiquetaPeriodoCombo;
	}

	/**
	 * Sets the etiqueta periodo combo.
	 *
	 * @param etiquetaPeriodoCombo
	 *            the new etiqueta periodo combo
	 */
	public void setEtiquetaPeriodoCombo(String etiquetaPeriodoCombo) {
		this.etiquetaPeriodoCombo = etiquetaPeriodoCombo;
	}

	/**
	 * Gets the fecha rentabilidad.
	 *
	 * @return the fecha rentabilidad
	 */
	public String getFechaRentabilidad() {
		return fechaRentabilidad;
	}

	/**
	 * Sets the fecha rentabilidad.
	 *
	 * @param fechaRentabilidad
	 *            the new fecha rentabilidad
	 */
	public void setFechaRentabilidad(String fechaRentabilidad) {
		this.fechaRentabilidad = fechaRentabilidad;
	}
		
}