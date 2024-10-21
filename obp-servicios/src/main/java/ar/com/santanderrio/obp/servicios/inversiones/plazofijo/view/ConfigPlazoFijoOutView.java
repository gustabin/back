/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.MinimosPlazoFijoDTO;

/**
 * The Class ConfigPlazoFijoOutView.
 *
 * @author juan.pablo.picate
 */
public class ConfigPlazoFijoOutView {

	/** The cuentas. */
	private List<CuentasAdhesionDebitoView> cuentasDebito;

	/** The legales. */
	private String legales;

	/** The minimos. */
	private MinimosPlazoFijoDTO minimos;

	/**
	 * Gets the cuentas debito.
	 *
	 * @return the cuentasDebito
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDebito() {
		return cuentasDebito;
	}

	/**
	 * Sets the cuentas debito.
	 *
	 * @param cuentasDebito
	 *            the cuentasDebito to set
	 */
	public void setCuentasDebito(List<CuentasAdhesionDebitoView> cuentasDebito) {
		this.cuentasDebito = cuentasDebito;
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
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the minimos.
	 *
	 * @return the minimos
	 */
	public MinimosPlazoFijoDTO getMinimos() {
		return minimos;
	}

	/**
	 * Sets the minimos.
	 *
	 * @param consultaTasasPlazoFijoOutDTO
	 *            the minimos to set
	 */
	public void setMinimos(MinimosPlazoFijoDTO consultaTasasPlazoFijoOutDTO) {
		this.minimos = consultaTasasPlazoFijoOutDTO;
	}

}
