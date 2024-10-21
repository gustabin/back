/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaConsolidadaView.
 *
 * @author juan.pablo.picate
 */
public class TenenciaConsolidadaView {

	/** The lista tenencias. */
	private List<TenenciaProductosPorMonedaView> listaTenencias = new ArrayList<TenenciaProductosPorMonedaView>();

	/** The lista tenencia reexpresada. */
	private List<TenenciaReexpresada> listaTenenciaReexpresada = new ArrayList<TenenciaReexpresada>();

	/** The total tenencia reexpresada pesos. */
	private String totalTenenciaReexpresadaPesos;

	/** The total tenencia reexpresada dolares. */
	private String totalTenenciaReexpresadaDolares;

//	/** The mensaje. */
//	private String mensaje;

	/** The mostrar totales. */
	private boolean mostrarTotales = true;

	/** The resultado ok pesos. */
	private boolean resultadoOkPesos = true;

	/** The resultado ok dolares. */
	private boolean resultadoOkDolares = true;

	/** The porcentaje moneda pesos. */
	private BigDecimal porcentajeMonedaPesos;

	/** The legales. */
	private String legales;
	
	/** Esto habilita la solicitud de cuenta titulo de repatriacion */
	private boolean habilitadaCtaTitRep;

	/**
	 * Gets the lista tenencias.
	 *
	 * @return the listaTenencias
	 */
	public List<TenenciaProductosPorMonedaView> getListaTenencias() {
		return listaTenencias;
	}

	/**
	 * Sets the lista tenencias.
	 *
	 * @param listaTenencias
	 *            the listaTenencias to set
	 */
	public void setListaTenencias(List<TenenciaProductosPorMonedaView> listaTenencias) {
		this.listaTenencias = listaTenencias;
	}

//	/**
//	 * Gets the mensaje.
//	 *
//	 * @return the mensaje
//	 */
//	public String getMensaje() {
//		return mensaje;
//	}
//
//	/**
//	 * Sets the mensaje.
//	 *
//	 * @param mensaje
//	 *            the mensaje to set
//	 */
//	public void setMensaje(String mensaje) {
//		this.mensaje = mensaje;
//	}

	/**
	 * Checks if is mostrar totales.
	 *
	 * @return the mostrarTotales
	 */
	public boolean isMostrarTotales() {
		return mostrarTotales;
	}

	/**
	 * Sets the mostrar totales.
	 *
	 * @param mostrarTotales
	 *            the mostrarTotales to set
	 */
	public void setMostrarTotales(boolean mostrarTotales) {
		this.mostrarTotales = mostrarTotales;
	}

	/**
	 * Gets the lista tenencia reexpresada.
	 *
	 * @return the listaTenenciaReexpresada
	 */
	public List<TenenciaReexpresada> getListaTenenciaReexpresada() {
		return listaTenenciaReexpresada;
	}

	/**
	 * Sets the lista tenencia reexpresada.
	 *
	 * @param listaTenenciaReexpresada
	 *            the listaTenenciaReexpresada to set
	 */
	public void setListaTenenciaReexpresada(List<TenenciaReexpresada> listaTenenciaReexpresada) {
		this.listaTenenciaReexpresada = listaTenenciaReexpresada;
	}

	/**
	 * Gets the total tenencia reexpresada pesos.
	 *
	 * @return the totalTenenciaReexpresadaPesos
	 */
	public String getTotalTenenciaReexpresadaPesos() {
		return totalTenenciaReexpresadaPesos;
	}

	/**
	 * Sets the total tenencia reexpresada pesos.
	 *
	 * @param totalTenenciaReexpresadaPesos
	 *            the totalTenenciaReexpresadaPesos to set
	 */
	public void setTotalTenenciaReexpresadaPesos(String totalTenenciaReexpresadaPesos) {
		this.totalTenenciaReexpresadaPesos = totalTenenciaReexpresadaPesos;
	}

	/**
	 * Gets the total tenencia reexpresada dolares.
	 *
	 * @return the totalTenenciaReexpresadaDolares
	 */
	public String getTotalTenenciaReexpresadaDolares() {
		return totalTenenciaReexpresadaDolares;
	}

	/**
	 * Sets the total tenencia reexpresada dolares.
	 *
	 * @param totalTenenciaReexpresadaDolares
	 *            the totalTenenciaReexpresadaDolares to set
	 */
	public void setTotalTenenciaReexpresadaDolares(String totalTenenciaReexpresadaDolares) {
		this.totalTenenciaReexpresadaDolares = totalTenenciaReexpresadaDolares;
	}

	/**
	 * Gets the porcentaje moneda pesos.
	 *
	 * @return the porcentajeMonedaPesos
	 */
	public BigDecimal getPorcentajeMonedaPesos() {
		return porcentajeMonedaPesos;
	}

	/**
	 * Sets the porcentaje moneda pesos.
	 *
	 * @param porcentajeMonedaPesos
	 *            the porcentajeMonedaPesos to set
	 */
	public void setPorcentajeMonedaPesos(BigDecimal porcentajeMonedaPesos) {
		this.porcentajeMonedaPesos = porcentajeMonedaPesos;
	}

	/**
	 * Checks if is resultado ok pesos.
	 *
	 * @return the resultadoNuloPesos
	 */
	public boolean isResultadoOkPesos() {
		return resultadoOkPesos;
	}

	/**
	 * Sets the resultado ok pesos.
	 *
	 * @param resultadoOkPesos
	 *            the new resultado ok pesos
	 */
	public void setResultadoOkPesos(boolean resultadoOkPesos) {
		this.resultadoOkPesos = resultadoOkPesos;
	}

	/**
	 * Checks if is resultado ok dolares.
	 *
	 * @return the resultadoNuloDolares
	 */
	public boolean isResultadoOkDolares() {
		return resultadoOkDolares;
	}

	/**
	 * Sets the resultado ok dolares.
	 *
	 * @param resultadoOkDolares
	 *            the new resultado ok dolares
	 */
	public void setResultadoOkDolares(boolean resultadoOkDolares) {
		this.resultadoOkDolares = resultadoOkDolares;
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

	public boolean isHabilitadaCtaTitRep() {
		return habilitadaCtaTitRep;
	}

	public void setHabilitadaCtaTitRep(boolean habilitadaCtaTitRep) {
		this.habilitadaCtaTitRep = habilitadaCtaTitRep;
	}
	
	

}
