/**
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.dao;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.AttachmentInInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.base.webservice.interceptor.BaseLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.scomp.client.ScompService;

/**
 * Gestionar la configuracion al ws de scomp.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component("gestionScomp")
public class ScompGestionarWSImpl extends GestionarWSAbstract<ScompService> {
	/** Codigo para scomp en las propiedades. */
	private static final String SCOMP = "SCOMP";
	/** El namespace a filtrar. */
	private static final String SCOMP_NAME_SPACE = "{http://services2.scomp.ar.bsch}*";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return SCOMP;
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
		cliente.getOutInterceptors().add(new BaseLoggingInterceptor(Phase.PRE_STREAM));
		cliente.getOutInterceptors().add(CleanNsInterceptor.cleanNsOut(SCOMP_NAME_SPACE, "*"));

		cliente.getInInterceptors().clear();
		cliente.getInInterceptors().add(CleanNsInterceptor.cleanNsIn("*", SCOMP_NAME_SPACE));
		BaseLoggingInterceptor bli = new BaseLoggingInterceptor(Phase.RECEIVE);
		bli.addAfter(AttachmentInInterceptor.class.getName());
		cliente.getInInterceptors().add(new CopyAttachmentInInterceptor());
		cliente.getInInterceptors().add(bli);
	}
}
