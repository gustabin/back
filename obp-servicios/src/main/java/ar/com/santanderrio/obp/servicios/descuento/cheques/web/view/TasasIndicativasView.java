/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasaIndicativaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasDTO;

/**
 * The Class TasasIndicativasView.
 */
public class TasasIndicativasView {

	/** The legales. */
	private String legales;
	
	/** The lista tasas. */
	private List<TasaIndicativaView> listaTasas = new ArrayList<TasaIndicativaView>();
	
	/**
	 * Instantiates a new tasas indicativas view.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	public TasasIndicativasView(TasasIndicativasDTO respuesta) {
		legales = respuesta.getLegales();
		for(TasaIndicativaDTO tasa: respuesta.getLista()){
			listaTasas.add(new TasaIndicativaView(tasa));
		}
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

	/**
	 * Gets the lista tasas.
	 *
	 * @return the lista tasas
	 */
	public List<TasaIndicativaView> getListaTasas() {
		return listaTasas;
	}

	/**
	 * Sets the lista tasas.
	 *
	 * @param listaTasas
	 *            the new lista tasas
	 */
	public void setListaTasas(List<TasaIndicativaView> listaTasas) {
		this.listaTasas = listaTasas;
	}
	
	

}
