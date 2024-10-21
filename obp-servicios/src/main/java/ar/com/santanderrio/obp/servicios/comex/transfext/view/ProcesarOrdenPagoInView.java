package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

public class ProcesarOrdenPagoInView {

	private ConceptoLiquidacionOrdenPagoView concepto;
	private CuentaView cuenta;
	private String importe;
	private String numeroReferencia;
	private List<ReporteView> documentacionAdjunta;
	private Boolean empresaVinculada;
	private Boolean esDescarga;
	private Integer idAdjunto;

	public ConceptoLiquidacionOrdenPagoView getConcepto() {
		return concepto;
	}

	public void setConcepto(ConceptoLiquidacionOrdenPagoView concepto) {
		this.concepto = concepto;
	}

	public CuentaView getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaView cuenta) {
		this.cuenta = cuenta;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public List<ReporteView> getDocumentacionAdjunta() {
		return documentacionAdjunta;
	}

	public void setDocumentacionAdjunta(List<ReporteView> documentacionAdjunta) {
		this.documentacionAdjunta = documentacionAdjunta;
	}

	public Boolean getEmpresaVinculada() {
		return empresaVinculada;
	}

	public void setEmpresaVinculada(Boolean empresaVinculada) {
		this.empresaVinculada = empresaVinculada;
	}

	/**
	 * @return the esDescarga
	 */
	public Boolean getEsDescarga() {
		return esDescarga;
	}

	/**
	 * @param esDescarga
	 *            the esDescarga to set
	 */
	public void setEsDescarga(Boolean esDescarga) {
		this.esDescarga = esDescarga;
	}

	/**
	 * @return the idAdjunto
	 */
	public Integer getIdAdjunto() {
		return idAdjunto;
	}

	/**
	 * @param idAdjunto
	 *            the idAdjunto to set
	 */
	public void setIdAdjunto(Integer idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

}
