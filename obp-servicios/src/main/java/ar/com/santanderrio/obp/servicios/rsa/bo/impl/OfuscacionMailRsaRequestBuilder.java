package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.ofuscardato.dto.MostrarDatoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;

public class OfuscacionMailRsaRequestBuilder extends AbstractRsaRequestBuilder {

	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
        EventData ed = new EventData();
        ed.setClientDefinedEventType("MOSTRAR_EMAIL");
        ed.setEventType(EventType.VIEW_STATEMENT);
		ed.setClientDefinedAttributeList(generarFactList(operacionDeRiesgo));
        return ed;
	}

	private FactList generarFactList(RsaDTO operacionDeRiesgo) {
		FactList factList = new FactList();
        MostrarDatoDTO mostrarDatoRsaDTO = (MostrarDatoDTO) operacionDeRiesgo;

		if(mostrarDatoRsaDTO.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(mostrarDatoRsaDTO.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}
		
		if(mostrarDatoRsaDTO.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(mostrarDatoRsaDTO.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
		
        return factList;
	}
	
}
