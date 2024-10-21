/**
 * 
 */
package ar.com.santanderrio.obp.base.signer.token;

// TODO: Auto-generated Javadoc
/**
 * The Class Token.
 *
 * @author sergio.e.goldentair
 */
public class Token {

	/** The jks. */
	private String jks;

	/** The requiere certificado. */
	private Boolean requiereCertificado = Boolean.FALSE;

	/**
	 * Gets the requiere certificado.
	 *
	 * @return the requiereCertificado
	 */
	public Boolean getRequiereCertificado() {
		return requiereCertificado;
	}

	/**
	 * Sets the requiere certificado.
	 *
	 * @param requiereCertificado
	 *            the requiereCertificado to set
	 */
	public void setRequiereCertificado(Boolean requiereCertificado) {
		this.requiereCertificado = requiereCertificado;
	}

	/**
	 * Gets the jks.
	 *
	 * @return the jks
	 */
	public String getJks() {
		return jks;
	}

	/**
	 * Sets the jks.
	 *
	 * @param jks
	 *            the jks to set
	 */
	public void setJks(String jks) {
		this.jks = jks;
	}
}
