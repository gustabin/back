/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.math.BigDecimal;

/**
 * The Class DatosGraficoRentabilidadView.
 */
public class DatosGraficoRentabilidadView {
	
	/** The codigo cabecera. */
	private String codigoCabecera;

	/** The rentabilidad neta. */
	private BigDecimal rentabilidadNeta;
	
	/** The rentabilidad tooltip. */
	RentabilidadTooltip rentabilidadTooltip;

	/**
	 * Gets the codigo cabecera.
	 *
	 * @return the codigo cabecera
	 */
	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	/**
	 * Sets the codigo cabecera.
	 *
	 * @param codigoCabecera
	 *            the new codigo cabecera
	 */
	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}

	/**
	 * Gets the rentabilidad neta.
	 *
	 * @return the rentabilidad neta
	 */
	public BigDecimal getRentabilidadNeta() {
		return rentabilidadNeta;
	}

	/**
	 * Sets the rentabilidad neta.
	 *
	 * @param rentabilidadNeta
	 *            the new rentabilidad neta
	 */
	public void setRentabilidadNeta(BigDecimal rentabilidadNeta) {
		this.rentabilidadNeta = rentabilidadNeta;
	}

	/**
	 * Gets the rentabilidad tooltip.
	 *
	 * @return the rentabilidad tooltip
	 */
	public RentabilidadTooltip getRentabilidadTooltip() {
		return rentabilidadTooltip;
	}

	/**
	 * Sets the rentabilidad tooltip.
	 *
	 * @param rentabilidadTooltip
	 *            the new rentabilidad tooltip
	 */
	public void setRentabilidadTooltip(RentabilidadTooltip rentabilidadTooltip) {
		this.rentabilidadTooltip = rentabilidadTooltip;
	}
}
