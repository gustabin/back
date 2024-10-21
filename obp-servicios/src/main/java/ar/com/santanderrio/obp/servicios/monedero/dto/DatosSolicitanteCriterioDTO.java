/*
 * 
 */

package ar.com.santanderrio.obp.servicios.monedero.dto;

/**
 * The Class DatosSolicitanteCriterioView.
 */
public class DatosSolicitanteCriterioDTO {

	/** The nup. */
	private String nup;

	/** The doc tipo. */
	private String docTipo;

	/** The doc. */
	private String doc;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The adicional. */
	private boolean adicional;

	/**
	 * Gets the doc tipo.
	 *
	 * @return the doc tipo
	 */
	public String getDocTipo() {
		return docTipo;
	}

	/**
	 * Sets the doc tipo.
	 *
	 * @param docTipo
	 *            the new doc tipo
	 */
	public void setDocTipo(String docTipo) {
		this.docTipo = docTipo;
	}

	/**
	 * Gets the doc.
	 *
	 * @return the doc
	 */
	public String getDoc() {
		return doc;
	}

	/**
	 * Sets the doc.
	 *
	 * @param doc
	 *            the new doc
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Checks if is adicional.
	 *
	 * @return true, if is adicional
	 */
	public boolean isAdicional() {
		return adicional;
	}

	/**
	 * Sets the adicional.
	 *
	 * @param adicional
	 *            the new adicional
	 */
	public void setAdicional(boolean adicional) {
		this.adicional = adicional;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

}
