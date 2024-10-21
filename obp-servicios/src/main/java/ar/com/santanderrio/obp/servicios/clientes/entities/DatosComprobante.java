/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

/**
 * The Class DatosComprobante.
 */
public class DatosComprobante {

	/** The nro comprobante. */
	private String nroComprobante;

	/** The tipo comprobante. */
	private String tipoComprobante;

	/** The legales. */
	private String legales;

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the tipo comprobante.
	 *
	 * @return the tipo comprobante
	 */
	public String getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * Sets the tipo comprobante.
	 *
	 * @param tipoComprobante
	 *            the new tipo comprobante
	 */
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

}
