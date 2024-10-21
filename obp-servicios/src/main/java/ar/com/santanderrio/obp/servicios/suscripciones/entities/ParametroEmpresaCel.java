/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class ParametroEmpresaCel.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ParametroEmpresaCel {

	/** The id empresa cel. */
	@XmlAttribute(name = "id")
	private String idEmpresaCel;

	/** The codigo emp cel. */
	@XmlElement(name = "CodigoEmpCel")
	private String codigoEmpCel;

	/** The descrip emp cel. */
	@XmlElement(name = "DescripEmpCel")
	private String descripEmpCel;

	/**
	 * Gets the id empresa cel.
	 *
	 * @return the id empresa cel
	 */
	public String getIdEmpresaCel() {
		return idEmpresaCel;
	}

	/**
	 * Sets the id empresa cel.
	 *
	 * @param idEmpresaCel
	 *            the new id empresa cel
	 */
	public void setIdEmpresaCel(String idEmpresaCel) {
		this.idEmpresaCel = idEmpresaCel;
	}

	/**
	 * Gets the codigo emp cel.
	 *
	 * @return the codigo emp cel
	 */
	public String getCodigoEmpCel() {
		return codigoEmpCel;
	}

	/**
	 * Sets the codigo emp cel.
	 *
	 * @param codigoEmpCel
	 *            the new codigo emp cel
	 */
	public void setCodigoEmpCel(String codigoEmpCel) {
		this.codigoEmpCel = codigoEmpCel;
	}

	/**
	 * Gets the descrip emp cel.
	 *
	 * @return the descrip emp cel
	 */
	public String getDescripEmpCel() {
		return descripEmpCel;
	}

	/**
	 * Sets the descrip emp cel.
	 *
	 * @param descripEmpCel
	 *            the new descrip emp cel
	 */
	public void setDescripEmpCel(String descripEmpCel) {
		this.descripEmpCel = descripEmpCel;
	}

}