/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.Serializable;
import java.util.List;

/**
 * The Class ConsumoTarjetasDTO.
 */
public class ConsumoTarjetasDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The consumos tarjetas. */
	private List<ConsumoTarjetaDTO> consumosTarjetas;

	/**
	 * Gets the consumos tarjetas.
	 *
	 * @return the consumos tarjetas
	 */
	public List<ConsumoTarjetaDTO> getConsumosTarjetas() {
		return consumosTarjetas;
	}

	/**
	 * Sets the consumos tarjetas.
	 *
	 * @param consumosTarjetas
	 *            the new consumos tarjetas
	 */
	public void setConsumosTarjetas(List<ConsumoTarjetaDTO> consumosTarjetas) {
		this.consumosTarjetas = consumosTarjetas;
	}

}
