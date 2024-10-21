/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class ParametroFrecuencia.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ParametroFrecuencia {

	/** The id frecuencia. */
	@XmlAttribute(name = "id")
	private String idFrecuencia;

	/** The codigo frecuencia. */
	@XmlElement(name = "CodigoFrecuencia")
	private String codigoFrecuencia;

	/** The descrip frecuencia. */
	@XmlElement(name = "DescripFrecuencia")
	private String descripFrecuencia;

	/**
	 * Gets the id frecuencia.
	 *
	 * @return the id frecuencia
	 */
	public String getIdFrecuencia() {
		return idFrecuencia;
	}

	/**
	 * Sets the id frecuencia.
	 *
	 * @param idFrecuencia
	 *            the new id frecuencia
	 */
	public void setIdFrecuencia(String idFrecuencia) {
		this.idFrecuencia = idFrecuencia;
	}

	/**
	 * Gets the codigo frecuencia.
	 *
	 * @return the codigo frecuencia
	 */
	public String getCodigoFrecuencia() {
		return codigoFrecuencia;
	}

	/**
	 * Sets the codigo frecuencia.
	 *
	 * @param codigoFrecuencia
	 *            the new codigo frecuencia
	 */
	public void setCodigoFrecuencia(String codigoFrecuencia) {
		this.codigoFrecuencia = codigoFrecuencia;
	}

	/**
	 * Gets the descrip frecuencia.
	 *
	 * @return the descrip frecuencia
	 */
	public String getDescripFrecuencia() {
		return descripFrecuencia;
	}

	/**
	 * Sets the descrip frecuencia.
	 *
	 * @param descripFrecuencia
	 *            the new descrip frecuencia
	 */
	public void setDescripFrecuencia(String descripFrecuencia) {
		this.descripFrecuencia = descripFrecuencia;
	}

}