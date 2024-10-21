/*
 * 
 */
package ar.com.santanderrio.obp.servicios.iatx.dao;

import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;

/**
 * The Interface IatxComm.
 */
public interface IatxComm {

	/**
	 * Exec.
	 *
	 * @param request
	 *            the request
	 * @return the iatx response
	 * @throws IatxException
	 *             the iatx exception
	 */
	IatxResponse exec(IatxRequest request) throws IatxException;

}
