/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Reintentar.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Reintentar {

	/** The reintentar. */
	private String reintentar;

	/** The punto de acceso. */
	private String puntoDeAcceso;

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}

	/**
	 * Gets the punto de acceso.
	 *
	 * @return the punto de acceso
	 */
	public String getPuntoDeAcceso() {
		return puntoDeAcceso;
	}

	/**
	 * Sets the punto de acceso.
	 *
	 * @param puntoDeAcceso
	 *            the new punto de acceso
	 */
	public void setPuntoDeAcceso(String puntoDeAcceso) {
		this.puntoDeAcceso = puntoDeAcceso;
	}
}
