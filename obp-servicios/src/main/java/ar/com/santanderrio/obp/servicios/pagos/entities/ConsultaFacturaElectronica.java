/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.io.Serializable;

/**
 * The Class ConsultaFacturaElectronica.
 */
public class ConsultaFacturaElectronica implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The canal. */
	private String canal;

	/** The numero documento. */
	private String numeroDocumento;

	/** The tipo documento. */
	private String tipoDocumento;

	/** The terminal. */
	private String terminal;

	/** The id session. */
	private String idSession;

	/** The ip origen. */
	private String ipOrigen;

	/** The cod empresa. */
	private String codEmpresa;

	/** The nro factura. */
	private String nroFactura;

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Setter para canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
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
	 * Setter para numero documento.
	 *
	 * @param numeroDocumento
	 *            the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Setter para tipo documento.
	 *
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the terminal.
	 *
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * Setter para terminal.
	 *
	 * @param terminal
	 *            the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * Gets the id session.
	 *
	 * @return the idSession
	 */
	public String getIdSession() {
		return idSession;
	}

	/**
	 * Setter para id session.
	 *
	 * @param idSession
	 *            the idSession to set
	 */
	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	/**
	 * Gets the ip origen.
	 *
	 * @return the ipOrigen
	 */
	public String getIpOrigen() {
		return ipOrigen;
	}

	/**
	 * Setter para ip origen.
	 *
	 * @param ipOrigen
	 *            the ipOrigen to set
	 */
	public void setIpOrigen(String ipOrigen) {
		this.ipOrigen = ipOrigen;
	}

	/**
	 * Gets the cod empresa.
	 *
	 * @return the codEmpresa
	 */
	public String getCodEmpresa() {
		return codEmpresa;
	}

	/**
	 * Setter para cod empresa.
	 *
	 * @param codEmpresa
	 *            the codEmpresa to set
	 */
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	/**
	 * Gets the nro factura.
	 *
	 * @return the nroFactura
	 */
	public String getNroFactura() {
		return nroFactura;
	}

	/**
	 * Setter para nro factura.
	 *
	 * @param nroFactura
	 *            the nroFactura to set
	 */
	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

}
