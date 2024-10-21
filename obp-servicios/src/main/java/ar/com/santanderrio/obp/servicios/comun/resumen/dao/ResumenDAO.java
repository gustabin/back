/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.resumen.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.resumen.entity.ResumenInEntity;

/**
 * The Interface ResumenDAO.
 */
public interface ResumenDAO {

	/**
	 * Grabar motivo.
	 *
	 * @param input
	 *            the input
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	String grabarMotivo(ResumenInEntity input) throws DAOException;

}
