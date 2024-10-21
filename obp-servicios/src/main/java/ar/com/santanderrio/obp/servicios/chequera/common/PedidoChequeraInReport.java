/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.common;

import java.util.Collection;
import java.util.Vector;

/**
 * The Class PedidoChequeraInReport.
 */
public class PedidoChequeraInReport {

	/** The cantidad chequera. */
	private String cantidadChequera;

	/** The cantidad cheques. */
	private String cantidadCheques;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/**
	 * Gets the cantidad chequera.
	 *
	 * @return the cantidadChequera
	 */
	public String getCantidadChequera() {
		return cantidadChequera;
	}

	/**
	 * Sets the cantidad chequera.
	 *
	 * @param cantidadChequera
	 *            the cantidadChequera to set
	 */
	public void setCantidadChequera(String cantidadChequera) {
		this.cantidadChequera = cantidadChequera;
	}

	/**
	 * Gets the cantidad cheques.
	 *
	 * @return the cantidadCheques
	 */
	public String getCantidadCheques() {
		return cantidadCheques;
	}

	/**
	 * Sets the cantidad cheques.
	 *
	 * @param cantidadCheques
	 *            the cantidadCheques to set
	 */
	public void setCantidadCheques(String cantidadCheques) {
		this.cantidadCheques = cantidadCheques;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the pedido chequera in report list.
	 *
	 * @return the pedido chequera in report list
	 */
	@SuppressWarnings("rawtypes")
	public static Collection getPedidoChequeraInReportList() {
		Vector<PedidoChequeraInReport> lista = new Vector<PedidoChequeraInReport>();

		PedidoChequeraInReport pedido = new PedidoChequeraInReport();
		pedido.cantidadChequera = "10";
		pedido.cantidadCheques = "1";
		pedido.numeroComprobante = "012345";
		pedido.tipoCuenta = "00";
		lista.add(pedido);
		return lista;
	}
}
