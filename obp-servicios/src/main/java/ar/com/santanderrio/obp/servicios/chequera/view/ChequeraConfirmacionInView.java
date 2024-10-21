/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ChequeraConfirmacionInView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChequeraConfirmacionInView {
	/* La cuenta viene en formato 023-123456/7 */
	/** The cantidad cheques comun. */
	// private String cuenta;
	private String cantidadChequesComun;

	/** The cantidad cheques pago diferido. */
	private String cantidadChequesPagoDiferido;

	/** The cantidad chequera comun. */
	private String cantidadChequeraComun;

	/** The cantidad chequera pago diferido. */
	private String cantidadChequeraPagoDiferido;

	/** The moneda. */
	private String moneda;

	/** The pedido chequera comun. */
	private boolean pedidoChequeraComun;

	/** The pedido chequera pago diferido. */
	private boolean pedidoChequeraPagoDiferido;

	/** The cuenta seleccionada. */
	private CuentaView cuentaSeleccionada;
	
	/** The flagVolver. */
	private boolean flagVolver;

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Checks if is pedido chequera comun.
	 *
	 * @return true, if is pedido chequera comun
	 */
	public boolean isPedidoChequeraComun() {
		return pedidoChequeraComun;
	}

	/**
	 * Sets the pedido chequera comun.
	 *
	 * @param pedidoChequeraComun
	 *            the new pedido chequera comun
	 */
	public void setPedidoChequeraComun(boolean pedidoChequeraComun) {
		this.pedidoChequeraComun = pedidoChequeraComun;
	}

	/**
	 * Checks if is pedido chequera pago diferido.
	 *
	 * @return true, if is pedido chequera pago diferido
	 */
	public boolean isPedidoChequeraPagoDiferido() {
		return pedidoChequeraPagoDiferido;
	}

	/**
	 * Sets the pedido chequera pago diferido.
	 *
	 * @param pedidoChequeraPagoDiferido
	 *            the new pedido chequera pago diferido
	 */
	public void setPedidoChequeraPagoDiferido(boolean pedidoChequeraPagoDiferido) {
		this.pedidoChequeraPagoDiferido = pedidoChequeraPagoDiferido;
	}

	/**
	 * Gets the cuenta seleccionada.
	 *
	 * @return the cuenta seleccionada
	 */
	public CuentaView getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * Sets the cuenta seleccionada.
	 *
	 * @param cuentaSeleccionada
	 *            the new cuenta seleccionada
	 */
	public void setCuentaSeleccionada(CuentaView cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	/**
	 * Gets the cantidad cheques comun.
	 *
	 * @return the cuenta
	 */
	// public String getCuenta() {
	// return cuenta;
	// }
	// /**
	// * @param cuenta the cuenta to set
	// */
	// public void setCuenta(String cuenta) {
	// this.cuenta = cuenta;
	// }
	/**
	 * @return the cantidadChequeComun
	 */
	public String getCantidadChequesComun() {
		return cantidadChequesComun;
	}

	/**
	 * Sets the cantidad cheques comun.
	 *
	 * @param cantidadChequesComun
	 *            the new cantidad cheques comun
	 */
	public void setCantidadChequesComun(String cantidadChequesComun) {
		this.cantidadChequesComun = cantidadChequesComun;
	}

	/**
	 * Gets the cantidad cheques pago diferido.
	 *
	 * @return the cantidadChequePagoDiferido
	 */
	public String getCantidadChequesPagoDiferido() {
		return cantidadChequesPagoDiferido;
	}

	/**
	 * Sets the cantidad cheques pago diferido.
	 *
	 * @param cantidadChequesPagoDiferido
	 *            the new cantidad cheques pago diferido
	 */
	public void setCantidadChequesPagoDiferido(String cantidadChequesPagoDiferido) {
		this.cantidadChequesPagoDiferido = cantidadChequesPagoDiferido;
	}

	/**
	 * Gets the cantidad chequera comun.
	 *
	 * @return the cantidadChequeraComun
	 */
	public String getCantidadChequeraComun() {
		return cantidadChequeraComun;
	}

	/**
	 * Sets the cantidad chequera comun.
	 *
	 * @param cantidadChequeraComun
	 *            the cantidadChequeraComun to set
	 */
	public void setCantidadChequeraComun(String cantidadChequeraComun) {
		this.cantidadChequeraComun = cantidadChequeraComun;
	}

	/**
	 * Gets the cantidad chequera pago diferido.
	 *
	 * @return the cantidadChequeraPagoDiferido
	 */
	public String getCantidadChequeraPagoDiferido() {
		return cantidadChequeraPagoDiferido;
	}

	/**
	 * Sets the cantidad chequera pago diferido.
	 *
	 * @param cantidadChequeraPagoDiferido
	 *            the cantidadChequeraPagoDiferido to set
	 */
	public void setCantidadChequeraPagoDiferido(String cantidadChequeraPagoDiferido) {
		this.cantidadChequeraPagoDiferido = cantidadChequeraPagoDiferido;
	}

	/**
	 * Checks if is flag volver.
	 *
	 * @return true, if is flag volver
	 */
	public boolean isFlagVolver() {
		return flagVolver;
	}

	/**
	 * Sets the flag volver.
	 *
	 * @param flagVolver
	 *            the new flag volver
	 */
	public void setFlagVolver(boolean flagVolver) {
		this.flagVolver = flagVolver;
	}
}
