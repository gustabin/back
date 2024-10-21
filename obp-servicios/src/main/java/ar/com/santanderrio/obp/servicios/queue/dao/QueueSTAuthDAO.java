package ar.com.santanderrio.obp.servicios.queue.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Interface QueueSTAuthDAO.
 */
public interface QueueSTAuthDAO {

	/**
	 * Obtener auth token.
	 *
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	String obtenerAuthToken() throws DAOException;

	/**
	 * Limpiar cache queue ST token.
	 */
	void limpiarCacheQueueSTToken();

}
