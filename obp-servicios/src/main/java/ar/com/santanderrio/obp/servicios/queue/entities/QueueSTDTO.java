package ar.com.santanderrio.obp.servicios.queue.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class QueueSTDTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueSTDTO {

	/** The turn. */
	private TurnDTO turn;

	/** The count. */
	@JsonProperty("count")
	private String count;

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

}
