package ar.com.santanderrio.obp.servicios.obp.base.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.log.cxf.interceptor.RSALoggingInterceptor;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.PlanVService;

/**
 * The Class PlanVWS.
 */
@Component("beta")
public class PlanVWS extends GestionarWSAbstract<PlanVService> {

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#
     * asignarInterceptors(org.apache.cxf.endpoint.Client)
     */
    @Override
    protected void asignarInterceptors(Client cliente) {
        cliente.getOutInterceptors().clear();
        cliente.getOutInterceptors().add(new RSALoggingInterceptor(Phase.PRE_STREAM));
        cliente.getInInterceptors().clear();
        cliente.getInInterceptors().add(new RSALoggingInterceptor(Phase.RECEIVE));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
     */
    @Override
    public String getCodigoWS() {
        return "PLANV";
    }

}
