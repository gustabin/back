/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;

/**
 * The Class PedidoChequeraInEntity.
 */
public class PedidoChequeraInEntity {

	/** The cuenta. */
	private AbstractCuenta cuenta;

	/** The cantidad cheque. */
	private String cantidadCheque;

	/** The cantidad chequera. */
	private String cantidadChequera;

	/** The cliente. */
	private Cliente cliente;

	/** The tipo chequera. */
	private String tipoChequera;

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public AbstractCuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(AbstractCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the cantidad cheque.
	 *
	 * @return the cantidadCheque
	 */
	public String getCantidadCheque() {
		return cantidadCheque;
	}

	/**
	 * Sets the cantidad cheque.
	 *
	 * @param cantidadCheque
	 *            the cantidadCheque to set
	 */
	public void setCantidadCheque(String cantidadCheque) {
		this.cantidadCheque = cantidadCheque;
	}

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
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the tipo chequera.
	 *
	 * @return the tipoChequera
	 */
	public String getTipoChequera() {
		return tipoChequera;
	}

	/**
	 * Sets the tipo chequera.
	 *
	 * @param tipoChequera
	 *            the tipoChequera to set
	 */
	public void setTipoChequera(String tipoChequera) {
		this.tipoChequera = tipoChequera;
	}

}
