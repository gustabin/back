/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * The Class ConfigTransferenciaInView.
 */
public class ConfigTransferenciaInView {

	/** The tiene gastos. */
	private String tieneGastos;

	/** The id mensaje legal. */
	private String idMensajeGastos;
	
	/** The codigo fondo. */
	private String codigoFondo;

	/**
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * @param codigoFondo the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the tiene gastos.
	 *
	 * @return the tieneGastos
	 */
	public String getTieneGastos() {
		return tieneGastos;
	}

	/**
	 * Sets the tiene gastos.
	 *
	 * @param tieneGastos
	 *            the tieneGastos to set
	 */
	public void setTieneGastos(String tieneGastos) {
		this.tieneGastos = tieneGastos;
	}

	/**
	 * Gets the id mensaje gastos.
	 *
	 * @return the idMensajeGastos
	 */
	public String getIdMensajeGastos() {
		return idMensajeGastos;
	}

	/**
	 * Sets the id mensaje gastos.
	 *
	 * @param idMensajeGastos
	 *            the idMensajeGastos to set
	 */
	public void setIdMensajeGastos(String idMensajeGastos) {
		this.idMensajeGastos = idMensajeGastos;
	}

}
