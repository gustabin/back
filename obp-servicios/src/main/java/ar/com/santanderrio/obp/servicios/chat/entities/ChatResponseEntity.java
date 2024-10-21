/**
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ChatMessageEntity.
 *
 * @author Federico_Puente
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatResponseEntity {

	/** The chat id. */
	@JsonProperty("chatId")
	private String chatId;
	
	/** The user id. */
	@JsonProperty("userId")
	private String userId;
	
	/** The secure key. */
	@JsonProperty("secureKey")
	private String secureKey;
	
	/** The alias. */
	@JsonProperty("alias")
	private String alias;
	
	/** The status code. */
	@JsonProperty("statusCode")
	private Integer statusCode;
	
	/** The chat ended. */
	@JsonProperty("chatEnded")
	private Boolean chatEnded;
	
	/** The next position. */
	@JsonProperty("nextPosition")
	private Integer nextPosition;
	
	/** The messages. */
	@JsonProperty("messages")
	private List<ChatMessageEntity> messages;
	
	/**
	 * Instantiates a new chat response entity.
	 */
	public ChatResponseEntity () {
		
	}
	
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
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * Gets the status code.
	 *
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}
	
	/**
	 * Sets the status code.
	 *
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the chat ended.
	 *
	 * @return the chatEnded
	 */
	public Boolean getChatEnded() {
		return chatEnded;
	}

	/**
	 * Sets the chat ended.
	 *
	 * @param chatEnded the chatEnded to set
	 */
	public void setChatEnded(Boolean chatEnded) {
		this.chatEnded = chatEnded;
	}

	/**
	 * Gets the next position.
	 *
	 * @return the nextPosition
	 */
	public Integer getNextPosition() {
		return nextPosition;
	}

	/**
	 * Sets the next position.
	 *
	 * @param nextPosition the nextPosition to set
	 */
	public void setNextPosition(Integer nextPosition) {
		this.nextPosition = nextPosition;
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<ChatMessageEntity> getMessages() {
		return messages;
	}

	/**
	 * Sets the messages.
	 *
	 * @param messages the messages to set
	 */
	public void setMessages(List<ChatMessageEntity> messages) {
		this.messages = messages;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "{ ChatResponseEntity[statusCode="+statusCode+"] }";
	}
	
}
