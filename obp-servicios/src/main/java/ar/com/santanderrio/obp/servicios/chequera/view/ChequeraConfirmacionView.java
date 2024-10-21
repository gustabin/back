/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ChequeraConfirmacionView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChequeraConfirmacionView {

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The cantidad chequera. */
	private String cantidadChequera;

	/** The cantidad cheque. */
	private String cantidadCheque;

	/** The tipo chequera. */
	private String tipoChequera;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The pedido chequera ok. */
	private boolean pedidoChequeraOk;

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

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
	 * Gets the cantidad chequera.
	 *
	 * @return the cantidad chequera
	 */
	public String getCantidadChequera() {
		return cantidadChequera;
	}

	/**
	 * Sets the cantidad chequera.
	 *
	 * @param cantidadChequera
	 *            the new cantidad chequera
	 */
	public void setCantidadChequera(String cantidadChequera) {
		this.cantidadChequera = cantidadChequera;
	}

	/**
	 * Gets the cantidad cheque.
	 *
	 * @return the cantidad cheque
	 */
	public String getCantidadCheque() {
		return cantidadCheque;
	}

	/**
	 * Sets the cantidad cheque.
	 *
	 * @param cantidadCheque
	 *            the new cantidad cheque
	 */
	public void setCantidadCheque(String cantidadCheque) {
		this.cantidadCheque = cantidadCheque;
	}

	/**
	 * Gets the tipo chequera.
	 *
	 * @return the tipo chequera
	 */
	public String getTipoChequera() {
		return tipoChequera;
	}

	/**
	 * Sets the tipo chequera.
	 *
	 * @param tipoChequera
	 *            the new tipo chequera
	 */
	public void setTipoChequera(String tipoChequera) {
		this.tipoChequera = tipoChequera;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Checks if is pedido chequera ok.
	 *
	 * @return true, if is pedido chequera ok
	 */
	public boolean isPedidoChequeraOk() {
		return pedidoChequeraOk;
	}

	/**
	 * Sets the pedido chequera ok.
	 *
	 * @param pedidoChequeraOk
	 *            the new pedido chequera ok
	 */
	public void setPedidoChequeraOk(boolean pedidoChequeraOk) {
		this.pedidoChequeraOk = pedidoChequeraOk;
	}
}
