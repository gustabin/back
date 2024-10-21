/**
 * 
 */
package ar.com.santanderrio.obp.base.security.keystore;

import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Clase que va contener el keystore, la clave privada y la lista de
 * certificados.
 *
 * @author sergio.e.goldentair
 *
 */
public class KSStore {

	/** The key store. */
	private KeyStore keyStore;

	/** The private key. */
	private PrivateKey privateKey;

	/** The certs. */
	private List<Certificate> certs = new ArrayList<Certificate>();

	/**
	 * Instantiates a new KS store.
	 */
	public KSStore() {
		super();
	}

	/**
	 * Gets the key store.
	 *
	 * @return the keyStore
	 */
	public KeyStore getKeyStore() {
		return keyStore;
	}

	/**
	 * Sets the key store.
	 *
	 * @param keyStore
	 *            the keyStore to set
	 */
	public void setKeyStore(KeyStore keyStore) {
		this.keyStore = keyStore;
	}

	/**
	 * Gets the private key.
	 *
	 * @return the privateKey
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * Sets the private key.
	 *
	 * @param privateKey
	 *            the privateKey to set
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * Gets the certs.
	 *
	 * @return the certs
	 */
	public List<Certificate> getCerts() {
		return certs;
	}

	/**
	 * Sets the certs.
	 *
	 * @param certs
	 *            the certs to set
	 */
	public void setCerts(List<Certificate> certs) {
		this.certs = certs;
	}

}
