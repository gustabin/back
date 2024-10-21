package ar.com.santanderrio.obp.base.iatx.helpers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class IatxSenderMockImpl.
 *
 * @author pablo.martin.gore
 */
public class IatxSenderMockImpl implements IatxSender {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IatxSenderMockImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.iatx.helpers.IatxSender#send(ar.com.
	 * santanderrio.obp.base.iatx.entities.IatxRequest)
	 */
	@Override
	public String send(IatxRequest iatxRequest) throws IatxException {
		StringBuilder finalUrl = new StringBuilder(this.getBaseURL());
		finalUrl.append("/").append(iatxRequest.getNombreServicio()).append("?version=")
				.append(iatxRequest.getVersionServicio());
		LOGGER.info("Consulto mock por servicio {} version {}.", iatxRequest.getNombreServicio(),
				iatxRequest.getVersionServicio());
		try {
			String tramaRetornada = this.getRestTemplate().getForObject(finalUrl.toString(), String.class);
			LOGGER.info("Trama retornada {}", tramaRetornada);
			return tramaRetornada;
		} catch (RestClientException rce) {
			LOGGER.error("Error al consultar el servicio {} version {}.", iatxRequest.getNombreServicio(),
					iatxRequest.getVersionServicio(), rce);
			throw new ISBANRuntimeException("Servicio no mockeado");
		}
	}

	/**
	 * Gets the rest template.
	 *
	 * @return the rest template
	 */
	private RestTemplate getRestTemplate() {
		return ContextHolder.getContext().getBean(RestTemplate.class);
	}

	/**
	 * Gets the base URL.
	 *
	 * @return the base URL
	 */
	private String getBaseURL() {
		String baseURL = ContextHolder.getContext().getEnvironment().getProperty("IATX.BASEURL");
		
		return StringUtils.isEmpty(baseURL) ? "http://localhost:9099" : baseURL;
	}
}