package ar.com.santanderrio.obp.base.iatx.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class SessionUsuarioData.
 */
@Component
@Scope("session")
public class SessionUsuarioData {

	/** The iatx session user name. */
	private String iatxSessionUserName;

	/** The iatx nro req session name. */
	private String iatxNroReqSessionName;

	/**
	 * Gets the iatx session user name.
	 *
	 * @return the iatxSessionUserName
	 */
	public String getIatxSessionUserName() {
		return iatxSessionUserName;
	}

	/**
	 * Setter para iatx session user name.
	 *
	 * @param iatxSessionUserName
	 *            the iatxSessionUserName to set
	 */
	public void setIatxSessionUserName(String iatxSessionUserName) {
		this.iatxSessionUserName = iatxSessionUserName;
	}

	/**
	 * Gets the iatx nro req session name.
	 *
	 * @return the iatxNroReqSessionName
	 */
	public String getIatxNroReqSessionName() {
		return iatxNroReqSessionName;
	}

	/**
	 * Setter para iatx nro req session name.
	 *
	 * @param iatxNroReqSessionName
	 *            the iatxNroReqSessionName to set
	 */
	public void setIatxNroReqSessionName(String iatxNroReqSessionName) {
		this.iatxNroReqSessionName = iatxNroReqSessionName;
	}

}
