/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class TotalesEntity.
 */
@XmlRootElement(name = "totales")
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalesEntity {

	/** The movimientos. */
	@XmlElement(name = "movimientos")
	private MovimientosEntity movimientos;

	/**
	 * Gets the movimientos.
	 *
	 * @return the movimientos
	 */
	public MovimientosEntity getMovimientos() {
		return movimientos;
	}

	/**
	 * Sets the movimientos.
	 *
	 * @param movimientos
	 *            the new movimientos
	 */
	public void setMovimientos(MovimientosEntity movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "TotalesEntity [movimientos=" + movimientos + "]";
	}
}
