package ar.com.santanderrio.obp.servicios.configuration.view;

import java.util.Map;

/**
 * The Class CustomConfigView.
 */
public class CustomConfigView {

	/** The config ID. */
	private String configID;

	/** The admin ID. */
	private String adminID;

	/** The value map. */
	private Map<String, String> valueMap;

	/**
	 * Gets the config ID.
	 *
	 * @return the config ID
	 */
	public String getConfigID() {
		return configID;
	}

	/**
	 * Sets the config ID.
	 *
	 * @param configID the new config ID
	 */
	public void setConfigID(String configID) {
		this.configID = configID;
	}

	/**
	 * Gets the admin ID.
	 *
	 * @return the admin ID
	 */
	public String getAdminID() {
		return adminID;
	}

	/**
	 * Sets the admin ID.
	 *
	 * @param adminID the new admin ID
	 */
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	/**
	 * Gets the value map.
	 *
	 * @return the value map
	 */
	public Map<String, String> getValueMap() {
		return valueMap;
	}

	/**
	 * Sets the value map.
	 *
	 * @param valueMap the value map
	 */
	public void setValueMap(Map<String, String> valueMap) {
		this.valueMap = valueMap;
	}

}
