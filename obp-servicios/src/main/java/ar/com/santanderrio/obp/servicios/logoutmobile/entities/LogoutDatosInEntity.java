/**
 * 
 */
package ar.com.santanderrio.obp.servicios.logoutmobile.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author sergio.e.goldentair
 *
 */
public class LogoutDatosInEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -298129567189216549L;

	private String canal;
	private String nup;
	@JsonProperty("user")
	private String usuario;
	@JsonProperty("pass")
	private String usuPsw;

	/**
	 * @param canal
	 * @param nup
	 * @param usuario
	 * @param usuPsw
	 */
	public LogoutDatosInEntity(String canal, String nup, String usuario, String usuPsw) {
		super();
		this.canal = canal;
		this.nup = nup;
		this.usuario = usuario;
		this.usuPsw = usuPsw;
	}

	/**
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @return the usuPsw
	 */
	public String getUsuPsw() {
		return usuPsw;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("canal", canal).append("nup", nup).append("usuario", usuario)
		        .append("pass", "*****").toString();
	}
}
