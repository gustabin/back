/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Class StopDebitOut.
 */
public class StopDebitOut extends ComprobanteFeedbackView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1368226395698517968L;

	/** The resultado. */
	private String resultado;

	/** The adherir debito autom. */
	private Boolean adherirDebitoAutom;

	/** The error code. */
	private int errorCode;

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Gets the adherir debito autom.
	 *
	 * @return the adherir debito autom
	 */
	public Boolean getAdherirDebitoAutom() {
		return adherirDebitoAutom;
	}

	/**
	 * Sets the adherir debito autom.
	 *
	 * @param adherirDebitoAutom
	 *            the new adherir debito autom
	 */
	public void setAdherirDebitoAutom(Boolean adherirDebitoAutom) {
		this.adherirDebitoAutom = adherirDebitoAutom;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode
	 *            the new error code
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}