/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ItemError;

/**
 * The Class DatosRespuestaRentabilidadTotal.
 */
public class DatosRespuestaRentabilidadTotal {

	/** The lista errores. */
	@JsonProperty("ListaErrores")
	private List<ItemError> listaErrores;
	
	/** The rent total periodo moneda. */
	@JsonProperty("Resultado")
	private RentTotalPeriodoMoneda rentTotalPeriodoMoneda;

	
	/**
	 * Gets the lista errores.
	 *
	 * @return the lista errores
	 */
	public List<ItemError> getListaErrores() {
		return listaErrores;
	}

	/**
	 * Sets the lista errores.
	 *
	 * @param listaErrores
	 *            the new lista errores
	 */
	public void setListaErrores(List<ItemError> listaErrores) {
		this.listaErrores = listaErrores;
	}

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


	
	
	
	
}