/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.view;

import java.util.List;

/**
 * The Class DiasDisponiblesView.
 *
 * @author IT Resources
 */
public class DiasDisponiblesView {
	
	/** The fecha. */
	private String fecha;
	
	/** The fecha format. */
	private String fechaFormat;
	
	/** The fraccion horaria disponible. */
	private List<FraccionHorariaDisponibleView> fraccionHorariaDisponible;

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the fraccion horaria disponible.
	 *
	 * @return the fraccionHorariaDisponible
	 */
	public List<FraccionHorariaDisponibleView> getFraccionHorariaDisponible() {
		return fraccionHorariaDisponible;
	}

	/**
	 * Sets the fraccion horaria disponible.
	 *
	 * @param fraccionHorariaDisponible
	 *            the fraccionHorariaDisponible to set
	 */
	public void setFraccionHorariaDisponible(List<FraccionHorariaDisponibleView> fraccionHorariaDisponible) {
		this.fraccionHorariaDisponible = fraccionHorariaDisponible;
	}

	/**
	 * Gets the fecha format.
	 *
	 * @return the fechaFormat
	 */
	public String getFechaFormat() {
		return fechaFormat;
	}

	/**
	 * Sets the fecha format.
	 *
	 * @param fechaFormat
	 *            the fechaFormat to set
	 */
	public void setFechaFormat(String fechaFormat) {
		this.fechaFormat = fechaFormat;
	}


	

}
