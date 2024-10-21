/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

/**
 * The Class DatosBeneficiarioOutView.
 */
public class DatosBeneficiarioOutView {
	
	//TODO se pidio comentar combo VINCULO_COMEX
//	/** The comboVinculo. */
//	private List<String> comboVinculo;
	
	/** The comboPaises. */
	private List<ComboView> comboPaises;

	/**
	 * Gets the combo paises.
	 *
	 * @return the comboPaises
	 */
	public List<ComboView> getComboPaises() {
		return comboPaises;
	}

	/**
	 * Sets the combo paises.
	 *
	 * @param comboPaises
	 *            the comboPaises to set
	 */
	public void setComboPaises(List<ComboView> comboPaises) {
		this.comboPaises = comboPaises;
	}

	//TODO se pidio comentar combo VINCULO_COMEX
//	/**
//	 * @return the comboVinculo
//	 */
//	public List<String> getComboVinculo() {
//		return comboVinculo;
//	}

//	/**
//	 * @param comboVinculo the comboVinculo to set
//	 */
//	public void setComboVinculo(List<String> comboVinculo) {
//		this.comboVinculo = comboVinculo;
//	}

	
	
}