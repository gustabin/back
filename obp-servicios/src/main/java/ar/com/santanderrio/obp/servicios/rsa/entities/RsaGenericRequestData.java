/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class RsaGenericRequestData.
 *
 * @author Ignacio_Valek
 */
public class RsaGenericRequestData extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The device print. */
	// deviceRequest
	private String devicePrint;

	/** The http accept. */
	private String httpAccept;

	/** The http accept chars. */
	private String httpAcceptChars;

	/** The http accept encoding. */
	private String httpAcceptEncoding;

	/** The http accept language. */
	private String httpAcceptLanguage;

	/** The http referrer. */
	private String httpReferrer;

	/** The user agent. */
	private String userAgent;

	/** The transaction id. */
	// identificationData
	private String transactionId;

	/** The device token FSO. */
	/*
	 * RSAMessage#fillGenericFields RSAConstants.ATTR_TOKEN_FSO ||
	 * RSAConstants.ATTR_TOKEN_FSO_NXT || RSAConstants.ATTR_FLASH_SO
	 */
	private String deviceTokenFSO;

	/** The device token cookie. */
	/*
	 * RSAMessage#fillGenericFields RSAConstants.ATTR_TOKEN_COOKIE ||
	 * RSAConstants.ATTR_TOKEN_COOKIE_NXT || RSAConstants.RSA_COOKIE_NAME
	 */
	private String deviceTokenCookie;

	/** The rsa cookie. */
	// RSA_COOKIE
	private String rsaCookie;

	/** The remote ip. */
	private String remoteIp;

	/** The resumen cliente. */
	// RawClientData
	private ResumenCliente resumenCliente;

	/**
	 * Instantiates a new rsa generic request data.
	 */
	public RsaGenericRequestData() {
		this.resumenCliente = new ResumenCliente();
	}

	/**
	 * Gets the device print.
	 *
	 * @return the device print
	 */
	public String getDevicePrint() {
		return devicePrint;
	}

	/**
	 * Setter para device print.
	 *
	 * @param devicePrint
	 *            el nuevo device print
	 */
	public void setDevicePrint(String devicePrint) {
		this.devicePrint = devicePrint;
	}

	/**
	 * Gets the http accept.
	 *
	 * @return the http accept
	 */
	public String getHttpAccept() {
		return httpAccept;
	}

	/**
	 * Setter para http accept.
	 *
	 * @param httpAccept
	 *            el nuevo http accept
	 */
	public void setHttpAccept(String httpAccept) {
		this.httpAccept = httpAccept;
	}

	/**
	 * Gets the http accept chars.
	 *
	 * @return the http accept chars
	 */
	public String getHttpAcceptChars() {
		return httpAcceptChars;
	}

	/**
	 * Setter para http accept chars.
	 *
	 * @param httpAcceptChars
	 *            el nuevo http accept chars
	 */
	public void setHttpAcceptChars(String httpAcceptChars) {
		this.httpAcceptChars = httpAcceptChars;
	}

	/**
	 * Gets the http accept encoding.
	 *
	 * @return the http accept encoding
	 */
	public String getHttpAcceptEncoding() {
		return httpAcceptEncoding;
	}

	/**
	 * Setter para http accept encoding.
	 *
	 * @param httpAcceptEncoding
	 *            el nuevo http accept encoding
	 */
	public void setHttpAcceptEncoding(String httpAcceptEncoding) {
		this.httpAcceptEncoding = httpAcceptEncoding;
	}

	/**
	 * Gets the http accept language.
	 *
	 * @return the http accept language
	 */
	public String getHttpAcceptLanguage() {
		return httpAcceptLanguage;
	}

	/**
	 * Setter para http accept language.
	 *
	 * @param httpAcceptLanguage
	 *            el nuevo http accept language
	 */
	public void setHttpAcceptLanguage(String httpAcceptLanguage) {
		this.httpAcceptLanguage = httpAcceptLanguage;
	}

	/**
	 * Gets the http referrer.
	 *
	 * @return the http referrer
	 */
	public String getHttpReferrer() {
		return httpReferrer;
	}

	/**
	 * Setter para http referrer.
	 *
	 * @param httpReferrer
	 *            el nuevo http referrer
	 */
	public void setHttpReferrer(String httpReferrer) {
		this.httpReferrer = httpReferrer;
	}

	/**
	 * Gets the user agent.
	 *
	 * @return the user agent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Setter para user agent.
	 *
	 * @param userAgent
	 *            el nuevo user agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * Gets the request device token FSO.
	 *
	 * @return the request device token FSO
	 */
	public String getRequestDeviceTokenFSO() {
		return deviceTokenFSO;
	}

	/**
	 * Sets the request device token FSO.
	 *
	 * @param deviceTokenFSO
	 *            the new request device token FSO
	 */
	public void setRequestDeviceTokenFSO(String deviceTokenFSO) {
		this.deviceTokenFSO = deviceTokenFSO;
	}

	/**
	 * Gets the device token cookie.
	 *
	 * @return the device token cookie
	 */
	public String getDeviceTokenCookie() {
		return deviceTokenCookie;
	}

	/**
	 * Sets the device token cookie.
	 *
	 * @param deviceTokenCookie
	 *            the new device token cookie
	 */
	public void setDeviceTokenCookie(String deviceTokenCookie) {
		this.deviceTokenCookie = deviceTokenCookie;
	}

	/**
	 * Gets the remote ip.
	 *
	 * @return the remote ip
	 */
	public String getRemoteIp() {
		return remoteIp;
	}

	/**
	 * Setter para remote ip.
	 *
	 * @param remoteIp
	 *            el nuevo remote ip
	 */
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	/**
	 * Gets the resumen cliente.
	 *
	 * @return the resumen cliente
	 */
	public ResumenCliente getResumenCliente() {
		return resumenCliente;
	}

	/**
	 * Setter para resumen cliente.
	 *
	 * @param resumenCliente
	 *            el nuevo resumen cliente
	 */
	public void setResumenCliente(ResumenCliente resumenCliente) {
		this.resumenCliente = resumenCliente;
	}

	/**
	 * Gets the rsa cookie.
	 *
	 * @return the rsa cookie
	 */
	public String getRsaCookie() {
		return rsaCookie;
	}

	/**
	 * Setter para rsa cookie.
	 *
	 * @param rsaCookie
	 *            el nuevo rsa cookie
	 */
	public void setRsaCookie(String rsaCookie) {
		this.rsaCookie = rsaCookie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(remoteIp);
		hcb.append(resumenCliente);
		hcb.append(deviceTokenFSO);
		hcb.append(userAgent);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		RsaGenericRequestData other = (RsaGenericRequestData) obj;
		return new EqualsBuilder().append(remoteIp, other.remoteIp).append(resumenCliente, other.resumenCliente)
				.append(deviceTokenFSO, other.deviceTokenFSO).append(userAgent, other.userAgent).isEquals();
	}

	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId
	 *            the new transaction id
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
