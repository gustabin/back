package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

public class ExtraccionSinTarjetaRsaRequestBuilder extends AbstractRsaRequestBuilder {

	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
        EventData ed = new EventData();
        ed.setClientDefinedEventType("NEW_REQUEST_ATM");
        ed.setEventType(EventType.CLIENT_DEFINED);
        return ed;
	}

}
