/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dto;

import ar.com.santanderrio.obp.servicios.inversiones.comun.PeriodoRendimientoTenenciaEnum;

/**
 * The Class ElementoRendimientoDTO.
 */
public class ElementoRendimientoDTO {

	/** The Constant GUION_MEDIO. */
	private static final String GUION_MEDIO = "-";

	/** The periodo. */
	private String periodo;
	
	/** The descripcion periodo. */
	private String descripcionPeriodo;

	/** The rendimiento. */
	private String rendimiento;

	/** The rentabilidad. */
	private String rentabilidad;

	/**
	 * Instantiates a new elemento rendimiento DTO.
	 */
	public ElementoRendimientoDTO(){
		super();
	}
	
	/**
	 * Instantiates a new elemento rendimiento DTO.
	 *
	 * @param periodo
	 *            the periodo
	 * @param rendimiento
	 *            the rendimiento
	 * @param rentabilidad
	 *            the rentabilidad
	 */
	public ElementoRendimientoDTO(String periodo, String rendimiento, String rentabilidad){
		this.periodo = periodo;
		this.descripcionPeriodo = PeriodoRendimientoTenenciaEnum.fromCodigoString(periodo).getDescripcion();
		this.setRendimiento(rendimiento);
		this.setRentabilidad(rentabilidad);
	}
	
	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo
	 *            the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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
	 * Gets the rendimiento.
	 *
	 * @return the rendimiento
	 */
	public String getRendimiento() {
		return rendimiento;
	}

	/**
	 * Sets the rendimiento.
	 *
	 * @param rendimiento
	 *            the new rendimiento
	 */
	public void setRendimiento(String rendimiento) {
		if(rendimiento == null){
			this.rendimiento = GUION_MEDIO;
		}else{
			this.rendimiento = rendimiento;
		}
	}

	/**
	 * Gets the rentabilidad.
	 *
	 * @return the rentabilidad
	 */
	public String getRentabilidad() {
		return rentabilidad;
	}

	/**
	 * Sets the rentabilidad.
	 *
	 * @param rentabilidad
	 *            the new rentabilidad
	 */
	public void setRentabilidad(String rentabilidad) {
		if(rentabilidad == null){
			this.rentabilidad = GUION_MEDIO;
		}else{
			this.rentabilidad = rendimiento;
		}
	}

}
