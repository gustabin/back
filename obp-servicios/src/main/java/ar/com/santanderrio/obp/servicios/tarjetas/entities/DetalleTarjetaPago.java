/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;

/**
 * The Class DetalleTarjetaPago.
 */
public class DetalleTarjetaPago {

	/** The Constant ceroSaldo. */
	public static final String CERO_SALDO_PESOS = "$ 0,00";

	/** The Constant CERO_SALDO_DOLARES. */
	public static final String CERO_SALDO_DOLARES = "u$s 0,00";

	/** The Constant FORMATO_FECHA. */
	public static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The tarjeta numero. */
	private String numeroTarjeta;

	/** The alias tarjeta. */
	private String aliasTarjeta;

	/** The tarjeta tipo. */
	private String tarjetaTipo;

	/** The imprte pesos. */
	private String importePesos;

	/** The alias cuenta pesos. */
	private String aliasCuentaPesos;

	/** The tipo cuenta pesos. */
	private String tipoCuentaPesos;

	/** The numero cuenta pesos. */
	private String numeroCuentaPesos;

	/** The importe dolares. */
	private String importeDolares;

	/** The alias cuenta dolares. */
	private String aliasCuentaDolares;

	/** The tipo cuenta dolares. */
	private String tipoCuentaDolares;

	/** The numero cuenta dolares. */
	private String numeroCuentaDolares;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The ultimo resumen pesos. */
	private String ultimoResumenPesos;

	/** The ultimo resumen dolares. */
	private String ultimoResumenDolares;

	/** The pagos realizados pesos. */
	private String pagosRealizadosPesos;

	/** The pagos realizados dolares. */
	private String pagosRealizadosDolares;

	/** The fecha. */
	private String fechaProximoVencimientoTC;

	/** The pago minimo. */
	private String pagoMinimo;

	/** The tipo de pago. */
	private String descripcionTipoDePago;

	/** The tipo de pago. */
	private TipoDePagoEnum tipoDePago;

	/** solo pago programado. */
	private String fechaProgramada;

	/** The stop debit. */
	private Boolean stopDebit;

	/**
	 * Gets the tarjeta numero.
	 *
	 * @return the tarjeta numero
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the tarjeta numero.
	 *
	 * @param tarjetaNumero
	 *            the new tarjeta numero
	 */
	public void setNumeroTarjeta(String tarjetaNumero) {
		this.numeroTarjeta = tarjetaNumero;
	}

	/**
	 * Gets the alias tarjeta.
	 *
	 * @return the alias tarjeta
	 */
	public String getAliasTarjeta() {
		return aliasTarjeta;
	}

	/**
	 * Sets the alias tarjeta.
	 *
	 * @param aliasTarjeta
	 *            the new alias tarjeta
	 */
	public void setAliasTarjeta(String aliasTarjeta) {
		this.aliasTarjeta = StringUtils.isNotBlank(aliasTarjeta) && !aliasTarjeta.contains("\"")
				? "\"" + aliasTarjeta + "\"" : aliasTarjeta;
	}

	/**
	 * Gets the tarjeta tipo.
	 *
	 * @return the tarjeta tipo
	 */
	public String getTarjetaTipo() {
		return tarjetaTipo;
	}

	/**
	 * Sets the tarjeta tipo.
	 *
	 * @param tarjetaTipo
	 *            the new tarjeta tipo
	 */
	public void setTarjetaTipo(String tarjetaTipo) {
		this.tarjetaTipo = tarjetaTipo;
	}

	/**
	 * Gets the importe pesos.
	 *
	 * @return the importe pesos
	 */
	public String getImportePesos() {
		return importePesos;
	}

	/**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the new importe pesos
	 */
	public void setImportePesos(String importePesos) {
		this.importePesos = importePesos;
	}

	/**
	 * Gets the alias cuenta pesos.
	 *
	 * @return the alias cuenta pesos
	 */
	public String getAliasCuentaPesos() {
		return aliasCuentaPesos;
	}

	/**
	 * Sets the alias cuenta pesos.
	 *
	 * @param aliasCuentaPesos
	 *            the new alias cuenta pesos
	 */
	public void setAliasCuentaPesos(String aliasCuentaPesos) {
		this.aliasCuentaPesos = aliasCuentaPesos;
	}

	/**
	 * Gets the tipo cuenta pesos.
	 *
	 * @return the tipo cuenta pesos
	 */
	public String getTipoCuentaPesos() {
		return tipoCuentaPesos;
	}

	/**
	 * Sets the tipo cuenta pesos.
	 *
	 * @param tipoCuentaPesos
	 *            the new tipo cuenta pesos
	 */
	public void setTipoCuentaPesos(String tipoCuentaPesos) {
		this.tipoCuentaPesos = tipoCuentaPesos;
	}

	/**
	 * Gets the numero cuenta pesos.
	 *
	 * @return the numero cuenta pesos
	 */
	public String getNumeroCuentaPesos() {
		return numeroCuentaPesos;
	}

	/**
	 * Sets the numero cuenta pesos.
	 *
	 * @param numeroCuentaPesos
	 *            the new numero cuenta pesos
	 */
	public void setNumeroCuentaPesos(String numeroCuentaPesos) {
		this.numeroCuentaPesos = numeroCuentaPesos;
	}

	/**
	 * Gets the importe dolares.
	 *
	 * @return the importe dolares
	 */
	public String getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Sets the importe dolares.
	 *
	 * @param importeDolares
	 *            the new importe dolares
	 */
	public void setImporteDolares(String importeDolares) {
		this.importeDolares = importeDolares;
	}

	/**
	 * Gets the tipo cuenta dolares.
	 *
	 * @return the tipo cuenta dolares
	 */
	public String getTipoCuentaDolares() {
		return tipoCuentaDolares;
	}

	/**
	 * Sets the tipo cuenta dolares.
	 *
	 * @param tipoCuentaDolares
	 *            the new tipo cuenta dolares
	 */
	public void setTipoCuentaDolares(String tipoCuentaDolares) {
		this.tipoCuentaDolares = tipoCuentaDolares;
	}

	/**
	 * Gets the numero cuenta dolares.
	 *
	 * @return the numero cuenta dolares
	 */
	public String getNumeroCuentaDolares() {
		return numeroCuentaDolares;
	}

	/**
	 * Sets the numero cuenta dolares.
	 *
	 * @param numeroCuentaDolares
	 *            the new numero cuenta dolares
	 */
	public void setNumeroCuentaDolares(String numeroCuentaDolares) {
		this.numeroCuentaDolares = numeroCuentaDolares;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFechaProximoVencimientoTC() {
		return fechaProximoVencimientoTC;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFechaProximoVencimientoTC(String fecha) {
		this.fechaProximoVencimientoTC = fecha;
	}

	/**
	 * Gets the pago minimo.
	 *
	 * @return the pago minimo
	 */
	public String getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoMinimo
	 *            the new pago minimo
	 */
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Gets the descripcion tipo de pago.
	 *
	 * @return the descripcion tipo de pago
	 */
	public String getDescripcionTipoDePago() {
		return descripcionTipoDePago;
	}

	/**
	 * Sets the descripcion tipo de pago.
	 *
	 * @param descripcionTipoDePago
	 *            the new descripcion tipo de pago
	 */
	public void setDescripcionTipoDePago(String descripcionTipoDePago) {
		this.descripcionTipoDePago = descripcionTipoDePago;
	}

	/**
	 * Gets the alias cuenta dolares.
	 *
	 * @return the alias cuenta dolares
	 */
	public String getAliasCuentaDolares() {
		return aliasCuentaDolares;
	}

	/**
	 * Sets the alias cuenta dolares.
	 *
	 * @param aliasCuentaDolares
	 *            the new alias cuenta dolares
	 */
	public void setAliasCuentaDolares(String aliasCuentaDolares) {
		this.aliasCuentaDolares = aliasCuentaDolares;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo pesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldo dolares
	 */
	public String getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the new saldo dolares
	 */
	public void setSaldoDolares(String saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the ultimo resumen pesos.
	 *
	 * @return the ultimo resumen pesos
	 */
	public String getUltimoResumenPesos() {
		return ultimoResumenPesos;
	}

	/**
	 * Sets the ultimo resumen pesos.
	 *
	 * @param ultimoResumenPesos
	 *            the new ultimo resumen pesos
	 */
	public void setUltimoResumenPesos(String ultimoResumenPesos) {
		this.ultimoResumenPesos = ultimoResumenPesos;
	}

	/**
	 * Gets the ultimo resumen dolares.
	 *
	 * @return the ultimo resumen dolares
	 */
	public String getUltimoResumenDolares() {
		return ultimoResumenDolares;
	}

	/**
	 * Sets the ultimo resumen dolares.
	 *
	 * @param ultimoResumenDolares
	 *            the new ultimo resumen dolares
	 */
	public void setUltimoResumenDolares(String ultimoResumenDolares) {
		this.ultimoResumenDolares = ultimoResumenDolares;
	}

	/**
	 * Gets the pagos realizados pesos.
	 *
	 * @return the pagos realizados pesos
	 */
	public String getPagosRealizadosPesos() {
		return pagosRealizadosPesos;
	}

	/**
	 * Sets the pagos realizados pesos.
	 *
	 * @param pagosRealizadosPesos
	 *            the new pagos realizados pesos
	 */
	public void setPagosRealizadosPesos(String pagosRealizadosPesos) {
		this.pagosRealizadosPesos = pagosRealizadosPesos;
	}

	/**
	 * Gets the pagos realizados dolares.
	 *
	 * @return the pagos realizados dolares
	 */
	public String getPagosRealizadosDolares() {
		return pagosRealizadosDolares;
	}

	/**
	 * Sets the pagos realizados dolares.
	 *
	 * @param pagosRealizadosDolares
	 *            the new pagos realizados dolares
	 */
	public void setPagosRealizadosDolares(String pagosRealizadosDolares) {
		this.pagosRealizadosDolares = pagosRealizadosDolares;
	}

	/**
	 * Gets the tipo de pago.
	 *
	 * @return the tipo de pago
	 */
	public TipoDePagoEnum getTipoDePago() {
		return tipoDePago;
	}

	/**
	 * Sets the tipo de pago.
	 *
	 * @param tipoDePago
	 *            the new tipo de pago
	 */
	public void setTipoDePago(TipoDePagoEnum tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fechaProximoVencimientoTC = fecha;
	}

	/**
	 * Gets the fecha programada.
	 *
	 * @return the fecha programada
	 */
	public String getFechaProgramada() {
		return fechaProgramada;
	}

	/**
	 * Sets the fecha programada.
	 *
	 * @param fechaProgramada
	 *            the new fecha programada
	 */
	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	/**
	 * Checks if is stop debit.
	 *
	 * @return true, if is stop debit
	 */
	public Boolean isStopDebit() {
		return stopDebit;
	}

	/**
	 * Sets the stop debit.
	 *
	 * @param stopDebit
	 *            the new stop debit
	 */
	public void setStopDebit(Boolean stopDebit) {
		this.stopDebit = stopDebit;
	}

	/**
	 * Instantiates a new detalle tarjeta pago.
	 *
	 * @param datosTarjeta
	 *            the datos tarjeta
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @param cuentaTarjeta
	 *            the cuenta tarjeta
	 */
	public DetalleTarjetaPago(DatosTarjeta datosTarjeta, String numeroTarjeta, Cuenta cuentaTarjeta) {

		this.numeroTarjeta = numeroTarjeta;
		this.tarjetaTipo = StringUtils.upperCase(cuentaTarjeta.getTipoCuentaEnum().getAbreviatura());
		this.aliasTarjeta = cuentaTarjeta.getAlias();

		if (datosTarjeta.getFechaVencimientoLiquidacionTC() != null) {
			this.fechaProximoVencimientoTC = DateFormatUtils.format(datosTarjeta.getFechaVencimientoLiquidacionTC(),
					FORMATO_FECHA);
		}

	}

	/**
	 * Detalle saldos.
	 *
	 * @param datosTarjeta
	 *            the datos tarjeta
	 */
	private void detalleSaldos(DatosTarjeta datosTarjeta) {
		this.pagoMinimo = ISBANStringUtils.formatearSaldoConDivisa(datosTarjeta.getPagoMinimoPesosTC(),
				DivisaEnum.PESO);
		this.ultimoResumenPesos = ISBANStringUtils
				.formatearSaldoNegadoConDivisa(datosTarjeta.getSaldoUltimoCierrePesosTC(), DivisaEnum.PESO);
		this.ultimoResumenDolares = ISBANStringUtils
				.formatearSaldoNegadoConDivisa(datosTarjeta.getSaldoUltimoCierreDolaresTC(), DivisaEnum.DOLAR);
		this.pagosRealizadosPesos = ISBANStringUtils.formatearSaldoSinAbsConDivisa(datosTarjeta.getPagosPesosTC(),
				DivisaEnum.PESO);
		this.pagosRealizadosDolares = ISBANStringUtils.formatearSaldoSinAbsConDivisa(datosTarjeta.getPagosDolaresTC(),
				DivisaEnum.DOLAR);
		this.saldoPesos = ISBANStringUtils.formatearSaldoNegadoConDivisa(datosTarjeta.getSaldoPesosTC(),
				DivisaEnum.PESO);
		this.saldoDolares = ISBANStringUtils.formatearSaldoNegadoConDivisa(datosTarjeta.getSaldoDolaresTC(),
				DivisaEnum.DOLAR);

	}

	/**
	 * Detalle cuenta.
	 *
	 * @param cuentaPesos
	 *            the cuenta pesos
	 */
	private void detalleCuenta(Cuenta cuentaPesos) {
		if (cuentaPesos != null) {
			this.tipoCuentaPesos = cuentaPesos.getTipoCuentaEnum().getDescripcion();
			this.aliasCuentaPesos = cuentaPesos.getAlias();
			this.numeroCuentaPesos = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaPesos);
		}
	}

	/**
	 * Detalle cuenta dolares.
	 *
	 * @param cuentaDolares
	 *            the cuenta dolares
	 */
	private void detalleCuentaDolares(Cuenta cuentaDolares) {
		if (cuentaDolares != null) {
			this.tipoCuentaDolares = cuentaDolares.getTipoCuentaEnum().getDescripcion();
			this.aliasCuentaDolares = cuentaDolares.getAlias();
			this.numeroCuentaDolares = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaDolares);
		}
	}

	/**
	 * Metodo constructor segun los datos requeridos para pago programado.
	 *
	 * @author leonardo.medina
	 * @param datosTarjeta
	 *            the datos tarjeta
	 * @param cuentaPesos
	 *            the alias pesos
	 * @param cuentaDolares
	 *            the alias dolares
	 * @param cuentaTarjeta
	 *            the cuenta tarjeta
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @return the detalle tarjeta pago
	 */
	public static DetalleTarjetaPago crearPagoProgramado(DatosTarjeta datosTarjeta, Cuenta cuentaPesos,
			Cuenta cuentaDolares, Cuenta cuentaTarjeta, String numeroTarjeta) {
		DetalleTarjetaPago detalle = new DetalleTarjetaPago(datosTarjeta, numeroTarjeta, cuentaTarjeta);

		detalle.setSaldoPesos(
				ISBANStringUtils.formatearSaldoSinAbsConDivisa(datosTarjeta.getSaldoPesosTC(), DivisaEnum.PESO));
		detalle.setImportePesos(detalle.getSaldoPesos());
		detalle.detalleCuenta(cuentaPesos);
		
		if (datosTarjeta.getSaldoDolaresTC() != null && datosTarjeta.getSaldoDolaresTC().signum() != 0) {
		    detalle.setSaldoDolares(
	                ISBANStringUtils.formatearSaldoSinAbsConDivisa(datosTarjeta.getSaldoDolaresTC(), DivisaEnum.DOLAR));
	        detalle.setImporteDolares(detalle.getSaldoDolares());
		}
		detalle.setTipoDePago(TipoDePagoEnum.PAGOPROGRAMADO);

		detalle.detalleCuentaDolares(cuentaDolares);

		detalle.setFechaProgramada(DateFormatUtils.format(datosTarjeta.getFechaPagoProgramado(), FORMATO_FECHA));

		return detalle;
	}

	/**
	 * Metodo constructor segun los datos requeridos para debito automatico.
	 *
	 * @param datosTarjeta
	 *            the datos tarjeta
	 * @param cuentaAdheridaDebitAutomatico
	 *            the cuenta adherida debit automatico
	 * @param tipoDePago
	 *            the tipo de pago
	 * @param cuentaTarjeta
	 *            the cuenta tarjeta
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @return the detalle tarjeta pago
	 */
	public static DetalleTarjetaPago crearDebitoAutomatico(DatosTarjeta datosTarjeta,
			CuentaAdheridaDebitoAutomatico cuentaAdheridaDebitAutomatico, TipoDePagoEnum tipoDePago,
			Cuenta cuentaTarjeta, String numeroTarjeta) {

		DetalleTarjetaPago detalle = new DetalleTarjetaPago(datosTarjeta, numeroTarjeta, cuentaTarjeta);

		detalle.detalleSaldos(datosTarjeta);

		detalle.setTipoDePago(tipoDePago);

		if (TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO.equals(tipoDePago)) {
			detalle.setDescripcionTipoDePago("Pago Mínimo");
		} else if (TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOTOTAL.equals(tipoDePago)) {
			detalle.setDescripcionTipoDePago("Pago total");
		}

		detalle.detalleCuenta(cuentaAdheridaDebitAutomatico.getCuenta());

		return detalle;
	}

	/**
	 * Pago puntual build.
	 *
	 * @param datosTarjeta
	 *            the datos tarjeta
	 * @param cuentaTarjeta
	 *            the cuenta tarjeta
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @return the detalle tarjeta pago
	 */
	public static DetalleTarjetaPago crearPagoPuntual(DatosTarjeta datosTarjeta, Cuenta cuentaTarjeta,
			String numeroTarjeta) {

		DetalleTarjetaPago detalle = new DetalleTarjetaPago(datosTarjeta, numeroTarjeta, cuentaTarjeta);

		detalle.detalleSaldos(datosTarjeta);

		detalle.setTipoDePago(TipoDePagoEnum.TARJETAPAGOPUNTUAL);
		detalle.setDescripcionTipoDePago(TipoDePagoEnum.TARJETAPAGOPUNTUAL.getNombreTipoDePago());

		return detalle;
	}

}
