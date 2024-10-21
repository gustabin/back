/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.entities;

import java.io.Serializable;

/**
 * The Class ChatSessionEntity.
 *
 * @author Federico_Puente
 */
public class ChatSessionEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5989915634272260628L;
	
	/** The user id. */
	private String userId;
	
	/** The chat id. */
	private String chatId;
	
	/** The secure key. */
	private String secureKey;
	
	/** The alias. */
	private String alias;
	
	/**
	 * Gets the chat id.
	 *
	 * @return the chatId
	 */
	public String getChatId() {
		return chatId;
	}
	
	/**
	 * Sets the chat id.
	 *
	 * @param chatId the chatId to set
	 */
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	
	/**
	 * Gets the secure key.
	 *
	 * @return the secureKey
	 */
	public String getSecureKey() {
		return secureKey;
	}
	
	/**
	 * Sets the secure key.
	 *
	 * @param secureKey the secureKey to set
	 */
	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}
	
	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	
	/**
	 * Sets the alias.
	 *
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
