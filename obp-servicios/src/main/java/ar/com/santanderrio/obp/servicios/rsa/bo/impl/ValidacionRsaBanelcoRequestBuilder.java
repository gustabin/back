package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

public class ValidacionRsaBanelcoRequestBuilder extends AbstractRsaRequestBuilder {

	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
        EventData ed = new EventData();
        ed.setClientDefinedEventType("GENERACION_CLAVE");
        ed.setEventType(EventType.SESSION_SIGNIN);
        return ed;
	}
	
}
