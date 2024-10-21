/*
 * 
 */
package ar.com.santanderrio.obp.servicios.session.manager;

import ar.com.santanderrio.obp.servicios.exception.SessionManagerException;
import ar.com.santanderrio.obp.servicios.session.dto.SessionControlDTO;

/**
 * Created by pablo.martin.gore on 8/31/2016.
 */
public interface SessionControlManager {

	/**
	 * Se crea una session nueva cuando no existe.
	 *
	 * @param nup
	 *            the nup
	 * @param token
	 *            the token
	 * @throws SessionManagerException
	 *             the session manager exception
	 */
	void saveOrUpdate(Long nup, String token) throws SessionManagerException;

	/**
	 * obtener token de una session exitente por nup.
	 *
	 * @param nup
	 *            the nup
	 * @return the session control DTO
	 * @throws SessionManagerException
	 *             the session manager exception
	 */
	SessionControlDTO get(Long nup) throws SessionManagerException;
}
