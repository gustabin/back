/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity;

/**
 * The Class OrdenInEntity.
 */
public class OrdenInEntity {

	/** The cuenta. */
	private String cuenta;

	/** The codigo sis. */
	private String codigoSis;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The operaciones. */
	private String operaciones;

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the codigo sis.
	 *
	 * @return the codigo sis
	 */
	public String getCodigoSis() {
		return codigoSis;
	}

	/**
	 * Sets the codigo sis.
	 *
	 * @param codigoSis
	 *            the new codigo sis
	 */
	public void setCodigoSis(String codigoSis) {
		this.codigoSis = codigoSis;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
	public String getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
	public void setOperaciones(String operaciones) {
		this.operaciones = operaciones;
	}

}
