/*
 * 
 */
package ar.com.santanderrio.obp.servicios.delete.account.ws.manager.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.discador.client.IClickToCall;

/**
 * The Class DeleteAccountWebManagerImpl.
 */
@Component("clickToCallWS")
public class DiscadorWSManagerImpl extends GestionarWSAbstract<IClickToCall> {

	@Override
	public String getCodigoWS() {
		return "CLICKTOCALL";
	}

}
