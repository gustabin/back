/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.scomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ar.com.santanderrio.obp.generated.webservices.scomp.dominio.RespuestaScomp;

/**
 * The Class ComprobanteResponse.
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ComprobanteResponse {

	/** The cod ret. */
	@XmlElement(name = "cod-ret")
	protected long codRet;

	/** The msj info. */
	@XmlElement(name = "msj-info", required = true)
	protected String msjInfo;

	/** The msj error. */
	@XmlElement(name = "msj-error", required = true)
	protected String msjError;

	/** The respuesta scomp. */
	@XmlTransient
	protected RespuestaScomp respuestaScomp;

	/**
	 * Aca se vuelca el detalle de un comprobante producto de consultar la
	 * operacion de detalle.
	 */
	@XmlTransient
	private String detalleHtml;

	/**
	 * Gets the cod ret.
	 *
	 * @return the codRet
	 */
	public long getCodRet() {
		return codRet;
	}

	/**
	 * Sets the cod ret.
	 *
	 * @param codRet
	 *            the codRet to set
	 */
	public void setCodRet(long codRet) {
		this.codRet = codRet;
	}

	/**
	 * Gets the msj info.
	 *
	 * @return the msjInfo
	 */
	public String getMsjInfo() {
		return msjInfo;
	}

	/**
	 * Sets the msj info.
	 *
	 * @param msjInfo
	 *            the msjInfo to set
	 */
	public void setMsjInfo(String msjInfo) {
		this.msjInfo = msjInfo;
	}

	/**
	 * Gets the msj error.
	 *
	 * @return the msjError
	 */
	public String getMsjError() {
		return msjError;
	}

	/**
	 * Sets the msj error.
	 *
	 * @param msjError
	 *            the msjError to set
	 */
	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}

	/**
	 * Gets the respuesta scomp.
	 *
	 * @return the respuestaScomp
	 */
	public RespuestaScomp getRespuestaScomp() {
		return respuestaScomp;
	}

	/**
	 * Sets the respuesta scomp.
	 *
	 * @param respuestaScomp
	 *            the respuestaScomp to set
	 */
	public void setRespuestaScomp(RespuestaScomp respuestaScomp) {
		this.respuestaScomp = respuestaScomp;
	}

	/**
	 * @return the detalleHtml
	 */
	public String getDetalleHtml() {
		return detalleHtml;
	}

	/**
	 * @param detalleHtml
	 *            the detalleHtml to set
	 */
	public void setDetalleHtml(String detalleHtml) {
		this.detalleHtml = detalleHtml;
	}

}
