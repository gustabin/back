/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.ondemand.ws;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class OnDemandResponse.
 *
 * @author sergio.e.goldentair
 */
public class OnDemandResponse {

	/** The Constant CODIGO_RETORNO. */
	public static final String CODIGO_RETORNO = "cod-ret";

	/** The Constant MENSAJE_INFO. */
	public static final String MENSAJE_INFO = "msj-info";

	/** The Constant MENSAJE_ERROR. */
	public static final String MENSAJE_ERROR = "msj-error";

	/** The codigo retorno. */
	private String codigoRetorno;

	/** The info. */
	private List<OnDemandResponseData> info = new ArrayList<OnDemandResponseData>();

	/** The error. */
	private List<OnDemandResponseData> error = new ArrayList<OnDemandResponseData>();

	/**
	 * Gets the codigo retorno.
	 *
	 * @return the codigo retorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Sets the codigo retorno.
	 *
	 * @param codigoRetorno
	 *            the new codigo retorno
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Gets the info.
	 *
	 * @return the info
	 */
	public List<OnDemandResponseData> getInfo() {
		return info;
	}

	/**
	 * Sets the info.
	 *
	 * @param info
	 *            the info to set
	 */
	public void setInfo(List<OnDemandResponseData> info) {
		this.info = info;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public List<OnDemandResponseData> getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the error to set
	 */
	public void setError(List<OnDemandResponseData> error) {
		this.error = error;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(codigoRetorno).append(info).append(error).hashCode();
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
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OnDemandResponse other = (OnDemandResponse) obj;
		return new EqualsBuilder().append(codigoRetorno, other.getCodigoRetorno()).append(info, other.getInfo())
				.append(error, other.getError()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("codigoRetorno", codigoRetorno).append("info", info)
				.append("error", error).toString();
	}

}
