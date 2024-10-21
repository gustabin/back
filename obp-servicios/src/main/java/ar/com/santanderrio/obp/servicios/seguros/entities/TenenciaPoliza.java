/**
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.entities;

import java.io.Serializable;

/**
 * The Class TenenciaPoliza.
 *
 * @author sergio.e.goldentair
 */
public class TenenciaPoliza implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1160042353847004789L;

	/** The tipo documento. */
	private String tipoDocumento;

	/** The numero documento. */
	private String numeroDocumento;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The nup. */
	private String nup;

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the numero documento.
	 *
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numeroDocumento
	 *            the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}
}
