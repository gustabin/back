package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView;

/**
 * The Class CambioDatosContactoRsaRequestBuilder.
 */
public class CambioDatosContactoRsaRequestBuilder extends AbstractRsaRequestBuilder {

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
		CambioDatosContactoView view = (CambioDatosContactoView) operacionDeRiesgo;
		ed.setEventType(OperacionesRSAEnum.CAMBIOEMAIL.equals(view.getTipoOperacion()) ? EventType.CHANGE_EMAIL
				: EventType.CHANGE_PHONE);
        ed.setClientDefinedAttributeList(generarFactList(view));
		return ed;
	}
	
    private FactList generarFactList(CambioDatosContactoView view) {
    	FactList factList = new FactList();

    	if (OperacionesRSAEnum.CAMBIOEMAIL.equals(view.getTipoOperacion())) {
    		if (StringUtils.isNotBlank(view.getEmailPrimario())) {
    	    	ClientDefinedFact emailPrimario = new ClientDefinedFact();
    	    	emailPrimario.setName("email");
    	    	emailPrimario.setValue(view.getEmailPrimario());
    	    	emailPrimario.setDataType(DataType.STRING);
    	        factList.getFact().add(emailPrimario);
    	        
    	        ClientDefinedFact emailPrimarioAnterior = new ClientDefinedFact();
    	    	emailPrimarioAnterior.setName("emailAntiguo");
    	    	emailPrimarioAnterior.setValue(view.getEmailPrimarioPrevio());
    	    	emailPrimarioAnterior.setDataType(DataType.STRING);
    	        factList.getFact().add(emailPrimarioAnterior);
    	        
    		}
    		
    		if (StringUtils.isNotBlank(view.getEmailSecundario())) {
    	    	ClientDefinedFact emailSecundario = new ClientDefinedFact();
    	    	emailSecundario.setName("email");
    	    	emailSecundario.setValue(view.getEmailSecundario());
    	    	emailSecundario.setDataType(DataType.STRING);
    	        factList.getFact().add(emailSecundario);
    	        
    	        ClientDefinedFact emailSecundarioAnterior = new ClientDefinedFact();
    	    	emailSecundarioAnterior.setName("emailAntiguo");
    	    	emailSecundarioAnterior.setValue(view.getEmailSecundarioPrevio());
    	    	emailSecundarioAnterior.setDataType(DataType.STRING);
    	        factList.getFact().add(emailSecundarioAnterior);
    		}
    	} else {
    	    	ClientDefinedFact numeroCelular = new ClientDefinedFact();
    	    	numeroCelular.setName("numeroCelular");
    	    	numeroCelular.setValue(view.getCodigoArea()+view.getCelularIn());
    	    	numeroCelular.setDataType(DataType.STRING);
    	        factList.getFact().add(numeroCelular);
    	        
    	        ClientDefinedFact numeroCelularAnterior = new ClientDefinedFact();
    	    	numeroCelularAnterior.setName("numeroCelularAntiguo");
    	    	numeroCelularAnterior.setValue(view.getCelularPrevio());
    	    	numeroCelularAnterior.setDataType(DataType.STRING);
    	        factList.getFact().add(numeroCelularAnterior);
    	}
        
        return factList;
    }

}
