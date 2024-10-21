/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ondemand.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class XMLFile.
 */
@XmlRootElement(name = "xml")
public class XMLFile {

	/** The response. */
	private Response response;

	/** The datos. */
	private Datos datos;

	/** The cod ret. */
	private String codRet;

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	@XmlElement(name = "response", type = Response.class)
	public Response getResponse() {
		return response;
	}

	/**
	 * Sets the response.
	 *
	 * @param response
	 *            the new response
	 */
	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	@XmlElement(name = "datos", type = Datos.class)
	public Datos getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(Datos datos) {
		this.datos = datos;
	}

	/**
	 * Gets the cod ret.
	 *
	 * @return the codRet
	 */
	@XmlElement(name = "codRet", type = String.class)
	public String getCodRet() {
		return codRet;
	}

	/**
	 * Sets the cod ret.
	 *
	 * @param codRet
	 *            the codRet to set
	 */
	public void setCodRet(String codRet) {
		this.codRet = codRet;
	}

}
