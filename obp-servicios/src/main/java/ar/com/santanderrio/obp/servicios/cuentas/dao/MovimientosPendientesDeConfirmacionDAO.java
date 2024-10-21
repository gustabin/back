/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoDeCuenta;

/**
 * The Interface MovimientosPendientesDeConfirmacionDAO.
 */
public interface MovimientosPendientesDeConfirmacionDAO {

	/**
	 * Obtener movimientos pendientes de confirmacion por cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<MovimientoDeCuenta> obtenerMovimientosPendientesDeConfirmacionPorCuenta(AbstractCuenta cuenta)
			throws DAOException;

}
