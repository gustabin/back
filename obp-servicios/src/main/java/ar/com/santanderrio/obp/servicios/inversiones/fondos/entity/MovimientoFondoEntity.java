/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.beanio.annotation.Field;

/**
 * The Class MovimientoFondoEntity.
 */
public class MovimientoFondoEntity {

	/** The codigo fondo. */
	@Field()
	private String codigoFondo;

	/** The codigo cliente. */
	@Field()
	private String codigoCliente;

	/** The codigo agente. */
	@Field()
	private String codigoAgente;

	/** The codigo canal. */
	@Field()
	private String codigoCanal;

	/** The descripcion movimiento. */
	@Field()
	private String descripcionMovimiento;

	/** The fecha de pedido. */
	// DT INPUT
	@Field()
	private String fechaDePedido;

	/** The fecha de conversion. */
	// DT CERTIF
	@Field()
	private String fechaDeConversion;

	/** The numero certificado. */
	@Field()
	private String numeroCertificado;

	/** The importe. */
	@Field()
	private String importe;

	/** The cantidad cuota partes. */
	@Field()
	private String cantidadCuotaPartes;

	/** The valor cuota. */
	@Field()
	private String valorCuota;

	/** The numero suscripcion. */
	@Field()
	private String numeroSuscripcion;

	/** The estado atu. */
	@Field()
	private String estadoAtu;

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the codigo cliente.
	 *
	 * @return the codigo cliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * Sets the codigo cliente.
	 *
	 * @param codigoCliente
	 *            the new codigo cliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * Gets the codigo agente.
	 *
	 * @return the codigo agente
	 */
	public String getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * Sets the codigo agente.
	 *
	 * @param codigoAgente
	 *            the new codigo agente
	 */
	public void setCodigoAgente(String codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	/**
	 * Gets the codigo canal.
	 *
	 * @return the codigo canal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * Sets the codigo canal.
	 *
	 * @param codigoCanal
	 *            the new codigo canal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * Gets the descripcion movimiento.
	 *
	 * @return the descripcion movimiento
	 */
	public String getDescripcionMovimiento() {
		return descripcionMovimiento;
	}

	/**
	 * Sets the descripcion movimiento.
	 *
	 * @param descripcionMovimiento
	 *            the new descripcion movimiento
	 */
	public void setDescripcionMovimiento(String descripcionMovimiento) {
		this.descripcionMovimiento = descripcionMovimiento;
	}

	/**
	 * Gets the fecha de pedido.
	 *
	 * @return the fecha de pedido
	 */
	public String getFechaDePedido() {
		return fechaDePedido;
	}

	/**
	 * Sets the fecha de pedido.
	 *
	 * @param fechaDePedido
	 *            the new fecha de pedido
	 */
	public void setFechaDePedido(String fechaDePedido) {
		this.fechaDePedido = fechaDePedido;
	}

	/**
	 * Gets the fecha de conversion.
	 *
	 * @return the fecha de conversion
	 */
	public String getFechaDeConversion() {
		return fechaDeConversion;
	}

	/**
	 * Sets the fecha de conversion.
	 *
	 * @param fechaDeConversion
	 *            the new fecha de conversion
	 */
	public void setFechaDeConversion(String fechaDeConversion) {
		this.fechaDeConversion = fechaDeConversion;
	}

	/**
	 * Gets the numero certificado.
	 *
	 * @return the numero certificado
	 */
	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	/**
	 * Sets the numero certificado.
	 *
	 * @param numeroCertificado
	 *            the new numero certificado
	 */
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the cantidad cuota partes.
	 *
	 * @return the cantidad cuota partes
	 */
	public String getCantidadCuotaPartes() {
		return cantidadCuotaPartes;
	}

	/**
	 * Sets the cantidad cuota partes.
	 *
	 * @param cantidadCuotaPartes
	 *            the new cantidad cuota partes
	 */
	public void setCantidadCuotaPartes(String cantidadCuotaPartes) {
		this.cantidadCuotaPartes = cantidadCuotaPartes;
	}

	/**
	 * Gets the valor cuota.
	 *
	 * @return the valor cuota
	 */
	public String getValorCuota() {
		return valorCuota;
	}

	/**
	 * Sets the valor cuota.
	 *
	 * @param valorCuota
	 *            the new valor cuota
	 */
	public void setValorCuota(String valorCuota) {
		this.valorCuota = valorCuota;
	}

	/**
	 * Gets the numero suscripcion.
	 *
	 * @return the numero suscripcion
	 */
	public String getNumeroSuscripcion() {
		return numeroSuscripcion;
	}

	/**
	 * Sets the numero suscripcion.
	 *
	 * @param numeroSuscripcion
	 *            the new numero suscripcion
	 */
	public void setNumeroSuscripcion(String numeroSuscripcion) {
		this.numeroSuscripcion = numeroSuscripcion;
	}

	/**
	 * Gets the estado atu.
	 *
	 * @return the estado atu
	 */
	public String getEstadoAtu() {
		return estadoAtu;
	}

	/**
	 * Sets the estado atu.
	 *
	 * @param estadoAtu
	 *            the new estado atu
	 */
	public void setEstadoAtu(String estadoAtu) {
		this.estadoAtu = estadoAtu;
	}

}
