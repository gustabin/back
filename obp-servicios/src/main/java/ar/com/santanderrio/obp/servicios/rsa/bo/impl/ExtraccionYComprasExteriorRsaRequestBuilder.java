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
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * The Class ExtraccionYComprasExteriorRsaRequestBuilder.
 * 
 * @author Silvina_Luque
 */
public class
ExtraccionYComprasExteriorRsaRequestBuilder extends AbstractRsaRequestBuilder {

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
		ModifTarjetaOperaExtraccionView operacion = (ModifTarjetaOperaExtraccionView) operacionDeRiesgo;
		ed.setEventType(EventType.WITHDRAW);
		ed.setClientDefinedEventType(RSAConstants.CAMBIO_CUENTA_EXTERIOR);
		ed.setClientDefinedAttributeList(generarFactListExtComprasExterior(operacion));
		return ed;
	}

	/**
	 * Generar fact list ModifTarjetaOperaExtraccionView.
	 *
	 * @param operacion
	 *            the operacion
	 * @return the fact list
	 */
	private FactList generarFactListExtComprasExterior(ModifTarjetaOperaExtraccionView operacion) {
		FactList factList = new FactList();
		
        if(operacion.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(operacion.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}
		
		if(operacion.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(operacion.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
		
		ClientDefinedFact moneda = new ClientDefinedFact();
		moneda.setName("moneda");
		moneda.setValue(String.valueOf(operacion.getMoneda()));
		moneda.setDataType(DataType.STRING);
		factList.getFact().add(moneda);
		
		return factList;
	}

}
