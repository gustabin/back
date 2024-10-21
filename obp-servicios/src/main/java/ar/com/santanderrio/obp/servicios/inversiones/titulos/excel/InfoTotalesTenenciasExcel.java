package ar.com.santanderrio.obp.servicios.inversiones.titulos.excel;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosCuentaDTO;

public class InfoTotalesTenenciasExcel {

	private String tenenciaValuadaHoyArs;
	
	private String resultadoArs;
	
	private String tenenciaValuadaHoyUsd;
	
	private String resultadoUsd;

	
	public InfoTotalesTenenciasExcel() {
		super();
	}
	
	public InfoTotalesTenenciasExcel(TenenciaTitulosCuentaDTO tenenciaCuentaTitulosDTO) {
		
		this.resultadoArs = tenenciaCuentaTitulosDTO.getTotalResultadoPesos() == null ? DivisaEnum.PESO.getSimbolo() + "-" : DivisaEnum.PESO.getSimbolo() + " " + formatearMonto(tenenciaCuentaTitulosDTO.getTotalResultadoPesos().toString());
		this.resultadoUsd = tenenciaCuentaTitulosDTO.getTotalResultadoDolares() == null ? DivisaEnum.DOLAR.getSimbolo() + "-" : DivisaEnum.DOLAR.getSimbolo() + " " + formatearMonto(tenenciaCuentaTitulosDTO.getTotalResultadoDolares().toString());
		this.tenenciaValuadaHoyArs = tenenciaCuentaTitulosDTO.getTotalTenenciaValuadaPesos() == null ? DivisaEnum.PESO.getSimbolo() + "-" : DivisaEnum.PESO.getSimbolo() + " " + formatearMonto(tenenciaCuentaTitulosDTO.getTotalTenenciaValuadaPesos().toString());
		this.tenenciaValuadaHoyUsd = tenenciaCuentaTitulosDTO.getTotalTenenciaValuadaDolares() == null ? DivisaEnum.DOLAR.getSimbolo() + "-" : DivisaEnum.DOLAR.getSimbolo() + " " + formatearMonto(tenenciaCuentaTitulosDTO.getTotalTenenciaValuadaDolares().toString());
		
	}
	
	
	private String formatearMonto (String montoSinFormato) {
		
		String montoFormateado = ISBANStringUtils.formatearConComaYDosDecimales(montoSinFormato);
		
		if (montoSinFormato.startsWith("-")) {
			return "-" + montoFormateado;
		}
		return montoFormateado;
		
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
