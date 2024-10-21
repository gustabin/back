/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;

/**
 * The Class RentabilidadPeriodoRequestView.
 */
public class RentabilidadPeriodoRequestView {
	
	/** The cartera A consultar. */
	private String carteraAConsultar;
	
	/** The lista cuentas. */
	private List<CuentaACView> listaCuentas = new ArrayList<CuentaACView>();

	/** The periodo. */
	private String periodo;

	/** The periodo fecha inicial. */
	private String periodoFechaInicial;
	
	/** The periodo fecha fin. */
	private String periodoFechaFin;
	
	/** The moneda. */
	private String moneda;
	
	/** The tipo banca enum. */
	private TipoBancaEnum tipoBancaEnum;
	
	/** The clasificacion. */
	private String clasificacion;
	
	
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

	/**
	 * Gets the tipo banca enum.
	 *
	 * @return the tipo banca enum
	 */
	public TipoBancaEnum getTipoBancaEnum() {
		return tipoBancaEnum;
	}

	/**
	 * Sets the tipo banca enum.
	 *
	 * @param tipoBancaEnum
	 *            the new tipo banca enum
	 */
	public void setTipoBancaEnum(TipoBancaEnum tipoBancaEnum) {
		this.tipoBancaEnum = tipoBancaEnum;
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

	/**
	 * Gets the clasificacion.
	 *
	 * @return the clasificacion
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * Sets the clasificacion.
	 *
	 * @param clasificacion
	 *            the new clasificacion
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}	
		
}
