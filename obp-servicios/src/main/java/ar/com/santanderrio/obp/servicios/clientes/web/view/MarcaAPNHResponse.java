/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.web.view;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class MarcaAPNHResponse.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarcaAPNHResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The marca APNH. */
	private String marcaAPNH;

	/** The dni. */
	private String dni;

	/** The aviso cambio clave. */
	private String avisoCambioClave;
	
	/** The aviso cambio usuario. */
	private String avisoCambioUsuario;
	
	/**
	 * Gets the marca APNH.
	 *
	 * @return the marca APNH
	 */
	public String getMarcaAPNH() {
		return marcaAPNH;
	}

	/**
	 * Sets the marca APNH.
	 *
	 * @param marcaAPNH
	 *            the new marca APNH
	 */
	public void setMarcaAPNH(String marcaAPNH) {
		this.marcaAPNH = marcaAPNH;
	}

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
	 * @return the avisoCambioClave
	 */
	public String getAvisoCambioClave() {
		return avisoCambioClave;
	}

	/**
	 * @param avisoCambioClave the avisoCambioClave to set
	 */
	public void setAvisoCambioClave(String avisoCambioClave) {
		this.avisoCambioClave = avisoCambioClave;
	}

	/**
	 * @return the avisoCambioUsuario
	 */
	public String getAvisoCambioUsuario() {
		return avisoCambioUsuario;
	}

	/**
	 * @param avisoCambioUsuario the avisoCambioUsuario to set
	 */
	public void setAvisoCambioUsuario(String avisoCambioUsuario) {
		this.avisoCambioUsuario = avisoCambioUsuario;
	}

}
