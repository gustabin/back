/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.LogIn;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 * @author ignacio.valek
 * @see {@link AbstractRsaRequestBuilder}
 * @since Nov 11, 2016
 */
public class LogInRsaRequestBuilder extends AbstractRsaRequestBuilder {

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
		LogIn logInDTO = (LogIn) operacionDeRiesgo;
		
		if ("SESSION_SIGNIN_GENERACION_CLAVE".equals(logInDTO.getLugarIngreso())) {
			ed.setEventType(EventType.SESSION_SIGNIN);
			ed.setClientDefinedEventType("GENERACION_CLAVE");
		} else if ("CHANGE_PASSWORD_GENERACION_CLAVE".equals(logInDTO.getLugarIngreso())){
			ed.setEventType(EventType.CHANGE_PASSWORD);
			ed.setClientDefinedEventType("GENERACION_CLAVE");
		} else {
			ed.setEventType(EventType.SESSION_SIGNIN);
		}

		if (logInDTO.isFromApp()) {
			ed.setEventType(EventType.SESSION_SIGNIN);
			ed.setClientDefinedEventType("SALTO");
			ed.setClientDefinedAttributeList(generarFactListSaltoCanal(logInDTO));
		}

		if (logInDTO.isFromObe()) {
			ed.setEventType(EventType.SESSION_SIGNIN);
			ed.setClientDefinedEventType("SALTO");
			ed.setClientDefinedAttributeList(generarFactListSaltoCanal(logInDTO));
		}

		if(logInDTO.getBiocatchResponseData() != null){
			FactList factList = new FactList();
			ed.setClientDefinedAttributeList(BiocatchRsaRequestBuilder.buildBiocatchRsaRequestBuilder(logInDTO.getBiocatchResponseData(), factList));
		}

		return ed;
	}

	private FactList generarFactListSaltoCanal(LogIn logInDTO) {
		FactList factList = new FactList();
		ClientDefinedFact customFactSalto = new ClientDefinedFact();

		if (logInDTO.isFromApp()) {
			customFactSalto.setName("CanalOrigen");
			customFactSalto.setValue("APP");
			customFactSalto.setDataType(DataType.STRING);
			factList.getFact().add(customFactSalto);
		} else if (logInDTO.isFromObe()) {
			customFactSalto.setName("CanalOrigen");
			customFactSalto.setValue("OBE");
			customFactSalto.setDataType(DataType.STRING);
			factList.getFact().add(customFactSalto);
		}

		return factList;
	}

}
