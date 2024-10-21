/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class DatosMyaView.
 *
 * @author B043069 View para recibir datos que manda el front para actualizar
 *         confirmar datos de mya
 */
@XmlRootElement(name = "loginView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosMyaView implements Serializable {

	/** The serialVersionUID. */
	private static final long serialVersionUID = 6285514787448984418L;

	/** The email. */
	private String email;

	/** The celular. */
	private String celular;

	/** The codigo area. */
	private String codigoArea;

	/** The compania. */
	private String compania;

	/** The terminos. */
	private String terminos;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular
	 *            the new celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Gets the terminos.
	 *
	 * @return the terminos
	 */
	public String getTerminos() {
		return terminos;
	}

	/**
	 * Sets the terminos.
	 *
	 * @param terminos
	 *            the new terminos
	 */
	public void setTerminos(String terminos) {
		this.terminos = terminos;
	}

	/**
	 * Gets the codigo area.
	 *
	 * @return the codigo area
	 */
	public String getCodigoArea() {
		return codigoArea;
	}

	/**
	 * Sets the codigo area.
	 *
	 * @param codigoArea
	 *            the new codigo area
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	/**
	 * Gets the compania.
	 *
	 * @return the compania
	 */
	public String getCompania() {
		return compania;
	}

	/**
	 * Sets the compania.
	 *
	 * @param compania
	 *            the new compania
	 */
	public void setCompania(String compania) {
		this.compania = compania;
	}

}
