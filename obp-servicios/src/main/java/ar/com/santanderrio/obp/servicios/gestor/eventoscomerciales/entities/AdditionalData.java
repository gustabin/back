package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class AdditionalData.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class AdditionalData {

	/** The key. */
	@JsonProperty("key")
	private String key;

	/** The value. */
	@JsonProperty("value")
	private String value;

	/**
	 * Instantiates a new additional data.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public AdditionalData(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
