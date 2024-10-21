/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contrato.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author pablo.d.gargaglione
 *
 */
public class ContratoInEntity {

	/** The dni. */
	private String dni;

	/** The sinonimo. */
	private String sinonimo;

	/** The campo. */
	private String campo;

	/** The fecha nac. */
	private String fechaNac;

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
	 * Gets the sinonimo.
	 *
	 * @return the sinonimo
	 */
	public String getSinonimo() {
		return sinonimo;
	}

	/**
	 * Sets the sinonimo.
	 *
	 * @param sinonimo
	 *            the new sinonimo
	 */
	public void setSinonimo(String sinonimo) {
		this.sinonimo = sinonimo;
	}

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * Sets the campo.
	 *
	 * @param campo
	 *            the new campo
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * Gets the fecha nac.
	 *
	 * @return the fecha nac
	 */
	public String getFechaNac() {
		return fechaNac;
	}

	/**
	 * Sets the fecha nac.
	 *
	 * @param fechaNac
	 *            the new fecha nac
	 */
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContratoInEntity [dni=" + dni + ", sinonimo=" + sinonimo + ", campo=" + campo + ", fechaNac=" + fechaNac
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(dni);
		hcb.append(sinonimo);
		hcb.append(campo);
		hcb.append(fechaNac);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ContratoInEntity other = (ContratoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(dni, other.dni);
		eb.append(sinonimo, other.sinonimo);
		eb.append(campo, other.campo);
		eb.append(fechaNac, other.fechaNac);
		return eb.isEquals();
	}

}
