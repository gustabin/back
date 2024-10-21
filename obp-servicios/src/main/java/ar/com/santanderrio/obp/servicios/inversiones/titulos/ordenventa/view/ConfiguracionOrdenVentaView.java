/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class ConfiguracionOrdenVentaView.
 */
public class ConfiguracionOrdenVentaView {

	/** The moneda. */
	private String moneda;
	
	/** The plazo default. */
	private Integer plazoDefault;
	
	/** The opciones. */
	private List<OpcionSelectorConfiguracionView> opciones;
	
	/** The plazos. */
	private List<PlazoAcreditacionOrdenVentaView> plazos;
	
	/** The cuentas destino pesos. */
	private List<CuentasAdhesionDebitoView> cuentasDestinoPesos;
	
	/** The cuentas destino dolar. */
	private List<CuentasAdhesionDebitoView> cuentasDestinoDolar;
	
	/** The precios referencia. */
	private List<PrecioReferenciaView> preciosReferencia;
	
	/** The texto precio limite. */
	private String textoPrecioLimite;
	
	/** The texto origen fondos. */
	private String textoOrigenFondos;
	
	/** The leyenda legal. */
	private String leyendaLegal;
	
	/** The tipo cuenta. */
	private String tipoCuenta;
	
	/** The condiciones cuentas custodia. */
	private CondicionesGeneralesCuentaCustodiaView condicionesCuentasCustodia;
	
	/** The cuenta operativa. */
	private CuentasAdhesionDebitoView cuentaOperativa;
	
	/** The legales precio referencia. */
	private String legalesPrecioReferencia;
	
	private String indicadorPDCPesos;

	private String indicadorPDCDolares;

	private String legalCNV;

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
	public List<PlazoAcreditacionOrdenVentaView> getPlazos() {
		return plazos;
	}

	/**
	 * Sets the plazos.
	 *
	 * @param plazos
	 *            the plazos to set
	 */
	public void setPlazos(List<PlazoAcreditacionOrdenVentaView> plazos) {
		this.plazos = plazos;
	}

	/**
	 * Gets the cuentas destino pesos.
	 *
	 * @return the cuentasDestinoPesos
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDestinoPesos() {
		return cuentasDestinoPesos;
	}

	/**
	 * Sets the cuentas destino pesos.
	 *
	 * @param cuentasDestinoPesos
	 *            the cuentasDestinoPesos to set
	 */
	public void setCuentasDestinoPesos(List<CuentasAdhesionDebitoView> cuentasDestinoPesos) {
		this.cuentasDestinoPesos = cuentasDestinoPesos;
	}

	/**
	 * Gets the cuentas destino dolar.
	 *
	 * @return the cuentasDestinoDolar
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDestinoDolar() {
		return cuentasDestinoDolar;
	}

	/**
	 * Sets the cuentas destino dolar.
	 *
	 * @param cuentasDestinoDolar
	 *            the cuentasDestinoDolar to set
	 */
	public void setCuentasDestinoDolar(List<CuentasAdhesionDebitoView> cuentasDestinoDolar) {
		this.cuentasDestinoDolar = cuentasDestinoDolar;
	}

	/**
	 * Gets the opciones.
	 *
	 * @return the opciones
	 */
	public List<OpcionSelectorConfiguracionView> getOpciones() {
		return opciones;
	}

	/**
	 * Sets the opciones.
	 *
	 * @param opciones
	 *            the opciones to set
	 */
	public void setOpciones(List<OpcionSelectorConfiguracionView> opciones) {
		this.opciones = opciones;
	}

	/**
	 * Gets the plazo default.
	 *
	 * @return the plazoDefault
	 */
	public Integer getPlazoDefault() {
		return plazoDefault;
	}

	/**
	 * Sets the plazo default.
	 *
	 * @param plazoDefault
	 *            the plazoDefault to set
	 */
	public void setPlazoDefault(Integer plazoDefault) {
		this.plazoDefault = plazoDefault;
	}

	/**
	 * Gets the precios referencia.
	 *
	 * @return the preciosReferencia
	 */
	public List<PrecioReferenciaView> getPreciosReferencia() {
		return preciosReferencia;
	}

	/**
	 * Sets the precios referencia.
	 *
	 * @param preciosReferencia
	 *            the preciosReferencia to set
	 */
	public void setPreciosReferencia(List<PrecioReferenciaView> preciosReferencia) {
		this.preciosReferencia = preciosReferencia;
	}

	/**
	 * Gets the texto precio limite.
	 *
	 * @return the textoPrecioLimite
	 */
	public String getTextoPrecioLimite() {
		return textoPrecioLimite;
	}

	/**
	 * Sets the texto precio limite.
	 *
	 * @param textoPrecioLimite
	 *            the textoPrecioLimite to set
	 */
	public void setTextoPrecioLimite(String textoPrecioLimite) {
		this.textoPrecioLimite = textoPrecioLimite;
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
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the condiciones cuentas custodia.
	 *
	 * @return the condicionesCuentasCustodia
	 */
	public CondicionesGeneralesCuentaCustodiaView getCondicionesCuentasCustodia() {
		return condicionesCuentasCustodia;
	}

	/**
	 * Sets the condiciones cuentas custodia.
	 *
	 * @param condicionesCuentasCustodia
	 *            the condicionesCuentasCustodia to set
	 */
	public void setCondicionesCuentasCustodia(CondicionesGeneralesCuentaCustodiaView condicionesCuentasCustodia) {
		this.condicionesCuentasCustodia = condicionesCuentasCustodia;
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

	public String getLegalCNV() {
		return legalCNV;
	}

	public void setLegalCNV(String legalCNV) {
		this.legalCNV = legalCNV;
	}


}