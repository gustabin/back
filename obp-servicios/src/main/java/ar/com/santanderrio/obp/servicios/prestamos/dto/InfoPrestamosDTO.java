/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

/**
 * The Class PrestamoPermitidoDTO.
 */
public class InfoPrestamosDTO {

	/**
	 * Instantiates a new info prestamos DTO.
	 */
	public InfoPrestamosDTO() {
		super();
		this.prestamosHipotecarios = 0;
		this.prestamosPersonales = 0;
		this.prestamosPrendarios = 0;
	}

	/** The prestamos hipotecarios. */
	private Integer prestamosHipotecarios;

	/** The prestamos personales. */
	private Integer prestamosPersonales;

	/** The prestamos prendarios. */
	private Integer prestamosPrendarios;

	/**
	 * Gets the prestamos hipotecarios.
	 *
	 * @return the prestamosHipotecarios
	 */
	public Integer getPrestamosHipotecarios() {
		return prestamosHipotecarios;
	}

	/**
	 * Sets the prestamos hipotecarios.
	 *
	 * @param prestamosHipotecarios
	 *            the prestamosHipotecario to set
	 */
	public void setPrestamosHipotecarios(Integer prestamosHipotecarios) {
		this.prestamosHipotecarios = prestamosHipotecarios;
	}

	/**
	 * Gets the prestamos personales.
	 *
	 * @return the prestamosPersonales
	 */
	public Integer getPrestamosPersonales() {
		return prestamosPersonales;
	}

	/**
	 * Sets the prestamos personales.
	 *
	 * @param prestamosPersonales
	 *            the prestamosPersonales to set
	 */
	public void setPrestamosPersonales(Integer prestamosPersonales) {
		this.prestamosPersonales = prestamosPersonales;
	}

	/**
	 * Gets the prestamos prendarios.
	 *
	 * @return the prestamosPrendarios
	 */
	public Integer getPrestamosPrendarios() {
		return prestamosPrendarios;
	}

	/**
	 * Sets the prestamos prendarios.
	 *
	 * @param prestamosPrendarios
	 *            the prestamosPrendarios to set
	 */
	public void setPrestamosPrendarios(Integer prestamosPrendarios) {
		this.prestamosPrendarios = prestamosPrendarios;
	}

}
