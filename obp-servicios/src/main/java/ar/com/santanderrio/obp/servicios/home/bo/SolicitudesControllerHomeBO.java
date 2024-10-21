/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface SolicitudesControllerHomeBO.
 */
public interface SolicitudesControllerHomeBO {

	/**
	 * Aplica cuentas y paquetes.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaCuentasPaquetes(Cliente cliente);

	/**
	 * Aplica prestamos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaPrestamos(Cliente cliente);

	/**
	 * Aplica tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaTarjetas(Cliente cliente);

	/**
	 * Aplica seguros.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaSeguros(Cliente cliente);

	/**
	 * Aplica turnos online.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	

	/**
	 * Aplica otros medios de pago.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	Boolean aplicaOtrosMediosPago(Cliente cliente);

}