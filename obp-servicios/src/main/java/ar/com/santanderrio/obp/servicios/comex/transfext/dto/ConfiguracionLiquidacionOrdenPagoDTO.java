package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

public class ConfiguracionLiquidacionOrdenPagoDTO {

	private List<ConceptoConfiguracionOrdenPagoDTO> conceptos;
	private List<Cuenta> cuentasPesos;
	private List<Cuenta> cuentasPesosDolares;

	public List<ConceptoConfiguracionOrdenPagoDTO> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<ConceptoConfiguracionOrdenPagoDTO> conceptos) {
		this.conceptos = conceptos;
	}

	public List<Cuenta> getCuentasPesos() {
		return cuentasPesos;
	}

	public void setCuentasPesos(List<Cuenta> cuentasPesos) {
		this.cuentasPesos = cuentasPesos;
	}

	public List<Cuenta> getCuentasPesosDolares() {
		return cuentasPesosDolares;
	}

	public void setCuentasPesosDolares(List<Cuenta> cuentasPesosDolares) {
		this.cuentasPesosDolares = cuentasPesosDolares;
	}

}
