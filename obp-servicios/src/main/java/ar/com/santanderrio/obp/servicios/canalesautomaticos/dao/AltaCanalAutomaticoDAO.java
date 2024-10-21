/**
 * 
 */
package ar.com.santanderrio.obp.servicios.canalesautomaticos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;

/**
 * The Interface AltaCanalAutomaticoDAO.
 *
 * @author alejandro_leal
 */
public interface AltaCanalAutomaticoDAO {

	/**
	 * Alta canal automatico.
	 *
	 * @param entity
	 *            the entity
	 * @return the alta canal automatico out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	AltaCanalAutomaticoOutEntity altaCanalAutomatico(AltaCanalAutomaticoInEntity entity) throws DAOException;

}
