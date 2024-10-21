/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class PagoMisCuentasDTO.
 */
public class PagoMisCuentasDTO {
	/** The codigo empresa. */
	@Pattern(regexp = "^[a-zA-Z0-9 ]{4}$")
	private final String codigoEmpresa;

	/** The tipo empresa. */
	@Pattern(regexp = "[F ]")
	private final String tipoEmpresa;

	/** The tipo pago. */
	@Pattern(regexp = "^[123]{1}$")
	private final String tipoPago;

	/** The cuit cliente. */
	@Pattern(regexp = "[0-9]{2}-[0-9]{8}-[0-9]")
	private final String cuitCliente;

	/** The cuit empleador. */
	@Pattern(regexp = "[0-9]{2}-[0-9]{8}-[0-9]")
	private final String cuitEmpleador;

	/** variable reintentar. */
	private final String reintentar;

	/** The periodo pago. */
	@Pattern(regexp = "^[0-9]{6}$")
	private final String periodoPago;

	/** simbolo moneda. */
	private final String simboloMoneda;

	/** The identificacion cliente. */
	@Pattern(regexp = "^[0-9 ]{1,19}$")
	private final String identificacionCliente;

	/** The sucursal. */
	@Pattern(regexp = "^[a-zA-Z0-9]{4}$")
	private final String sucursal;

	/** nombre de la empresa o servicio a pagar. */
	private final String descripcionServicioPago;

	/** The numero cuenta. */
	@Pattern(regexp = "^[a-zA-Z0-9]{12}$")
	private final String numeroCuenta;

	/** The cuit empleador. */
	@Pattern(regexp = "[01][09]")
	private final String tipoCuenta;

	/** The monto. Desde la grilla. */
	@Pattern(regexp = "^[0-9]{1,20}$")
	private final String monto;

	/** variable como viene de FE. */
	private final String montoSinFormatear;

	/** The moneda. */
	@Pattern(regexp = "ARS|USD")
	private final String moneda;

	/** The tipo pago empresa. */
	@Pattern(regexp = "[123]{1}")
	private final String tipoPagoEmpresa;

	/** The numero factura. */
	@Pattern(regexp = "^[0-9 ]{1,20}$")
	private final String numeroFactura;

	/** nro Anticipo. */
	private final String numeroDeAnticipo;

	/** nro cuota. */
	private final String numeroDeCuota;

	/** The numero referencia pago. */
	private final String numeroReferenciaPago;

	/** The validacion importe. */
	private final boolean validacionImporte;

	/** The identificacion mensaje feedback. */
	private String identificacionMensajeFeedback;

	/** The monto mensaje feedback. */
	private String montoMensajeFeedback;

	/**
	 * Instantiates a new pago mis cuentas DTO.
	 *
	 * @param pago
	 *            the pago
	 * @param cuenta
	 *            the cuenta
	 * @param medioPago
	 *            the medio pago
	 * @param validacionImporte
	 *            the validacion importe
	 */
	public PagoMisCuentasDTO(NuevoPago pago, Cuenta cuenta, MedioPago medioPago, boolean validacionImporte) {
		this.codigoEmpresa = StringUtils.rightPad(String.valueOf(pago.getFiid()), 4, " ");
		this.cuitCliente = pago.getCodigoPagoElectronico();
		this.cuitEmpleador = pago.getCodigoPagoElectronico2();
		this.numeroDeAnticipo = pago.getNumeroDeAnticipo();
		this.numeroDeCuota = pago.getNumeroDeCuota();
		this.numeroReferenciaPago = pago.getNumeroReferenciaPago();
		this.monto = setearMontoConDecimales(pago.getMonto());
		this.montoSinFormatear = pago.getMonto();
		this.numeroFactura = pago.getFacturaNumero();
		this.reintentar = pago.getReintentar();
		this.numeroCuenta = cuenta.getNroCuentaProducto().substring(4);
		this.validacionImporte = validacionImporte;
		this.sucursal = cuenta.getNroSucursal();
		this.identificacionCliente = pago.getCodigoPagoElectronico();
		this.moneda = cuenta.getMonedaAltair();
		this.periodoPago = setearPeriodoPago(pago);
		this.tipoCuenta = cuenta.getTipoCuenta();

		this.simboloMoneda = medioPago.getMoneda();
		this.tipoEmpresa = medioPago.getTipoEmpresa();
		this.tipoPagoEmpresa = medioPago.getTipoPago().toString();
		this.tipoPago = medioPago.getTipoPago().toString();
		this.descripcionServicioPago = medioPago.getNombreFantasia();
	}

	/**
	 * setear el periodo q se esta pagando.
	 *
	 * @param pago
	 *            the pago
	 * @return the string
	 */
	private String setearPeriodoPago(NuevoPago pago) {
		String periodo = null;

		if (pago.getMes() != null && pago.getAnio() != null) {
			if (pago.getMes().length() == 1) {
				periodo = "0" + pago.getMes();
			} else {
				periodo = pago.getMes();
			}

			periodo = periodo + pago.getAnio();
		}
		return periodo;
	}

	/**
	 * se usa para transformar el dato enviado por FE y poner los decimales
	 * correctos para enviarlo al servicio.
	 *
	 * @param monto
	 *            the monto
	 * @return the string
	 */
	private String setearMontoConDecimales(String monto) {

		if (monto.contains(".")) {
			int posPunto = monto.indexOf('.');
			int agregarCeros = monto.length() - posPunto;

			monto = monto.replace(".", "");
			if (agregarCeros == 2) {
				monto += "0";
			}

		} else {
			monto += "00";
		}

		return StringUtils.leftPad(String.valueOf(monto), 14, "0");
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
	 * Gets the tipo empresa.
	 *
	 * @return the tipo empresa
	 */
	public String getTipoEmpresa() {
		return tipoEmpresa;
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
	 * Gets the cuit cliente.
	 *
	 * @return the cuit cliente
	 */
	public String getCuitCliente() {
		return cuitCliente;
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
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
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
	 * Gets the simbolo moneda.
	 *
	 * @return the simbolo moneda
	 */
	public String getSimboloMoneda() {
		return simboloMoneda;
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
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
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
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
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
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
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
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
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
	 * Gets the numero factura.
	 *
	 * @return the numero factura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
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
	 * Gets the numero de cuota.
	 *
	 * @return the numero de cuota
	 */
	public String getNumeroDeCuota() {
		return numeroDeCuota;
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
	 * Checks if is validacion importe.
	 *
	 * @return true, if is validacion importe
	 */
	public boolean isValidacionImporte() {
		return validacionImporte;
	}

	/**
	 * Gets the identificacion mensaje feedback.
	 *
	 * @return the identificacion mensaje feedback
	 */
	public String getIdentificacionMensajeFeedback() {
		return identificacionMensajeFeedback;
	}

	/**
	 * Sets the identificacion mensaje feedback.
	 *
	 * @param identificacionMensajeFeedback
	 *            the new identificacion mensaje feedback
	 */
	public void setIdentificacionMensajeFeedback(String identificacionMensajeFeedback) {
		this.identificacionMensajeFeedback = identificacionMensajeFeedback;
	}

	/**
	 * Gets the monto mensaje feedback.
	 *
	 * @return the monto mensaje feedback
	 */
	public String getMontoMensajeFeedback() {
		return montoMensajeFeedback;
	}

	/**
	 * Sets the monto mensaje feedback.
	 *
	 * @param montoMensajeFeedback
	 *            the new monto mensaje feedback
	 */
	public void setMontoMensajeFeedback(String montoMensajeFeedback) {
		this.montoMensajeFeedback = montoMensajeFeedback;
	}

}
