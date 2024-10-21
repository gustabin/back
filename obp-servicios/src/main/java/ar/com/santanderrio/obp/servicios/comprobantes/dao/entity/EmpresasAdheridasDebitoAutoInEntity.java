/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class EmpresasAdheridasDebitoAutoInEntity.
 */
public class EmpresasAdheridasDebitoAutoInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The nombre apellido. */
	private String nombreApellido;

	/**
	 * Bloque de datos para rellamada
	 */
	private String bloqueRellamada;

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
	 * @param cliente the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the nombre apellido.
	 *
	 * @return the nombre apellido
	 */
	public String getNombreApellido() {
		return nombreApellido;
	}

	/**
	 * Sets the nombre apellido.
	 *
	 * @param nombreApellido the new nombre apellido
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	/**
	 * getter bloque rellamada
	 * @return
	 */
	public String getBloqueRellamada() {
		return bloqueRellamada;
	}

	/**
	 * setter bloque rellamada
	 * @param bloqueRellamada
	 */
	public void setBloqueRellamada(String bloqueRellamada) {
		this.bloqueRellamada = bloqueRellamada;
	}

}
