/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class EjecutarTransferencia.
 *
 * @author B041299
 */
public class EjecutarTransferencia extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cliente. */
	private Cliente cliente;

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
}
