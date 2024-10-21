package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

public class InicioLiquidacionOrdenPagoView {

	private List<ConceptoLiquidacionOrdenPagoView> conceptos;
	private List<CuentaView> cuentasPesos;
	private List<CuentaView> cuentasPesosDolares;
	private String legal;
	private String cantidadMaxArchivos;
	private String sizeMax;
	private String extensionesPermitidas;

	public List<ConceptoLiquidacionOrdenPagoView> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<ConceptoLiquidacionOrdenPagoView> conceptos) {
		this.conceptos = conceptos;
	}

	public List<CuentaView> getCuentasPesos() {
		return cuentasPesos;
	}

	public void setCuentasPesos(List<CuentaView> cuentasPesos) {
		this.cuentasPesos = cuentasPesos;
	}

	public List<CuentaView> getCuentasPesosDolares() {
		return cuentasPesosDolares;
	}

	public void setCuentasPesosDolares(List<CuentaView> cuentasPesosDolares) {
		this.cuentasPesosDolares = cuentasPesosDolares;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getCantidadMaxArchivos() {
		return cantidadMaxArchivos;
	}

	public void setCantidadMaxArchivos(String cantidadMaxArchivos) {
		this.cantidadMaxArchivos = cantidadMaxArchivos;
	}

	public String getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(String sizeMax) {
		this.sizeMax = sizeMax;
	}

	public String getExtensionesPermitidas() {
		return extensionesPermitidas;
	}

	public void setExtensionesPermitidas(String extensionesPermitidas) {
		this.extensionesPermitidas = extensionesPermitidas;
	}

}
