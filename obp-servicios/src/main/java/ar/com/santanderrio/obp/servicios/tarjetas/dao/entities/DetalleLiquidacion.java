/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class DetalleLiquidacion. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/detalleLiquidacion/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "detalleLiquidacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetalleLiquidacion {

	/** The List lineas. */
	@XmlElement(name = "linea")
	private List<LineaUltimaLiquidacionEntity> lineas;

	/**
	 * Gets the lineas.
	 *
	 * @return the lineas
	 */
	public List<LineaUltimaLiquidacionEntity> getLineas() {
		return lineas;
	}

	/**
	 * Sets the lineas.
	 *
	 * @param lineas
	 *            the lineas to set
	 */
	public void setLineas(List<LineaUltimaLiquidacionEntity> lineas) {
		this.lineas = lineas;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "DetalleLiquidacion [lineas=" + lineas + "]";
	}

}
