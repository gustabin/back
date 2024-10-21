/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;

/**
 * The Class ConfiguracionOrdenesCompraResponse.
 */
public class ConfiguracionOrdenesCompraResponse {

	/** The apertura especie. */
	private ListaAperturaEspecie aperturaEspecie;
	
	/** The cuentas debito pesos. */
	private List<CuentasAdhesionDebitoView> cuentasDebitoPesos;
	
	/** The cuentas debito dolares. */
	private List<CuentasAdhesionDebitoView> cuentasDebitoDolares;
	
	/** The cuenta operativa. */
	private List<CuentasAdhesionDebitoView> cuentaOperativa;
	
	/** The poder compra OK. */
	private boolean poderCompraOK = false;
	
	/** The poder compra pesos. */
	private boolean poderCompraPesos = false;
	
	/** The poder compra dolares. */
	private boolean poderCompraDolares = false;
	
	/** The contrato aceptado. */
	private boolean contratoAceptado = false;
	
	/** The indicador PDC pesos. */
	private String indicadorPDCPesos;
	
	/** The indicador PDC dolares. */
	private String indicadorPDCDolares;
	
	/** The mensaje precio limite. */
	private String mensajePrecioLimite;
	
	/** The legales resolucion. */
	private String legalesResolucion;
	
	/** The precio referencia. */
	private String precioReferencia;
	
	/** The legal contrato. */
	private String legalContrato;

	/** The comprobantesCnvDisponibles. */
	private boolean comprobantesCnvDisponibles = false;

	/** The condicionesGeneralesAceptadas. */
	private boolean condicionesGeneralesAceptadas = false;
	
    /** The cuentasCustodia. */
    private List<CuentaCustodiaView> cuentasCustodia = new ArrayList<CuentaCustodiaView>();
    
    /** The cuentasCustodiaTotales. */
    private List<CuentaCustodiaView> cuentasCustodiaTotales = new ArrayList<CuentaCustodiaView>();
    
    /** The legales precio referencia. */
    private String legalesPrecioReferencia;

	/**
	 * Gets the apertura especie.
	 *
	 * @return the apertura especie
	 */
	public ListaAperturaEspecie getAperturaEspecie() {
		return aperturaEspecie;
	}

	/**
	 * Sets the apertura especie.
	 *
	 * @param aperturaEspecie
	 *            the new apertura especie
	 */
	public void setAperturaEspecie(ListaAperturaEspecie aperturaEspecie) {
		this.aperturaEspecie = aperturaEspecie;
	}

	/**
	 * Gets the cuentas debito pesos.
	 *
	 * @return the cuentas debito pesos
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDebitoPesos() {
		return cuentasDebitoPesos;
	}

	/**
	 * Sets the cuentas debito pesos.
	 *
	 * @param cuentasDebitoPesos
	 *            the new cuentas debito pesos
	 */
	public void setCuentasDebitoPesos(List<CuentasAdhesionDebitoView> cuentasDebitoPesos) {
		this.cuentasDebitoPesos = cuentasDebitoPesos;
	}

	/**
	 * Gets the cuentas debito dolares.
	 *
	 * @return the cuentas debito dolares
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDebitoDolares() {
		return cuentasDebitoDolares;
	}

	/**
	 * Sets the cuentas debito dolares.
	 *
	 * @param cuentasDebitoDolares
	 *            the new cuentas debito dolares
	 */
	public void setCuentasDebitoDolares(List<CuentasAdhesionDebitoView> cuentasDebitoDolares) {
		this.cuentasDebitoDolares = cuentasDebitoDolares;
	}

	/**
	 * Checks if is poder compra OK.
	 *
	 * @return the poderCompraOK
	 */
	public boolean isPoderCompraOK() {
		return poderCompraOK;
	}

	/**
	 * Sets the poder compra OK.
	 *
	 * @param poderCompraOK
	 *            the poderCompraOK to set
	 */
	public void setPoderCompraOK(boolean poderCompraOK) {
		this.poderCompraOK = poderCompraOK;
	}

	/**
	 * Gets the indicador PDC pesos.
	 *
	 * @return the indicadorPDCPesos
	 */
	public String getIndicadorPDCPesos() {
		return indicadorPDCPesos;
	}

	/**
	 * Sets the indicador PDC pesos.
	 *
	 * @param indicadorPDCPesos
	 *            the indicadorPDCPesos to set
	 */
	public void setIndicadorPDCPesos(String indicadorPDCPesos) {
		this.indicadorPDCPesos = indicadorPDCPesos;
	}

	/**
	 * Gets the indicador PDC dolares.
	 *
	 * @return the indicadorPDCDolares
	 */
	public String getIndicadorPDCDolares() {
		return indicadorPDCDolares;
	}

	/**
	 * Sets the indicador PDC dolares.
	 *
	 * @param indicadorPDCDolares
	 *            the indicadorPDCDolares to set
	 */
	public void setIndicadorPDCDolares(String indicadorPDCDolares) {
		this.indicadorPDCDolares = indicadorPDCDolares;
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
	 * Checks if is poder compra pesos.
	 *
	 * @return the poderCompraPesos
	 */
	public boolean isPoderCompraPesos() {
		return poderCompraPesos;
	}

	/**
	 * Sets the poder compra pesos.
	 *
	 * @param poderCompraPesos
	 *            the poderCompraPesos to set
	 */
	public void setPoderCompraPesos(boolean poderCompraPesos) {
		this.poderCompraPesos = poderCompraPesos;
	}

	/**
	 * Checks if is poder compra dolares.
	 *
	 * @return the poderCompraDolares
	 */
	public boolean isPoderCompraDolares() {
		return poderCompraDolares;
	}

	/**
	 * Sets the poder compra dolares.
	 *
	 * @param poderCompraDolares
	 *            the poderCompraDolares to set
	 */
	public void setPoderCompraDolares(boolean poderCompraDolares) {
		this.poderCompraDolares = poderCompraDolares;
	}

	/**
	 * Gets the legales resolucion.
	 *
	 * @return the legales resolucion
	 */
	public String getLegalesResolucion() {
		return legalesResolucion;
	}

	/**
	 * Sets the legales resolucion.
	 *
	 * @param legalesResolucion
	 *            the new legales resolucion
	 */
	public void setLegalesResolucion(String legalesResolucion) {
		this.legalesResolucion = legalesResolucion;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precio referencia
	 */
	public String getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the new precio referencia
	 */
	public void setPrecioReferencia(String precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Checks if is contrato aceptado.
	 *
	 * @return true, if is contrato aceptado
	 */
	public boolean isContratoAceptado() {
		return contratoAceptado;
	}

	/**
	 * Sets the contrato aceptado.
	 *
	 * @param contratoAceptado
	 *            the new contrato aceptado
	 */
	public void setContratoAceptado(boolean contratoAceptado) {
		this.contratoAceptado = contratoAceptado;
	}

	/**
	 * Gets the legal contrato.
	 *
	 * @return the legal contrato
	 */
	public String getLegalContrato() {
		return legalContrato;
	}

	/**
	 * Sets the legal contrato.
	 *
	 * @param legalContrato
	 *            the new legal contrato
	 */
	public void setLegalContrato(String legalContrato) {
		this.legalContrato = legalContrato;
	}

	/**
	 * Checks if is comprobantes cnv disponibles.
	 *
	 * @return true, if is comprobantes cnv disponibles
	 */
	public boolean isComprobantesCnvDisponibles() {
		return comprobantesCnvDisponibles;
	}

	/**
	 * Sets the comprobantes cnv disponibles.
	 *
	 * @param comprobantesCnvDisponibles
	 *            the new comprobantes cnv disponibles
	 */
	public void setComprobantesCnvDisponibles(boolean comprobantesCnvDisponibles) {
		this.comprobantesCnvDisponibles = comprobantesCnvDisponibles;
	}

	/**
	 * Checks if is condiciones generales aceptadas.
	 *
	 * @return true, if is condiciones generales aceptadas
	 */
	public boolean isCondicionesGeneralesAceptadas() {
		return condicionesGeneralesAceptadas;
	}

	/**
	 * Sets the condiciones generales aceptadas.
	 *
	 * @param condicionesGeneralesAceptadas
	 *            the new condiciones generales aceptadas
	 */
	public void setCondicionesGeneralesAceptadas(boolean condicionesGeneralesAceptadas) {
		this.condicionesGeneralesAceptadas = condicionesGeneralesAceptadas;
	}

	/**
	 * Gets the cuentas custodia.
	 *
	 * @return the cuentas custodia
	 */
	public List<CuentaCustodiaView> getCuentasCustodia() {
		return cuentasCustodia;
	}

	/**
	 * Sets the cuentas custodia.
	 *
	 * @param cuentasCustodia
	 *            the new cuentas custodia
	 */
	public void setCuentasCustodia(List<CuentaCustodiaView> cuentasCustodia) {
		this.cuentasCustodia = cuentasCustodia;
	}

	/**
	 * Gets the cuentas custodia totales.
	 *
	 * @return the cuentas custodia totales
	 */
	public List<CuentaCustodiaView> getCuentasCustodiaTotales() {
		return cuentasCustodiaTotales;
	}

	/**
	 * Sets the cuentas custodia totales.
	 *
	 * @param cuentasCustodiaTotales
	 *            the new cuentas custodia totales
	 */
	public void setCuentasCustodiaTotales(List<CuentaCustodiaView> cuentasCustodiaTotales) {
		this.cuentasCustodiaTotales = cuentasCustodiaTotales;
	}

	/**
	 * Gets the cuenta operativa.
	 *
	 * @return the cuenta operativa
	 */
	public List<CuentasAdhesionDebitoView> getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * Sets the cuenta operativa.
	 *
	 * @param cuentaOperativa
	 *            the new cuenta operativa
	 */
	public void setCuentaOperativa(List<CuentasAdhesionDebitoView> cuentaOperativa) {
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
	
		
}
