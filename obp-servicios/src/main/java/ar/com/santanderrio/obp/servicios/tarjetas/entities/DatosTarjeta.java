/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;

/**
 * The Class DatosTarjeta.
 */
public class DatosTarjeta extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TARJETA_CERRADA. */
	private static final String TARJETA_CERRADA = "29";

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

	/** Numero Cuenta De la Tarjeta de Credito -N15- de CNSTJCPAGP. */
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
	 * Gets the ciclo tarjeta credito.
	 *
	 * @return the ciclo tarjeta credito
	 */
	public String getCicloTarjetaCredito() {
		return cicloTarjetaCredito;
	}

	/**
	 * Sets the ciclo tarjeta credito.
	 *
	 * @param cicloTarjetaCredito
	 *            the new ciclo tarjeta credito
	 */
	public void setCicloTarjetaCredito(String cicloTarjetaCredito) {
		this.cicloTarjetaCredito = cicloTarjetaCredito;
	}

	/**
	 * Gets the fecha ultimo cierre TC.
	 *
	 * @return the fecha ultimo cierre TC
	 */
	public Date getFechaUltimoCierreTC() {
		return fechaUltimoCierreTC == null ? null : new Date(fechaUltimoCierreTC.getTime());
	}

	/**
	 * Sets the fecha ultimo cierre TC.
	 *
	 * @param fechaUltimoCierreTC
	 *            the new fecha ultimo cierre TC
	 */
	public void setFechaUltimoCierreTC(Date fechaUltimoCierreTC) {
		this.fechaUltimoCierreTC = fechaUltimoCierreTC == null ? null : new Date(fechaUltimoCierreTC.getTime());
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
	 * Sets the pagos pesos TC.
	 *
	 * @param pagosPesosTC
	 *            the new pagos pesos TC
	 */
	public void setPagosPesosTC(BigDecimal pagosPesosTC) {
		this.pagosPesosTC = pagosPesosTC;
	}

	/**
	 * Gets the fecha ultimo pago TC.
	 *
	 * @return the fecha ultimo pago TC
	 */
	public Date getFechaUltimoPagoTC() {
		return fechaUltimoPagoTC == null ? null : new Date(fechaUltimoPagoTC.getTime());
	}

	/**
	 * Sets the fecha ultimo pago TC.
	 *
	 * @param fechaUltimoPagoTC
	 *            the new fecha ultimo pago TC
	 */
	public void setFechaUltimoPagoTC(Date fechaUltimoPagoTC) {
		this.fechaUltimoPagoTC = fechaUltimoPagoTC == null ? null : new Date(fechaUltimoPagoTC.getTime());
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
	 * Sets the pagos dolares TC.
	 *
	 * @param pagosDolaresTC
	 *            the new pagos dolares TC
	 */
	public void setPagosDolaresTC(BigDecimal pagosDolaresTC) {
		this.pagosDolaresTC = pagosDolaresTC;
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
	 * Sets the pago minimo impago TC.
	 *
	 * @param pagoMinimoImpagoTC
	 *            the new pago minimo impago TC
	 */
	public void setPagoMinimoImpagoTC(BigDecimal pagoMinimoImpagoTC) {
		this.pagoMinimoImpagoTC = pagoMinimoImpagoTC;
	}

	/**
	 * Gets the fecha proximo cierre TC.
	 *
	 * @return the fecha proximo cierre TC
	 */
	public Date getFechaProximoCierreTC() {
		return fechaProximoCierreTC == null ? null : new Date(fechaProximoCierreTC.getTime());
	}

	/**
	 * Sets the fecha proximo cierre TC.
	 *
	 * @param fechaProximoCierreTC
	 *            the new fecha proximo cierre TC
	 */
	public void setFechaProximoCierreTC(Date fechaProximoCierreTC) {
		this.fechaProximoCierreTC = fechaProximoCierreTC == null ? null : new Date(fechaProximoCierreTC.getTime());
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
	 * Sets the ajuste credito pesos TC.
	 *
	 * @param ajusteCreditoPesosTC
	 *            the new ajuste credito pesos TC
	 */
	public void setAjusteCreditoPesosTC(BigDecimal ajusteCreditoPesosTC) {
		this.ajusteCreditoPesosTC = ajusteCreditoPesosTC;
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
	 * Sets the ajuste debito pesos TC.
	 *
	 * @param ajusteDebitoPesosTC
	 *            the new ajuste debito pesos TC
	 */
	public void setAjusteDebitoPesosTC(BigDecimal ajusteDebitoPesosTC) {
		this.ajusteDebitoPesosTC = ajusteDebitoPesosTC;
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
	 * Sets the adelantos pesos TC.
	 *
	 * @param adelantosPesosTC
	 *            the new adelantos pesos TC
	 */
	public void setAdelantosPesosTC(BigDecimal adelantosPesosTC) {
		this.adelantosPesosTC = adelantosPesosTC;
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
	 * Sets the consumos pesos TC.
	 *
	 * @param consumosPesosTC
	 *            the new consumos pesos TC
	 */
	public void setConsumosPesosTC(BigDecimal consumosPesosTC) {
		this.consumosPesosTC = consumosPesosTC;
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
	 * Sets the saldo actual pesos TC.
	 *
	 * @param saldoActualPesosTC
	 *            the new saldo actual pesos TC
	 */
	public void setSaldoActualPesosTC(BigDecimal saldoActualPesosTC) {
		this.saldoActualPesosTC = saldoActualPesosTC;
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
	 * Sets the limite compra pesos TC.
	 *
	 * @param limiteCompraPesosTC
	 *            the new limite compra pesos TC
	 */
	public void setLimiteCompraPesosTC(BigDecimal limiteCompraPesosTC) {
		this.limiteCompraPesosTC = limiteCompraPesosTC;
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
	 * Sets the ajuste credito dolares TC.
	 *
	 * @param ajusteCreditoDolaresTC
	 *            the new ajuste credito dolares TC
	 */
	public void setAjusteCreditoDolaresTC(BigDecimal ajusteCreditoDolaresTC) {
		this.ajusteCreditoDolaresTC = ajusteCreditoDolaresTC;
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
	 * Sets the ajuste debito dolares TC.
	 *
	 * @param ajusteDebitoDolaresTC
	 *            the new ajuste debito dolares TC
	 */
	public void setAjusteDebitoDolaresTC(BigDecimal ajusteDebitoDolaresTC) {
		this.ajusteDebitoDolaresTC = ajusteDebitoDolaresTC;
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
	 * Sets the adelantos dolares TC.
	 *
	 * @param adelantosDolaresTC
	 *            the new adelantos dolares TC
	 */
	public void setAdelantosDolaresTC(BigDecimal adelantosDolaresTC) {
		this.adelantosDolaresTC = adelantosDolaresTC;
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
	 * Sets the consumos dolares TC.
	 *
	 * @param consumosDolaresTC
	 *            the new consumos dolares TC
	 */
	public void setConsumosDolaresTC(BigDecimal consumosDolaresTC) {
		this.consumosDolaresTC = consumosDolaresTC;
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
	 * Sets the saldo actual dolares TC.
	 *
	 * @param saldoActualDolaresTC
	 *            the new saldo actual dolares TC
	 */
	public void setSaldoActualDolaresTC(BigDecimal saldoActualDolaresTC) {
		this.saldoActualDolaresTC = saldoActualDolaresTC;
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
	 * Sets the limite financiacion TC.
	 *
	 * @param limiteFinanciacionTC
	 *            the new limite financiacion TC
	 */
	public void setLimiteFinanciacionTC(BigDecimal limiteFinanciacionTC) {
		this.limiteFinanciacionTC = limiteFinanciacionTC;
	}

	/**
	 * Gets the estado tarjeta credito.
	 *
	 * @return the estado tarjeta credito
	 */
	public String getEstadoTarjetaCredito() {
		return estadoTarjetaCredito;
	}

	/**
	 * Sets the estado tarjeta credito.
	 *
	 * @param estadoTarjetaCredito
	 *            the new estado tarjeta credito
	 */
	public void setEstadoTarjetaCredito(String estadoTarjetaCredito) {
		this.estadoTarjetaCredito = estadoTarjetaCredito;
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
	 * Sets the forma pago tarjeta credito.
	 *
	 * @param formaPagoTarjetaCredito
	 *            the new forma pago tarjeta credito
	 */
	public void setFormaPagoTarjetaCredito(String formaPagoTarjetaCredito) {
		this.formaPagoTarjetaCredito = formaPagoTarjetaCredito;
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
	 * Sets the saldo ultimo cierre pesos TC.
	 *
	 * @param saldoUltimoCierrePesosTC
	 *            the new saldo ultimo cierre pesos TC
	 */
	public void setSaldoUltimoCierrePesosTC(BigDecimal saldoUltimoCierrePesosTC) {
		this.saldoUltimoCierrePesosTC = saldoUltimoCierrePesosTC;
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
	 * Sets the saldo pesos TC.
	 *
	 * @param saldoPesosTC
	 *            the new saldo pesos TC
	 */
	public void setSaldoPesosTC(BigDecimal saldoPesosTC) {
		this.saldoPesosTC = saldoPesosTC;
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
	 * Sets the pago minimo pesos TC.
	 *
	 * @param pagoMinimoPesosTC
	 *            the new pago minimo pesos TC
	 */
	public void setPagoMinimoPesosTC(BigDecimal pagoMinimoPesosTC) {
		this.pagoMinimoPesosTC = pagoMinimoPesosTC;
	}

	/**
	 * Gets the fecha vencimiento liquidacion TC.
	 *
	 * @return the fecha vencimiento liquidacion TC
	 */
	public Date getFechaVencimientoLiquidacionTC() {
		return fechaVencimientoLiquidacionTC == null ? null : new Date(fechaVencimientoLiquidacionTC.getTime());
	}

	/**
	 * Sets the fecha vencimiento liquidacion TC.
	 *
	 * @param fechaVencimientoLiquidacionTC
	 *            the new fecha vencimiento liquidacion TC
	 */
	public void setFechaVencimientoLiquidacionTC(Date fechaVencimientoLiquidacionTC) {
		this.fechaVencimientoLiquidacionTC = fechaVencimientoLiquidacionTC == null ? null
				: new Date(fechaVencimientoLiquidacionTC.getTime());
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
	 * Sets the saldo ultimo cierre dolares TC.
	 *
	 * @param saldoUltimoCierreDolaresTC
	 *            the new saldo ultimo cierre dolares TC
	 */
	public void setSaldoUltimoCierreDolaresTC(BigDecimal saldoUltimoCierreDolaresTC) {
		this.saldoUltimoCierreDolaresTC = saldoUltimoCierreDolaresTC;
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
	 * Sets the saldo dolares TC.
	 *
	 * @param saldoDolaresTC
	 *            the new saldo dolares TC
	 */
	public void setSaldoDolaresTC(BigDecimal saldoDolaresTC) {
		this.saldoDolaresTC = saldoDolaresTC;
	}

	/**
	 * Gets the fecha proximo vencimiento TC.
	 *
	 * @return the fecha proximo vencimiento TC
	 */
	public Date getFechaProximoVencimientoTC() {
		return fechaProximoVencimientoTC == null ? null : new Date(fechaProximoVencimientoTC.getTime());
	}

	/**
	 * Sets the fecha proximo vencimiento TC.
	 *
	 * @param fechaProximoVencimientoTC
	 *            the new fecha proximo vencimiento TC
	 */
	public void setFechaProximoVencimientoTC(Date fechaProximoVencimientoTC) {
		this.fechaProximoVencimientoTC = fechaProximoVencimientoTC == null ? null
				: new Date(fechaProximoVencimientoTC.getTime());
	}

	/**
	 * Gets the tipo cuenta tarjeta.
	 *
	 * @return the tipoCuentaTarjeta
	 */
	public TipoCuentaTarjeta getTipoCuentaTarjeta() {
		return tipoCuentaTarjeta;
	}

	/**
	 * Sets the tipo cuenta tarjeta.
	 *
	 * @param tipoCuentaTarjeta
	 *            the tipoCuentaTarjeta to set
	 */
	public void setTipoCuentaTarjeta(TipoCuentaTarjeta tipoCuentaTarjeta) {
		this.tipoCuentaTarjeta = tipoCuentaTarjeta;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the fecha pago programado.
	 *
	 * @return the fechaPagoProgramado
	 */
	public Date getFechaPagoProgramado() {
		return fechaPagoProgramado == null ? null : new Date(fechaPagoProgramado.getTime());
	}

	/**
	 * Sets the fecha pago programado.
	 *
	 * @param fechaPagoProgramado
	 *            the fechaPagoProgramado to set
	 */
	public void setFechaPagoProgramado(Date fechaPagoProgramado) {
		this.fechaPagoProgramado = fechaPagoProgramado == null ? null : new Date(fechaPagoProgramado.getTime());
	}

	/**
	 * Gets the tipo cuenta banco pesos.
	 *
	 * @return the tipoCuentaBancoPesos
	 */
	public String getTipoCuentaBancoPesos() {
		return tipoCuentaBancoPesos;
	}

	/**
	 * Sets the tipo cuenta banco pesos.
	 *
	 * @param tipoCuentaBancoPesos
	 *            the tipoCuentaBancoPesos to set
	 */
	public void setTipoCuentaBancoPesos(String tipoCuentaBancoPesos) {
		this.tipoCuentaBancoPesos = tipoCuentaBancoPesos;
	}

	/**
	 * Gets the sucursal banco pesos.
	 *
	 * @return the sucursalBancoPesos
	 */
	public int getSucursalBancoPesos() {
		return sucursalBancoPesos;
	}

	/**
	 * Sets the sucursal banco pesos.
	 *
	 * @param sucursalBancoPesos
	 *            the sucursalBancoPesos to set
	 */
	public void setSucursalBancoPesos(int sucursalBancoPesos) {
		this.sucursalBancoPesos = sucursalBancoPesos;
	}

	/**
	 * Gets the numero cuenta banco pesos.
	 *
	 * @return the numeroCuentaBancoPesos
	 */
	public int getNumeroCuentaBancoPesos() {
		return numeroCuentaBancoPesos;
	}

	/**
	 * Sets the numero cuenta banco pesos.
	 *
	 * @param numeroCuentaBancoPesos
	 *            the numeroCuentaBancoPesos to set
	 */
	public void setNumeroCuentaBancoPesos(int numeroCuentaBancoPesos) {
		this.numeroCuentaBancoPesos = numeroCuentaBancoPesos;
	}

	/**
	 * Gets the tipo cuenta banco dolares.
	 *
	 * @return the tipoCuentaBancoDolares
	 */
	public String getTipoCuentaBancoDolares() {
		return tipoCuentaBancoDolares;
	}

	/**
	 * Sets the tipo cuenta banco dolares.
	 *
	 * @param tipoCuentaBancoDolares
	 *            the tipoCuentaBancoDolares to set
	 */
	public void setTipoCuentaBancoDolares(String tipoCuentaBancoDolares) {
		this.tipoCuentaBancoDolares = tipoCuentaBancoDolares;
	}

	/**
	 * Gets the sucursal banco dolares.
	 *
	 * @return the sucursalBancoDolares
	 */
	public int getSucursalBancoDolares() {
		return sucursalBancoDolares;
	}

	/**
	 * Sets the sucursal banco dolares.
	 *
	 * @param sucursalBancoDolares
	 *            the sucursalBancoDolares to set
	 */
	public void setSucursalBancoDolares(int sucursalBancoDolares) {
		this.sucursalBancoDolares = sucursalBancoDolares;
	}

	/**
	 * Gets the numero cuenta banco dolares.
	 *
	 * @return the numeroCuentaBancoDolares
	 */
	public int getNumeroCuentaBancoDolares() {
		return numeroCuentaBancoDolares;
	}

	/**
	 * Sets the numero cuenta banco dolares.
	 *
	 * @param numeroCuentaBancoDolares
	 *            the numeroCuentaBancoDolares to set
	 */
	public void setNumeroCuentaBancoDolares(int numeroCuentaBancoDolares) {
		this.numeroCuentaBancoDolares = numeroCuentaBancoDolares;
	}

	/**
	 * Gets the tipo pago.
	 *
	 * @return the tipoPago
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * Sets the tipo pago.
	 *
	 * @param tipoPago
	 *            the tipoPago to set
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	/**
	 * Gets the momento pago.
	 *
	 * @return the momentoPago
	 */
	public String getMomentoPago() {
		return momentoPago;
	}

	/**
	 * Sets the momento pago.
	 *
	 * @param momentoPago
	 *            the momentoPago to set
	 */
	public void setMomentoPago(String momentoPago) {
		this.momentoPago = momentoPago;

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
	 * Sets the codigo titularidad.
	 *
	 * @param codigoTitularidad
	 *            the new codigo titularidad
	 */
	public void setCodigoTitularidad(String codigoTitularidad) {
		this.codigoTitularidad = codigoTitularidad;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return this.alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(adelantosDolaresTC);
		hcb.append(adelantosPesosTC);
		hcb.append(ajusteCreditoDolaresTC);
		hcb.append(ajusteCreditoPesosTC);
		hcb.append(ajusteDebitoDolaresTC);
		hcb.append(ajusteDebitoPesosTC);
		hcb.append(cicloTarjetaCredito);
		hcb.append(consumosDolaresTC);
		hcb.append(consumosPesosTC);
		hcb.append(estadoTarjetaCredito);
		hcb.append(fechaProximoCierreTC);
		hcb.append(fechaProximoVencimientoTC);
		hcb.append(fechaUltimoCierreTC);
		hcb.append(fechaUltimoPagoTC);
		hcb.append(fechaVencimientoLiquidacionTC);
		hcb.append(formaPagoTarjetaCredito);
		hcb.append(limiteCompraPesosTC);
		hcb.append(limiteFinanciacionTC);
		hcb.append(numeroTarjeta);
		hcb.append(pagoMinimoImpagoTC);
		hcb.append(pagoMinimoPesosTC);
		hcb.append(pagosDolaresTC);
		hcb.append(pagosPesosTC);
		hcb.append(saldoActualDolaresTC);
		hcb.append(saldoActualPesosTC);
		hcb.append(saldoDolaresTC);
		hcb.append(saldoPesosTC);
		hcb.append(saldoUltimoCierreDolaresTC);
		hcb.append(saldoUltimoCierrePesosTC);
		hcb.append(tipoCuentaTarjeta);
		hcb.append(cotizacionComprador);
		hcb.append(cotizacionVendedor);
		hcb.append(saldoPendienteDolares);
		hcb.append(saldoPendientePesos);
		hcb.append(saldoTotalConvDolares);
		hcb.append(saldoTotalConvPesos);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		// TODO: IMPLEMENTAR CORRECTAMENTE usando EqualsBuilder.
		return true;
	}

	/**
	 * Es tarjeta valida.
	 *
	 * @return true, if successful
	 */
	public boolean esTarjetaValida() {
		return !(this.getEstadoTarjetaCredito().equalsIgnoreCase(TARJETA_CERRADA)
				&& this.getSaldoPesosTC().compareTo(BigDecimal.ZERO) <= 0
				&& this.getSaldoDolaresTC().compareTo(BigDecimal.ZERO) <= 0);
	}

	/**
	 * Gets the numero cuenta de tarjeta credito.
	 *
	 * @return the numeroCuentaDeTarjetaCredito
	 */
	public String getNumeroCuentaDeTarjetaCredito() {
		return numeroCuentaDeTarjetaCredito;
	}

	/**
	 * Sets the numero cuenta de tarjeta credito.
	 *
	 * @param numeroCuentaDeTarjetaCredito
	 *            the numeroCuentaDeTarjetaCredito to set
	 */
	public void setNumeroCuentaDeTarjetaCredito(String numeroCuentaDeTarjetaCredito) {
		this.numeroCuentaDeTarjetaCredito = numeroCuentaDeTarjetaCredito;
	}

}
