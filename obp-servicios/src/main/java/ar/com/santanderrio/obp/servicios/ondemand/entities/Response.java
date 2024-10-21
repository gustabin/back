/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ondemand.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class Response.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {

	/** The resumenes. */
	@XmlElement(name = "resumen", type = Resumen.class)
	private List<Resumen> resumenes;

	/**
	 * Gets the resumenes.
	 *
	 * @return the resumenes
	 */
	public List<Resumen> getResumenes() {
		return resumenes;
	}

	/**
	 * Sets the resumenes.
	 *
	 * @param resumenes
	 *            the new resumenes
	 */
	public void setResumenes(List<Resumen> resumenes) {
		this.resumenes = resumenes;
	}

}
