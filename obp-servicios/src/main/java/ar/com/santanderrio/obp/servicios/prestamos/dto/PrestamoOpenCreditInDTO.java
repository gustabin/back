/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * OLYMPUS PrestamoOpenCreditInDTO.
 *
 * @author Silvina_Luque
 */
public class PrestamoOpenCreditInDTO {

	/** The Cliente. */
	Cliente cliente;

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
