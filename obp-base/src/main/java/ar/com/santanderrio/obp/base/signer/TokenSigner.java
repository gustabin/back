/**
 * 
 */
package ar.com.santanderrio.obp.base.signer;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * Api por la cual firmar el token creado.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface TokenSigner {

	/**
	 * Obtener un token firmado.
	 *
	 * @param tokenCode
	 *            the token code
	 * @param entidades
	 *            the entidades
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	String obtenerTokenFirmado(String tokenCode, Entity... entidades) throws DAOException;
}
