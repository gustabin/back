/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.licitaciones.ws;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.inv.licitacion.ILicitacionesCanalService;

/**
 * The Class LicitacionesWSImpl.
 */
@Component
public class LicitacionesWSImpl extends GestionarWSAbstract<ILicitacionesCanalService> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "BEL";
	}

}
