/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Interface RsaRequestData.
 */
public abstract class RsaRequestData extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7819758628952365535L;

	/** The operacion. */
	private OperacionesRSAEnum operacion;

	/**
	 * The ws user type. Paramentros que se pasan al crear un RSAAnalyze desde
	 * RSA Helper(son enums en cliente rsa)
	 */
	private String wsUserType;

	/** The user status. */
	private String userStatus;

	/** The rsa generic request data. */
	private RsaGenericRequestData rsaGenericRequestData;

	/**
	 * Instantiates a new rsa request data.
	 */
	public RsaRequestData() {
		this.rsaGenericRequestData = new RsaGenericRequestData();
	}

	/**
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public OperacionesRSAEnum getOperacion() {
		return operacion;
	}

	/**
	 * Setter para operacion.
	 *
	 * @param operacion
	 *            el nuevo operacion
	 */
	public void setOperacion(OperacionesRSAEnum operacion) {
		this.operacion = operacion;
	}

	/**
	 * Gets the ws user type.
	 *
	 * @return the ws user type
	 */
	public String getWsUserType() {
		return wsUserType;
	}

	/**
	 * Setter para ws user type.
	 *
	 * @param wsUserType
	 *            el nuevo ws user type
	 */
	public void setWsUserType(String wsUserType) {
		this.wsUserType = wsUserType;
	}

	/**
	 * Gets the user status.
	 *
	 * @return the user status
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * Setter para user status.
	 *
	 * @param userStatus
	 *            el nuevo user status
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * Gets the rsa generic request data.
	 *
	 * @return the rsa generic request data
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.rsa.entities.RsaRequestData#
	 * getRsaGenericRequestData()
	 */
	public RsaGenericRequestData getRsaGenericRequestData() {
		return this.rsaGenericRequestData;
	}

	/**
	 * Sets the rsa generic request data.
	 *
	 * @param rsaGenericRequestData
	 *            the new rsa generic request data
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.rsa.entities.RsaRequestData#
	 * setRsaGenericRequestData(ar.com.santanderrio.obp.rsa.entities.
	 * RsaGenericRequestData)
	 */
	public void setRsaGenericRequestData(RsaGenericRequestData rsaGenericRequestData) {
		this.rsaGenericRequestData = rsaGenericRequestData;
	}
}
