/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.io.Serializable;

/**
 * The Class FacturaElectronica.
 */
public class FacturaElectronica implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The data. */
	private byte[] data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		byte[] returnData = null;
		if (data != null) {
			returnData = data.clone();
		}
		return returnData;
	}

	/**
	 * Setter para data.
	 *
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data.clone();
	}

}
