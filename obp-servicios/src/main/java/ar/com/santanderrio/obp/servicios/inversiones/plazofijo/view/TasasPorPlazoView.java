/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.List;

/**
 * The Class TasasPorPlazoView.
 */
public class TasasPorPlazoView {

	/** The nombre plazo. */
	String nombrePlazo;

	/** The lista valores tasa. */
	List<ValoresTasa> listaValoresTasa;

	/**
	 * Gets the nombre plazo.
	 *
	 * @return the nombre plazo
	 */
	public String getNombrePlazo() {
		return nombrePlazo;
	}

	/**
	 * Sets the nombre plazo.
	 *
	 * @param nombrePlazo
	 *            the new nombre plazo
	 */
	public void setNombrePlazo(String nombrePlazo) {
		this.nombrePlazo = nombrePlazo;
	}

	/**
	 * Gets the lista valores tasa.
	 *
	 * @return the lista valores tasa
	 */
	public List<ValoresTasa> getListaValoresTasa() {
		return listaValoresTasa;
	}

	/**
	 * Sets the lista valores tasa.
	 *
	 * @param listaValoresTasa
	 *            the new lista valores tasa
	 */
	public void setListaValoresTasa(List<ValoresTasa> listaValoresTasa) {
		this.listaValoresTasa = listaValoresTasa;
	}
}
