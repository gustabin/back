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
 * The Class FechasUltimaLiquidacion. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/fechas/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "fechas")
@XmlAccessorType(XmlAccessType.FIELD)
public class FechasUltimaLiquidacion {

	/** The List fechasUltimaLiquidacion. */
	@XmlElement(name = "fecha")
	private List<FechaUltimaLiquidacion> fechas;

	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public List<FechaUltimaLiquidacion> getFechas() {
		return fechas;
	}

	/**
	 * Sets the fechas.
	 *
	 * @param fechas
	 *            the fechas to set
	 */
	public void setFechas(List<FechaUltimaLiquidacion> fechas) {
		this.fechas = fechas;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FechasUltimaLiquidacion [fechas=" + fechas + "]";
	}

}
