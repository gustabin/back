/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.SietePorVeintiCuatroConfigResponse;

/**
 * The Class CNSAgendaConfigResponse.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CNSAgendaConfigResponse extends SietePorVeintiCuatroConfigResponse {

	/** The ejecucion. */
	@XmlElement
	private CNSAgendaEjecucionReq ejecucion;

	/**
	 * Gets the ejecucion.
	 *
	 * @return the ejecucion
	 */
	public CNSAgendaEjecucionReq getEjecucion() {
		return ejecucion;
	}

	/**
	 * Sets the ejecucion.
	 *
	 * @param ejecucion
	 *            the ejecucion to set
	 */
	public void setEjecucion(CNSAgendaEjecucionReq ejecucion) {
		this.ejecucion = ejecucion;
	}

}
