/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.modificacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.SietePorVeintiCuatroConfigReq;

/**
 * The Class ModificacionTAConfigReq.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ModificacionTAConfigReq extends SietePorVeintiCuatroConfigReq {

	/** The ejecucion. */
	@XmlElement
	private ModificacionTAEjecucionReq ejecucion;

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

	/**
	 * Gets the ejecucion.
	 *
	 * @return the ejecucion
	 */
	public ModificacionTAEjecucionReq getEjecucion() {
		return ejecucion;
	}

	/**
	 * Sets the ejecucion.
	 *
	 * @param ejecucion
	 *            the new ejecucion
	 */
	public void setEjecucion(ModificacionTAEjecucionReq ejecucion) {
		this.ejecucion = ejecucion;
	}

}
