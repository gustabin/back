/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.licitaciones.ws;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.mercado.canal.IMercadosCanalService;


/**
 * The Class MercadosCanalWSImpl.
 */
@Component
public class MercadosCanalWSImpl extends GestionarWSAbstract<IMercadosCanalService> {

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "MERCADOSCANAL";
	}

}
