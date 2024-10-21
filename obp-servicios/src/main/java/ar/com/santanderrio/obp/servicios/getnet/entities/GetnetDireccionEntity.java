package ar.com.santanderrio.obp.servicios.getnet.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class GetNetInformacionPersonalEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GetnetDireccionEntity {
	
	/** The calle. */
	@JsonProperty("street")
	private String calle;
	
	/** The numero. */
	@JsonProperty("number")
	private String numero;
	
	/** The piso. */
	@JsonProperty("floor")
	private String piso;
	
	/** The departamento. */
	@JsonProperty("apartment")
	private String departamento;
	
	/** The codigo postal. */
	@JsonProperty("post_code")
	private String codigoPostal;
	
	/** The ciudad. */
	@JsonProperty("city")
	private String ciudad;
	
	/** The provincia. */
	@JsonProperty("province")
	private String provincia;
	
	/** The pais. */
	@JsonProperty("country")
	private String pais;
	
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
	 *            the new calle
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
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * Gets the piso.
	 *
	 * @return the piso
	 */
	public String getPiso() {
		return piso;
	}

	/**
	 * Sets the piso.
	 *
	 * @param piso
	 *            the new piso
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	/**
	 * Gets the departamento.
	 *
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * Sets the departamento.
	 *
	 * @param departamento
	 *            the new departamento
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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
	 *            the new codigo postal
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	/**
	 * Gets the ciudad.
	 *
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Sets the ciudad.
	 *
	 * @param ciudad
	 *            the new ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	/**
	 * Gets the provincia.
	 *
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * Sets the provincia.
	 *
	 * @param provincia
	 *            the new provincia
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	/**
	 * Gets the pais.
	 *
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Sets the pais.
	 *
	 * @param pais
	 *            the new pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
		
}
