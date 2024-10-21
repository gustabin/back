/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import org.beanio.annotation.Field;

/**
 * The Class ComprobantePMCEntity.
 *
 * @author dante.omar.olmedo
 */
public class ComprobantePMCEntity {

	/** The empresa (A04). */
	@Field
	private String empresa;

	/** The id cliente empresa (A19). */
	@Field
	private String idClienteEmpresa;

	/** The fecha pago (A08). */
	@Field
	private String fechaPago;

	/** The hora pago (N04). */
	@Field
	private String horaPago;

	/** The fecha vencimiento (A08). */
	@Field
	private String fechaVencimiento;

	/** The factura (A20). */
	@Field
	private String factura;

	/** The moneda (N01). */
	@Field
	private String moneda;

	/** The importe (A14). */
	@Field
	private String importe;

	/** The tipo de cuenta (A02). */
	@Field
	private String tipoCuenta;

	/** The sucursal cuenta (A04). */
	@Field
	private String sucursalCuenta;

	/** The nro de cuenta (A12). */
	@Field
	private String nroCuenta;

	/** The nro de control ticket (A04). */
	@Field
	private String nroControlTicket;

	/** The nro de transaccion (N06). */
	@Field
	private String nroTransaccion;

	/** The estado (N01). */
	@Field
	private String estado;

	/** The nro tarjeta banelco (N01). */
	@Field
	private String nroTarjetaBanelco;

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the new empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the id cliente empresa.
	 *
	 * @return the id cliente empresa
	 */
	public String getIdClienteEmpresa() {
		return idClienteEmpresa;
	}

	/**
	 * Sets the id cliente empresa.
	 *
	 * @param idClienteEmpresa
	 *            the new id cliente empresa
	 */
	public void setIdClienteEmpresa(String idClienteEmpresa) {
		this.idClienteEmpresa = idClienteEmpresa;
	}

	/**
	 * Gets the fecha pago.
	 *
	 * @return the fecha pago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fechaPago
	 *            the new fecha pago
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Gets the hora pago.
	 *
	 * @return the hora pago
	 */
	public String getHoraPago() {
		return horaPago;
	}

	/**
	 * Sets the hora pago.
	 *
	 * @param horaPago
	 *            the new hora pago
	 */
	public void setHoraPago(String horaPago) {
		this.horaPago = horaPago;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the factura.
	 *
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * Sets the factura.
	 *
	 * @param factura
	 *            the new factura
	 */
	public void setFactura(String factura) {
		this.factura = factura;
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
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
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the nro control ticket.
	 *
	 * @return the nro control ticket
	 */
	public String getNroControlTicket() {
		return nroControlTicket;
	}

	/**
	 * Sets the nro control ticket.
	 *
	 * @param nroControlTicket
	 *            the new nro control ticket
	 */
	public void setNroControlTicket(String nroControlTicket) {
		this.nroControlTicket = nroControlTicket;
	}

	/**
	 * Gets the nro transaccion.
	 *
	 * @return the nro transaccion
	 */
	public String getNroTransaccion() {
		return nroTransaccion;
	}

	/**
	 * Sets the nro transaccion.
	 *
	 * @param nroTransaccion
	 *            the new nro transaccion
	 */
	public void setNroTransaccion(String nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the nro tarjeta banelco.
	 *
	 * @return the nro tarjeta banelco
	 */
	public String getNroTarjetaBanelco() {
		return nroTarjetaBanelco;
	}

	/**
	 * Sets the nro tarjeta banelco.
	 *
	 * @param nroTarjetaBanelco
	 *            the new nro tarjeta banelco
	 */
	public void setNroTarjetaBanelco(String nroTarjetaBanelco) {
		this.nroTarjetaBanelco = nroTarjetaBanelco;
	}

	// TODO:Hacer StringBuilder,HashBuilder y EqualBuilders

}
