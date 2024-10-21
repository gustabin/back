/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface SoftTokenDAO.
 */
public interface SoftTokenDAO {

	/**
	 * Ejecutar validacion token.
	 *
	 * @author b039542 - ignacio_valek@itrsa.com.ar - 20/09/2016
	 * @param token
	 *            the token
	 * @param ip
	 *            (sin puntos)
	 * @param cliente
	 *            the cliente
	 * @return resultado de la validacion de token
	 * @throws DAOException
	 *             the DAO exception
	 */
	boolean ejecutarValidacionToken(String token, String ip, Cliente cliente) throws DAOException;

}
