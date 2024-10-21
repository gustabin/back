/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class ComprobanteTransferenciaFondoOutEntity.
 */
@Record
public class ComprobanteTransferenciaFondoOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The status resultado. */
	@Field
	private String statusResultado;

	/** The descripcion moneda. */
	@Field
	private String descripcionMoneda;

	/** The importe bruto. */
	@Field
	private String importeBruto;

	/** The importe neto. */
	@Field
	private String importeNeto;

	/** The cant cuotas partes. */
	@Field
	private String cantCuotasPartes;

	/** The porcentaje comision. */
	@Field
	private String porcentajeComision;

	/** The valor comision. */
	@Field
	private String valorComision;

	/** The forma pago. */
	@Field
	private String formaPago;

	/** The estado actual. */
	@Field
	private String estadoActual;

	/** The motivo actual. */
	@Field
	private String motivoActual;

	/** The dias carencia. */
	@Field
	private String diasCarencia;

	/** The nro certificado destino. */
	@Field
	private String nroCertificadoDestino;

	/** The porcentaje comis D. */
	@Field
	private String porcentajeComisD;

	/** The estado destino. */
	@Field
	private String estadoDestino;

	/** The motivo destino. */
	@Field
	private String motivoDestino;

	/** The nombre fondo. */
	@Field
	private String nombreFondo;

	/** The descripcion moneda destino. */
	@Field
	private String descripcionMonedaDestino;

	/** The nombre fondo destino. */
	@Field
	private String nombreFondoDestino;

	/** The nro operacion. */
	@Field
	private String nroOperacion;

	/**
	 * Gets the status resultado.
	 *
	 * @return the status resultado
	 */
	public String getStatusResultado() {
		return statusResultado;
	}

	/**
	 * Sets the status resultado.
	 *
	 * @param statusResultado
	 *            the new status resultado
	 */
	public void setStatusResultado(String statusResultado) {
		this.statusResultado = statusResultado;
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
	 * Gets the importe bruto.
	 *
	 * @return the importe bruto
	 */
	public String getImporteBruto() {
		return importeBruto;
	}

	/**
	 * Sets the importe bruto.
	 *
	 * @param importeBruto
	 *            the new importe bruto
	 */
	public void setImporteBruto(String importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importe neto
	 */
	public String getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the new importe neto
	 */
	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the cant cuotas partes.
	 *
	 * @return the cant cuotas partes
	 */
	public String getCantCuotasPartes() {
		return cantCuotasPartes;
	}

	/**
	 * Sets the cant cuotas partes.
	 *
	 * @param cantCuotasPartes
	 *            the new cant cuotas partes
	 */
	public void setCantCuotasPartes(String cantCuotasPartes) {
		this.cantCuotasPartes = cantCuotasPartes;
	}

	/**
	 * Gets the porcentaje comision.
	 *
	 * @return the porcentaje comision
	 */
	public String getPorcentajeComision() {
		return porcentajeComision;
	}

	/**
	 * Sets the porcentaje comision.
	 *
	 * @param porcentajeComision
	 *            the new porcentaje comision
	 */
	public void setPorcentajeComision(String porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	/**
	 * Gets the valor comision.
	 *
	 * @return the valor comision
	 */
	public String getValorComision() {
		return valorComision;
	}

	/**
	 * Sets the valor comision.
	 *
	 * @param valorComision
	 *            the new valor comision
	 */
	public void setValorComision(String valorComision) {
		this.valorComision = valorComision;
	}

	/**
	 * Gets the forma pago.
	 *
	 * @return the forma pago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the new forma pago
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
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
	 * Gets the dias carencia.
	 *
	 * @return the dias carencia
	 */
	public String getDiasCarencia() {
		return diasCarencia;
	}

	/**
	 * Sets the dias carencia.
	 *
	 * @param diasCarencia
	 *            the new dias carencia
	 */
	public void setDiasCarencia(String diasCarencia) {
		this.diasCarencia = diasCarencia;
	}

	/**
	 * Gets the nro certificado destino.
	 *
	 * @return the nro certificado destino
	 */
	public String getNroCertificadoDestino() {
		return nroCertificadoDestino;
	}

	/**
	 * Sets the nro certificado destino.
	 *
	 * @param nroCertificadoDestino
	 *            the new nro certificado destino
	 */
	public void setNroCertificadoDestino(String nroCertificadoDestino) {
		this.nroCertificadoDestino = nroCertificadoDestino;
	}

	/**
	 * Gets the porcentaje comis D.
	 *
	 * @return the porcentaje comis D
	 */
	public String getPorcentajeComisD() {
		return porcentajeComisD;
	}

	/**
	 * Sets the porcentaje comis D.
	 *
	 * @param porcentajeComisD
	 *            the new porcentaje comis D
	 */
	public void setPorcentajeComisD(String porcentajeComisD) {
		this.porcentajeComisD = porcentajeComisD;
	}

	/**
	 * Gets the estado destino.
	 *
	 * @return the estado destino
	 */
	public String getEstadoDestino() {
		return estadoDestino;
	}

	/**
	 * Sets the estado destino.
	 *
	 * @param estadoDestino
	 *            the new estado destino
	 */
	public void setEstadoDestino(String estadoDestino) {
		this.estadoDestino = estadoDestino;
	}

	/**
	 * Gets the motivo destino.
	 *
	 * @return the motivo destino
	 */
	public String getMotivoDestino() {
		return motivoDestino;
	}

	/**
	 * Sets the motivo destino.
	 *
	 * @param motivoDestino
	 *            the new motivo destino
	 */
	public void setMotivoDestino(String motivoDestino) {
		this.motivoDestino = motivoDestino;
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
	 * Gets the descripcion moneda destino.
	 *
	 * @return the descripcion moneda destino
	 */
	public String getDescripcionMonedaDestino() {
		return descripcionMonedaDestino;
	}

	/**
	 * Sets the descripcion moneda destino.
	 *
	 * @param descripcionMonedaDestino
	 *            the new descripcion moneda destino
	 */
	public void setDescripcionMonedaDestino(String descripcionMonedaDestino) {
		this.descripcionMonedaDestino = descripcionMonedaDestino;
	}

	/**
	 * Gets the nombre fondo destino.
	 *
	 * @return the nombre fondo destino
	 */
	public String getNombreFondoDestino() {
		return nombreFondoDestino;
	}

	/**
	 * Sets the nombre fondo destino.
	 *
	 * @param nombreFondoDestino
	 *            the new nombre fondo destino
	 */
	public void setNombreFondoDestino(String nombreFondoDestino) {
		this.nombreFondoDestino = nombreFondoDestino;
	}

	/**
	 * Gets the nro operacion.
	 *
	 * @return the nro operacion
	 */
	public String getNroOperacion() {
		return nroOperacion;
	}

	/**
	 * Sets the nro operacion.
	 *
	 * @param nroOperacion
	 *            the new nro operacion
	 */
	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}

}
