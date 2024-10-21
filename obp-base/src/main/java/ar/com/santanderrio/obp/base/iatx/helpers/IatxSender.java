package ar.com.santanderrio.obp.base.iatx.helpers;

import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IatxSender.
 */
public interface IatxSender {

	/**
	 * Send.
	 *
	 * @param iatxRequest
	 *            the iatx request
	 * @return the string
	 * @throws IatxException
	 *             the iatx exception
	 */
	String send(IatxRequest iatxRequest) throws IatxException;

}
