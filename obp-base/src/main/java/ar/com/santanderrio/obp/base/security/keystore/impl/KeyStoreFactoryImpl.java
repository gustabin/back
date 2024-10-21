package ar.com.santanderrio.obp.base.security.keystore.impl;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreException;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyStoreFactoryImpl.
 */
@Component
public class KeyStoreFactoryImpl implements KeyStoreFactory {

	/** The Constant KEYSTORE_TOKEN. */
	private static final String KEYSTORE_TOKEN = "KEYSTORE.";

	/** The credential security factory. */
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The property map. */
	@Autowired
	private PropertyMap propertyMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.base.security.keystore.KeyStoreFactory#createKeyStore
	 * (java.lang.String)
	 */
	@Override
	public KeyStore createKeyStore(String keystore) throws KeyStoreException {

		KeyStore ks = null;

		String keystoreType = propertyMap.get(KEYSTORE_TOKEN + keystore + ".TYPE");
		String keystorePath = propertyMap.get(KEYSTORE_TOKEN + keystore + ".PATH");
		String idSeguridad = propertyMap.get(KEYSTORE_TOKEN + keystore + ".IDSEGURIDAD");

		String keystoreAlias;
		String keystorePassword;

		if (StringUtils.isNotBlank(idSeguridad)) {
			try {
				Credential credential = credentialSecurityFactory.getCredentialById(idSeguridad);
				keystoreAlias = credential.getUsuario();
				keystorePassword = credential.getPassword();
			} catch (SQLException e) {
				throw new KeyStoreException(e);
			}
		} else {
			keystoreAlias = propertyMap.get(KEYSTORE_TOKEN + keystore + ".KEY.ALIAS");
			keystorePassword = propertyMap.get(KEYSTORE_TOKEN + keystore + ".KEY.PASSWORD");
		}

		if (keystoreAlias != null && keystorePassword != null) {
			ks = new KeyStore();

			ks.setKeystoreType(keystoreType);
			ks.setKeystorePath(keystorePath);
			ks.setKeystoreAlias(keystoreAlias);
			ks.setKeystorePassword(keystorePassword);
		}
		return ks;
	}

}
