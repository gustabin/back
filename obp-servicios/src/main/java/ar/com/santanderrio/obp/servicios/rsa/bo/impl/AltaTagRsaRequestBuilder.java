/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * The Class AltaTagRsaRequestBuilder.
 *
 * @author nicolas_lavagna
 */
public class AltaTagRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.rsaDTO)
	 */
	@Override
	public EventData build(RsaDTO altaTag) {
		EventData ed = new EventData();
		ed.setEventType(EventType.REQUEST_NEW_CARD);
		ed.setClientDefinedEventType(RSAConstants.EVT_CLTDEF_ADHPAGOAUTO);
		return ed;
	}

}
