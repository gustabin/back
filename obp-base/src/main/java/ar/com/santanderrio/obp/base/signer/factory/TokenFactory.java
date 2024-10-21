/**
 * 
 */
package ar.com.santanderrio.obp.base.signer.factory;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.base.signer.token.Token;

// TODO: Auto-generated Javadoc
/**
 * Establecer la firma para crear factorias de token segun la funcionalidad a
 * implementar.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface TokenFactory {

	/**
	 * Crear token.
	 *
	 * @param tokenCode
	 *            the token code
	 * @param entidades
	 *            the entidades
	 * @return the token
	 */
	Token crearToken(String tokenCode, Entity... entidades);
}
