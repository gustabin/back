/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author marcelo.ruiz
 *
 */
public class CuentaTituloInEntity {

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
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
