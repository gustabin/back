/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 * @author ignacio.valek
 * @see {@link AbstractRsaRequestBuilder}
 * @since Nov 11, 2016
 */
public class CambioUsuarioInRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.
	 * OperacionDeRiesgo)
	 */
	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
		EventData ed = new EventData();
		ed.setEventType(EventType.CHANGE_LOGIN_ID);
		return ed;
	}

}
