/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cotizacion.dto;

/**
 * Clase CotizacionParametrosEntity.
 */
public class CotizacionParametrosEntity {

	/** Variable nroCuentaTarjeta. */
	private String nroCuentaTarjeta;

	/** Variable tipoTarjeta. */
	private String tipoTarjeta;

	/** Variable nroTarjeta. */
	private String nroTarjeta;

	/** Variable importePesos. */
	private String importePesos;

	/** Variable importeDolares. */
	private String importeDolares;

	/** Variable monedaPago. */
	private String monedaPago;

	/**
	 * Devuelve el nroCuentaTarjeta.
	 *
	 * @return el nroCuentaTarjeta
	 */
	public String getNroCuentaTarjeta() {
		return nroCuentaTarjeta;
	}

	/**
	 * Setea el nroCuentaTarjeta.
	 *
	 * @param nroCuentaTarjeta
	 *            el nuevo nroCuentaTarjeta
	 */
	public void setNroCuentaTarjeta(String nroCuentaTarjeta) {
		this.nroCuentaTarjeta = nroCuentaTarjeta;
	}

	/**
	 * Devuelve el tipoTarjeta.
	 *
	 * @return el tipoTarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Setea el tipoTarjeta.
	 *
	 * @param tipoTarjeta
	 *            el nuevo tipoTarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Devuelve el nroTarjeta.
	 *
	 * @return el nroTarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Setea el nroTarjeta.
	 *
	 * @param nroTarjeta
	 *            el nuevo nroTarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Devuelve el importePesos.
	 *
	 * @return el importePesos
	 */
	public String getImportePesos() {
		return importePesos;
	}

	/**
	 * Setea el importePesos.
	 *
	 * @param importePesos
	 *            el nuevo importePesos
	 */
	public void setImportePesos(String importePesos) {
		this.importePesos = importePesos;
	}

	/**
	 * Devuelve el importeDolares.
	 *
	 * @return el importeDolares
	 */
	public String getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Setea el importe dolares.
	 *
	 * @param importeDolares
	 *            el nuevo importeDolares
	 */
	public void setImporteDolares(String importeDolares) {
		this.importeDolares = importeDolares;
	}

	/**
	 * Devuelve la monedaPago.
	 *
	 * @return la monedaPago
	 */
	public String getMonedaPago() {
		return monedaPago;
	}

	/**
	 * Setea la moneda pago.
	 *
	 * @param monedaPago
	 *            la nueva monedaPago
	 */
	public void setMonedaPago(String monedaPago) {
		this.monedaPago = monedaPago;
	}

}
