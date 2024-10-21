/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.List;

/**
 * The Class ConsultarFinanciacionWrapper.
 */
public class ConsultarFinanciacionWrapper {

	/** The fecha reporte. */
	private String fechaReporte;

	/** The lista consulta financiaciones. */
	private List<ConsultaFinanciacionDetalleDTO> listaConsultaFinanciaciones;

	/**
	 * Instantiates a new consultar financiacion wrapper.
	 *
	 * @param fechaReporte
	 *            the fecha reporte
	 * @param listaConsultaFinanciaciones
	 *            the lista consulta financiaciones
	 */
	public ConsultarFinanciacionWrapper(String fechaReporte,
			List<ConsultaFinanciacionDetalleDTO> listaConsultaFinanciaciones) {
		this.fechaReporte = fechaReporte;
		this.listaConsultaFinanciaciones = listaConsultaFinanciaciones;
	}

	/**
	 * Gets the lista consulta financiaciones.
	 *
	 * @return the listaConsultaFinanciaciones
	 */
	public List<ConsultaFinanciacionDetalleDTO> getListaConsultaFinanciaciones() {
		return listaConsultaFinanciaciones;
	}

	/**
	 * Sets the lista consulta financiaciones.
	 *
	 * @param listaConsultaFinanciaciones
	 *            the listaConsultaFinanciaciones to set
	 */
	public void setListaConsultaFinanciaciones(List<ConsultaFinanciacionDetalleDTO> listaConsultaFinanciaciones) {
		this.listaConsultaFinanciaciones = listaConsultaFinanciaciones;
	}

	/**
	 * Gets the fecha reporte.
	 *
	 * @return the fecha reporte
	 */
	public String getFechaReporte() {
		return fechaReporte;
	}

}
