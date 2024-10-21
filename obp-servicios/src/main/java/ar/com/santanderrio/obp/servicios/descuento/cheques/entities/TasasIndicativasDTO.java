/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TasasIndicativasDTO.
 */
public class TasasIndicativasDTO {

	/** The lista. */
	private List<TasaIndicativaDTO> lista = new ArrayList<TasaIndicativaDTO>();
	
	/** The legales. */
	private String legales;

	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public List<TasaIndicativaDTO> getLista() {
		return lista;
	}

	/**
	 * Sets the lista.
	 *
	 * @param lista
	 *            the new lista
	 */
	public void setLista(List<TasaIndicativaDTO> lista) {
		this.lista = lista;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}
	
	
}
