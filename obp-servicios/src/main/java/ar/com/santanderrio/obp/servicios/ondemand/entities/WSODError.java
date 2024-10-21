/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package ar.com.santanderrio.obp.servicios.ondemand.entities;

/**
 * The Class WSODError.
 */
public class WSODError {

	/** The cod. */
	private String cod = null;

	/** The des. */
	private String des = null;

	/**
	 * Instantiates a new WSOD error.
	 *
	 * @param cod
	 *            the cod
	 * @param des
	 *            the des
	 */
	public WSODError(String cod, String des) {
		this.cod = cod;
		this.des = des;
	}

	/**
	 * Gets the cod.
	 *
	 * @return the cod
	 */
	public String getCod() {
		return this.cod;
	}

	/**
	 * Gets the des.
	 *
	 * @return the des
	 */
	public String getDes() {
		return this.des;
	}
}