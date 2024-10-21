/**
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinView;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * The Class DebinRsaRequestBuilder.
 *
 * @author sergio.e.goldentair
 */
public class DebinRsaRequestBuilder extends AbstractRsaRequestBuilder implements RsaRequestBuilder {

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
     * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
     */
    @Override
    public EventData build(RsaDTO rsaDto) {
        EventData ed = new EventData();
        DebinView debin = (DebinView) rsaDto;
        ed.setEventType(EventType.CLIENT_DEFINED);
        ed.setClientDefinedEventType(RSAConstants.GESTION_ALIAS);
        ed.setClientDefinedAttributeList(generarFactListAumentoLimiteTransferencia(debin));
        return ed;
    }

    /**
	 * Generar fact list transferencia.
	 *
	 * @param debinView
	 *            the debin view
	 * @return the fact list
	 */
    private FactList generarFactListAumentoLimiteTransferencia(DebinView debinView) {
        FactList factList = new FactList();

        ClientDefinedFact celularMyA = new ClientDefinedFact();
        celularMyA.setName("celularmya");
        celularMyA.setValue(String.valueOf(debinView.isCelularMyA()));
        celularMyA.setDataType(DataType.BOOLEAN);
        factList.getFact().add(celularMyA);
        return factList;
    }

}
