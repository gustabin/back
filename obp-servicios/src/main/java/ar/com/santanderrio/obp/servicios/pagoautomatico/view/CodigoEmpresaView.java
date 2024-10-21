/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class CodigoEmpresaView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CodigoEmpresaView {

	/** The nombre fantasia. */
	private String nombreFantasia;

	/**
	 * Gets the nombre fantasia.
	 *
	 * @return the nombre fantasia
	 */
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	/**
	 * Sets the nombre fantasia.
	 *
	 * @param nombreFantasia
	 *            the new nombre fantasia
	 */
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

}
