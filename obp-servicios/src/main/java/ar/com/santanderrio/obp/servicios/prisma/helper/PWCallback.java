/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prisma.helper;

import java.io.IOException;
import java.sql.SQLException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;

/**
 * Agrega la password para poder abrir el jks al ser utilizado por wss4j.
 * 
 * @author sergio.e.goldentair
 *
 */
public class PWCallback implements CallbackHandler {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PWCallback.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.
	 * callback.Callback[])
	 */
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		CredentialSecurityFactory credentialSecurityFactory = ContextHolder.getContext()
				.getBean(CredentialSecurityFactory.class);
		String codigo = ContextHolder.getContext().getEnvironment()
				.getProperty("KEYSTORE.FACTURAELECTRONICA.IDSEGURIDAD");

		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof WSPasswordCallback) {
				WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
				try {
					Credential credential = credentialSecurityFactory.getCredentialById(codigo);
					pc.setPassword(credential.getPassword());
				} catch (SQLException e) {
					LOGGER.error("Error al obtener la password.", e);
					throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
				}
			} else {
				throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
			}
		}
	}
}
