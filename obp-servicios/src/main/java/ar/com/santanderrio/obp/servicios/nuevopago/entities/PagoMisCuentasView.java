/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Class PagoMisCuentasView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class PagoMisCuentasView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5041753647299140295L;

	/** The codigo empresa. */
	@Pattern(regexp = "^[a-zA-Z0-9 ]{4}$")
	private String codigoEmpresa;

	/** The tipo empresa. */
	@Pattern(regexp = "[F ]")
	private String tipoEmpresa;

	/** The tipo pago. */
	@Pattern(regexp = "^[123]{1}$")
	private String tipoPago;

	/** The cuit cliente. */
	@Pattern(regexp = "[0-9]{2}-[0-9]{8}-[0-9]")
	private String cuitCliente;

	/** The cuit empleador. */
	@Pattern(regexp = "[0-9]{2}-[0-9]{8}-[0-9]")
	private String cuitEmpleador;

	/** variable reintentar. */
	private String reintentar;

	/** The periodo pago. */
	@Pattern(regexp = "^[0-9]{6}$")
	private String periodoPago;

	/** simbolo moneda. */
	private String simboloMoneda;

	/** The identificacion cliente. */
	@Pattern(regexp = "^[0-9 ]{1,19}$")
	private String identificacionCliente;

	/** The sucursal. */
	@Pattern(regexp = "^[a-zA-Z0-9]{4}$")
	private String sucursal;

	/** nombre de la empresa o servicio a pagar. */
	private String descripcionServicioPago;

	/** The numero cuenta. */
	@Pattern(regexp = "^[a-zA-Z0-9]{12}$")
	private String numeroCuenta;

	/** The cuit empleador. */
	@Pattern(regexp = "[01][09]")
	private String tipoCuenta;

	/** The monto. Desde la grilla. */
	@Pattern(regexp = "^[0-9]{1,20}$")
	private String monto;

	/** The monto id. */
	@Pattern(regexp = "^[0-9]{1,20}$")
	private String montoId;

	/** The monto inicial. Ingresado por el cliente. */
	@Pattern(regexp = "^[0-9]{1,20}$")
	private String montoInicial;

	/** variable como viene de FE. */
	private String montoSinFormatear;

	/** The moneda. */
	@Pattern(regexp = "ARS|USD")
	private String moneda;

	/** The tipo pago empresa. */
	@Pattern(regexp = "[123]{1}")
	private String tipoPagoEmpresa;

	/** The numero factura. TODO: chequear max 20 a 15 */
	@Pattern(regexp = "^[0-9 ]{1,20}$")
	private String numeroFactura;

	/** varible tipoMonto. */
	private String tipoMonto;

	/** The es servicio domestico. */
	private Boolean servicioDomestico;

	/** nro Anticipo. */
	private String numeroDeAnticipo;

	/** nro cuota. */
	private String numeroDeCuota;

	/** The mensaje feedback. */
	private String mensajeFeedback;

	/** The nro de comprobante. */
	private String nroDeComprobante;

	/** The fecha hora. */
	private String fechaHora;

	/** The nro control. */
	private String nroControl;

	/** The desafio. */
	private AutentificacionDTO desafio;

	/** The numero referencia pago. */
	private String numeroReferenciaPago;

	/**
	 * Instantiates a new pago mis cuentas view.
	 */
	public PagoMisCuentasView() {
		super();
	}

	/**
	 * Gets the numero de anticipo.
	 *
	 * @return the numero de anticipo
	 */
	public String getNumeroDeAnticipo() {
		return numeroDeAnticipo;
	}

	/**
	 * Sets the numero de anticipo.
	 *
	 * @param numeroDeAnticipo
	 *            the new numero de anticipo
	 */
	public void setNumeroDeAnticipo(String numeroDeAnticipo) {
		this.numeroDeAnticipo = numeroDeAnticipo;
	}

	/**
	 * Gets the numero de cuota.
	 *
	 * @return the numero de cuota
	 */
	public String getNumeroDeCuota() {
		return numeroDeCuota;
	}

	/**
	 * Sets the numero de cuota.
	 *
	 * @param numeroDeCuota
	 *            the new numero de cuota
	 */
	public void setNumeroDeCuota(String numeroDeCuota) {
		this.numeroDeCuota = numeroDeCuota;
	}

	/**
	 * Gets the codigo empresa.
	 *
	 * @return the codigo empresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * Sets the codigo empresa.
	 *
	 * @param codigoEmpresa
	 *            the new codigo empresa
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * Gets the descripcion servicio pago.
	 *
	 * @return the descripcion servicio pago
	 */
	public String getDescripcionServicioPago() {
		return descripcionServicioPago;
	}

	/**
	 * Sets the descripcion servicio pago.
	 *
	 * @param descripcionServicioPago
	 *            the new descripcion servicio pago
	 */
	public void setDescripcionServicioPago(String descripcionServicioPago) {
		this.descripcionServicioPago = descripcionServicioPago;
	}

	/**
	 * Gets the monto sin formatear.
	 *
	 * @return the monto sin formatear
	 */
	public String getMontoSinFormatear() {
		return montoSinFormatear;
	}

	/**
	 * Sets the monto sin formatear.
	 *
	 * @param montoSinFormatear
	 *            the new monto sin formatear
	 */
	public void setMontoSinFormatear(String montoSinFormatear) {
		this.montoSinFormatear = montoSinFormatear;
	}

	/**
	 * Gets the simbolo moneda.
	 *
	 * @return the simbolo moneda
	 */
	public String getSimboloMoneda() {
		return simboloMoneda;
	}

	/**
	 * Sets the simbolo moneda.
	 *
	 * @param simboloMoneda
	 *            the new simbolo moneda
	 */
	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}

	/**
	 * Gets the tipo empresa.
	 *
	 * @return the tipo empresa
	 */
	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * Gets the servicio domestico.
	 *
	 * @return the servicio domestico
	 */
	public Boolean getServicioDomestico() {
		return servicioDomestico;
	}

	/**
	 * Sets the servicio domestico.
	 *
	 * @param servicioDomestico
	 *            the new servicio domestico
	 */
	public void setServicioDomestico(Boolean servicioDomestico) {
		this.servicioDomestico = servicioDomestico;
	}

	/**
	 * Gets the tipo monto.
	 *
	 * @return the tipo monto
	 */
	public String getTipoMonto() {
		return tipoMonto;
	}

	/**
	 * Sets the tipo monto.
	 *
	 * @param tipoMonto
	 *            the new tipo monto
	 */
	public void setTipoMonto(String tipoMonto) {
		this.tipoMonto = tipoMonto;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}

	/**
	 * Sets the tipo empresa.
	 *
	 * @param tipoEmpresa
	 *            the new tipo empresa
	 */
	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * Gets the tipo pago.
	 *
	 * @return the tipo pago
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * Sets the tipo pago.
	 *
	 * @param tipoPago
	 *            the new tipo pago
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	/**
	 * Gets the cuit cliente.
	 *
	 * @return the cuit cliente
	 */
	public String getCuitCliente() {
		return cuitCliente;
	}

	/**
	 * Sets the cuit cliente.
	 *
	 * @param cuitCliente
	 *            the new cuit cliente
	 */
	public void setCuitCliente(String cuitCliente) {
		this.cuitCliente = cuitCliente;
	}

	/**
	 * Gets the cuit empleador.
	 *
	 * @return the cuit empleador
	 */
	public String getCuitEmpleador() {
		return cuitEmpleador;
	}

	/**
	 * Sets the cuit empleador.
	 *
	 * @param cuitEmpleador
	 *            the new cuit empleador
	 */
	public void setCuitEmpleador(String cuitEmpleador) {
		this.cuitEmpleador = cuitEmpleador;
	}

	/**
	 * Gets the periodo pago.
	 *
	 * @return the periodo pago
	 */
	public String getPeriodoPago() {
		return periodoPago;
	}

	/**
	 * Sets the periodo pago.
	 *
	 * @param periodoPago
	 *            the new periodo pago
	 */
	public void setPeriodoPago(String periodoPago) {
		this.periodoPago = periodoPago;
	}

	/**
	 * Gets the identificacion cliente.
	 *
	 * @return the identificacion cliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 * Sets the identificacion cliente.
	 *
	 * @param identificacionCliente
	 *            the new identificacion cliente
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
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
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the monto inicial.
	 *
	 * @return the montoInicial
	 */
	public String getMontoInicial() {
		return montoInicial;
	}

	/**
	 * Sets the monto inicial.
	 *
	 * @param montoInicial
	 *            the montoInicial to set
	 */
	public void setMontoInicial(String montoInicial) {
		this.montoInicial = montoInicial;
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
	 * Gets the tipo pago empresa.
	 *
	 * @return the tipo pago empresa
	 */
	public String getTipoPagoEmpresa() {
		return tipoPagoEmpresa;
	}

	/**
	 * Sets the tipo pago empresa.
	 *
	 * @param tipoPagoEmpresa
	 *            the new tipo pago empresa
	 */
	public void setTipoPagoEmpresa(String tipoPagoEmpresa) {
		this.tipoPagoEmpresa = tipoPagoEmpresa;
	}

	/**
	 * Gets the numero factura.
	 *
	 * @return the numero factura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * Sets the numero factura.
	 *
	 * @param numeroFactura
	 *            the new numero factura
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
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
	 * Gets the mensaje feedback.
	 *
	 * @return the mensaje feedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback
	 *            the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the nro de comprobante.
	 *
	 * @return the nro de comprobante
	 */
	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	/**
	 * Sets the nro de comprobante.
	 *
	 * @param nroDeComprobante
	 *            the new nro de comprobante
	 */
	public void setNroDeComprobante(String nroDeComprobante) {
		this.nroDeComprobante = nroDeComprobante;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the nro control.
	 *
	 * @return the nro control
	 */
	public String getNroControl() {
		return nroControl;
	}

	/**
	 * Sets the nro control.
	 *
	 * @param nroControl
	 *            the new nro control
	 */
	public void setNroControl(String nroControl) {
		this.nroControl = nroControl;
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Gets the monto id.
	 *
	 * @return the montoId
	 */
	public String getMontoId() {
		return montoId;
	}

	/**
	 * Sets the monto id.
	 *
	 * @param montoId
	 *            the montoId to set
	 */
	public void setMontoId(String montoId) {
		this.montoId = montoId;
	}

	/**
	 * Gets the numero referencia pago.
	 *
	 * @return the numero referencia pago
	 */
	public String getNumeroReferenciaPago() {
		return numeroReferenciaPago;
	}

	/**
	 * Sets the numero referencia pago.
	 *
	 * @param numeroReferenciaPago
	 *            the new numero referencia pago
	 */
	public void setNumeroReferenciaPago(String numeroReferenciaPago) {
		this.numeroReferenciaPago = numeroReferenciaPago;
	}

}
