/**
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.rsa.AdaptiveAuthenticationInterface;

/**
 * Poder operar con el ws de RSA.
 *
 * @author sergio.e.goldentair
 *
 */
@Component
public class RsaGestionarWSImpl extends GestionarWSAbstract<AdaptiveAuthenticationInterface> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "RSA";
	}

}
