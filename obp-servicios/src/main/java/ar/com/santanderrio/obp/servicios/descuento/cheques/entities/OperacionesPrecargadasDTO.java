/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class OperacionesPrecargadasDTO.
 */
public class OperacionesPrecargadasDTO {

	/** The dto. */
	private List<OperacionPrecargadaDTO> dto = new ArrayList<OperacionPrecargadaDTO>();
	
	/** The tiene mas operaciones. */
	private Boolean tieneMasOperaciones;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public List<OperacionPrecargadaDTO> getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto
	 *            the new dto
	 */
	public void setDto(List<OperacionPrecargadaDTO> dto) {
		this.dto = dto;
	}

	/**
	 * Gets the tiene mas operaciones.
	 *
	 * @return the tiene mas operaciones
	 */
	public Boolean getTieneMasOperaciones() {
		return tieneMasOperaciones;
	}

	/**
	 * Sets the tiene mas operaciones.
	 *
	 * @param tieneMasOperaciones
	 *            the new tiene mas operaciones
	 */
	public void setTieneMasOperaciones(Boolean tieneMasOperaciones) {
		this.tieneMasOperaciones = tieneMasOperaciones;
	}
	
	
}
