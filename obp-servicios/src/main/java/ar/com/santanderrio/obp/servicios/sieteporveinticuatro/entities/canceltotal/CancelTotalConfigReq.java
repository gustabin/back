/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.canceltotal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaConfigReq;

/**
 * The Class CancelTotalConfigReq.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CancelTotalConfigReq extends CNSAgendaConfigReq {

	/** The nro op terminal. */
	@XmlElement
	private String nroOpTerminal;

	/**
	 * Gets the nro op terminal.
	 *
	 * @return the nroOpTerminal
	 */
	public String getNroOpTerminal() {
		return nroOpTerminal;
	}

	/**
	 * Sets the nro op terminal.
	 *
	 * @param nroOpTerminal
	 *            the nroOpTerminal to set
	 */
	public void setNroOpTerminal(String nroOpTerminal) {
		this.nroOpTerminal = nroOpTerminal;
	}

}
