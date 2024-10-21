/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto;

/**
 * CuentaOperacionExteriorDTO.
 *
 * @author Silvina_Luque
 */
public class CuentaOperacionExteriorDTO {

	/** The id. */
	private String id;

	/** The cuenta relacionada. */
	private String cuentaRelacionada;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The cuenta preferida. */
	private boolean cuentaPreferida;

	/**
	 * Gets the cuenta relacionada.
	 *
	 * @return the cuenta relacionada
	 */
	public String getCuentaRelacionada() {
		return cuentaRelacionada;
	}

	/**
	 * Sets the cuenta relacionada.
	 *
	 * @param cuentaRelacionada
	 *            the new cuenta relacionada
	 */
	public void setCuentaRelacionada(String cuentaRelacionada) {
		this.cuentaRelacionada = cuentaRelacionada;
	}

	/**
	 * Checks if is cuenta preferida.
	 *
	 * @return true, if is cuenta preferida
	 */
	public boolean isCuentaPreferida() {
		return cuentaPreferida;
	}

	/**
	 * Sets the cuenta preferida.
	 *
	 * @param cuentaPreferida
	 *            the new cuenta preferida
	 */
	public void setCuentaPreferida(boolean cuentaPreferida) {
		this.cuentaPreferida = cuentaPreferida;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
