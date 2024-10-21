/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.monedero.entities.MonederoActivacionInEntities;

/**
 * The Interface MonederoActivacionDAO.
 *
 * @author alejandro_leal
 */
public interface MonederoActivacionDAO {

	/**
	 * Activar.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<ResultadoTransaccion> activar(MonederoActivacionInEntities entity) throws DAOException;

}
