/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.modificacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaEjecucionReq;

/**
 * The Class ModificacionTAEjecucionReq.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ModificacionTAEjecucionReq extends CNSAgendaEjecucionReq {

	/** The tpo agend. */
	@XmlElement
	private String tpoAgend;

	/** The fecha base. */
	@XmlElement
	private String fechaBase;

	/** The frec rec. */
	@XmlElement
	private String frecRec;

	/** The max rec. */
	@XmlElement
	private String maxRec;

	/** The tpo rec. */
	@XmlElement
	private String tpoRec;

	/** The dias aviso prev. */
	@XmlElement
	private String diasAvisoPrev;

	/** The max FV. */
	@XmlElement
	private String maxFV;

	/**
	 * Gets the tpo agend.
	 *
	 * @return the tpo agend
	 */
	public String getTpoAgend() {
		return tpoAgend;
	}

	/**
	 * Sets the tpo agend.
	 *
	 * @param tpoAgend
	 *            the new tpo agend
	 */
	public void setTpoAgend(String tpoAgend) {
		this.tpoAgend = tpoAgend;
	}

	/**
	 * Gets the fecha base.
	 *
	 * @return the fecha base
	 */
	public String getFechaBase() {
		return fechaBase;
	}

	/**
	 * Sets the fecha base.
	 *
	 * @param fechaBase
	 *            the new fecha base
	 */
	public void setFechaBase(String fechaBase) {
		this.fechaBase = fechaBase;
	}

	/**
	 * Gets the frec rec.
	 *
	 * @return the frec rec
	 */
	public String getFrecRec() {
		return frecRec;
	}

	/**
	 * Sets the fre rec.
	 *
	 * @param frecRec
	 *            the new fre rec
	 */
	public void setFreRec(String frecRec) {
		this.frecRec = frecRec;
	}

	/**
	 * Gets the max rec.
	 *
	 * @return the max rec
	 */
	public String getMaxRec() {
		return maxRec;
	}

	/**
	 * Sets the max rec.
	 *
	 * @param maxRec
	 *            the new max rec
	 */
	public void setMaxRec(String maxRec) {
		this.maxRec = maxRec;
	}

	/**
	 * Gets the tpo rec.
	 *
	 * @return the tpo rec
	 */
	public String getTpoRec() {
		return tpoRec;
	}

	/**
	 * Sets the tpo rec.
	 *
	 * @param tpoRec
	 *            the new tpo rec
	 */
	public void setTpoRec(String tpoRec) {
		this.tpoRec = tpoRec;
	}

	/**
	 * Gets the dias aviso prev.
	 *
	 * @return the dias aviso prev
	 */
	public String getDiasAvisoPrev() {
		return diasAvisoPrev;
	}

	/**
	 * Sets the dias aviso prev.
	 *
	 * @param diasAvisoPrev
	 *            the new dias aviso prev
	 */
	public void setDiasAvisoPrev(String diasAvisoPrev) {
		this.diasAvisoPrev = diasAvisoPrev;
	}

	/**
	 * Gets the max FV.
	 *
	 * @return the max FV
	 */
	public String getMaxFV() {
		return maxFV;
	}

	/**
	 * Sets the max FV.
	 *
	 * @param maxFV
	 *            the new max FV
	 */
	public void setMaxFV(String maxFV) {
		this.maxFV = maxFV;
	}

}
