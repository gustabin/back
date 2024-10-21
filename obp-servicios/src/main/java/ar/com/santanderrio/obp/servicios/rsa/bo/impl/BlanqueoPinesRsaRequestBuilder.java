package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.servicios.blanqueopin.dto.BlanqueoPinRSADTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

public class BlanqueoPinesRsaRequestBuilder extends AbstractRsaRequestBuilder {

	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
        EventData ed = new EventData();
        ed.setEventType(EventType.CARD_PIN_CHANGE);
		ed.setClientDefinedAttributeList(generarClientDefinedAttributeListBlanqueoPin(operacionDeRiesgo));
        return ed;
	}

	private FactList generarClientDefinedAttributeListBlanqueoPin(RsaDTO operacionDeRiesgo) {
		FactList factList = new FactList();
		BlanqueoPinRSADTO blanqueoPinRSADTO = (BlanqueoPinRSADTO) operacionDeRiesgo; 
		
        if(blanqueoPinRSADTO.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(blanqueoPinRSADTO.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}
		
		if(blanqueoPinRSADTO.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(blanqueoPinRSADTO.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
		
		return factList;
	}

}
