/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.List;

/**
 * The Class RentabilidadTotalInView.
 */
public class RentabilidadTotalInView {

	/** The cartera A consultar. */
	private String carteraAConsultar;

	/** The lista cuentas. */
	private List<String> listaCuentas;

	/** The periodo. */
	private String periodo;

	/** The periodo fecha inicial. */
	private String periodoFechaInicial;

	/** The periodo fecha fin. */
	private String periodoFechaFin;

	/**
	 * Instantiates a new rentabilidad total in view.
	 */
	public RentabilidadTotalInView() {
	}

	/**
	 * Instantiates a new rentabilidad total in view.
	 *
	 * @param carteraAConsultar
	 *            the cartera A consultar
	 * @param periodo
	 *            the periodo
	 */
	public RentabilidadTotalInView(String carteraAConsultar, String periodo) {
		this.carteraAConsultar = carteraAConsultar;
		this.periodo = periodo;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo
	 *            the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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
	 * Gets the lista cuentas.
	 *
	 * @return the lista cuentas
	 */
	public List<String> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the new lista cuentas
	 */
	public void setListaCuentas(List<String> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the periodo fecha inicial.
	 *
	 * @return the periodo fecha inicial
	 */
	public String getPeriodoFechaInicial() {
		return periodoFechaInicial;
	}

	/**
	 * Sets the periodo fecha inicial.
	 *
	 * @param periodoFechaInicial
	 *            the new periodo fecha inicial
	 */
	public void setPeriodoFechaInicial(String periodoFechaInicial) {
		this.periodoFechaInicial = periodoFechaInicial;
	}

	/**
	 * Gets the periodo fecha fin.
	 *
	 * @return the periodo fecha fin
	 */
	public String getPeriodoFechaFin() {
		return periodoFechaFin;
	}

	/**
	 * Sets the periodo fecha fin.
	 *
	 * @param periodoFechaFin
	 *            the new periodo fecha fin
	 */
	public void setPeriodoFechaFin(String periodoFechaFin) {
		this.periodoFechaFin = periodoFechaFin;
	}

}
