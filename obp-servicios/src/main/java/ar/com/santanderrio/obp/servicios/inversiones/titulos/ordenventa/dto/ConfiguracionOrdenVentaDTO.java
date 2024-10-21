/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CuentasPDC;

/**
 * The Class ConfiguracionOrdenVentaDTO.
 */
public class ConfiguracionOrdenVentaDTO {

	/** The moneda. */
	private String moneda;
	
	/** The plazos. */
	private List<PlazoAcreditacionOrdenVentaDTO> plazos;
	
	/** The mensaje precio limite. */
	private String mensajePrecioLimite;
	
	/** The texto origen fondos. */
	private String textoOrigenFondos;
	
	/** The leyenda legal. */
	private String leyendaLegal;
	
	/** The condiciones generales. */
	private CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesGenerales;
	
	/** The cuenta operativa. */
	private CuentasAdhesionDebitoView cuentaOperativa;
	
	/** The legales precio referencia. */
	private String legalesPrecioReferencia;
	
	private String indicadorPDCPesos;
	
	private String indicadorPDCDolares;
	
	private List<CuentasPDC> listaCuentasPDC;
	
	private boolean poderCompraOk; 
	
	private String legalCNV;
	
	/**
	 * Sets the condiciones generales.
	 *
	 * @param condicionesGenerales
	 *            the condicionesGenerales to set
	 */
	public void setCondicionesGenerales(CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesGenerales) {
		this.condicionesGenerales = condicionesGenerales;
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
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the plazos.
	 *
	 * @return the plazos
	 */
	public List<PlazoAcreditacionOrdenVentaDTO> getPlazos() {
		return plazos;
	}

	/**
	 * Sets the plazos.
	 *
	 * @param plazos
	 *            the plazos to set
	 */
	public void setPlazos(List<PlazoAcreditacionOrdenVentaDTO> plazos) {
		this.plazos = plazos;
	}

	/**
	 * Gets the mensaje precio limite.
	 *
	 * @return the mensajePrecioLimite
	 */
	public String getMensajePrecioLimite() {
		return mensajePrecioLimite;
	}

	/**
	 * Sets the mensaje precio limite.
	 *
	 * @param mensajePrecioLimite
	 *            the mensajePrecioLimite to set
	 */
	public void setMensajePrecioLimite(String mensajePrecioLimite) {
		this.mensajePrecioLimite = mensajePrecioLimite;
	}

	/**
	 * Gets the texto origen fondos.
	 *
	 * @return the textoOrigenFondos
	 */
	public String getTextoOrigenFondos() {
		return textoOrigenFondos;
	}

	/**
	 * Sets the texto origen fondos.
	 *
	 * @param textoOrigenFondos
	 *            the textoOrigenFondos to set
	 */
	public void setTextoOrigenFondos(String textoOrigenFondos) {
		this.textoOrigenFondos = textoOrigenFondos;
	}

	/**
	 * Gets the leyenda legal.
	 *
	 * @return the leyendaLegal
	 */
	public String getLeyendaLegal() {
		return leyendaLegal;
	}

	/**
	 * Sets the leyenda legal.
	 *
	 * @param leyendaLegal
	 *            the leyendaLegal to set
	 */
	public void setLeyendaLegal(String leyendaLegal) {
		this.leyendaLegal = leyendaLegal;
	}

	/**
	 * Gets the condiciones generales.
	 *
	 * @return the condicionesGenerales
	 */
	public CondicionesGeneralesCuentasCustodiaOrdenVentaDTO getCondicionesGenerales() {
		return condicionesGenerales;
	}

	/**
	 * Gets the cuenta operativa.
	 *
	 * @return the cuentaOperativa
	 */
	public CuentasAdhesionDebitoView getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * Sets the cuenta operativa.
	 *
	 * @param cuentaOperativa
	 *            the cuentaOperativa to set
	 */
	public void setCuentaOperativa(CuentasAdhesionDebitoView cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	/**
	 * Gets the legales precio referencia.
	 *
	 * @return the legales precio referencia
	 */
	public String getLegalesPrecioReferencia() {
		return legalesPrecioReferencia;
	}

	/**
	 * Sets the legales precio referencia.
	 *
	 * @param legalesPrecioReferencia
	 *            the new legales precio referencia
	 */
	public void setLegalesPrecioReferencia(String legalesPrecioReferencia) {
		this.legalesPrecioReferencia = legalesPrecioReferencia;
	}

	/**
	 * @return the indicadorPDCPesos
	 */
	public String getIndicadorPDCPesos() {
		return indicadorPDCPesos;
	}

	/**
	 * @param indicadorPDCPesos the indicadorPDCPesos to set
	 */
	public void setIndicadorPDCPesos(String indicadorPDCPesos) {
		this.indicadorPDCPesos = indicadorPDCPesos;
	}

	/**
	 * @return the indicadorPDCDolares
	 */
	public String getIndicadorPDCDolares() {
		return indicadorPDCDolares;
	}

	/**
	 * @param indicadorPDCDolares the indicadorPDCDolares to set
	 */
	public void setIndicadorPDCDolares(String indicadorPDCDolares) {
		this.indicadorPDCDolares = indicadorPDCDolares;
	}

	public List<CuentasPDC> getListaCuentasPDC() {
		return listaCuentasPDC;
	}

	public void setListaCuentasPDC(List<CuentasPDC> listaCuentasPDC) {
		this.listaCuentasPDC = listaCuentasPDC;
	}

	public boolean isPoderCompraOk() {
		return poderCompraOk;
	}

	public void setPoderCompraOk(boolean poderCompraOk) {
		this.poderCompraOk = poderCompraOk;
	}

	public String getLegalCNV() {
		return legalCNV;
	}

	public void setLegalCNV(String legalCNV) {
		this.legalCNV = legalCNV;
	}

}
