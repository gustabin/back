package ar.com.santanderrio.obp.servicios.inversiones.titulos.excel;

import java.util.List;

public class CuentaTitulosExcel {

	private String numeroCuenta;
	
	private String intervinientes;
	
	private List<InfoTenenciasExcel> tenenciasPesos;
	
	private Boolean isTenenciasPesos = Boolean.FALSE;
	
	private List<InfoTenenciasExcel> tenenciasDolares;
	
	private Boolean isTenenciasDolares = Boolean.FALSE;

	private InfoTotalesTenenciasExcel totales;
	
	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getIntervinientes() {
		return intervinientes;
	}

	public void setIntervinientes(String intervinientes) {
		this.intervinientes = intervinientes;
	}

	public List<InfoTenenciasExcel> getTenenciasPesos() {
		return tenenciasPesos;
	}

	public void setTenenciasPesos(List<InfoTenenciasExcel> tenenciasPesos) {
		this.tenenciasPesos = tenenciasPesos;
	}

	public List<InfoTenenciasExcel> getTenenciasDolares() {
		return tenenciasDolares;
	}

	public void setTenenciasDolares(List<InfoTenenciasExcel> tenenciasDolares) {
		this.tenenciasDolares = tenenciasDolares;
	}

	public Boolean getIsTenenciasPesos() {
		return isTenenciasPesos;
	}

	public void setIsTenenciasPesos(Boolean isTenenciasPesos) {
		this.isTenenciasPesos = isTenenciasPesos;
	}

	public Boolean getIsTenenciasDolares() {
		return isTenenciasDolares;
	}

	public void setIsTenenciasDolares(Boolean isTenenciasDolares) {
		this.isTenenciasDolares = isTenenciasDolares;
	}

	public InfoTotalesTenenciasExcel getTotales() {
		return totales;
	}

	public void setTotales(InfoTotalesTenenciasExcel totales) {
		this.totales = totales;
	}
				
}
