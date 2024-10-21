/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConsultaFinanciacionDTO.
 *
 * @author sabrina.cis
 */
public class ConsultaFinanciacionDTO {

	/** The detallesFinanciaciones. */
	private List<ConsultaFinanciacionDetalleDTO> detallesFinanciaciones = new ArrayList<ConsultaFinanciacionDetalleDTO>();

	/**
	 * Gets the detalles financiaciones.
	 *
	 * @return the detalles financiaciones
	 */
	public List<ConsultaFinanciacionDetalleDTO> getDetallesFinanciaciones() {
		return detallesFinanciaciones;
	}

	/**
	 * Sets the detalles financiaciones.
	 *
	 * @param detallesFinanciaciones
	 *            the new detalles financiaciones
	 */
	public void setDetallesFinanciaciones(List<ConsultaFinanciacionDetalleDTO> detallesFinanciaciones) {
		this.detallesFinanciaciones = detallesFinanciaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(detallesFinanciaciones);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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
		ConsultaFinanciacionDTO other = (ConsultaFinanciacionDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(detallesFinanciaciones, other.detallesFinanciaciones);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaFinanciacionDTO [detallesFinanciaciones=" + detallesFinanciaciones + "]";
	}
}
