/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities;

import java.util.List;


/**
 * The Class ConsultaDatosTarjetaOutEntity.
 */
public class ConsultaDatosTarjetaOutEntity {

	/** The tarjetas. */
	private List<TarjetaDatos> tarjetas;

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaDatos> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetaDatos> tarjetas) {
		this.tarjetas = tarjetas;
	}
	
}
