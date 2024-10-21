/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * The Class ConfigTransferenciaInDTO.
 */
public class ConfigTransferenciaInDTO {

	/** The tiene gastos. */
	private boolean tieneGastos = false;

	/** The id mensaje legal. */
	private String idMensajeGastos;

	/**
	 * Checks if is tiene gastos.
	 *
	 * @return true, if is tiene gastos
	 */
	public boolean isTieneGastos() {
		return tieneGastos;
	}

	/**
	 * Sets the tiene gastos.
	 *
	 * @param tieneGastos
	 *            the new tiene gastos
	 */
	public void setTieneGastos(boolean tieneGastos) {
		this.tieneGastos = tieneGastos;
	}

	/**
	 * Gets the id mensaje gastos.
	 *
	 * @return the id mensaje gastos
	 */
	public String getIdMensajeGastos() {
		return idMensajeGastos;
	}

	/**
	 * Sets the id mensaje gastos.
	 *
	 * @param idMensajeGastos
	 *            the new id mensaje gastos
	 */
	public void setIdMensajeGastos(String idMensajeGastos) {
		this.idMensajeGastos = idMensajeGastos;
	}

}
