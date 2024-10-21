package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinView;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

public class GestionarAdhesionDebinRsaRequestBuilder extends AbstractRsaRequestBuilder {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionarAdhesionDebinRsaRequestBuilder.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
     * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
     */
    @Override
    public EventData build(RsaDTO operacionDeRiesgo) {
        LOGGER.debug(operacionDeRiesgo.getTipoOperacion().getDescripcion());

        EventData ed = new EventData();
        ed.setEventType(EventType.CLIENT_DEFINED);
        ed.setClientDefinedEventType(RSAConstants.ADHESION_DEBIN_RECURRENTE);

        return ed;
    }

}
