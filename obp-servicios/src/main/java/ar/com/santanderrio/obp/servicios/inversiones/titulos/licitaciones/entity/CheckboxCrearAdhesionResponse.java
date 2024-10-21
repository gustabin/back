/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CheckboxCrearAdhesionResponse.
 */
public class CheckboxCrearAdhesionResponse {
	
	/** The desc. */
	@JsonProperty("Desc")
	private String desc;
	
	/** The checked. */
	@JsonProperty("Checked")
	private boolean checked;

	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc
	 *            the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Checks if is checked.
	 *
	 * @return true, if is checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the checked.
	 *
	 * @param checked
	 *            the new checked
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
