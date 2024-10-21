/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

/**
 * The Enum CaracteristicasCuentaCredito.
 *
 * @author B015167
 */
public enum CaracteristicasCuentaCredito {

	/** The plazo 24 hs. */
	PLAZO_24_HS("00"),
	/** The inmediato. */
	INMEDIATO("02");

	/** The caracteristica. */
	private String caracteristica;

	/**
	 * Instantiates a new caracteristicas cuenta credito.
	 *
	 * @param valorTrfcci
	 *            the valor trfcci
	 */
	private CaracteristicasCuentaCredito(String valorTrfcci) {
		this.caracteristica = valorTrfcci;
	}

	/**
	 * Gets the valor trfcci.
	 *
	 * @return the valor trfcci
	 */
	public String getValorTrfcci() {
		return caracteristica;
	}
}
