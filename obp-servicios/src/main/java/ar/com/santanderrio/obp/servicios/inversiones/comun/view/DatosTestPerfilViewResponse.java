/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * The Class DatosTestPerfilViewResponse.
 */
@XStreamAlias("datosFirmados")
public class DatosTestPerfilViewResponse {

	/** The link test perfil inversor. */
	@XStreamOmitField
	private String linkTestPerfilInversor;

	/** The time stamp. */
	private String timeStamp;

	/** The nup cliente. */
	@XStreamAlias("NupCliente")
	private String nupCliente;

	/** The cod canal. */
	@XStreamAlias("CodCanal")
	private String codCanal;

	/** The cod tipo cliente. */
	@XStreamAlias("CodTipoCliente")
	private String codTipoCliente;

	/** la firma. */
	private String firma;

	/**
	 * Instantiates a new datos test perfil view response.
	 *
	 * @param linkTestPerfilInversor
	 *            the link test perfil inversor
	 */
	public DatosTestPerfilViewResponse(String linkTestPerfilInversor) {
		this.linkTestPerfilInversor = linkTestPerfilInversor;
	}

	/**
	 * Gets the link test perfil inversor.
	 *
	 * @return the link test perfil inversor
	 */
	public String getLinkTestPerfilInversor() {
		return linkTestPerfilInversor;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp.
	 *
	 * @param timeStamp
	 *            the new time stamp
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets the nup cliente.
	 *
	 * @return the nup cliente
	 */
	public String getNupCliente() {
		return nupCliente;
	}

	/**
	 * Sets the nup cliente.
	 *
	 * @param nupCliente
	 *            the new nup cliente
	 */
	public void setNupCliente(String nupCliente) {
		this.nupCliente = nupCliente;
	}

	/**
	 * Gets the cod canal.
	 *
	 * @return the cod canal
	 */
	public String getCodCanal() {
		return codCanal;
	}

	/**
	 * Sets the cod canal.
	 *
	 * @param codCanal
	 *            the new cod canal
	 */
	public void setCodCanal(String codCanal) {
		this.codCanal = codCanal;
	}

	/**
	 * Gets the cod tipo cliente.
	 *
	 * @return the cod tipo cliente
	 */
	public String getCodTipoCliente() {
		return codTipoCliente;
	}

	/**
	 * Sets the cod tipo cliente.
	 *
	 * @param codTipoCliente
	 *            the new cod tipo cliente
	 */
	public void setCodTipoCliente(String codTipoCliente) {
		this.codTipoCliente = codTipoCliente;
	}

	/**
	 * Gets the firma.
	 *
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * Sets the firma.
	 *
	 * @param firma
	 *            the new firma
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}

}
