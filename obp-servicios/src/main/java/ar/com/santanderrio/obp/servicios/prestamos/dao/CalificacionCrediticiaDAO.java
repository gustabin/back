/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;

/**
 * The Interface CalificacionCrediticiaDAO.
 *
 * @author
 */
public interface CalificacionCrediticiaDAO {

	/**
	 * Obtener situacion crediticia.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacionServicio
	 *            the operacion servicio
	 * @return the calificacion crediticia out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	CalificacionCrediticiaOutEntity obtenerSituacionCrediticia(Cuenta cuenta, String operacionServicio)
			throws DAOException;
}