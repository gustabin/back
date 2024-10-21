/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;

/**
 * The Interface ChequesYValoresDebitosPendientesDeConfirmacionDAO.
 */
public interface ChequesYValoresDebitosPendientesDeConfirmacionDAO {

	/**
	 * Obtener debitos pendientes de confirmacion por cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DetalleMovimientoChequesYValoresEntity> obtenerDebitosPendientesDeConfirmacionPorCuenta(AbstractCuenta cuenta)
			throws DAOException;

}
