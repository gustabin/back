/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prisma.helper;

/**
 * The Class CryptoHelperDTO.
 */
public class CryptoHelperDTO {
	/** Alias ID SEGURIDAD. */
	private String aliasIdSeguridad;

	/** Prisma Key Alias. */
	private String prismaPubKeyAlias;

	/** Keystore Alias path. */
	private String keyStorePath;

	/** Keystore Alias type. */
	private String keyStoreType;

	/** Alias ttl timestamp in second. */
	private String ttlTimestamp;

	/**
	 * Gets the alias id seguridad.
	 *
	 * @return the aliasIdSeguridad
	 */
	public String getAliasIdSeguridad() {
		return aliasIdSeguridad;
	}

	/**
	 * Sets the alias id seguridad.
	 *
	 * @param aliasIdSeguridad
	 *            the aliasIdSeguridad to set
	 */
	public void setAliasIdSeguridad(String aliasIdSeguridad) {
		this.aliasIdSeguridad = aliasIdSeguridad;
	}

	/**
	 * Gets the prisma pub key alias.
	 *
	 * @return the prismaPubKeyAlias
	 */
	public String getPrismaPubKeyAlias() {
		return prismaPubKeyAlias;
	}

	/**
	 * Sets the prisma pub key alias.
	 *
	 * @param prismaPubKeyAlias
	 *            the prismaPubKeyAlias to set
	 */
	public void setPrismaPubKeyAlias(String prismaPubKeyAlias) {
		this.prismaPubKeyAlias = prismaPubKeyAlias;
	}

	/**
	 * Gets the key store path.
	 *
	 * @return the keyStorePath
	 */
	public String getKeyStorePath() {
		return keyStorePath;
	}

	/**
	 * Sets the key store path.
	 *
	 * @param keyStorePath
	 *            the keyStorePath to set
	 */
	public void setKeyStorePath(String keyStorePath) {
		this.keyStorePath = keyStorePath;
	}

	/**
	 * Gets the key store type.
	 *
	 * @return the keyStoreType
	 */
	public String getKeyStoreType() {
		return keyStoreType;
	}

	/**
	 * Sets the key store type.
	 *
	 * @param keyStoreType
	 *            the keyStoreType to set
	 */
	public void setKeyStoreType(String keyStoreType) {
		this.keyStoreType = keyStoreType;
	}

	/**
	 * Gets the ttl timestamp.
	 *
	 * @return the ttlTimestamp
	 */
	public String getTtlTimestamp() {
		return ttlTimestamp;
	}

	/**
	 * Sets the ttl timestamp.
	 *
	 * @param ttlTimestamp
	 *            the ttlTimestamp to set
	 */
	public void setTtlTimestamp(String ttlTimestamp) {
		this.ttlTimestamp = ttlTimestamp;
	}

}
