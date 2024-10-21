/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.chat.ISecurity;

/**
 * Obtener el servicio para Chat Security.
 *
 */
@Component("chatSecurityGestor")
public class ChatSecurityGestionarWSImpl extends GestionarWSAbstract<ISecurity> {

	/** The Constant CHAT_CODE. */
	private static final String CHAT_CODE = "CHAT";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return CHAT_CODE;
	}

}
