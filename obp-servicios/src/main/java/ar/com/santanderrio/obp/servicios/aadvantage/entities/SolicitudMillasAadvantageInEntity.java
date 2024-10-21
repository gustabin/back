/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class SolicitudMillasAadvantageInEntity.
 */
public class SolicitudMillasAadvantageInEntity {

	/** The cliente. */
	private Cliente cliente;
	
	/** The cant meses. */
	private Integer cantMeses;

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

	/**
	 * Gets the cant meses.
	 *
	 * @return the cant meses
	 */
	public Integer getCantMeses() {
		return cantMeses;
	}

	/**
	 * Sets the cant meses.
	 *
	 * @param cantMeses
	 *            the new cant meses
	 */
	public void setCantMeses(Integer cantMeses) {
		this.cantMeses = cantMeses;
	}
}
