/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author desa
 *
 */
public class TenenciasCitiInDTO {

	/** The nup. */
	private String nup;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nup == null) ? 0 : nup.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TenenciasCitiInDTO [nup=" + nup + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		TenenciasCitiInDTO other = (TenenciasCitiInDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(nup, other.getNup()).isEquals();
	}
}
