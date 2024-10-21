package ar.com.santanderrio.obp.base.iatx.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import iatx.Iatx;

// TODO: Auto-generated Javadoc
/**
 * The Class IatxSenderImpl.
 */
public class IatxSenderImpl implements IatxSender {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IatxSenderImpl.class);

	/** The Constant POSICION_INICIAL_PW_RACF_40. */
	private static final int POSICION_INICIAL_PW_RACF_40 = 40;

	/** The Constant POSICION_FINAL_PW_RACF_48. */
	private static final int POSICION_FINAL_PW_RACF_48 = 48;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.base.iatx.helpers.IatxSender#send(ar.com.santanderrio
	 * .base.iatx.entities.IatxRequest)
	 */
	@Override
	public String send(IatxRequest iatxRequest) throws IatxException {

		LOGGER.info("{}, {}", iatxRequest.getNombreServicio(), iatxRequest.getVersionServicio());

		IatxRequestData iatxRequestData = iatxRequest.getData();

		iatxRequestData.setNombreServicio(iatxRequest.getNombreServicio());
		iatxRequestData.setVersionServicio(iatxRequest.getVersionServicio());

		String rqd = iatxRequestData.getData();
		String aVuelta = null;

		if (!iatxRequestData.isNoLogRequest()) {
			StringBuilder sb = new StringBuilder(rqd);
			// censuro el pwRacf
			for (int n = POSICION_INICIAL_PW_RACF_40; n < POSICION_FINAL_PW_RACF_48; ++n) {
				sb.setCharAt(n, '*');
			}
			if (iatxRequestData.getNReqCensurar() != -1) {
				// le sumo 1 porque se informa el delim
				IatxUtil.censurar(sb, iatxRequestData.getNResCensurar() + 1);
			}
			LOGGER.info("IATREQ<{}>", sb.toString());
		} else {
			LOGGER.info("IATREQ<{}>", rqd);
		}

		try {
			aVuelta = doSend(rqd);
		} catch (Exception e) {
			throw new IatxException(e);
		}

		if (!iatxRequestData.isNoLogResponse()) {
			// el pwRacf ya viene censurado!
			if (iatxRequestData.getNResCensurar() != -1) {
				StringBuilder sb1 = new StringBuilder(aVuelta);
				// le sumo 1 porque se informa el delim
				IatxUtil.censurar(sb1, iatxRequestData.getNResCensurar() + 1);
				LOGGER.info("IATRES<{}>", sb1.toString());
			} else {
				LOGGER.info("IATRES<{}>", aVuelta);
			}
		} else {
			LOGGER.info("implementacion no disponible.");
		}
		return aVuelta;
	}

	/**
	 * Do send.
	 *
	 * @param rqd
	 *            the rqd
	 * @return the string
	 * @throws IatxException
	 *             the iatx exception
	 */
	public String doSend(String rqd) throws iatx.exceptions.IatxException {
		return Iatx.send(rqd);
	}

}
