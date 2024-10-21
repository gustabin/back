/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;

/**
 * The Class CambioDomicilioRsaRequestBuilder.
 * 
 * @author Silvina_Luque
 */
public class CambioDomicilioRsaRequestBuilder extends AbstractRsaRequestBuilder {

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
		CambioDomicilioView operacion = (CambioDomicilioView) operacionDeRiesgo;
		ed.setEventType(EventType.CHANGE_ADDRESS);
		ed.setClientDefinedAttributeList(generarFactListCambioDomicilio(operacion));
		return ed;
	}

	/**
	 * Generar fact list modificacion cambio domicilio.
	 *
	 * @param operacion
	 *            the operacion
	 * @return the fact list
	 */
	private FactList generarFactListCambioDomicilio(CambioDomicilioView operacion) {
		FactList factList = new FactList();
		ClientDefinedFact celularMyA = new ClientDefinedFact();
		celularMyA.setName("celularmya");
		celularMyA.setValue(String.valueOf(operacion.isTieneCelularMyA()));
		celularMyA.setDataType(DataType.BOOLEAN);
		factList.getFact().add(celularMyA);

		return factList;
	}

}
