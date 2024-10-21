/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * The Class FiltroPorFechaRequestView.
 */
public class FiltroPorFechaRequestView {
	
	/** The es banca privada. */
	private Boolean esBancaPrivada;

	/** The lista cuentas. */
	private List<CuentaACView> listaCuentas;

	/** The cartera A consultar. */
	@NotNull
	private String carteraAConsultar;

	/** The moneda. */
	private String moneda;


	/**
	 * Gets the es banca privada.
	 *
	 * @return the es banca privada
	 */
	public Boolean getEsBancaPrivada() {
		return esBancaPrivada;
	}

	/**
	 * Sets the es banca privada.
	 *
	 * @param esBancaPrivada
	 *            the new es banca privada
	 */
	public void setEsBancaPrivada(Boolean esBancaPrivada) {
		this.esBancaPrivada = esBancaPrivada;
	}

	/**
	 * Gets the lista cuentas.
	 *
	 * @return the lista cuentas
	 */
	public List<CuentaACView> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the new lista cuentas
	 */
	public void setListaCuentas(List<CuentaACView> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the cartera A consultar.
	 *
	 * @return the cartera A consultar
	 */
	public String getCarteraAConsultar() {
		return carteraAConsultar;
	}

	/**
	 * Sets the cartera A consultar.
	 *
	 * @param carteraAConsultar
	 *            the new cartera A consultar
	 */
	public void setCarteraAConsultar(String carteraAConsultar) {
		this.carteraAConsultar = carteraAConsultar;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}


}
