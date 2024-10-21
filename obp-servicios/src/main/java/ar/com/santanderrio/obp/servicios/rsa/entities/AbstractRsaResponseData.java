/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class AbstractRsaResponseData.
 */
public abstract class AbstractRsaResponseData extends Entity implements RsaResponseData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The rsa generic response data. */
	private RsaGenericResponseData rsaGenericResponseData;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.entities.RsaResponseData#
	 * getRsaGenericResponseData()
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.rsa.entities.RsaResponseData#
	 * getRsaGenericResponseData()
	 */
	@Override
	public RsaGenericResponseData getRsaGenericResponseData() {
		return this.rsaGenericResponseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.entities.RsaResponseData#
	 * setRsaGenericResponseData(ar.com.santanderrio.obp.servicios.rsa.entities.
	 * RsaGenericResponseData)
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.rsa.entities.RsaResponseData#
	 * setRsaGenericResponseData(ar.com.santanderrio.obp.rsa.entities.
	 * RsaGenericResponseData)
	 */
	@Override
	public void setRsaGenericResponseData(RsaGenericResponseData rsaGenericResponseData) {
		this.rsaGenericResponseData = rsaGenericResponseData;
	}

}
