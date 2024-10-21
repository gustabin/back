/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.entitites;

import java.util.List;

/**
 * The Class ListaTarjetasDTO.
 */
public class ListaTarjetasDTO {

	/** The tarjetas. */
	private List<TarjetaHomeDTO> tarjetas;

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaHomeDTO> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetaHomeDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}

}
