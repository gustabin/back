/**
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.entities;

import java.io.Serializable;

/**
 * The Class TenenciaRespuesta.
 *
 * @author sergio.e.goldentair
 */
public class TenenciaRespuesta implements Serializable {
	/** Serial Id tenencia respuesta. */
	private static final long serialVersionUID = -6183530796934134336L;
	/** token para operaciones contra tenencia. */
	private String token;

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

}
