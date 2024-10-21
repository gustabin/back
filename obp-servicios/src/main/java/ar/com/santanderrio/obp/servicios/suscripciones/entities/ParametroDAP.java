/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class ParametroDAP.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ParametroDAP {

	/** The id dap. */
	@XmlAttribute(name = "id")
	private String idDap;

	/** The codigo dap. */
	@XmlElement(name = "CodigoDAP")
	private String codigoDap;

	/** The descrip dap. */
	@XmlElement(name = "DescripDAP")
	private String descripDap;

	/**
	 * Gets the id dap.
	 *
	 * @return the id dap
	 */
	public String getIdDap() {
		return idDap;
	}

	/**
	 * Sets the id dap.
	 *
	 * @param idDap
	 *            the new id dap
	 */
	public void setIdDap(String idDap) {
		this.idDap = idDap;
	}

	/**
	 * Gets the codigo dap.
	 *
	 * @return the codigo dap
	 */
	public String getCodigoDap() {
		return codigoDap;
	}

	/**
	 * Sets the codigo dap.
	 *
	 * @param codigoDap
	 *            the new codigo dap
	 */
	public void setCodigoDap(String codigoDap) {
		this.codigoDap = codigoDap;
	}

	/**
	 * Gets the descrip dap.
	 *
	 * @return the descrip dap
	 */
	public String getDescripDap() {
		return descripDap;
	}

	/**
	 * Sets the descrip dap.
	 *
	 * @param descripDap
	 *            the new descrip dap
	 */
	public void setDescripDap(String descripDap) {
		this.descripDap = descripDap;
	}

}