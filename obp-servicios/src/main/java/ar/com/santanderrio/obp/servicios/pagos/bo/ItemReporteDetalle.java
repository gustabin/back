/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

/**
 * The Class ItemReporteDetalle.
 */
public class ItemReporteDetalle {

	/** The label. */
	private String label;

	/** The value. */
	private String value;
	
	public ItemReporteDetalle() {
       super();
    }

	public ItemReporteDetalle(String label, String value) {
	    this.label = label;
	    this.value = value;
	}
	
	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the new label
	 */
	public void setLabel(String label) {
		this.label = label;
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
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
