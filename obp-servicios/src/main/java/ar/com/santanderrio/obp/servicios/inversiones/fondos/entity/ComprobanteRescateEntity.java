/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * La entidad con los datos de respuesta del servicio RESFCI 160.
 *
 * @author marcelo.ruiz
 */
@Record
public class ComprobanteRescateEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The status resultado extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String statusResultadoExtendido;

	/** The descripcion moneda. */
	@Field
	private String descripcionMoneda;

	/** The estado actual. */
	@Field
	private String estadoActual;

	/** The motivo actual. */
	@Field
	private String motivoActual;

	/** The nombre fondo. */
	@Field
	private String nombreFondo;

	/** The nro rescate. */
	@Field
	private String nroRescate;

	/** The importe rescate neto. */
	@Field
	private String importeRescateNeto;

	/** The importe rescate comision. */
	@Field
	private String importeRescateComision;

	/** The importe rescate bruto. */
	@Field
	private String importeRescateBruto;

	/** The total cuotas partes. */
	@Field
	private String totalCuotasPartes;

	/** The monto cambio. */
	@Field
	private String montoCambio;

	/** The cotacao pact. */
	@Field
	private String cotacaoPact;

	/** The marca KU. */
	@Field
	private String marcaKU;

	/** Estos campos aparecen si codigo retorno extendido es <>0. */

	// @Field(nillable=true)
	private String idSistema;

	/** The cant desc status resultado. */
	// @Field(nillable=true)
	private String cantDescStatusResultado;

	/** The descripcion status resultado. */
	// @Field(nillable=true)
	private String descripcionStatusResultado;

	/**
	 * Gets the status resultado extendido.
	 *
	 * @return the status resultado extendido
	 */
	public String getStatusResultadoExtendido() {
		return statusResultadoExtendido;
	}

	/**
	 * Sets the status resultado extendido.
	 *
	 * @param statusResultadoExtendido
	 *            the new status resultado extendido
	 */
	public void setStatusResultadoExtendido(String statusResultadoExtendido) {
		this.statusResultadoExtendido = statusResultadoExtendido;
	}

	/**
	 * Gets the descripcion moneda.
	 *
	 * @return the descripcion moneda
	 */
	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}

	/**
	 * Sets the descripcion moneda.
	 *
	 * @param descripcionMoneda
	 *            the new descripcion moneda
	 */
	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}

	/**
	 * Gets the estado actual.
	 *
	 * @return the estado actual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}

	/**
	 * Sets the estado actual.
	 *
	 * @param estadoActual
	 *            the new estado actual
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	/**
	 * Gets the motivo actual.
	 *
	 * @return the motivo actual
	 */
	public String getMotivoActual() {
		return motivoActual;
	}

	/**
	 * Sets the motivo actual.
	 *
	 * @param motivoActual
	 *            the new motivo actual
	 */
	public void setMotivoActual(String motivoActual) {
		this.motivoActual = motivoActual;
	}

	/**
	 * Gets the nombre fondo.
	 *
	 * @return the nombre fondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the new nombre fondo
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	/**
	 * Gets the nro rescate.
	 *
	 * @return the nro rescate
	 */
	public String getNroRescate() {
		return nroRescate;
	}

	/**
	 * Sets the nro rescate.
	 *
	 * @param nroRescate
	 *            the new nro rescate
	 */
	public void setNroRescate(String nroRescate) {
		this.nroRescate = nroRescate;
	}

	/**
	 * Gets the importe rescate neto.
	 *
	 * @return the importe rescate neto
	 */
	public String getImporteRescateNeto() {
		return importeRescateNeto;
	}

	/**
	 * Sets the importe rescate neto.
	 *
	 * @param importeRescateNeto
	 *            the new importe rescate neto
	 */
	public void setImporteRescateNeto(String importeRescateNeto) {
		this.importeRescateNeto = importeRescateNeto;
	}

	/**
	 * Gets the importe rescate comision.
	 *
	 * @return the importe rescate comision
	 */
	public String getImporteRescateComision() {
		return importeRescateComision;
	}

	/**
	 * Sets the importe rescate comision.
	 *
	 * @param importeRescateComision
	 *            the new importe rescate comision
	 */
	public void setImporteRescateComision(String importeRescateComision) {
		this.importeRescateComision = importeRescateComision;
	}

	/**
	 * Gets the importe rescate bruto.
	 *
	 * @return the importe rescate bruto
	 */
	public String getImporteRescateBruto() {
		return importeRescateBruto;
	}

	/**
	 * Sets the importe rescate bruto.
	 *
	 * @param importeRescateBruto
	 *            the new importe rescate bruto
	 */
	public void setImporteRescateBruto(String importeRescateBruto) {
		this.importeRescateBruto = importeRescateBruto;
	}

	/**
	 * Gets the total cuotas partes.
	 *
	 * @return the total cuotas partes
	 */
	public String getTotalCuotasPartes() {
		return totalCuotasPartes;
	}

	/**
	 * Sets the total cuotas partes.
	 *
	 * @param totalCuotasPartes
	 *            the new total cuotas partes
	 */
	public void setTotalCuotasPartes(String totalCuotasPartes) {
		this.totalCuotasPartes = totalCuotasPartes;
	}

	/**
	 * Gets the monto cambio.
	 *
	 * @return the monto cambio
	 */
	public String getMontoCambio() {
		return montoCambio;
	}

	/**
	 * Sets the monto cambio.
	 *
	 * @param montoCambio
	 *            the new monto cambio
	 */
	public void setMontoCambio(String montoCambio) {
		this.montoCambio = montoCambio;
	}

	/**
	 * Gets the cotacao pact.
	 *
	 * @return the cotacao pact
	 */
	public String getCotacaoPact() {
		return cotacaoPact;
	}

	/**
	 * Sets the cotacao pact.
	 *
	 * @param cotacaoPact
	 *            the new cotacao pact
	 */
	public void setCotacaoPact(String cotacaoPact) {
		this.cotacaoPact = cotacaoPact;
	}

	/**
	 * Gets the marca KU.
	 *
	 * @return the marca KU
	 */
	public String getMarcaKU() {
		return marcaKU;
	}

	/**
	 * Sets the marca KU.
	 *
	 * @param marcaKU
	 *            the new marca KU
	 */
	public void setMarcaKU(String marcaKU) {
		this.marcaKU = marcaKU;
	}

	/**
	 * Gets the id sistema.
	 *
	 * @return the id sistema
	 */
	public String getIdSistema() {
		return idSistema;
	}

	/**
	 * Sets the id sistema.
	 *
	 * @param idSistema
	 *            the new id sistema
	 */
	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the cant desc status resultado.
	 *
	 * @return the cant desc status resultado
	 */
	public String getCantDescStatusResultado() {
		return cantDescStatusResultado;
	}

	/**
	 * Sets the cant desc status resultado.
	 *
	 * @param cantDescStatusResultado
	 *            the new cant desc status resultado
	 */
	public void setCantDescStatusResultado(String cantDescStatusResultado) {
		this.cantDescStatusResultado = cantDescStatusResultado;
	}

	/**
	 * Gets the descripcion status resultado.
	 *
	 * @return the descripcion status resultado
	 */
	public String getDescripcionStatusResultado() {
		return descripcionStatusResultado;
	}

	/**
	 * Sets the descripcion status resultado.
	 *
	 * @param descripcionStatusResultado
	 *            the new descripcion status resultado
	 */
	public void setDescripcionStatusResultado(String descripcionStatusResultado) {
		this.descripcionStatusResultado = descripcionStatusResultado;
	}

}
