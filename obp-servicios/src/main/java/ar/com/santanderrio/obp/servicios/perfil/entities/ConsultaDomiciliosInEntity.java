/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * ConsultaDomiciliosInEntity.
 *
 * @author Silvina_Luque
 */
public class ConsultaDomiciliosInEntity {

	/** The cliente. */
	private Cliente cliente;
	
	/** The secuencia domicilio. */
	private  String secuenciaDomicilio = "000";

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
	 * Gets the secuencia domicilio.
	 *
	 * @return the secuencia domicilio
	 */
	public String getSecuenciaDomicilio() {
		return secuenciaDomicilio;
	}

	/**
	 * Sets the secuencia domicilio.
	 *
	 * @param secuenciaDomicilio the new secuencia domicilio
	 */
	public void setSecuenciaDomicilio(String secuenciaDomicilio) {
		this.secuenciaDomicilio = secuenciaDomicilio;
	}

}
