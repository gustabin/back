/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.dto;

import ar.com.santanderrio.obp.servicios.chat.entities.ChatResponseEntity;

/**
 * The Class ChatConectarDTO.
 *
 * @author Federico_Puente
 */
public class ChatOutDTO {

	/** The chat message entity. */
	private ChatResponseEntity chatMessageEntity;

	/**
	 * Gets the chat message entity.
	 *
	 * @return the chatMessageEntity
	 */
	public ChatResponseEntity getChatMessageEntity() {
		return chatMessageEntity;
	}

	/**
	 * Sets the chat message entity.
	 *
	 * @param chatMessageEntity the chatMessageEntity to set
	 */
	public void setChatMessageEntity(ChatResponseEntity chatMessageEntity) {
		this.chatMessageEntity = chatMessageEntity;
	}

	
}
