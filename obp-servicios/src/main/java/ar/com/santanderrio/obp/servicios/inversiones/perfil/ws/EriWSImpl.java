/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.perfil.ws;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EriService;

/**
 * The Class EriWSImpl.
 *
 * @author sergio.e.goldentair
 */
@Component
public class EriWSImpl extends GestionarWSAbstract<EriService> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "ERI";
	}

}
