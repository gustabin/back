/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class BilleteraRsaRequestBuilder.
 *
 */
public class BilleteraRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BilleteraRsaRequestBuilder.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
	 */
	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
		LOGGER.debug(operacionDeRiesgo.getTipoOperacion().getDescripcion());
		return new EventData();
	}
}
