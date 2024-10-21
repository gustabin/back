/**
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ChatFromEntity.
 *
 * @author Federico_Puente
 */
public class ChatFromEntity {
	/** The nickname. */
	@JsonProperty("nickname")
	private String nickname;
		
	/** The participant id. */
	@JsonProperty("participantId")
	private Integer participantId;
		
	/** The type. */
	@JsonProperty("type")
	private String type;

	/**
	 * Instantiates a new chat from entity.
	 */
	public ChatFromEntity() {
		
	}
	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Sets the nickname.
	 *
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Gets the participant id.
	 *
	 * @return the participantId
	 */
	public Integer getParticipantId() {
		return participantId;
	}

	/**
	 * Sets the participant id.
	 *
	 * @param participantId the participantId to set
	 */
	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
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
}