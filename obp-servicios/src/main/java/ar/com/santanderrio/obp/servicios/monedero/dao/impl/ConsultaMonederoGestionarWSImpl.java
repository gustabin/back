/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao.impl;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.monedero.SingleServiceSoap;

/**
 * Obtener el servicio para consultar el ws de MONEDERO.
 *
 * @author juan.magnacco
 *
 */
@Component("monederoWeb")
public class ConsultaMonederoGestionarWSImpl extends GestionarWSAbstract<SingleServiceSoap> {

	/** The Constant CONSULTA_FINANCIACION_CODE. */
	private static final String CONSULTA_MONEDERO_CODE = "MONEDERO";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return CONSULTA_MONEDERO_CODE;
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#
     * asignarInterceptors(org.apache.cxf.endpoint.Client)
     */
    @Override
    protected void asignarInterceptors(Client cliente) {
        cliente.getOutInterceptors().clear();
        cliente.getOutInterceptors().add(new MonederoLoggingInterceptor(Phase.PRE_STREAM));
        cliente.getInInterceptors().clear();
        cliente.getInInterceptors().add(new MonederoLoggingInterceptor(Phase.RECEIVE));
    }

}
