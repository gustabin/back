/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;

/**
 * The Interface ConsultaInhabilitadosDAO.
 *
 * @author alejandro_leal
 */
public interface ConsultaInhabilitadosDAO {

	/**
	 * Consulta inhabilitados.
	 *
	 * @param entity
	 *            the entity
	 * @return the consulta inhabilitados out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaInhabilitadosOutEntity consultaInhabilitados(ConsultaInhabilitadosInEntity entity) throws DAOException;

}
