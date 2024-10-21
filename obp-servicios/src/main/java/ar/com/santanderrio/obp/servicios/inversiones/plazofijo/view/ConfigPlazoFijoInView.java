/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.List;

/**
 * The Class ConfigPlazoFijoInView.
 */
public class ConfigPlazoFijoInView {
	
	/** The tipo banca. */
	private String tipoBanca;
	
	/** The cabecera stack. */
	private String cabeceraStack;
	
	/** The cuentas titulo. */
	private List<String> cuentasTitulo;
	
	/** The fondos totales. */
	private List<String> fondosTotales;
	
	/**
	 * Gets the tipo banca.
	 *
	 * @return the tipo banca
	 */
	public String getTipoBanca() {
		return tipoBanca;
	}

	/**
	 * Sets the tipo banca.
	 *
	 * @param tipoBanca
	 *            the tipo banca to set
	 */
	public void setTipoBanca(String tipoBanca) {
		this.tipoBanca = tipoBanca;
	}
	
	/**
	 * Gets the cabecera stack.
	 *
	 * @return the cabecera stack
	 */
	public String getCabeceraStack() {
		return cabeceraStack;
	}

	/**
	 * Sets the cabecera stack.
	 *
	 * @param cabeceraStack
	 *            the cabeceraStack to set
	 */
	public void setCabeceraStack(String cabeceraStack) {
		this.cabeceraStack = cabeceraStack;
	}
	
	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<String> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the cuentas titulo to set
	 */
	public void setCuentasTitulo(List<String> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}
	
	/**
	 * Gets the fondos totales.
	 *
	 * @return the fondos totales
	 */
	public List<String> getFondosTotales() {
		return fondosTotales;
	}

	/**
	 * Sets the fondos totales.
	 *
	 * @param fondosTotales
	 *            the fondos totales to set
	 */
	public void setFondosTotales(List<String> fondosTotales) {
		this.fondosTotales = fondosTotales;
	}
	
}
