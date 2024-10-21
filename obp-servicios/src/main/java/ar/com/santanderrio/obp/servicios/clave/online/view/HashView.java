/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.view;

/**
 * The Class HashView.
 */
public class HashView {

	/** The hash. */
	private String hash;

	/**
	 * Instantiates a new hash view.
	 *
	 * @param hash
	 *            the hash
	 */
	public HashView(String hash) {
		super();
		this.hash = hash;
	}

	/**
	 * Instantiates a new hash view.
	 */
	public HashView() {
		super();
	}

	/**
	 * Gets the hash.
	 *
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Sets the hash.
	 *
	 * @param hash
	 *            the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

}
