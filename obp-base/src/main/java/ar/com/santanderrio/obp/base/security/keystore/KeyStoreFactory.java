package ar.com.santanderrio.obp.base.security.keystore;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating KeyStore objects.
 */
public interface KeyStoreFactory {

	/**
	 * Creates a new KeyStore object.
	 *
	 * @param keystore
	 *            the keystore
	 * @return the key store
	 * @throws KeyStoreException
	 *             the key store exception
	 */
	KeyStore createKeyStore(String keystore) throws KeyStoreException;

}
