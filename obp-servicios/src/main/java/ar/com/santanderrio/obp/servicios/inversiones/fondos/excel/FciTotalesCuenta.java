package ar.com.santanderrio.obp.servicios.inversiones.fondos.excel;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaPorFondoDTO;

public class FciTotalesCuenta {

	private String totalTenenciaValuadaArs;
	
	private String totalResultadoArs;
	
	private String totalTenenciaValuadaUsd;
	
	private String totalResultadoUsd;

	
	public FciTotalesCuenta() {
		super();
	}
	
	public FciTotalesCuenta(TenenciaPorFondoDTO fondoDTO) {
		
		this.totalTenenciaValuadaArs = fondoDTO.getTotalValuacionPesos() == null ? DivisaEnum.PESO.getSimbolo() + "-" : DivisaEnum.PESO.getSimbolo() + " " + formatearMonto(fondoDTO.getTotalValuacionPesos().toString()); 
		this.totalResultadoArs = fondoDTO.getTotalResultadoPesos() == null ? DivisaEnum.PESO.getSimbolo() + "-" : DivisaEnum.PESO.getSimbolo() + " " + formatearMonto(fondoDTO.getTotalResultadoPesos().toString());
		this.totalTenenciaValuadaUsd = fondoDTO.getTotalValuacionDolares() == null ? DivisaEnum.DOLAR.getSimbolo() + "-" : DivisaEnum.DOLAR.getSimbolo() + " " + formatearMonto(fondoDTO.getTotalValuacionDolares().toString());
		this.totalResultadoUsd = fondoDTO.getTotalResultadoDolares() == null ? DivisaEnum.DOLAR.getSimbolo() + "-" : DivisaEnum.DOLAR.getSimbolo() + " " + formatearMonto(fondoDTO.getTotalResultadoDolares().toString());		
	}
	
	
	public String getTotalTenenciaValuadaArs() {
		return totalTenenciaValuadaArs;
	}

	public void setTotalTenenciaValuadaArs(String totalTenenciaValuadaArs) {
		this.totalTenenciaValuadaArs = totalTenenciaValuadaArs;
	}

	public String getTotalResultadoArs() {
		return totalResultadoArs;
	}

	public void setTotalResultadoArs(String totalResultadoArs) {
		this.totalResultadoArs = totalResultadoArs;
	}

	public String getTotalTenenciaValuadaUsd() {
		return totalTenenciaValuadaUsd;
	}

	public void setTotalTenenciaValuadaUsd(String totalTenenciaValuadaUsd) {
		this.totalTenenciaValuadaUsd = totalTenenciaValuadaUsd;
	}

	public String getTotalResultadoUsd() {
		return totalResultadoUsd;
	}

	public void setTotalResultadoUsd(String totalResultadoUsd) {
		this.totalResultadoUsd = totalResultadoUsd;
	}
	
	
	private String formatearMonto (String montoSinFormato) {
		
		String montoFormateado = ISBANStringUtils.formatearConComaYDosDecimales(montoSinFormato);
		
		if (montoSinFormato.startsWith("-")) {
			return "-" + montoFormateado;
		}
		return montoFormateado;
		
	}
		
}
