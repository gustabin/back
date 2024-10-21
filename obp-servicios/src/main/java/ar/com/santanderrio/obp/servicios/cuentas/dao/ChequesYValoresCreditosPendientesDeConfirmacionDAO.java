/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;

/**
 * The Interface ChequesYValoresCreditosPendientesDeConfirmacionDAO.
 */
public interface ChequesYValoresCreditosPendientesDeConfirmacionDAO {

	/**
	 * Obtener creditos pendientes de confirmacion por cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DetalleMovimientoChequesYValoresEntity> obtenerCreditosPendientesDeConfirmacionPorCuenta(AbstractCuenta cuenta)
			throws DAOException;

}
