/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao;

/**
 * The Class RtaTransferenciaBP.
 */
public class RtaTransferenciaBP {

	/** The codigo resultado. */
	private String codigoResultado;

	/** The descr resultado. */
	private String descrResultado;
	
	/** The comprobante transaccion. */
	private String comprobanteTransaccion;

	/**
	 * Instantiates a new rta transferencia BP.
	 *
	 * @param codigoResultado
	 *            the codigo resultado
	 * @param descrResultado
	 *            the descr resultado
	 */
	public RtaTransferenciaBP(String codigoResultado, String descrResultado) {
		this.codigoResultado = codigoResultado;
		this.descrResultado = descrResultado;
	}

	/**
	 * Gets the codigo resultado.
	 *
	 * @return the codigo resultado
	 */
	public String getCodigoResultado() {
		return codigoResultado;
	}

	/**
	 * Sets the codigo resultado.
	 *
	 * @param codigoResultado
	 *            the new codigo resultado
	 */
	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	/**
	 * Gets the descr resultado.
	 *
	 * @return the descr resultado
	 */
	public String getDescrResultado() {
		return descrResultado;
	}

	/**
	 * Sets the descr resultado.
	 *
	 * @param descrResultado
	 *            the new descr resultado
	 */
	public void setDescrResultado(String descrResultado) {
		this.descrResultado = descrResultado;
	}

	/**
	 * Gets the comprobante transaccion.
	 *
	 * @return the comprobante transaccion
	 */
	public String getComprobanteTransaccion() {
		return comprobanteTransaccion;
	}

	/**
	 * Sets the comprobante transaccion.
	 *
	 * @param comprobanteTransaccion
	 *            the new comprobante transaccion
	 */
	public void setComprobanteTransaccion(String comprobanteTransaccion) {
		this.comprobanteTransaccion = comprobanteTransaccion;
	}

	
}
