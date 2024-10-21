/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto utilizado para el Input del TenenciasSEI.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TenenciasDetalleInView {

	/** The anio desde. */
	private String anioDesde;

	/** The anio hasta. */
	private String anioHasta;

	/** The espe tipo. */
	private String espeTipo;

	/** The tipo detalle. */
	private int tipoDetalle;

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

	/**
	 * Gets the tipo detalle.
	 *
	 * @return the tipoDetalle
	 */
	public int getTipoDetalle() {
		return tipoDetalle;
	}

	/**
	 * Sets the tipo detalle.
	 *
	 * @param tipoDetalle
	 *            the tipoDetalle to set
	 */
	public void setTipoDetalle(int tipoDetalle) {
		this.tipoDetalle = tipoDetalle;
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
		TenenciasDetalleInView other = (TenenciasDetalleInView) obj;
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
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TenenciasDetalleInView [anioDesde=" + anioDesde + ", anioHasta=" + anioHasta + ", espeTipo=" + espeTipo
				+ "]";
	}

}
