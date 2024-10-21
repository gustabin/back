/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RequestDashboard.
 */
public class RequestDashboard {

	/** The defecto. */
	private boolean defecto;

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
	
	/** The clasificacion. */
	private String clasificacion;
	
	/** The subclasificacion. */
	private String subclasificacion;
	
	/** The armar filtro. */
	private Boolean armarFiltro = Boolean.FALSE;
		
	/** The producto. */
	private String producto;
	
	/** The categoria. */
	private String categoria;
	

	/**
	 * Checks if is defecto.
	 *
	 * @return true, if is defecto
	 */
	public boolean isDefecto() {
		return defecto;
	}

	/**
	 * Sets the defecto.
	 *
	 * @param defecto
	 *            the new defecto
	 */
	public void setDefecto(boolean defecto) {
		this.defecto = defecto;
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

	/**
	 * Gets the subclasificacion.
	 *
	 * @return the subclasificacion
	 */
	public String getSubclasificacion() {
		return subclasificacion;
	}

	/**
	 * Sets the subclasificacion.
	 *
	 * @param subclasificacion
	 *            the new subclasificacion
	 */
	public void setSubclasificacion(String subclasificacion) {
		this.subclasificacion = subclasificacion;
	}

	/**
	 * Gets the armar filtro.
	 *
	 * @return the armar filtro
	 */
	public Boolean getArmarFiltro() {
		return armarFiltro;
	}

	/**
	 * Sets the armar filtro.
	 *
	 * @param armarFiltro
	 *            the new armar filtro
	 */
	public void setArmarFiltro(Boolean armarFiltro) {
		this.armarFiltro = armarFiltro;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the new producto
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria
	 *            the new categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
