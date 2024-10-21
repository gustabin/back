/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author desa
 *
 */
public class TenenciasDetalleInEntity {

	/** The anio desde. */
	private String anioDesde;

	/** The anio hasta. */
	private String anioHasta;

	/** The nup. */
	private String nup;

	/** The espe tipo. */
	private String espeTipo;

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

	/**
	 * Gets the espe tipo.
	 *
	 * @return the espeTipo
	 */
	public String getEspeTipo() {
		return espeTipo;
	}

	/**
	 * Sets the espe tipo.
	 *
	 * @param espeTipo
	 *            the espeTipo to set
	 */
	public void setEspeTipo(String espeTipo) {
		this.espeTipo = espeTipo;
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
		result = prime * result + ((espeTipo == null) ? 0 : espeTipo.hashCode());
		result = prime * result + ((nup == null) ? 0 : nup.hashCode());
		return result;
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
		TenenciasDetalleInEntity other = (TenenciasDetalleInEntity) obj;
		if (anioDesde == null) {
			if (other.anioDesde != null)
				return false;
		} else if (!anioDesde.equals(other.anioDesde))
			return false;
		if (anioHasta == null) {
			if (other.anioHasta != null)
				return false;
		} else if (!anioHasta.equals(other.anioHasta))
			return false;
		if (espeTipo == null) {
			if (other.espeTipo != null)
				return false;
		} else if (!espeTipo.equals(other.espeTipo))
			return false;
		if (nup == null) {
			if (other.nup != null)
				return false;
		} else if (!nup.equals(other.nup))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TenenciasDetalleInEntity [anioDesde=" + anioDesde + ", anioHasta=" + anioHasta + ", nup=" + nup
				+ ", espeTipo=" + espeTipo + "]";
	}

}
