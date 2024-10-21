/**
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.dao.impl;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.base.webservice.interceptor.BaseLoggingInterceptor;
import ar.com.santanderrio.obp.generated.webservices.mya.v2.Services;
import ar.com.santanderrio.obp.servicios.scomp.dao.CleanNsInterceptor;

/**
 * The Class MyaGestionarWSImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("gestionMya2")
public class MyaGestionarWSImpl2 extends GestionarWSAbstract<Services> {
	/** El namespace a filtrar. */
	private static final String MYA_NAME_SPACE = "{http://services.mya.ar.bsch}*";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "MYA2";
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
		cliente.getOutInterceptors().add(new MyaSchemaOutInterceptor());
		cliente.getOutInterceptors().add(CleanNsInterceptor.cleanNsOut(MYA_NAME_SPACE, "*"));
		cliente.getOutInterceptors().add(new BaseLoggingInterceptor(Phase.PRE_STREAM));

		cliente.getInInterceptors().clear();
		cliente.getInInterceptors().add(CleanNsInterceptor.cleanNsIn("*", MYA_NAME_SPACE));
		cliente.getInInterceptors().add(new BaseLoggingInterceptor(Phase.RECEIVE));

	}

}
