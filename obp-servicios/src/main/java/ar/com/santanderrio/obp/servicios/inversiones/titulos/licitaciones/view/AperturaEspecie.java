/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class AperturaEspecie.
 */
public class AperturaEspecie{

	/** The plazo. */
	private Integer plazo;
	
	/** The lista monedas. */
	private List<DatosMonedaEspecie> listaMonedas = new ArrayList<DatosMonedaEspecie>();

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public Integer getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the lista monedas.
	 *
	 * @return the lista monedas
	 */
	public List<DatosMonedaEspecie> getListaMonedas() {
		return listaMonedas;
	}

	/**
	 * Sets the lista monedas.
	 *
	 * @param listaMonedas
	 *            the new lista monedas
	 */
	public void setListaMonedas(List<DatosMonedaEspecie> listaMonedas) {
		this.listaMonedas = listaMonedas;
	}
	
}
