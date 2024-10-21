/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ondemand.entities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class WSODResponse.
 */
public class WSODResponse {

	/** The cod ret. */
	private String codRet = null;

	/** The msj info. */
	private List<?> msjInfo = new ArrayList<Object>();

	/** The msj error. */
	private List<?> msjError = new ArrayList<Object>();

	/** The attach. */
	private InputStream attach = null;

	/** The Constant COD_RET_OK. */
	public static final String COD_RET_OK = "0";

	/** The Constant COD_RET_ERROR. */
	public static final String COD_RET_ERROR = "1";

	/**
	 * Instantiates a new WSOD response.
	 *
	 * @param codRet
	 *            the cod ret
	 * @param msjInfo
	 *            the msj info
	 * @param msjError
	 *            the msj error
	 * @param attach
	 *            the attach
	 */
	public WSODResponse(String codRet, List<?> msjInfo, List<?> msjError, InputStream attach) {
		this.codRet = codRet;
		this.msjInfo = msjInfo;
		this.msjError = msjError;
		this.attach = attach;
	}

	/**
	 * Gets the attach.
	 *
	 * @return the attach
	 */
	public InputStream getAttach() {
		return this.attach;
	}

	/**
	 * Gets the cod ret.
	 *
	 * @return the cod ret
	 */
	public String getCodRet() {
		return this.codRet;
	}

	/**
	 * Gets the msj error.
	 *
	 * @return the msj error
	 */
	public List<?> getMsjError() {
		return this.msjError;
	}

	/**
	 * Gets the msj info.
	 *
	 * @return the msj info
	 */
	public List<?> getMsjInfo() {
		return this.msjInfo;
	}
}