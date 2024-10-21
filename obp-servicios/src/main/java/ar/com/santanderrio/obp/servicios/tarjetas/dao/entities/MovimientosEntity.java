/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class MovimientosEntity.
 */
@XmlRootElement(name = "movimiento")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovimientosEntity {

	/** The pesos. */
	@XmlElement(name = "pesos")
	private String pesos;

	/** The dolares. */
	@XmlElement(name = "dolares")
	private String dolares;

	/**
	 * Gets the pesos.
	 *
	 * @return the pesos
	 */
	public String getPesos() {
		return pesos;
	}

	/**
	 * Sets the pesos.
	 *
	 * @param pesos
	 *            the new pesos
	 */
	public void setPesos(String pesos) {
		this.pesos = pesos;
	}

	/**
	 * Gets the dolares.
	 *
	 * @return the dolares
	 */
	public String getDolares() {
		return dolares;
	}

	/**
	 * Sets the dolares.
	 *
	 * @param dolares
	 *            the new dolares
	 */
	public void setDolares(String dolares) {
		this.dolares = dolares;
	}
	
	@Override
	public String toString() {
		return "MovimientosEntity [pesos=" + pesos + ", dolares=" + dolares + "]";
	}

}
