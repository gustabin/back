package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;

/**
 * The Class DatosTarjetaBuilder.
 */
public class DatosTarjetaBuilder {

	/** The estado tarjeta credito. */
	private String estadoTarjetaCredito;

	/** The ciclo tarjeta credito. */
	private String cicloTarjetaCredito;

	/** The forma pago tarjeta credito. */
	private String formaPagoTarjetaCredito;

	/** The fecha ultimo cierre TC. */
	private Date fechaUltimoCierreTC;

	/** The saldo ultimo cierre pesos TC. */
	private BigDecimal saldoUltimoCierrePesosTC;

	/** The saldo pesos TC. */
	private BigDecimal saldoPesosTC;

	/** The pagos pesos TC. */
	private BigDecimal pagosPesosTC;

	/** The pago minimo pesos TC. */
	private BigDecimal pagoMinimoPesosTC;

	/** The fecha ultimo pago TC. */
	private Date fechaUltimoPagoTC;

	/** The fecha vencimiento liquidacion TC. */
	private Date fechaVencimientoLiquidacionTC;

	/** The saldo ultimo cierre dolares TC. */
	private BigDecimal saldoUltimoCierreDolaresTC;

	/** The pagos dolares TC. */
	private BigDecimal pagosDolaresTC;

	/** The saldo dolares TC. */
	private BigDecimal saldoDolaresTC;

	/** The pago minimo impago TC. */
	private BigDecimal pagoMinimoImpagoTC;

	/** The fecha proximo cierre TC. */
	private Date fechaProximoCierreTC;

	/** The ajuste credito pesos TC. */
	private BigDecimal ajusteCreditoPesosTC;

	/** The ajuste debito pesos TC. */
	private BigDecimal ajusteDebitoPesosTC;

	/** The adelantos pesos TC. */
	private BigDecimal adelantosPesosTC;

	/** The consumos pesos TC. */
	private BigDecimal consumosPesosTC;

	/** The saldo actual pesos TC. */
	private BigDecimal saldoActualPesosTC;

	/** The limite compra pesos TC. */
	private BigDecimal limiteCompraPesosTC;

	/** The fecha proximo vencimiento TC. */
	private Date fechaProximoVencimientoTC;

	/** The tipo cuenta tarjeta. */
	private TipoCuentaTarjeta tipoCuentaTarjeta;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The ajuste credito dolares TC. */
	private BigDecimal ajusteCreditoDolaresTC;

	/** The ajuste debito dolares TC. */
	private BigDecimal ajusteDebitoDolaresTC;

	/** The adelantos dolares TC. */
	private BigDecimal adelantosDolaresTC;

	/** The consumos dolares TC. */
	private BigDecimal consumosDolaresTC;

	/** The saldo actual dolares TC. */
	private BigDecimal saldoActualDolaresTC;

	/** The limite financiacion TC. */
	private BigDecimal limiteFinanciacionTC;

	/** The fecha pago programado. */
	private Date fechaPagoProgramado;

	/** The tipo cuenta banco pesos. */
	private String tipoCuentaBancoPesos;

	/** The sucursal banco pesos. */
	private int sucursalBancoPesos;

	/** The numero cuenta banco pesos. */
	private int numeroCuentaBancoPesos;

	/** The tipo cuenta banco dolares. */
	private String tipoCuentaBancoDolares;

	/** The sucursal banco dolares. */
	private int sucursalBancoDolares;

	/** The numero cuenta banco dolares. */
	private int numeroCuentaBancoDolares;

	/** The tipo pago. */
	private String tipoPago;

	/** The momento pago. */
	private String momentoPago;

	/** The codigo titularidad. */
	private String codigoTitularidad;

	/** The alias. */
	private String alias;

	/** The numero cuenta de tarjeta credito. */
	private String numeroCuentaDeTarjetaCredito;

	/** The cotizacion comprador. */
	private BigDecimal cotizacionComprador;
	
	/** The cotizacion vendedor. */
	private BigDecimal cotizacionVendedor;
	
	/** The saldo pendiente dolares. */
	private BigDecimal saldoPendienteDolares;

	/** The saldo pendiente pesos. */
	private BigDecimal saldoPendientePesos;

	/** The saldo total conv dolares. */
	private BigDecimal saldoTotalConvDolares;

	/** The saldo total conv pesos. */
	private BigDecimal saldoTotalConvPesos;

	/**
	 * Gets the estado tarjeta credito.
	 *
	 * @return the estado tarjeta credito
	 */
	public String getEstadoTarjetaCredito() {
		return estadoTarjetaCredito;
	}

	/**
	 * Gets the ciclo tarjeta credito.
	 *
	 * @return the ciclo tarjeta credito
	 */
	public String getCicloTarjetaCredito() {
		return cicloTarjetaCredito;
	}

	/**
	 * Gets the forma pago tarjeta credito.
	 *
	 * @return the forma pago tarjeta credito
	 */
	public String getFormaPagoTarjetaCredito() {
		return formaPagoTarjetaCredito;
	}

	/**
	 * Gets the fecha ultimo cierre TC.
	 *
	 * @return the fecha ultimo cierre TC
	 */
	public Date getFechaUltimoCierreTC() {
		return fechaUltimoCierreTC;
	}

	/**
	 * Gets the saldo ultimo cierre pesos TC.
	 *
	 * @return the saldo ultimo cierre pesos TC
	 */
	public BigDecimal getSaldoUltimoCierrePesosTC() {
		return saldoUltimoCierrePesosTC;
	}

	/**
	 * Gets the saldo pesos TC.
	 *
	 * @return the saldo pesos TC
	 */
	public BigDecimal getSaldoPesosTC() {
		return saldoPesosTC;
	}

	/**
	 * Gets the pagos pesos TC.
	 *
	 * @return the pagos pesos TC
	 */
	public BigDecimal getPagosPesosTC() {
		return pagosPesosTC;
	}

	/**
	 * Gets the pago minimo pesos TC.
	 *
	 * @return the pago minimo pesos TC
	 */
	public BigDecimal getPagoMinimoPesosTC() {
		return pagoMinimoPesosTC;
	}

	/**
	 * Gets the fecha ultimo pago TC.
	 *
	 * @return the fecha ultimo pago TC
	 */
	public Date getFechaUltimoPagoTC() {
		return fechaUltimoPagoTC;
	}

	/**
	 * Gets the fecha vencimiento liquidacion TC.
	 *
	 * @return the fecha vencimiento liquidacion TC
	 */
	public Date getFechaVencimientoLiquidacionTC() {
		return fechaVencimientoLiquidacionTC;
	}

	/**
	 * Gets the saldo ultimo cierre dolares TC.
	 *
	 * @return the saldo ultimo cierre dolares TC
	 */
	public BigDecimal getSaldoUltimoCierreDolaresTC() {
		return saldoUltimoCierreDolaresTC;
	}

	/**
	 * Gets the pagos dolares TC.
	 *
	 * @return the pagos dolares TC
	 */
	public BigDecimal getPagosDolaresTC() {
		return pagosDolaresTC;
	}

	/**
	 * Gets the saldo dolares TC.
	 *
	 * @return the saldo dolares TC
	 */
	public BigDecimal getSaldoDolaresTC() {
		return saldoDolaresTC;
	}

	/**
	 * Gets the pago minimo impago TC.
	 *
	 * @return the pago minimo impago TC
	 */
	public BigDecimal getPagoMinimoImpagoTC() {
		return pagoMinimoImpagoTC;
	}

	/**
	 * Gets the fecha proximo cierre TC.
	 *
	 * @return the fecha proximo cierre TC
	 */
	public Date getFechaProximoCierreTC() {
		return fechaProximoCierreTC;
	}

	/**
	 * Gets the ajuste credito pesos TC.
	 *
	 * @return the ajuste credito pesos TC
	 */
	public BigDecimal getAjusteCreditoPesosTC() {
		return ajusteCreditoPesosTC;
	}

	/**
	 * Gets the ajuste debito pesos TC.
	 *
	 * @return the ajuste debito pesos TC
	 */
	public BigDecimal getAjusteDebitoPesosTC() {
		return ajusteDebitoPesosTC;
	}

	/**
	 * Gets the adelantos pesos TC.
	 *
	 * @return the adelantos pesos TC
	 */
	public BigDecimal getAdelantosPesosTC() {
		return adelantosPesosTC;
	}

	/**
	 * Gets the consumos pesos TC.
	 *
	 * @return the consumos pesos TC
	 */
	public BigDecimal getConsumosPesosTC() {
		return consumosPesosTC;
	}

	/**
	 * Gets the saldo actual pesos TC.
	 *
	 * @return the saldo actual pesos TC
	 */
	public BigDecimal getSaldoActualPesosTC() {
		return saldoActualPesosTC;
	}

	/**
	 * Gets the limite compra pesos TC.
	 *
	 * @return the limite compra pesos TC
	 */
	public BigDecimal getLimiteCompraPesosTC() {
		return limiteCompraPesosTC;
	}

	/**
	 * Gets the fecha proximo vencimiento TC.
	 *
	 * @return the fecha proximo vencimiento TC
	 */
	public Date getFechaProximoVencimientoTC() {
		return fechaProximoVencimientoTC;
	}

	/**
	 * Gets the tipo cuenta tarjeta.
	 *
	 * @return the tipo cuenta tarjeta
	 */
	public TipoCuentaTarjeta getTipoCuentaTarjeta() {
		return tipoCuentaTarjeta;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Gets the ajuste credito dolares TC.
	 *
	 * @return the ajuste credito dolares TC
	 */
	public BigDecimal getAjusteCreditoDolaresTC() {
		return ajusteCreditoDolaresTC;
	}

	/**
	 * Gets the ajuste debito dolares TC.
	 *
	 * @return the ajuste debito dolares TC
	 */
	public BigDecimal getAjusteDebitoDolaresTC() {
		return ajusteDebitoDolaresTC;
	}

	/**
	 * Gets the adelantos dolares TC.
	 *
	 * @return the adelantos dolares TC
	 */
	public BigDecimal getAdelantosDolaresTC() {
		return adelantosDolaresTC;
	}

	/**
	 * Gets the consumos dolares TC.
	 *
	 * @return the consumos dolares TC
	 */
	public BigDecimal getConsumosDolaresTC() {
		return consumosDolaresTC;
	}

	/**
	 * Gets the saldo actual dolares TC.
	 *
	 * @return the saldo actual dolares TC
	 */
	public BigDecimal getSaldoActualDolaresTC() {
		return saldoActualDolaresTC;
	}

	/**
	 * Gets the limite financiacion TC.
	 *
	 * @return the limite financiacion TC
	 */
	public BigDecimal getLimiteFinanciacionTC() {
		return limiteFinanciacionTC;
	}

	/**
	 * Gets the fecha pago programado.
	 *
	 * @return the fecha pago programado
	 */
	public Date getFechaPagoProgramado() {
		return fechaPagoProgramado;
	}

	/**
	 * Gets the tipo cuenta banco pesos.
	 *
	 * @return the tipo cuenta banco pesos
	 */
	public String getTipoCuentaBancoPesos() {
		return tipoCuentaBancoPesos;
	}

	/**
	 * Gets the sucursal banco pesos.
	 *
	 * @return the sucursal banco pesos
	 */
	public int getSucursalBancoPesos() {
		return sucursalBancoPesos;
	}

	/**
	 * Gets the numero cuenta banco pesos.
	 *
	 * @return the numero cuenta banco pesos
	 */
	public int getNumeroCuentaBancoPesos() {
		return numeroCuentaBancoPesos;
	}

	/**
	 * Gets the tipo cuenta banco dolares.
	 *
	 * @return the tipo cuenta banco dolares
	 */
	public String getTipoCuentaBancoDolares() {
		return tipoCuentaBancoDolares;
	}

	/**
	 * Gets the sucursal banco dolares.
	 *
	 * @return the sucursal banco dolares
	 */
	public int getSucursalBancoDolares() {
		return sucursalBancoDolares;
	}

	/**
	 * Gets the numero cuenta banco dolares.
	 *
	 * @return the numero cuenta banco dolares
	 */
	public int getNumeroCuentaBancoDolares() {
		return numeroCuentaBancoDolares;
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
	 * Gets the momento pago.
	 *
	 * @return the momento pago
	 */
	public String getMomentoPago() {
		return momentoPago;
	}

	/**
	 * Gets the codigo titularidad.
	 *
	 * @return the codigo titularidad
	 */
	public String getCodigoTitularidad() {
		return codigoTitularidad;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Gets the numero cuenta de tarjeta credito.
	 *
	 * @return the numero cuenta de tarjeta credito
	 */
	public String getNumeroCuentaDeTarjetaCredito() {
		return numeroCuentaDeTarjetaCredito;
	}

	/**
	 * Gets the cotizacion comprador.
	 *
	 * @return the cotizacion comprador
	 */
	public BigDecimal getCotizacionComprador() {
		return cotizacionComprador;
	}

	/**
	 * Sets the cotizacion comprador.
	 *
	 * @param cotizacionComprador the new cotizacion comprador
	 */
	public void setCotizacionComprador(BigDecimal cotizacionComprador) {
		this.cotizacionComprador = cotizacionComprador;
	}

	/**
	 * Gets the cotizacion vendedor.
	 *
	 * @return the cotizacion vendedor
	 */
	public BigDecimal getCotizacionVendedor() {
		return cotizacionVendedor;
	}

	/**
	 * Sets the cotizacion vendedor.
	 *
	 * @param cotizacionVendedor the new cotizacion vendedor
	 */
	public void setCotizacionVendedor(BigDecimal cotizacionVendedor) {
		this.cotizacionVendedor = cotizacionVendedor;
	}

	/**
	 * Gets the saldo pendiente dolares.
	 *
	 * @return the saldo pendiente dolares
	 */
	public BigDecimal getSaldoPendienteDolares() {
		return saldoPendienteDolares;
	}

	/**
	 * Sets the saldo pendiente dolares.
	 *
	 * @param saldoPendienteDolares the new saldo pendiente dolares
	 */
	public void setSaldoPendienteDolares(BigDecimal saldoPendienteDolares) {
		this.saldoPendienteDolares = saldoPendienteDolares;
	}

	/**
	 * Gets the saldo pendiente pesos.
	 *
	 * @return the saldo pendiente pesos
	 */
	public BigDecimal getSaldoPendientePesos() {
		return saldoPendientePesos;
	}

	/**
	 * Sets the saldo pendiente pesos.
	 *
	 * @param saldoPendientePesos the new saldo pendiente pesos
	 */
	public void setSaldoPendientePesos(BigDecimal saldoPendientePesos) {
		this.saldoPendientePesos = saldoPendientePesos;
	}

	/**
	 * Gets the saldo total conv dolares.
	 *
	 * @return the saldo total conv dolares
	 */
	public BigDecimal getSaldoTotalConvDolares() {
		return saldoTotalConvDolares;
	}

	/**
	 * Sets the saldo total conv dolares.
	 *
	 * @param saldoTotalConvDolares the new saldo total conv dolares
	 */
	public void setSaldoTotalConvDolares(BigDecimal saldoTotalConvDolares) {
		this.saldoTotalConvDolares = saldoTotalConvDolares;
	}

	/**
	 * Gets the saldo total conv pesos.
	 *
	 * @return the saldo total conv pesos
	 */
	public BigDecimal getSaldoTotalConvPesos() {
		return saldoTotalConvPesos;
	}

	/**
	 * Sets the saldo total conv pesos.
	 *
	 * @param saldoTotalConvPesos the new saldo total conv pesos
	 */
	public void setSaldoTotalConvPesos(BigDecimal saldoTotalConvPesos) {
		this.saldoTotalConvPesos = saldoTotalConvPesos;
	}

	/**
	 * Instantiates a new datos tarjeta builder.
	 *
	 * @param datosTarjeta the datos tarjeta
	 */
	public DatosTarjetaBuilder(DatosTarjeta datosTarjeta) {
		this.estadoTarjetaCredito = datosTarjeta.getEstadoTarjetaCredito();
		this.cicloTarjetaCredito = datosTarjeta.getCicloTarjetaCredito();
		this.formaPagoTarjetaCredito = datosTarjeta.getFormaPagoTarjetaCredito();
		this.fechaUltimoCierreTC = datosTarjeta.getFechaUltimoCierreTC();
		this.saldoUltimoCierrePesosTC = datosTarjeta.getSaldoUltimoCierrePesosTC();
		this.saldoPesosTC = datosTarjeta.getSaldoPesosTC();
		this.pagosPesosTC = datosTarjeta.getPagosPesosTC();
		this.pagoMinimoPesosTC = datosTarjeta.getPagoMinimoPesosTC();
		this.fechaUltimoPagoTC = datosTarjeta.getFechaUltimoPagoTC();
		this.fechaVencimientoLiquidacionTC = datosTarjeta.getFechaVencimientoLiquidacionTC();
		this.saldoUltimoCierreDolaresTC = datosTarjeta.getSaldoUltimoCierreDolaresTC();
		this.pagosDolaresTC = datosTarjeta.getPagosDolaresTC();
		this.saldoDolaresTC = datosTarjeta.getSaldoDolaresTC();
		this.pagoMinimoImpagoTC = datosTarjeta.getPagoMinimoImpagoTC();
		this.fechaProximoCierreTC = datosTarjeta.getFechaProximoCierreTC();
		this.ajusteCreditoPesosTC = datosTarjeta.getAjusteCreditoPesosTC();
		this.ajusteDebitoPesosTC = datosTarjeta.getAjusteDebitoPesosTC();
		this.adelantosPesosTC = datosTarjeta.getAdelantosPesosTC();
		this.consumosPesosTC = datosTarjeta.getConsumosPesosTC();
		this.saldoActualPesosTC = datosTarjeta.getSaldoActualPesosTC();
		this.limiteCompraPesosTC = datosTarjeta.getLimiteCompraPesosTC();
		this.fechaProximoVencimientoTC = datosTarjeta.getFechaProximoVencimientoTC();
		this.tipoCuentaTarjeta = datosTarjeta.getTipoCuentaTarjeta();
		this.numeroTarjeta = datosTarjeta.getNumeroTarjeta();
		this.ajusteCreditoDolaresTC = datosTarjeta.getAjusteCreditoDolaresTC();
		this.ajusteDebitoDolaresTC = datosTarjeta.getAjusteDebitoDolaresTC();
		this.adelantosDolaresTC = datosTarjeta.getAdelantosDolaresTC();
		this.consumosDolaresTC = datosTarjeta.getConsumosDolaresTC();
		this.saldoActualDolaresTC = datosTarjeta.getSaldoActualDolaresTC();
		this.limiteFinanciacionTC = datosTarjeta.getLimiteFinanciacionTC();
		this.fechaPagoProgramado = datosTarjeta.getFechaPagoProgramado();
		this.tipoCuentaBancoPesos = datosTarjeta.getTipoCuentaBancoPesos();
		this.sucursalBancoPesos = datosTarjeta.getSucursalBancoPesos();
		this.numeroCuentaBancoPesos = datosTarjeta.getNumeroCuentaBancoPesos();
		this.tipoCuentaBancoDolares = datosTarjeta.getTipoCuentaBancoDolares();
		this.sucursalBancoDolares = datosTarjeta.getSucursalBancoDolares();
		this.numeroCuentaBancoDolares = datosTarjeta.getNumeroCuentaBancoDolares();
		this.tipoPago = datosTarjeta.getTipoPago();
		this.momentoPago = datosTarjeta.getMomentoPago();
		this.codigoTitularidad = datosTarjeta.getCodigoTitularidad();
		this.alias = datosTarjeta.getAlias();
		this.numeroCuentaDeTarjetaCredito = datosTarjeta.getNumeroCuentaDeTarjetaCredito();
		this.cotizacionComprador = datosTarjeta.getCotizacionComprador();
		this.cotizacionVendedor = datosTarjeta.getCotizacionVendedor();
		this.saldoPendienteDolares = datosTarjeta.getSaldoPendienteDolares();
		this.saldoPendientePesos = datosTarjeta.getSaldoPendientePesos();
		this.saldoTotalConvDolares = datosTarjeta.getSaldoTotalConvDolares();
		this.saldoTotalConvPesos = datosTarjeta.getSaldoTotalConvPesos();
	}

	/**
	 * Builds the.
	 *
	 * @return the datos tarjeta
	 */
	public DatosTarjeta build() {
		DatosTarjeta datosTarjeta = new DatosTarjeta();
		datosTarjeta.setAdelantosDolaresTC(getAdelantosDolaresTC());
		datosTarjeta.setAdelantosPesosTC(getAdelantosPesosTC());
		datosTarjeta.setAjusteCreditoDolaresTC(getAjusteCreditoDolaresTC());
		datosTarjeta.setAjusteCreditoPesosTC(getAjusteCreditoPesosTC());
		datosTarjeta.setAjusteDebitoDolaresTC(getAjusteDebitoDolaresTC());
		datosTarjeta.setAjusteDebitoPesosTC(getAjusteDebitoPesosTC());
		datosTarjeta.setAlias(getAlias());
		datosTarjeta.setCicloTarjetaCredito(getCicloTarjetaCredito());
		datosTarjeta.setCodigoTitularidad(getCodigoTitularidad());
		datosTarjeta.setConsumosDolaresTC(getConsumosDolaresTC());
		datosTarjeta.setConsumosPesosTC(getConsumosPesosTC());
		datosTarjeta.setEstadoTarjetaCredito(getEstadoTarjetaCredito());
		datosTarjeta.setFechaPagoProgramado(getFechaPagoProgramado());
		datosTarjeta.setFechaProximoCierreTC(getFechaProximoCierreTC());
		datosTarjeta.setFechaProximoVencimientoTC(getFechaProximoVencimientoTC());
		datosTarjeta.setFechaUltimoCierreTC(getFechaUltimoCierreTC());
		datosTarjeta.setFechaUltimoPagoTC(getFechaUltimoPagoTC());
		datosTarjeta.setFechaVencimientoLiquidacionTC(getFechaVencimientoLiquidacionTC());
		datosTarjeta.setFormaPagoTarjetaCredito(getFormaPagoTarjetaCredito());
		datosTarjeta.setLimiteCompraPesosTC(getLimiteCompraPesosTC());
		datosTarjeta.setLimiteFinanciacionTC(getLimiteFinanciacionTC());
		datosTarjeta.setMomentoPago(getMomentoPago());
		datosTarjeta.setNumeroCuentaBancoDolares(getNumeroCuentaBancoDolares());
		datosTarjeta.setNumeroCuentaBancoPesos(getNumeroCuentaBancoPesos());
		datosTarjeta.setNumeroCuentaDeTarjetaCredito(getNumeroCuentaDeTarjetaCredito());
		datosTarjeta.setNumeroTarjeta(getNumeroTarjeta());
		datosTarjeta.setPagoMinimoImpagoTC(getPagoMinimoImpagoTC());
		datosTarjeta.setPagoMinimoPesosTC(getPagoMinimoPesosTC());
		datosTarjeta.setPagosDolaresTC(getPagosDolaresTC());
		datosTarjeta.setPagosPesosTC(getPagosPesosTC());
		datosTarjeta.setSaldoActualDolaresTC(getSaldoActualDolaresTC());
		datosTarjeta.setSaldoActualPesosTC(getSaldoActualPesosTC());
		datosTarjeta.setSaldoDolaresTC(getSaldoDolaresTC());
		datosTarjeta.setSaldoPesosTC(getSaldoPesosTC());
		datosTarjeta.setSaldoUltimoCierreDolaresTC(getSaldoUltimoCierreDolaresTC());
		datosTarjeta.setSaldoUltimoCierrePesosTC(getSaldoUltimoCierrePesosTC());
		datosTarjeta.setSucursalBancoDolares(getSucursalBancoDolares());
		datosTarjeta.setSucursalBancoPesos(getSucursalBancoPesos());
		datosTarjeta.setTipoCuentaBancoDolares(getTipoCuentaBancoDolares());
		datosTarjeta.setTipoCuentaBancoPesos(getTipoCuentaBancoPesos());
		datosTarjeta.setTipoCuentaTarjeta(getTipoCuentaTarjeta());
		datosTarjeta.setTipoPago(getTipoPago());
		datosTarjeta.setCotizacionComprador(getCotizacionComprador());
		datosTarjeta.setCotizacionVendedor(getCotizacionVendedor());
		datosTarjeta.setSaldoPendienteDolares(getSaldoPendienteDolares());
		datosTarjeta.setSaldoPendientePesos(getSaldoPendientePesos());
		datosTarjeta.setSaldoTotalConvDolares(getSaldoTotalConvDolares());
		datosTarjeta.setSaldoTotalConvPesos(getSaldoTotalConvPesos());
		return datosTarjeta;
	}

}
