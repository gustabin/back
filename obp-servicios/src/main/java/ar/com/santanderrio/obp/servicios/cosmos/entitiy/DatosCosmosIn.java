/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cosmos.entitiy;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * The Class DatosCosmosIn.
 */
@JsonRootName(value = "datos")
public class DatosCosmosIn {

	/** The token. */
	public String token;

	/**
	 * Instantiates a new datos in.
	 *
	 * @param token
	 *            the token
	 */
	public DatosCosmosIn(String token) {
		this.token = token;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
}
