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
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * The Class ModificacionLimiteDebitoRequestBuilder.
 */
public class ModificacionLimiteDebitoRequestBuilder extends AbstractRsaRequestBuilder {

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
		ComprobanteModificacionLimiteDebitoView operacion = (ComprobanteModificacionLimiteDebitoView) operacionDeRiesgo;
		ed.setEventType(EventType.WITHDRAW);
		ed.setClientDefinedEventType(RSAConstants.EVT_CLTDEF_CAMBIOLIMITEEXT);
		ed.setClientDefinedAttributeList(generarFactListModificacionLimiteDebito(operacion));
		return ed;
	}

	/**
	 * Generar fact list modificacion limite debito.
	 *
	 * @param operacion
	 *            the operacion
	 * @return the fact list
	 */
	private FactList generarFactListModificacionLimiteDebito(ComprobanteModificacionLimiteDebitoView operacion) {
		FactList factList = new FactList();

		ClientDefinedFact celularMyA = new ClientDefinedFact();
		celularMyA.setName("celularmya");
		celularMyA.setValue(String.valueOf(operacion.isTieneCelularMyA()));
		celularMyA.setDataType(DataType.BOOLEAN);
		factList.getFact().add(celularMyA);

		return factList;
	}
}
