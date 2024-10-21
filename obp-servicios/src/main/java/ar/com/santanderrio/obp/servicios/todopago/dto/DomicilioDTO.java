/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Clase que encapsula los atributos de domicilio en la registracion de TodoPago.
 */
public class DomicilioDTO {

	/** calle. */
	private String calle;

	/** numero. */
	private String numero;

	/** piso. */
	private String piso;

	/** departamento. */
	private String departamento;

	/** provincia. */
	private String provincia;

	/** localidad. */
	private String localidad;

	/** codigo postal. */
	private String codigoPostal;

	/**
	 * Instantiates a new domicilio DTO.
	 */
	public DomicilioDTO() {
		// no implementado aun.
	}

	/**
	 * Retorna calle.
	 *
	 * @return calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Setea calle.
	 *
	 * @param calle
	 *            calle a setear
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Retorna numero.
	 *
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Setea numero.
	 *
	 * @param numero
	 *            numero a setear
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Retorna piso.
	 *
	 * @return piso
	 */
	public String getPiso() {
		return piso;
	}

	/**
	 * Setea piso.
	 *
	 * @param piso
	 *            piso a setear
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}

	/**
	 * Retorna departamento.
	 *
	 * @return departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * Setea departamento.
	 *
	 * @param departamento
	 *            departamento a setear
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * Retorna provincia.
	 *
	 * @return provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * Setea provincia.
	 *
	 * @param provincia
	 *            provincia a setear
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * Retorna localidad.
	 *
	 * @return localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Setea localidad.
	 *
	 * @param localidad
	 *            localidad a setear
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Retorna codigo postal.
	 *
	 * @return codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Setea codigo postal.
	 *
	 * @param codigoPostal
	 *            codigoPostal a setear
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(calle);
		hcb.append(codigoPostal);
		hcb.append(departamento);
		hcb.append(localidad);
		hcb.append(numero);
		hcb.append(piso);
		hcb.append(provincia);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomicilioDTO other = (DomicilioDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(calle, other.getCalle());
		eb.append(codigoPostal, other.getCodigoPostal());
		eb.append(departamento, other.getDepartamento());
		eb.append(localidad, other.getLocalidad());
		eb.append(numero, other.getNumero());
		eb.append(piso, other.getPiso());
		eb.append(provincia, other.getProvincia());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("calle", calle).append("numero", numero).append("piso", piso)
				.append("departamento", departamento).append("provincia", provincia).append("localidad", localidad)
				.append("codigoPostal", codigoPostal).toString();
	}

}
