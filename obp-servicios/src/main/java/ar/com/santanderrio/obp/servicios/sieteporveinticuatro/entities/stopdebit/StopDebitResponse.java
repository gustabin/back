/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.stopdebit;

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
public class StopDebitResponse extends SietePorVeintiCuatroResponse {

	/** The datos. */
	@XmlElement
	protected StopDebitDatosResponse datos;

	/** The info. */
	@XmlElement
	protected StopDebitInfoResponse info;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public StopDebitDatosResponse getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(StopDebitDatosResponse datos) {
		this.datos = datos;
	}

	/**
	 * Gets the info.
	 *
	 * @return the info
	 */
	public StopDebitInfoResponse getInfo() {
		return info;
	}

	/**
	 * Sets the info.
	 *
	 * @param info
	 *            the info to set
	 */
	public void setInfo(StopDebitInfoResponse info) {
		this.info = info;
	}

}
