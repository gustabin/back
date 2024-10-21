/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class CredencialesClaveOnlineView.
 *
 * @author B043069 View para recibir datos que manda el front para actualizar
 *         usuario o clave del cliente
 */
@XmlRootElement(name = "claveOnlineView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class CredencialesClaveOnlineView implements Serializable {

	/** The serialVersionUID. */
	private static final long serialVersionUID = 6285514787448984417L;

	/** The dni. */
	private String dni;

	/** The fechaNacimiento. */
	private String fechaNacimiento;

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
