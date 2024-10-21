package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ContenidoTenenciaBprivDTO;

public class TotalesPlazoFijoExcel {

	
	/** The capital inicial ars. */
	private String capitalInicialArs;

	/** The capital inicial usd. */
	private String capitalInicialUsd;

	/** The tenencia hoy ars. */
	private String tenenciaHoyArs;

	/** The tenencia hoy usd. */
	private String tenenciaHoyUsd;

	/** The resultado ars. */
	private String resultadoArs;

	/** The resultado usd. */
	private String resultadoUsd;

	
	public TotalesPlazoFijoExcel() {
		super();
	}
	
	public TotalesPlazoFijoExcel(ContenidoTenenciaBprivDTO cuentaTituloPFBO) {
		
		this.capitalInicialArs = cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getCapitalInicialArs() == null ? "-" : DivisaEnum.PESO.getSimbolo() + " " + formatearMonto(cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getCapitalInicialArs());
		this.capitalInicialUsd = cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getCapitalInicialUsd() == null ? "-" : DivisaEnum.DOLAR.getSimbolo() + " " + formatearMonto(cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getCapitalInicialUsd());
		this.resultadoArs = cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getResultadoArs() == null ? "-" : DivisaEnum.PESO.getSimbolo() + " " + formatearMonto(cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getResultadoArs());
		this.resultadoUsd = cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getResultadoUsd() == null ? "-" : DivisaEnum.DOLAR.getSimbolo() + " " + formatearMonto(cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getResultadoUsd());
		this.tenenciaHoyArs = cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getTenenciaHoyArs() == null ? "-" : DivisaEnum.PESO.getSimbolo() + " " + formatearMonto(cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getTenenciaHoyArs());
		this.tenenciaHoyUsd = cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getTenenciaHoyUsd() == null ? "-" : DivisaEnum.DOLAR.getSimbolo() + " " + formatearMonto(cuentaTituloPFBO.getTenenciaPlazoFijoDTO().getTotalesGrilla().getTenenciaHoyUsd());
		
	}
	
	public String getCapitalInicialArs() {
		return capitalInicialArs;
	}

	public void setCapitalInicialArs(String capitalInicialArs) {
		this.capitalInicialArs = capitalInicialArs;
	}

	public String getCapitalInicialUsd() {
		return capitalInicialUsd;
	}

	public void setCapitalInicialUsd(String capitalInicialUsd) {
		this.capitalInicialUsd = capitalInicialUsd;
	}

	public String getTenenciaHoyArs() {
		return tenenciaHoyArs;
	}

	public void setTenenciaHoyArs(String tenenciaHoyArs) {
		this.tenenciaHoyArs = tenenciaHoyArs;
	}

	public String getTenenciaHoyUsd() {
		return tenenciaHoyUsd;
	}

	public void setTenenciaHoyUsd(String tenenciaHoyUsd) {
		this.tenenciaHoyUsd = tenenciaHoyUsd;
	}

	public String getResultadoArs() {
		return resultadoArs;
	}

	public void setResultadoArs(String resultadoArs) {
		this.resultadoArs = resultadoArs;
	}

	public String getResultadoUsd() {
		return resultadoUsd;
	}

	public void setResultadoUsd(String resultadoUsd) {
		this.resultadoUsd = resultadoUsd;
	}
	
	private String formatearMonto(Double monto) {

		BigDecimal bigNumero = new BigDecimal(monto);
		String numeroString = bigNumero.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		
		return ISBANStringUtils.formatearConComaYDosDecimales(numeroString);
		
	}
	
}
