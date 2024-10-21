/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Interface ClienteCitiBO.
 */
public interface ClienteCitiBO {

	/**
	 * Checks if is ex cliente citi.
	 *
	 * @param nup
	 *            the nup
	 * @return the boolean
	 */
	Respuesta<Boolean> isExCiti(String nup);

}
