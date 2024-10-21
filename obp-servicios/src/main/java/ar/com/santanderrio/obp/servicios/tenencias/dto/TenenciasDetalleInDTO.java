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
public class TenenciasDetalleInDTO {

	/** The anio hasta. */
	private String anioHasta;

	/** The anio desde. */
	private String anioDesde;

	/** The nup. */
	private String nup;

	/** The p espe tipo. */
	private String pEspeTipo;

	/**
	 * Gets the anio hasta.
	 *
	 * @return the anioHasta
	 */
	public String getAnioHasta() {
		return anioHasta;
	}

	/**
	 * Sets the anio hasta.
	 *
	 * @param anioHasta
	 *            the anioHasta to set
	 */
	public void setAnioHasta(String anioHasta) {
		this.anioHasta = anioHasta;
	}

	/**
	 * Gets the anio desde.
	 *
	 * @return the anioDesde
	 */
	public String getAnioDesde() {
		return anioDesde;
	}

	/**
	 * Sets the anio desde.
	 *
	 * @param anioDesde
	 *            the anioDesde to set
	 */
	public void setAnioDesde(String anioDesde) {
		this.anioDesde = anioDesde;
	}

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

	/**
	 * Gets the p espe tipo.
	 *
	 * @return the pEspeTipo
	 */
	public String getpEspeTipo() {
		return pEspeTipo;
	}

	/**
	 * Sets the p espe tipo.
	 *
	 * @param pEspeTipo
	 *            the pEspeTipo to set
	 */
	public void setpEspeTipo(String pEspeTipo) {
		this.pEspeTipo = pEspeTipo;
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
		result = prime * result + ((anioDesde == null) ? 0 : anioDesde.hashCode());
		result = prime * result + ((anioHasta == null) ? 0 : anioHasta.hashCode());
		result = prime * result + ((nup == null) ? 0 : nup.hashCode());
		result = prime * result + ((pEspeTipo == null) ? 0 : pEspeTipo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TenenciasDetalleInDTO [anioHasta=" + anioHasta + ", anioDesde=" + anioDesde + ", nup=" + nup
				+ ", pEspeTipo=" + pEspeTipo + "]";
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
		TenenciasDetalleInDTO other = (TenenciasDetalleInDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(anioDesde, other.getAnioDesde()).append(anioHasta, other.getAnioHasta())
				.append(pEspeTipo, other.getpEspeTipo()).append(nup, other.getNup()).isEquals();
	}
}
