/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloOutEntity;

/**
 * The Interface CuentaTituloDAO.
 *
 * @author marcelo.ruiz
 */
public interface CuentaTituloDAO {

	/**
	 * Obtiene las cuentas titulo de un cliente.
	 *
	 * @param in
	 *            : cliente
	 * @return the cuenta titulo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	CuentaTituloOutEntity obtenerCuentasTitulo(CuentaTituloInEntity in) throws DAOException;
}
