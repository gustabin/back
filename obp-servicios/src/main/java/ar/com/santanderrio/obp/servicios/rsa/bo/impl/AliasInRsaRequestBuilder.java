/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AliasRsaView;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 */
public class AliasInRsaRequestBuilder extends AbstractRsaRequestBuilder {

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
		AliasRsaView operacion = (ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AliasRsaView) operacionDeRiesgo;
		ed.setClientDefinedAttributeList(generarFactListAliasCBU(operacion));
		ed.setClientDefinedEventType(RSAConstants.GESTION_ALIAS);
		ed.setEventType(EventType.CLIENT_DEFINED);
		return ed;
	}

	/**
	 * Generar fact list modificacion limite debito.
	 *
	 * @param operacion
	 *            the operacion
	 * @return the fact list
	 */
	private FactList generarFactListAliasCBU(AliasRsaView operacion) {
		FactList factList = new FactList();
		ClientDefinedFact cl = new ClientDefinedFact();
		cl.setName("canal");
		cl.setValue("OBP");
		cl.setDataType(DataType.STRING);
		factList.getFact().add(cl);
		ClientDefinedFact cl2 = new ClientDefinedFact();
		cl2.setName("Alias");
		cl2.setValue(operacion.getAliasCbu());
		cl2.setDataType(DataType.STRING);
		factList.getFact().add(cl2);
		return factList;
	}

}
