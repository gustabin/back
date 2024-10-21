/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * The Class RescateFondoRsaRequestBuilder.
 */
public class RescateFondoRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
	 */
	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
		EventData ed = new EventData();
		ed.setClientDefinedEventType(RSAConstants.EVT_CLTDEF_RESCATEDEFONDOS);
		ed.setEventType(EventType.CLIENT_DEFINED);
		return ed;
	}

}
