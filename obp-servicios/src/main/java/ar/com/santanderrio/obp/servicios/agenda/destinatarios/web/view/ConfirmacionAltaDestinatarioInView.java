/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

/**
 * The Class ConfirmacionAltaDestinatarioInView.
 */
public class ConfirmacionAltaDestinatarioInView extends AltaDestinatarioInView {

	/** The tipo documento destinatario. */
	private Boolean isCuit;

	/** The documento destinatario. */
	private String numeroDocumento;

	/** The tipo documento destinatario. */
	private String tipoDocumento;

	/** The descripcion cuenta destinatario. */
	private String referencia;

	/** The titular. */
	private String titular;

	/** The direccion correo. */
	private String direccionCorreo;

	/** The CBU. */
	private String cbu;

	/** The banco destino. */
	private String bancoDestino;

	/** The is Pesos. */
	private Boolean isPesos;

	/**
	 * Gets the checks if is cuit.
	 *
	 * @return the checks if is cuit
	 */
	public Boolean getIsCuit() {
		return isCuit;
	}

	/**
	 * Sets the checks if is cuit.
	 *
	 * @param isCuit
	 *            the new checks if is cuit
	 */
	public void setIsCuit(Boolean isCuit) {
		this.isCuit = isCuit;
	}

	/**
	 * Gets the numero documento.
	 *
	 * @return the numero documento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Gets the is Pesos.
	 *
	 * @return the numero documento
	 */
	public Boolean getIsPesos() {
		return isPesos;
	}

	/**
	 * Sets the is Pesos.
	 *
	 * @param isPesos
	 *            the new checks if is pesos
	 */
	public void setIsPesos(Boolean isPesos) {
		this.isPesos = isPesos;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numeroDocumento
	 *            the new numero documento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the referencia.
	 *
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Sets the referencia.
	 *
	 * @param referencia
	 *            the new referencia
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the direccion correo.
	 *
	 * @return the direccion correo
	 */
	public String getDireccionCorreo() {
		return direccionCorreo;
	}

	/**
	 * Sets the direccion correo.
	 *
	 * @param direccionCorreo
	 *            the new direccion correo
	 */
	public void setDireccionCorreo(String direccionCorreo) {
		this.direccionCorreo = direccionCorreo;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the banco destino.
	 *
	 * @return the bancoDestino
	 */
	public String getBancoDestino() {
		return bancoDestino;
	}

	/**
	 * Sets the banco destino.
	 *
	 * @param bancoDestino
	 *            the bancoDestino to set
	 */
	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the new tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
