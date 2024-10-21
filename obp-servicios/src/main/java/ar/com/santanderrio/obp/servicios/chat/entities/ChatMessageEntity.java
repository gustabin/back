/**
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ChatMessageEntity.
 *
 * @author Federico_Puente
 */
public class ChatMessageEntity {
	/** The chat from. */
	@JsonProperty("from")
	private ChatFromEntity chatFrom;
	
	/** The index. */
	@JsonProperty("index")
	private Integer index;
	
	/** The type. */
	@JsonProperty("type")
	private String type;

	/** The utc time. */
	@JsonProperty("utcTime")
	private Double utcTime;
	
	/**
	 * Instantiates a new chat message entity.
	 */
	public ChatMessageEntity() {
	}

	/**
	 * Gets the chat from.
	 *
	 * @return the chatFrom
	 */
	public ChatFromEntity getChatFrom() {
		return chatFrom;
	}

	/**
	 * Sets the chat from.
	 *
	 * @param chatFrom the chatFrom to set
	 */
	public void setChatFrom(ChatFromEntity chatFrom) {
		this.chatFrom = chatFrom;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the utc time.
	 *
	 * @return the utcTime
	 */
	public Double getUtcTime() {
		return utcTime;
	}

	/**
	 * Sets the utc time.
	 *
	 * @param utcTime the utcTime to set
	 */
	public void setUtcTime(Double utcTime) {
		this.utcTime = utcTime;
	}
			
}
