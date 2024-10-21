package ar.com.santanderrio.obp.base.webservices;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

// TODO: Auto-generated Javadoc
/**
 * The Class WSS4JCallback.
 */
public class WSS4JCallback implements CallbackHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.
	 * callback.Callback[])
	 */
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		String keyStorePwd = "storepass";
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof WSPasswordCallback) {
				WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
				/*
				 * here call a function/method to lookup the password for the
				 * given identifier (e.g. a user name or keystore alias) e.g.:
				 * pc.setPassword(passStore.getPassword(pc.getIdentfifier)) for
				 * Testing we supply a fixed name here.
				 */
				pc.setPassword(keyStorePwd);
			} else {
				throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
			}
		}
	}
}