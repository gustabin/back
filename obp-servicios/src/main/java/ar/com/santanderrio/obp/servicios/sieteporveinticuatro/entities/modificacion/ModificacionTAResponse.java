/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.modificacion;

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
@XmlType(name = "TRF")
public class ModificacionTAResponse extends SietePorVeintiCuatroResponse {

	/** The datos. */
	@XmlElement
	private ModificacionTADatosResponse datos;

	/** The info. */
	@XmlElement
	protected ModificacionTAInfoResponse info;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public ModificacionTADatosResponse getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(ModificacionTADatosResponse datos) {
		this.datos = datos;
	}

	/**
	 * Gets the info.
	 *
	 * @return the info
	 */
	public ModificacionTAInfoResponse getInfo() {
		return info;
	}

	/**
	 * Sets the info.
	 *
	 * @param info
	 *            the new info
	 */
	public void setInfo(ModificacionTAInfoResponse info) {
		this.info = info;
	}

}
