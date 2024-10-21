/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity;

/**
 * The Class ConsultaCliente.
 */
public class ConsultaCliente {

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The aceptacion contrato. */
	private String aceptacionContrato;

	/** The fecha inicio uso. */
	private String fechaInicioUso;

	/** The canal activacion. */
	private String canalActivacion;

	/**
	 * Gets the aceptacion contrato.
	 *
	 * @return the aceptacion contrato
	 */
	public String getAceptacionContrato() {
		return aceptacionContrato;
	}

	/**
	 * Sets the aceptacion contrato.
	 *
	 * @param aceptacionContrato
	 *            the new aceptacion contrato
	 */
	public void setAceptacionContrato(String aceptacionContrato) {
		this.aceptacionContrato = aceptacionContrato;
	}

	/**
	 * Gets the fecha inicio uso.
	 *
	 * @return the fecha inicio uso
	 */
	public String getFechaInicioUso() {
		return fechaInicioUso;
	}

	/**
	 * Sets the fecha inicio uso.
	 *
	 * @param fechaInicioUso
	 *            the new fecha inicio uso
	 */
	public void setFechaInicioUso(String fechaInicioUso) {
		this.fechaInicioUso = fechaInicioUso;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the canal activacion.
	 *
	 * @return the canal activacion
	 */
	public String getCanalActivacion() {
		return canalActivacion;
	}

	/**
	 * Sets the canal activacion.
	 *
	 * @param canalActivacion
	 *            the new canal activacion
	 */
	public void setCanalActivacion(String canalActivacion) {
		this.canalActivacion = canalActivacion;
	}

}
