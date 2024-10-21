/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ondemand.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class Datos.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Datos {

	/** The soporte. */
	@XmlElement(name = "soporte", type = String.class)
	private String soporte;

	/**
	 * Gets the soporte.
	 *
	 * @return the soporte
	 */
	public String getSoporte() {
		return soporte;
	}

	/**
	 * Sets the soporte.
	 *
	 * @param soporte
	 *            the soporte to set
	 */
	public void setSoporte(String soporte) {
		this.soporte = soporte;
	}

}
