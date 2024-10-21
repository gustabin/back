/*
 * 
 */
package ar.com.santanderrio.obp.servicios.fecp.entitiy;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * The Class DatosIn.
 */
@JsonRootName(value = "datos")
public class DatosIn {

	/** The token. */
	public String token;

	/**
	 * Instantiates a new datos in.
	 *
	 * @param token
	 *            the token
	 */
	public DatosIn(String token) {
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
