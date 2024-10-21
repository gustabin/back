/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesOutEntity;

/**
 * The Interface IntervinientesDAO.
 */
public interface IntervinientesDAO {

	/**
	 * Obtener interviniente titular.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the interviniente
	 * @throws DAOException
	 *             the DAO exception
	 */
	Interviniente obtenerIntervinienteTitular(Cliente cliente, Cuenta cuenta) throws DAOException;

	/**
	 * Obtiene Coleccion de intervinientes Titular + Cotitulares.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the intervinientes out entity
	 * @throws DAOException
	 *             the DAO exception
	 */

	IntervinientesOutEntity obtenerIntervinientes(Cuenta cuenta) throws DAOException;

}