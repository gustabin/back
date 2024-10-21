/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * The Class ProcesarTransferenciaComexRsaRequestBuilder.
 */
public class ProcesarTransferenciaComexRsaRequestBuilder extends AbstractRsaRequestBuilder implements RsaRequestBuilder {

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
	 */
	@Override
    public EventData build(RsaDTO rsaDto) {
        EventData ed = new EventData();
        ProcesarTransferenciaComexView procesarTransferenciaComexView = (ProcesarTransferenciaComexView) rsaDto;
        ed.setEventType(EventType.PAYMENT);
        ed.setClientDefinedEventType(RSAConstants.GESTION_ALIAS);
        ed.setClientDefinedAttributeList(generarFactListAumentoLimiteTransferencia(procesarTransferenciaComexView));
        return ed;
    }

    /**
	 * Generar fact list transferencia.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the fact list
	 */
    private FactList generarFactListAumentoLimiteTransferencia(ProcesarTransferenciaComexView procesarTransferenciaComexView) {
        FactList factList = new FactList();

        return factList;
    }

}
