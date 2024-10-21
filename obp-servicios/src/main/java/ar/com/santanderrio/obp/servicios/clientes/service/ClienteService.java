/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.service;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.Service;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface ClienteService.
 */
public interface ClienteService extends Service {

	/**
	 * Validar credenciales.
	 *
	 * @param cc
	 *            the cc
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ResumenCliente> validarCredenciales(CredencialCliente cc) throws ServiceException;

	/**
	 * Obtener cliente.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Cliente> obtenerCliente(ResumenCliente resumenCliente) throws ServiceException;

	/**
	 *  Valida credenciales que vienen de la APP
	 * @param cc
	 * @return
	 * @throws ServiceException
	 */
	Respuesta<ResumenCliente> validarCredencialesForApp(CredencialCliente cc) throws ServiceException;

}
