package ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorCuentaBPrivDTO;

public class TotalesTenenciaConsolidadaExcel {

	private String tenenciaValuadaCostoArs;
	
	private String tenenciaValuadaHoyArs;
	
	private String resultadoArs;
	
	private String tenenciaValuadaCostoUsd;
	
	private String tenenciaValuadaHoyUsd;
	
	private String resultadoUsd;

	
	public TotalesTenenciaConsolidadaExcel() {
		super();
	}
	
	public TotalesTenenciaConsolidadaExcel(TenenciaPorCuentaBPrivDTO tenenciasEnCuenta) {
		
		this.tenenciaValuadaCostoArs = DivisaEnum.PESO.getSimbolo() + " " + 
				formatearMonto(tenenciasEnCuenta.getTenenciaPesos().getTotalTenenciaValuadaCosto());
		this.tenenciaValuadaHoyArs = DivisaEnum.PESO.getSimbolo() + " " + 
				formatearMonto(tenenciasEnCuenta.getTenenciaPesos().getTotalTenenciaValuadaHoy());
		this.resultadoArs = DivisaEnum.PESO.getSimbolo() + " " + 
				formatearMonto(tenenciasEnCuenta.getTenenciaPesos().getTotalResultado());
		this.tenenciaValuadaCostoUsd = DivisaEnum.DOLAR.getSimbolo() + " " + 
				formatearMonto(tenenciasEnCuenta.getTenenciaDolares().getTotalTenenciaValuadaCosto());
		this.tenenciaValuadaHoyUsd = DivisaEnum.DOLAR.getSimbolo() + " " + 
				formatearMonto(tenenciasEnCuenta.getTenenciaDolares().getTotalTenenciaValuadaHoy());
		this.resultadoUsd = DivisaEnum.DOLAR.getSimbolo() + " " + 
				formatearMonto(tenenciasEnCuenta.getTenenciaDolares().getTotalResultado());
		
	}
	
	private String formatearMonto (String montoSinFormato) {
		
		String montoFormateado = ISBANStringUtils.formatearConComaYDosDecimales(montoSinFormato);
		
		if (montoSinFormato.startsWith("-")) {
			return "-" + montoFormateado;
		}
		return montoFormateado;
		
	}
	
	
	public String getTenenciaValuadaCostoArs() {
		return tenenciaValuadaCostoArs;
	}

	public void setTenenciaValuadaCostoArs(String tenenciaValuadaCostoArs) {
		this.tenenciaValuadaCostoArs = tenenciaValuadaCostoArs;
	}

	public String getTenenciaValuadaHoyArs() {
		return tenenciaValuadaHoyArs;
	}

	public void setTenenciaValuadaHoyArs(String tenenciaValuadaHoyArs) {
		this.tenenciaValuadaHoyArs = tenenciaValuadaHoyArs;
	}

	public String getResultadoArs() {
		return resultadoArs;
	}

	public void setResultadoArs(String resultadoArs) {
		this.resultadoArs = resultadoArs;
	}

	public String getTenenciaValuadaCostoUsd() {
		return tenenciaValuadaCostoUsd;
	}

	public void setTenenciaValuadaCostoUsd(String tenenciaValuadaCostoUsd) {
		this.tenenciaValuadaCostoUsd = tenenciaValuadaCostoUsd;
	}

	public String getTenenciaValuadaHoyUsd() {
		return tenenciaValuadaHoyUsd;
	}

	public void setTenenciaValuadaHoyUsd(String tenenciaValuadaHoyUsd) {
		this.tenenciaValuadaHoyUsd = tenenciaValuadaHoyUsd;
	}

	public String getResultadoUsd() {
		return resultadoUsd;
	}

	public void setResultadoUsd(String resultadoUsd) {
		this.resultadoUsd = resultadoUsd;
	}
		
}
