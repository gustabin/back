package ar.com.santanderrio.obp.base.security.keystore;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyStore.
 */
public class KeyStore implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The keystore path. */
	private String keystorePath;

	/** The keystore type. */
	private String keystoreType;

	/** The keystore alias. */
	private String keystoreAlias;

	/** The keystore password. */
	private String keystorePassword;

	/**
	 * Gets the keystore path.
	 *
	 * @return the keystorePath
	 */
	public String getKeystorePath() {
		return keystorePath;
	}

	/**
	 * Setter para keystore path.
	 *
	 * @param keystorePath
	 *            the keystorePath to set
	 */
	public void setKeystorePath(String keystorePath) {
		this.keystorePath = keystorePath;
	}

	/**
	 * Gets the keystore type.
	 *
	 * @return the keystoreType
	 */
	public String getKeystoreType() {
		return keystoreType;
	}

	/**
	 * Setter para keystore type.
	 *
	 * @param keystoreType
	 *            the keystoreType to set
	 */
	public void setKeystoreType(String keystoreType) {
		this.keystoreType = keystoreType;
	}

	/**
	 * Gets the keystore password.
	 *
	 * @return the keystorePassword
	 */
	public String getKeystorePassword() {
		return keystorePassword;
	}

	/**
	 * Setter para keystore password.
	 *
	 * @param keystorePassword
	 *            the keystorePassword to set
	 */
	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

	/**
	 * Gets the keystore alias.
	 *
	 * @return the keystoreAlias
	 */
	public String getKeystoreAlias() {
		return keystoreAlias;
	}

	/**
	 * Setter para keystore alias.
	 *
	 * @param keystoreAlias
	 *            the keystoreAlias to set
	 */
	public void setKeystoreAlias(String keystoreAlias) {
		this.keystoreAlias = keystoreAlias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keystoreAlias == null) ? 0 : keystoreAlias.hashCode());
		result = prime * result + ((keystorePassword == null) ? 0 : keystorePassword.hashCode());
		result = prime * result + ((keystorePath == null) ? 0 : keystorePath.hashCode());
		result = prime * result + ((keystoreType == null) ? 0 : keystoreType.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		KeyStore other = (KeyStore) obj;
		if (keystoreAlias == null) {
			if (other.keystoreAlias != null) {
				return false;
			}
		} else if (!keystoreAlias.equals(other.keystoreAlias)) {
			return false;
		}
		if (keystorePassword == null) {
			if (other.keystorePassword != null) {
				return false;
			}
		} else if (!keystorePassword.equals(other.keystorePassword)) {
			return false;
		}
		if (keystorePath == null) {
			if (other.keystorePath != null) {
				return false;
			}
		} else if (!keystorePath.equals(other.keystorePath)) {
			return false;
		}
		if (keystoreType == null) {
			if (other.keystoreType != null) {
				return false;
			}
		} else if (!keystoreType.equals(other.keystoreType)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeyStore [" + (keystorePath != null ? "keystorePath=" + keystorePath + ", " : "")
				+ (keystoreType != null ? "keystoreType=" + keystoreType + ", " : "")
				+ (keystoreAlias != null ? "keystoreAlias=" + keystoreAlias + ", " : "")
				+ (keystorePassword != null ? "keystorePassword=" + keystorePassword : "") + "]";
	}

}
