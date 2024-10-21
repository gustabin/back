/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extracto.dao;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.extractos.IExtractos;

/**
 * The Class GestionarExtractoWSImpl.
 */
@Component("extractos")
public class GestionarExtractoWSImpl extends GestionarWSAbstract<IExtractos> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "WSEXTRACTOS";
	}

}
