/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class LogIn.
 *
 * @author B039542 - ignacio_valek@itrsa.com.ar - 10/11/2016
 */
public class LogIn extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ip client. */
	private String ipClient;

	private String lugarIngreso;

	@JsonIgnore
	private BiocatchResponseDataDTO biocatchResponseData;

	private boolean fromApp;

	private boolean fromObe;

	/**
	 * Instantiates a new log in.
	 */
	public LogIn() {
		super(OperacionesRSAEnum.LOG_IN);
	}

	/**
	 * Gets the ip client.
	 *
	 * @return the ip client
	 */
	public String getIpClient() {
		return ipClient;
	}

	/**
	 * Sets the ip client.
	 *
	 * @param ipClient
	 *            the new ip client
	 */
	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}

	public String getLugarIngreso() {
		return lugarIngreso;
	}

	public void setLugarIngreso(String lugarIngreso) {
		this.lugarIngreso = lugarIngreso;
	}

	public BiocatchResponseDataDTO getBiocatchResponseData() {
		return biocatchResponseData;
	}

	public void setBiocatchResponseData(BiocatchResponseDataDTO biocatchResponseData) {
		this.biocatchResponseData = biocatchResponseData;
	}

	public boolean isFromObe() {
		return fromObe;
	}

	public void setFromObe(boolean fromObe) {
		this.fromObe = fromObe;
	}

	public boolean isFromApp() {
		return fromApp;
	}

	public void setFromApp(boolean fromApp) {
		this.fromApp = fromApp;
	}
}
