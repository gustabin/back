/*
 * 
 */
package ar.com.santanderrio.obp.servicios.session.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.exception.SessionManagerException;
import ar.com.santanderrio.obp.servicios.session.dao.SessionControlDAO;
import ar.com.santanderrio.obp.servicios.session.dto.SessionControlDTO;
import ar.com.santanderrio.obp.servicios.session.manager.SessionControlManager;

/**
 * Created by pablo.martin.gore on 8/31/2016.
 */

@Component("sessionControlManager")
public class SessionControlManagerImpl implements SessionControlManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionControlManagerImpl.class);

	/** The session control DAO. */
	@Autowired
	private SessionControlDAO sessionControlDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.session.manager.SessionControlManager#
	 * save(java.lang.Long, java.lang.String)
	 */
	@Override
	public void saveOrUpdate(Long nup, String token) throws SessionManagerException {
		try {
			sessionControlDAO.saveOrUpdate(nup, token);
		} catch (DAOException e) {
			LOGGER.error("Se produjo un error al registrar el token {} para el usuario {}.", token, nup, e);
			throw new SessionManagerException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.session.manager.SessionControlManager#
	 * get(java.lang.Long)
	 */
	@Override
	public SessionControlDTO get(Long nup) throws SessionManagerException {
		try {
			return sessionControlDAO.get(nup);
		} catch (DAOException e) {
			LOGGER.error("Se produjo un error al obtener el token para el usuario {}", nup, e);
			throw new SessionManagerException(e);
		}
	}
}
