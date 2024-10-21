package ar.com.santanderrio.obp.servicios.queue.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class TurnResponse.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnResponse {

	/** The turn. */
	@JsonProperty("turn")
	private TurnDTO turn;

	/** The nup. */
	@JsonProperty("count")
	private String count;

	/** The code. */
	@JsonProperty("code")
	private String code;

	/** The message. */
	@JsonProperty("message")
	private String message;

	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public TurnDTO getTurn() {
		return turn;
	}

	/**
	 * Sets the turn.
	 *
	 * @param turn the new turn
	 */
	public void setTurn(TurnDTO turn) {
		this.turn = turn;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
