/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class DomicilioView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DomicilioView {
	
	/** The calle. */
	private String calle;
	
	/** The numero. */
	private String numero;
	
	/** The codigoPostal. */
	private String codigoPostal;
	
	/** The localidad. */
	private String localidad;
	
	/**
	 * Gets the calle.
	 *
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}
	
	/**
	 * Sets the calle.
	 *
	 * @param calle
	 *            the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * Gets the codigo postal.
	 *
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	/**
	 * Sets the codigo postal.
	 *
	 * @param codigoPostal
	 *            the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	
	/**
	 * Sets the localidad.
	 *
	 * @param localidad
	 *            the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	

}
