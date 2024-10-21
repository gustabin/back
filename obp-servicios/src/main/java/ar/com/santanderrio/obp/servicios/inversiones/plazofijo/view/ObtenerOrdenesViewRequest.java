/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

/**
 * The Class ObtenerOrdenesViewRequest.
 */
public class ObtenerOrdenesViewRequest {

	/** The numero cuenta. */
	private String numeroCuenta;
	
	/** The tipo consulta. */
	private String tipoConsulta;
	
	/** The fecha desde. */
	private String fechaDesde;
	
	/** The fecha hasta. */
	private String fechaHasta;

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the tipo consulta.
	 *
	 * @return the tipo consulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * Sets the tipo consulta.
	 *
	 * @param tipoConsulta
	 *            the new tipo consulta
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
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
}
