/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FirmarCuentasInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FirmarCuentasOutEntity;

/**
 * The Interface FirmarCuentasDAO.
 */
public interface FirmarCuentasDAO {

	/**
	 * Firmar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @return the firmar cuentas out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	FirmarCuentasOutEntity firmar(Cliente cliente, FirmarCuentasInEntity request) throws DAOException;

}
