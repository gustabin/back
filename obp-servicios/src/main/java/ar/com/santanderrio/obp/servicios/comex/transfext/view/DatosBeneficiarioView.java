/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


/**
 * The Class DatosBeneficiarioView.
 *
 * @author B040619
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosBeneficiarioView {
	
	/** The nombre beneficiario. */
	private String nombreBeneficiario;
	
	//TODO se pidio comentar combo VINCULO_COMEX
//	/** The vinculo. */
//	private String vinculo;
		
	/** The domicilio calle. */
	private String domicilioCalle;
	
	/** The domicilio numero. */
	private String domicilioNumero;
	
	/** The domicilio localidad. */
	private String domicilioLocalidad;
	
	/** The domicilio pais. */
	private String domicilioPais;
	
	/** The cod pais. */
	private String codPais;

	/**
	 * Gets the nombre beneficiario.
	 *
	 * @return the nombreBeneficiario
	 */
	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	/**
	 * Sets the nombre beneficiario.
	 *
	 * @param nombreBeneficiario
	 *            the nombreBeneficiario to set
	 */
	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	//TODO se pidio comentar combo VINCULO_COMEX
//	/**
//	 * @return the vinculo
//	 */
//	public String getVinculo() {
//		return vinculo;
//	}
//
//	/**
//	 * @param vinculo the vinculo to set
//	 */
//	public void setVinculo(String vinculo) {
//		this.vinculo = vinculo;
//	}

	/**
	 * Gets the domicilio calle.
	 *
	 * @return the domicilioCalle
	 */
	public String getDomicilioCalle() {
		return domicilioCalle;
	}

	/**
	 * Sets the domicilio calle.
	 *
	 * @param domicilioCalle
	 *            the domicilioCalle to set
	 */
	public void setDomicilioCalle(String domicilioCalle) {
		this.domicilioCalle = domicilioCalle;
	}

	/**
	 * Gets the domicilio numero.
	 *
	 * @return the domicilioNumero
	 */
	public String getDomicilioNumero() {
		return domicilioNumero;
	}

	/**
	 * Sets the domicilio numero.
	 *
	 * @param domicilioNumero
	 *            the domicilioNumero to set
	 */
	public void setDomicilioNumero(String domicilioNumero) {
		this.domicilioNumero = domicilioNumero;
	}

	/**
	 * Gets the domicilio localidad.
	 *
	 * @return the domicilioLocalidad
	 */
	public String getDomicilioLocalidad() {
		return domicilioLocalidad;
	}

	/**
	 * Sets the domicilio localidad.
	 *
	 * @param domicilioLocalidad
	 *            the domicilioLocalidad to set
	 */
	public void setDomicilioLocalidad(String domicilioLocalidad) {
		this.domicilioLocalidad = domicilioLocalidad;
	}

	/**
	 * Gets the domicilio pais.
	 *
	 * @return the domicilioPais
	 */
	public String getDomicilioPais() {
		return domicilioPais;
	}

	/**
	 * Sets the domicilio pais.
	 *
	 * @param domicilioPais
	 *            the domicilioPais to set
	 */
	public void setDomicilioPais(String domicilioPais) {
		this.domicilioPais = domicilioPais;
	}

	/**
	 * Gets the cod pais.
	 *
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * Sets the cod pais.
	 *
	 * @param codPais
	 *            the codPais to set
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	
	
	

}
