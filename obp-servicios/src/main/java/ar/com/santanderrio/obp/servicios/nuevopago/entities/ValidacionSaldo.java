/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import java.io.Serializable;

/**
 * The Class ValidacionSaldo.
 */
public class ValidacionSaldo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo pago electronico. */
	private String numeroReferenciaPago;

	/** The nombre fantasia. */
	private String empresa;

	/** The monto. */
	private String monto;

	/** The numeroFactura. */
	private String numeroFactura;

	/** The nroCuentaProducto. */
	private String nroCuentaProducto;

	/**
	 * Instantiates a new validacion saldo.
	 */
	public ValidacionSaldo() {
		super();
	}

	/**
	 * Gets the numero referencia pago.
	 *
	 * @return the numeroReferenciaPago
	 */
	public String getNumeroReferenciaPago() {
		return numeroReferenciaPago;
	}

	/**
	 * Sets the numero referencia pago.
	 *
	 * @param numeroReferenciaPago
	 *            the numeroReferenciaPago to set
	 */
	public void setNumeroReferenciaPago(String numeroReferenciaPago) {
		this.numeroReferenciaPago = numeroReferenciaPago;
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the nro factura.
	 *
	 * @return the nroFactura
	 */
	public String getNroFactura() {
		return numeroFactura;
	}

	/**
	 * Sets the nro factura.
	 *
	 * @param numeroFactura
	 *            the nroFactura to set
	 */
	public void setNroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nroCuentaProducto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the nroCuentaProducto to set
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

}
