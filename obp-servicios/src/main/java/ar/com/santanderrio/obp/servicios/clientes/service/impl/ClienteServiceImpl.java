/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.service.ClienteService;

/**
 * The Class ClienteServiceImpl.
 */
@Component
public class ClienteServiceImpl implements ClienteService {

	/** The cliente bo. */
	@Autowired
	private ClienteBO clienteBO;

	/** The Credenciales BO. */
	@Autowired
	private CredencialesBO credencialesBO;

	@Override
	public Respuesta<ResumenCliente> validarCredenciales(CredencialCliente cc) throws ServiceException {
		try {
			return credencialesBO.validarCredenciales(cc);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Respuesta<ResumenCliente> validarCredencialesForApp(CredencialCliente cc) throws ServiceException {
		try {
			return credencialesBO.validarCredencialesForApp(cc);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Respuesta<Cliente> obtenerCliente(ResumenCliente resumenCliente) throws ServiceException {
		try {
			return clienteBO.obtenerCliente(resumenCliente);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

}
