/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.List;

/**
 * The Class CuentasIntermedioView.
 */
public class CuentasIntermedioView {

	/** The cabecera. */
	private CabeceraCuentasView cabecera;

	/** The grupos. */
	private List<GrupoCajitaCuentasView> grupos;

	/** The selector. */
	private List<CuentaSelectorView> selector;
	
	/** The puedeTransferir. */
	private boolean puedeTransferir = true;

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public CabeceraCuentasView getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(CabeceraCuentasView cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Gets the grupos.
	 *
	 * @return the grupos
	 */
	public List<GrupoCajitaCuentasView> getGrupos() {
		return grupos;
	}

	/**
	 * Sets the grupos.
	 *
	 * @param grupos
	 *            the new grupos
	 */
	public void setGrupos(List<GrupoCajitaCuentasView> grupos) {
		this.grupos = grupos;
	}

	/**
	 * Gets the selector.
	 *
	 * @return the selector
	 */
	public List<CuentaSelectorView> getSelector() {
		return selector;
	}

	/**
	 * Sets the selector.
	 *
	 * @param selector
	 *            the new selector
	 */
	public void setSelector(List<CuentaSelectorView> selector) {
		this.selector = selector;
	}

	/**
	 * Checks if is puede transferir.
	 *
	 * @return true, if is puede transferir
	 */
	public boolean isPuedeTransferir() {
		return puedeTransferir;
	}

	/**
	 * Sets the puede transferir.
	 *
	 * @param puedeTransferir
	 *            the new puede transferir
	 */
	public void setPuedeTransferir(boolean puedeTransferir) {
		this.puedeTransferir = puedeTransferir;
	}
}
