/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.mobile.dao;

import org.springframework.dao.EmptyResultDataAccessException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile;

/**
 * The Interface TokenMobileDAO.
 */
public interface TokenMobileDAO {

	/**
	 * Consultar token mobile.
	 *
	 * @param nup
	 *            the nup
	 * @return the token mobile
	 * @throws DAOException
	 *             the DAO exception
	 * @throws EmptyResultDataAccessException
	 *             the empty result data access exception
	 */
	TokenMobile consultarTokenMobile(String nup) throws DAOException, EmptyResultDataAccessException;

}
