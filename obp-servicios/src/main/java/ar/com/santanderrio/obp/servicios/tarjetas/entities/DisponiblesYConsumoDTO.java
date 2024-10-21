/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DisponiblesYConsumoDTO.
 *
 * @author sabrina.cis
 */
public class DisponiblesYConsumoDTO {

	/** The resumenes. */
	private List<ResumenTarjetaDTO> resumenes;

	/** The tarjetas activas. */
	private List<LimitesYDisponiblesDTO> limitesYDisponiblesDTO;

	/**
	 * Instantiates a new disponibles Y consumo DTO.
	 *
	 * @param resumenes
	 *            the resumenes
	 * @param limitesYDisponiblesDTO
	 *            the limites Y disponibles DTO
	 */
	public DisponiblesYConsumoDTO(List<ResumenTarjetaDTO> resumenes,
			List<LimitesYDisponiblesDTO> limitesYDisponiblesDTO) {
		super();
		this.resumenes = resumenes;
		this.setLimitesYDisponiblesDTO(limitesYDisponiblesDTO);
	}

	/**
	 * Instantiates a new disponibles Y consumo DTO.
	 */
	public DisponiblesYConsumoDTO() {
		super();
		this.resumenes = new ArrayList<ResumenTarjetaDTO>();
		this.limitesYDisponiblesDTO = new ArrayList<LimitesYDisponiblesDTO>();
	}

	/**
	 * Gets the resumenes.
	 *
	 * @return the resumenes
	 */
	public List<ResumenTarjetaDTO> getResumenes() {
		return resumenes;
	}

	/**
	 * Sets the resumenes.
	 *
	 * @param resumenes
	 *            the new resumenes
	 */
	public void setResumenes(List<ResumenTarjetaDTO> resumenes) {
		this.resumenes = resumenes;
	}

	/**
	 * Gets the limites Y disponibles DTO.
	 *
	 * @return the limites Y disponibles DTO
	 */
	public List<LimitesYDisponiblesDTO> getLimitesYDisponiblesDTO() {
		return limitesYDisponiblesDTO;
	}

	/**
	 * Sets the limites Y disponibles DTO.
	 *
	 * @param limitesYDisponiblesDTO
	 *            the new limites Y disponibles DTO
	 */
	public void setLimitesYDisponiblesDTO(List<LimitesYDisponiblesDTO> limitesYDisponiblesDTO) {
		this.limitesYDisponiblesDTO = limitesYDisponiblesDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(resumenes);
		hcb.append(limitesYDisponiblesDTO);
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
		DisponiblesYConsumoDTO other = (DisponiblesYConsumoDTO) obj;
		return new EqualsBuilder().append(resumenes, other.resumenes)
				.append(limitesYDisponiblesDTO, other.limitesYDisponiblesDTO).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DisponiblesYConsumoDTO [resumenes=" + resumenes + ", limitesYDisponiblesDTO=" + limitesYDisponiblesDTO
				+ "]";
	}

}
