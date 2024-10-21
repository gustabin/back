/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

import java.util.List;

/**
 * The Class DetalleViajeDTO.
 */
public class DetalleViajeDTO {

	/** The viajes DTO. */
	private ViajesDTO viajesDTO;

	/** The viajes. */
	private List<ViajesDTO> viajes;

	/**
	 * Gets the viajes DTO.
	 *
	 * @return the viajes DTO
	 */
	public ViajesDTO getViajesDTO() {
		return viajesDTO;
	}

	/**
	 * Sets the viajes DTO.
	 *  
	 * @param viajesDTO the new viajes DTO
	 */
	public void setViajesDTO(ViajesDTO viajesDTO) { 
		this.viajesDTO = viajesDTO;
	}

	/**
	 * Gets the viajes.
	 *
	 * @return the viajes
	 */
	public List<ViajesDTO> getViajes() {
		return viajes;
	}

	/**
	 * Sets the viajes.
	 *
	 * @param viajes the new viajes
	 */
	public void setViajes(List<ViajesDTO> viajes) {
		this.viajes = viajes;
	}

}
