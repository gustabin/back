/**
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.andreani.dao;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.base.webservice.interceptor.BaseLoggingInterceptor;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasService;
import ar.com.santanderrio.obp.servicios.scomp.dao.CleanNsInterceptor;

/**
 * The Class TrackingTarjetasGestionarWSImpl.
 *
 * @author julian.ariel.karp
 */
@Component("trackingTarjetasWS")
public class TrackingTarjetasGestionarWSImpl extends GestionarWSAbstract<TrackingTarjetasService> {
    /** El namespace a filtrar. */
    private static final String TRACKING_TARJETAS_NAME_SPACE = "{http://services.andreani.wsdeltarj}*";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#
     * asignarInterceptors(org.apache.cxf.endpoint.Client)
     */
    @Override
    protected void asignarInterceptors(Client cliente) {
        
        cliente.getOutInterceptors().clear();
        cliente.getOutInterceptors().add(CleanNsInterceptor.cleanNsOut(TRACKING_TARJETAS_NAME_SPACE, "*"));
        cliente.getOutInterceptors().add(new BaseLoggingInterceptor(Phase.PRE_STREAM));

        cliente.getInInterceptors().clear();
        cliente.getInInterceptors().add(CleanNsInterceptor.cleanNsIn("*", TRACKING_TARJETAS_NAME_SPACE));
        cliente.getInInterceptors().add(new BaseLoggingInterceptor(Phase.RECEIVE));
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
     */
    @Override
    public String getCodigoWS() {
        return "ANDREANI";
    }

}
