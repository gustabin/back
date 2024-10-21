/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.dto;

import ar.com.santanderrio.obp.servicios.chat.entities.ChatTokenEntity;

/**
 * The Class RequestChatConnectEntity.
 *
 * @author Federico_Puente
 */
public class ConectarInDTO {
	
	/** The nombre. */
	private String nombre;
	
	/** The apellido. */
	private String apellido;
	
	/** The nup. */
	private String nup;
	
	/** The j session ID. */
	private String jSessionId;
	
	/** The token. */
	private ChatTokenEntity token;

	/**
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Sets the firstname.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Sets the lastname.
	 *
	 * @param apellido the new apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the j session ID.
	 *
	 * @return the jSessionID
	 */
	public String getJSessionId() {
		return jSessionId;
	}

	/**
	 * Sets the j session ID.
	 *
	 * @param jSessionId
	 *            the new j session id
	 */
	public void setJSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public ChatTokenEntity getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the token to set
	 */
	public void setToken(ChatTokenEntity token) {
		this.token = token;
	}
	
}
