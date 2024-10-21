/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaPadronCuitInEntity.
 *
 * @author alejandro_leal
 */
public class ConsultaPadronCuitInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** Opción de búsqueda *. */
	private String opcion;

	/**
	 * Agrumento de busqueda: Para opción 1: CUIT/CUIL, Para opción 2: Nro de
	 * Documento alineado a derecha *.
	 */
	private String argumento;

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
	 * Gets the opcion.
	 *
	 * @return the opcion, puede tener el DNI alineado a la derecha ó CUIT/CUIL
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * Sets the opcion.
	 *
	 * @param opcion
	 *            the new opcion
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	/**
	 * Gets the argumento.
	 *
	 * @return the argumento, que puede ser 1: DNI , 2:CUIT/CUIL
	 */
	public String getArgumento() {
		return argumento;
	}

	/**
	 * Sets the argumento.
	 *
	 * @param argumento
	 *            the argumento, que puede ser 1: DNI , 2:CUIT/CUIL
	 */
	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

}
