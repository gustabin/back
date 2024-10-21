/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import javax.validation.constraints.Pattern;

import org.beanio.annotation.Field;

/**
 * The Class DetallePMCEntity.
 *
 * @author luis.pedro.lopez
 */
public class DetallePMCEntity {

	/** The id cliente empresa (A19). */
	@Field(nillable = false)
	@Pattern(regexp = "^[a-zA-Z0-9 ]{19}|[ ]{19}$")
	private String idClienteEmpresa;

	/** The fecha pago (N08). */
	@Field(nillable = false)
	@Pattern(regexp = "^[0-9]{8}|[ ]{8}$")
	private String fechaPago;

	/** The hora pago (N04). */
	@Field(nillable = false)
	@Pattern(regexp = "^[0-9]{4}|[ ]{4}$")
	private String horaPago;

	/** The moneda (A03). */
	@Field(nillable = false)
	@Pattern(regexp = "^[a-zA-Z0-9 ]{3}|[ ]{3}$")
	private String moneda;

	/** The importe (N14). */
	@Field(nillable = false)
	@Pattern(regexp = "^[0-9]{14}|[ ]{14}$")
	private String importe;

	/** The tipo de cuenta (A02). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9 ]{2}|[ ]{2}$")
	private String tipoCuenta;

	/** The sucursal (A04). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9 ]{4}|[ ]{4}$")
	private String sucursal;

	/** The nro de la cuenta (A12). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9 ]{12}|[ ]{12}$")
	private String nroCuenta;

	/** The nro de secuencia (A012). */
	@Field(nillable = false)
	@Pattern(regexp = "^[a-zA-Z0-9 ]{12}|[ ]{12}$")
	private String nroSecuencia;

	/** The nro de control ticket (A04). */
	@Field(nillable = false)
	@Pattern(regexp = "^[a-zA-Z0-9 ]{4}|[ ]{4}$")
	private String controlTicket;

	/** The descripcion recibo (A40). */
	@Field(nillable = false, maxLength = 40)
	private String descripcionRecibo;

	/**
	 * Gets the id cliente empresa.
	 *
	 * @return the idClienteEmpresa
	 */
	public String getIdClienteEmpresa() {
		return idClienteEmpresa;
	}

	/**
	 * Sets the id cliente empresa.
	 *
	 * @param idClienteEmpresa
	 *            the idClienteEmpresa to set
	 */
	public void setIdClienteEmpresa(String idClienteEmpresa) {
		this.idClienteEmpresa = idClienteEmpresa;
	}

	/**
	 * Gets the fecha pago.
	 *
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fechaPago
	 *            the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Gets the hora pago.
	 *
	 * @return the horaPago
	 */
	public String getHoraPago() {
		return horaPago;
	}

	/**
	 * Sets the hora pago.
	 *
	 * @param horaPago
	 *            the horaPago to set
	 */
	public void setHoraPago(String horaPago) {
		this.horaPago = horaPago;
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
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nroSucursal
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
	 * Gets the nro secuencia.
	 *
	 * @return the nroSecuencia
	 */
	public String getNroSecuencia() {
		return nroSecuencia;
	}

	/**
	 * Sets the nro secuencia.
	 *
	 * @param nroSecuencia
	 *            the nroSecuencia to set
	 */
	public void setNroSecuencia(String nroSecuencia) {
		this.nroSecuencia = nroSecuencia;
	}

	/**
	 * Gets the control ticket.
	 *
	 * @return the controlTicket
	 */
	public String getControlTicket() {
		return controlTicket;
	}

	/**
	 * Sets the control ticket.
	 *
	 * @param controlTicket
	 *            the controlTicket to set
	 */
	public void setControlTicket(String controlTicket) {
		this.controlTicket = controlTicket;
	}

	/**
	 * Gets the descripcion recibo.
	 *
	 * @return the descripcionRecibo
	 */
	public String getDescripcionRecibo() {
		return descripcionRecibo;
	}

	/**
	 * Sets the descripcion recibo.
	 *
	 * @param descripcionRecibo
	 *            the descripcionRecibo to set
	 */
	public void setDescripcionRecibo(String descripcionRecibo) {
		this.descripcionRecibo = descripcionRecibo;
	}

}
