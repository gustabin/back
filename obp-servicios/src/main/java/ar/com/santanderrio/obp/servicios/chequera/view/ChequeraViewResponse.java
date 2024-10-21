/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ChequeraViewResponse.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChequeraViewResponse {

	/** The cuentas. */
	private List<CuentaView> cuentas;

	/** The cantidad cheques comun view. */
	private List<String> cantidadChequesComunView;

	/** The cantidad cheques pago diferido view. */
	private List<String> cantidadChequesPagoDiferidoView;

	/** The cantidad chequera comun view. */
	private List<String> cantidadChequeraComunView;

	/** The cantidad chequera pago diferido view. */
	private List<String> cantidadChequeraPagoDiferidoView;

	/** The moneda chequera. */
	private String monedaChequera;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the cuentas to set
	 */
	public void setCuentas(List<CuentaView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the cantidad cheques comun view.
	 *
	 * @return the cantidadChequesComunView
	 */
	public List<String> getCantidadChequesComunView() {
		return cantidadChequesComunView;
	}

	/**
	 * Sets the cantidad cheques comun view.
	 *
	 * @param cantidadChequesComunView
	 *            the cantidadChequesComunView to set
	 */
	public void setCantidadChequesComunView(List<String> cantidadChequesComunView) {
		this.cantidadChequesComunView = cantidadChequesComunView;
	}

	/**
	 * Gets the cantidad cheques pago diferido view.
	 *
	 * @return the cantidadChequesPagoDiferidoView
	 */
	public List<String> getCantidadChequesPagoDiferidoView() {
		return cantidadChequesPagoDiferidoView;
	}

	/**
	 * Sets the cantidad cheques pago diferido view.
	 *
	 * @param cantidadChequesPagoDiferidoView
	 *            the cantidadChequesPagoDiferidoView to set
	 */
	public void setCantidadChequesPagoDiferidoView(List<String> cantidadChequesPagoDiferidoView) {
		this.cantidadChequesPagoDiferidoView = cantidadChequesPagoDiferidoView;
	}

	/**
	 * Gets the cantidad chequera comun view.
	 *
	 * @return the cantidadChequeraComunView
	 */
	public List<String> getCantidadChequeraComunView() {
		return cantidadChequeraComunView;
	}

	/**
	 * Sets the cantidad chequera comun view.
	 *
	 * @param cantidadChequeraComunView
	 *            the cantidadChequeraComunView to set
	 */
	public void setCantidadChequeraComunView(List<String> cantidadChequeraComunView) {
		this.cantidadChequeraComunView = cantidadChequeraComunView;
	}

	/**
	 * Gets the cantidad chequera pago diferido view.
	 *
	 * @return the cantidadChequeraPagoDiferidoView
	 */
	public List<String> getCantidadChequeraPagoDiferidoView() {
		return cantidadChequeraPagoDiferidoView;
	}

	/**
	 * Sets the cantidad chequera pago diferido view.
	 *
	 * @param cantidadChequeraPagoDiferidoView
	 *            the cantidadChequeraPagoDiferidoView to set
	 */
	public void setCantidadChequeraPagoDiferidoView(List<String> cantidadChequeraPagoDiferidoView) {
		this.cantidadChequeraPagoDiferidoView = cantidadChequeraPagoDiferidoView;
	}

	/**
	 * Gets the moneda chequera.
	 *
	 * @return the monedaChequera
	 */
	public String getMonedaChequera() {
		return monedaChequera;
	}

	/**
	 * Sets the moneda chequera.
	 *
	 * @param monedaChequera
	 *            the monedaChequera to set
	 */
	public void setMonedaChequera(String monedaChequera) {
		this.monedaChequera = monedaChequera;
	}

}
