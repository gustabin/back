/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author
 *
 */
public class FirmantesInEntity {

	/** The anio hasta. */
	private String anioHasta;

	/** The anio desde. */
	private String anioDesde;

	/** The nup. */
	private String nup;

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
		return new HashCodeBuilder().append(anioDesde).append(anioHasta).append(nup).toHashCode();
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

		FirmantesInEntity other = (FirmantesInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(anioHasta, other.anioHasta).append(anioDesde, other.anioDesde).append(nup, other.nup)
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("anioDesde", anioDesde).append("anioHasta", anioHasta)
				.append("nup", nup).toString();
	}
}
