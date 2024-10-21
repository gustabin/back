/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto;

import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentTotalPeriodoMoneda;

/**
 * The Class DetalleRentabilidadTotalDTO.
 */
public class DetalleRentabilidadTotalDTO {

	/** The rent total periodo moneda. */
	private RentTotalPeriodoMoneda rentTotalPeriodoMoneda;
	
	/** The hay error. */
	private Boolean hayError = Boolean.FALSE;
	
	/** The corresponde banca. */
	private Boolean correspondeBanca = Boolean.FALSE;
	
	/** The no tiene rentabilidad. */
	private Boolean noTieneRentabilidad = Boolean.FALSE;

	
	/**
	 * Gets the rent total periodo moneda.
	 *
	 * @return the rent total periodo moneda
	 */
	public RentTotalPeriodoMoneda getRentTotalPeriodoMoneda() {
		return rentTotalPeriodoMoneda;
	}

	/**
	 * Sets the rent total periodo moneda.
	 *
	 * @param rentTotalPeriodoMoneda
	 *            the new rent total periodo moneda
	 */
	public void setRentTotalPeriodoMoneda(RentTotalPeriodoMoneda rentTotalPeriodoMoneda) {
		this.rentTotalPeriodoMoneda = rentTotalPeriodoMoneda;
	}

	/**
	 * Gets the hay error.
	 *
	 * @return the hay error
	 */
	public Boolean getHayError() {	
		return hayError;
	}

	/**
	 * Sets the hay error.
	 *
	 * @param hayError
	 *            the new hay error
	 */
	public void setHayError(Boolean hayError) {
		this.hayError = hayError;
	}

	/**
	 * Gets the corresponde banca.
	 *
	 * @return the corresponde banca
	 */
	public Boolean getCorrespondeBanca() {
		return correspondeBanca;
	}

	/**
	 * Sets the corresponde banca.
	 *
	 * @param correspondeBanca
	 *            the new corresponde banca
	 */
	public void setCorrespondeBanca(Boolean correspondeBanca) {
		this.correspondeBanca = correspondeBanca;
	}

	/**
	 * Gets the no tiene rentabilidad.
	 *
	 * @return the no tiene rentabilidad
	 */
	public Boolean getNoTieneRentabilidad() {
		return noTieneRentabilidad;
	}

	/**
	 * Sets the no tiene rentabilidad.
	 *
	 * @param noTieneRentabilidad
	 *            the new no tiene rentabilidad
	 */
	public void setNoTieneRentabilidad(Boolean noTieneRentabilidad) {
		this.noTieneRentabilidad = noTieneRentabilidad;
	}
	
}