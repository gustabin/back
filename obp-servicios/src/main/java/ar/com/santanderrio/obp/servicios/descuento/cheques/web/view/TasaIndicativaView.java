/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasaIndicativaDTO;

/**
 * The Class TasaIndicativaView.
 */
public class TasaIndicativaView {

	/** The plazo. */
	private String plazo;
	
	/** The tasa. */
	private String tasa;
	
	/**
	 * Instantiates a new tasa indicativa view.
	 *
	 * @param tasa
	 *            the tasa
	 */
	public TasaIndicativaView(TasaIndicativaDTO tasa) {
		this.plazo = tasa.getPlazo().toString();
		if(tasa.getTasa().compareTo(BigDecimal.ZERO)!= 0) {
			this.tasa = ISBANStringUtils.formatearConComaYDosDecimales(tasa.getTasa().toString());	
		}else {
			this.tasa = "---";
		}
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the tasa.
	 *
	 * @return the tasa
	 */
	public String getTasa() {
		return tasa;
	}

	/**
	 * Sets the tasa.
	 *
	 * @param tasa
	 *            the new tasa
	 */
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

}
