/*
 * 
 */
package ar.com.santanderrio.obp.servicios.session.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.session.dto.SessionControlDTO;

/**
 * Created by pablo.martin.gore on 8/31/2016.
 */

public interface SessionControlDAO {

	/**
	 * Metodo para guardar o actualizar el estado de la session. El store al que
	 * se llama realiza un merge sobre el registro indicado. Si el nup recibido
	 * existe lo actualiza sino lo inserta.
	 *
	 * @param nup
	 *            the nup
	 * @param token
	 *            the token
	 * @throws DAOException
	 *             the DAO exception
	 */
	void saveOrUpdate(Long nup, String token) throws DAOException;

	/**
	 * Obtener token de una session exitente por nup.
	 *
	 * @param nup
	 *            the nup
	 * @return the session control DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	SessionControlDTO get(Long nup) throws DAOException;
}
