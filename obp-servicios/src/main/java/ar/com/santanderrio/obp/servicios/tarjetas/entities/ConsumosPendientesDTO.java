/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConsumosPendientesDTO.
 *
 * @author sabrina.cis
 */
public class ConsumosPendientesDTO {

	/** The consumos pendientes DTO. */
	private List<ConsumoPendienteDTO> consumosPendientesDTO = new ArrayList<ConsumoPendienteDTO>();

	/**
	 * Instantiates a new consumos pendientes DTO.
	 */
	public ConsumosPendientesDTO() {
		super();
	}

	/**
	 * Instantiates a new consumos pendientes DTO.
	 *
	 * @param consumosPendientesDTO
	 *            the consumos pendientes DTO
	 */
	public ConsumosPendientesDTO(List<ConsumoPendienteDTO> consumosPendientesDTO) {
		super();
		this.consumosPendientesDTO = consumosPendientesDTO;
	}

	/**
	 * Gets the consumos pendientes DTO.
	 *
	 * @return the consumos pendientes DTO
	 */
	public List<ConsumoPendienteDTO> getConsumosPendientesDTO() {
		return consumosPendientesDTO;
	}

	/**
	 * Sets the consumos pendientes DTO.
	 *
	 * @param consumosPendientesDTO
	 *            the new consumos pendientes DTO
	 */
	public void setConsumosPendientesDTO(List<ConsumoPendienteDTO> consumosPendientesDTO) {
		this.consumosPendientesDTO = consumosPendientesDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumosPendientesDTO);
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
		ConsumosPendientesDTO other = (ConsumosPendientesDTO) obj;
		return new EqualsBuilder().append(consumosPendientesDTO, other.consumosPendientesDTO).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsumosPendientesDTO [consumosPendientesDTO=" + consumosPendientesDTO + "]";
	}

}
