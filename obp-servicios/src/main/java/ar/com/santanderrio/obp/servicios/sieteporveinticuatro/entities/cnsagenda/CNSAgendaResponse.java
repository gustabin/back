/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.SietePorVeintiCuatroResponse;

/**
 * Response para el servicio CNSAGENDA.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CNSAGENDA")
public class CNSAgendaResponse extends SietePorVeintiCuatroResponse {

	/** The datos. */
	@XmlElement
	protected CNSAgendaDatosResponse datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public CNSAgendaDatosResponse getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(CNSAgendaDatosResponse datos) {
		this.datos = datos;
	}

}
