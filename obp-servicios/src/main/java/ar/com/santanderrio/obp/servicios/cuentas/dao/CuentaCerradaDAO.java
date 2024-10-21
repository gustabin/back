/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;

/**
 * The Interface CuentaCerradaDAO.
 */
public interface CuentaCerradaDAO {

	/**
	 * Obtener cuentas cerradas.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<List<CuentaCerrada>> obtenerCuentasCerradas(Cliente resumenCliente) throws DAOException;

}
