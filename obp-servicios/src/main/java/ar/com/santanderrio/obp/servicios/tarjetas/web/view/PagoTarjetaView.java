/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class PagoTarjetaView.
 */
public class PagoTarjetaView implements Comparable<PagoTarjetaView> {

	/** VISA = 7L; AMEX = 42L;. */
	private String tipoTarjeta;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The numero tarjeta sin marca. */
	private String numeroTarjetaSinMarca;

	/** The saldo dolares TC. */
	private String saldoDolaresTC;

	/** The saldo pesos TC. */
	private String saldoPesosTC;

	/** The pago minimo pesos TC. */
	private String pagoMinimoPesosTC;

	/** The fecha vencimiento liquidacion TC. */
	private String fechaVencimientoLiquidacionTC;

	/** The cotizacion dolares. */
	private String cotizacionDolares;

	/** The importe pesos convertido A dolares. */
	private String importePesosConvertidoADolares;

	/** The importe dolares convertido A pesos. */
	private String importeDolaresConvertidoAPesos;

	/** The total A pagar en pesos. */
	private String totalAPagarEnPesos;

	/** The total A pagar en dolares. */
	private String totalAPagarEnDolares;

	/** The forma pago tarjeta credito. */
	private String formaPagoTarjetaCredito;

	/** The tiene pagos programados. */
	private Boolean tienePagosProgramados;

	/** The mensaje pagos programados. */
	private String mensajePagosProgramados;

	/** The tiene saldo dolares. */
	private Boolean tieneSaldoDolares;

	/** The codigo titularidad. */
	private String codigoTitularidad;

	/** The sucursal cuenta tarjeta. */
	private String sucursalCuentaTarjeta;

	/** The mensaje stop debit. */
	private String mensajeSwitchOn;

	/** The mensaje switch off. */
	private String mensajeSwitchOff;

	/** The alias. */
	private String alias;

	/** The pago acumulado mensual pesos. */
	private String pagoAcumuladoMensualPesos;

	/** The saldo sin siguiente cierre pesos. */
	private String saldoSinSiguienteCierrePesos;

	/** The pago acumulado mensual dolares. */
	private String pagoAcumuladoMensualDolares;

	/** The saldo sin siguiente cierre dolares. */
	private String saldoSinSiguienteCierreDolares;

	/** The saldo sin siguiente cierre dolares positivo. */
	private String saldoSinSiguienteCierreDolaresPositivo;

	/** The saldo sin siguiente cierre pesos positivo. */
	private String saldoSinSiguienteCierrePesosPositivo;

	/** The saldo dolares TC positivo. */
	private Boolean saldoDolaresTCPositivo;

	/** The saldo pesos TC positivo. */
	private Boolean saldoPesosTCPositivo;

	/** The tiene debito automatico. */
	private Boolean tieneDebitoAutomatico;

	/** The mensaje debito automatico. */
	private String mensajeDebitoAutomatico;

	/** The saldo A pagar convertido A dolares. */
	private String saldoAPagarConvertidoADolares;

	/** The saldo A pagar convertido A pesos. */
	private String saldoAPagarConvertidoAPesos;

	/** The fecha vencimiento vencida. */
	private Boolean fechaVencimientoVencida;

	/** The saldo sin siguiente cierre pesos convertido A dolares. */
	private String saldoSinSiguienteCierrePesosConvertidoADolares;

	/** The saldo sin siguiente cierre dolares convertido A pesos. */
	private String saldoSinSiguienteCierreDolaresConvertidoAPesos;

	/** The servicio cotizacion disponible. */
	private Boolean servicioCotizacionDisponible;

	/** The cotizacion comprador. */
	private String cotizacionComprador;
	
	/** The cotizacion vendedor. */
	private String cotizacionVendedor;
	
	/** The saldo pendiente dolares. */
	private String saldoPendienteDolares;

	/** The saldo pendiente pesos. */
	private String saldoPendientePesos;

	/**
	 * Instantiates a new pago tarjeta view.
	 */
	public PagoTarjetaView() {
		super();
	}

	/**
	 * Instantiates a new pago tarjeta view.
	 *
	 * @param vacio
	 *            the vacio
	 */
	public PagoTarjetaView(String vacio) {
		this.cotizacionDolares = vacio;
		this.totalAPagarEnDolares = vacio;
		this.totalAPagarEnPesos = vacio;
		this.importePesosConvertidoADolares = vacio;
		this.importeDolaresConvertidoAPesos = vacio;
		this.servicioCotizacionDisponible = false;
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
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
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the saldo dolares TC.
	 *
	 * @return the saldo dolares TC
	 */
	public String getSaldoDolaresTC() {
		return saldoDolaresTC;
	}

	/**
	 * Gets the fecha vencimiento vencida.
	 *
	 * @return the fecha vencimiento vencida
	 */
	public Boolean getFechaVencimientoVencida() {
		return fechaVencimientoVencida;
	}

	/**
	 * Sets the fecha vencimiento vencida.
	 *
	 * @param fechaVencimientoVencida
	 *            the new fecha vencimiento vencida
	 */
	public void setFechaVencimientoVencida(Boolean fechaVencimientoVencida) {
		this.fechaVencimientoVencida = fechaVencimientoVencida;
	}

	/**
	 * Sets the saldo dolares TC.
	 *
	 * @param saldoDolaresTC
	 *            the new saldo dolares TC
	 */
	public void setSaldoDolaresTC(String saldoDolaresTC) {
		this.saldoDolaresTC = saldoDolaresTC;
		this.tieneSaldoDolares = StringUtils.isNotBlank(saldoDolaresTC)
				? !"0,00".equals(StringUtils.trim(saldoDolaresTC)) : false;
	}

	/**
	 * Gets the saldo pesos TC.
	 *
	 * @return the saldo pesos TC
	 */
	public String getSaldoPesosTC() {
		return saldoPesosTC;
	}

	/**
	 * Sets the saldo pesos TC.
	 *
	 * @param saldoPesosTC
	 *            the new saldo pesos TC
	 */
	public void setSaldoPesosTC(String saldoPesosTC) {
		this.saldoPesosTC = saldoPesosTC;
	}

	/**
	 * Gets the pago minimo pesos TC.
	 *
	 * @return the pago minimo pesos TC
	 */
	public String getPagoMinimoPesosTC() {
		return pagoMinimoPesosTC;
	}

	/**
	 * Sets the pago minimo pesos TC.
	 *
	 * @param pagoMinimoPesosTC
	 *            the new pago minimo pesos TC
	 */
	public void setPagoMinimoPesosTC(String pagoMinimoPesosTC) {
		this.pagoMinimoPesosTC = pagoMinimoPesosTC;
	}

	/**
	 * Gets the fecha vencimiento liquidacion TC.
	 *
	 * @return the fecha vencimiento liquidacion TC
	 */
	public String getFechaVencimientoLiquidacionTC() {
		return fechaVencimientoLiquidacionTC;
	}

	/**
	 * Sets the fecha vencimiento liquidacion TC.
	 *
	 * @param fechaVencimientoLiquidacionTC
	 *            the new fecha vencimiento liquidacion TC
	 */
	public void setFechaVencimientoLiquidacionTC(String fechaVencimientoLiquidacionTC) {
		this.fechaVencimientoLiquidacionTC = fechaVencimientoLiquidacionTC;
	}

	/**
	 * Gets the cotizacion dolares.
	 *
	 * @return the cotizacion dolares
	 */
	public String getCotizacionDolares() {
		return cotizacionDolares;
	}

	/**
	 * Sets the cotizacion dolares.
	 *
	 * @param cotizacionDolares
	 *            the new cotizacion dolares
	 */
	public void setCotizacionDolares(String cotizacionDolares) {
		this.cotizacionDolares = cotizacionDolares;
	}

	/**
	 * Gets the importe pesos convertido A dolares.
	 *
	 * @return the importe pesos convertido A dolares
	 */
	public String getImportePesosConvertidoADolares() {
		return importePesosConvertidoADolares;
	}

	/**
	 * Sets the importe pesos convertido A dolares.
	 *
	 * @param importePesosConvertidoADolares
	 *            the new importe pesos convertido A dolares
	 */
	public void setImportePesosConvertidoADolares(String importePesosConvertidoADolares) {
		this.importePesosConvertidoADolares = importePesosConvertidoADolares;
	}

	/**
	 * Gets the importe dolares convertido A pesos.
	 *
	 * @return the importe dolares convertido A pesos
	 */
	public String getImporteDolaresConvertidoAPesos() {
		return importeDolaresConvertidoAPesos;
	}

	/**
	 * Sets the importe dolares convertido A pesos.
	 *
	 * @param importeDolaresConvertidoAPesos
	 *            the new importe dolares convertido A pesos
	 */
	public void setImporteDolaresConvertidoAPesos(String importeDolaresConvertidoAPesos) {
		this.importeDolaresConvertidoAPesos = importeDolaresConvertidoAPesos;
	}

	/**
	 * Gets the total A pagar en pesos.
	 *
	 * @return the total A pagar en pesos
	 */
	public String getTotalAPagarEnPesos() {
		return totalAPagarEnPesos;
	}

	/**
	 * Sets the total A pagar en pesos.
	 *
	 * @param totalAPagarEnPesos
	 *            the new total A pagar en pesos
	 */
	public void setTotalAPagarEnPesos(String totalAPagarEnPesos) {
		this.totalAPagarEnPesos = totalAPagarEnPesos;
	}

	/**
	 * Gets the total A pagar en dolares.
	 *
	 * @return the total A pagar en dolares
	 */
	public String getTotalAPagarEnDolares() {
		return totalAPagarEnDolares;
	}

	/**
	 * Sets the total A pagar en dolares.
	 *
	 * @param totalAPagarEnDolares
	 *            the new total A pagar en dolares
	 */
	public void setTotalAPagarEnDolares(String totalAPagarEnDolares) {
		this.totalAPagarEnDolares = totalAPagarEnDolares;
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
	 * Gets the tiene pagos programados.
	 *
	 * @return the tiene pagos programados
	 */
	public Boolean getTienePagosProgramados() {
		return tienePagosProgramados;
	}

	/**
	 * Sets the tiene pagos programados.
	 *
	 * @param tienePagosProgramados
	 *            the new tiene pagos programados
	 */
	public void setTienePagosProgramados(Boolean tienePagosProgramados) {
		this.tienePagosProgramados = tienePagosProgramados;
	}

	/**
	 * Gets the mensaje pagos programados.
	 *
	 * @return the mensaje pagos programados
	 */
	public String getMensajePagosProgramados() {
		return mensajePagosProgramados;
	}

	/**
	 * Sets the mensaje pagos programados.
	 *
	 * @param mensajePagosProgramados
	 *            the new mensaje pagos programados
	 */
	public void setMensajePagosProgramados(String mensajePagosProgramados) {
		this.mensajePagosProgramados = mensajePagosProgramados;
	}

	/**
	 * Gets the tiene saldo dolares.
	 *
	 * @return the tiene saldo dolares
	 */
	public Boolean getTieneSaldoDolares() {
		return tieneSaldoDolares;
	}

	/**
	 * Gets the sucursal cuenta tarjeta.
	 *
	 * @return the sucursal cuenta tarjeta
	 */
	public String getSucursalCuentaTarjeta() {
		return sucursalCuentaTarjeta;
	}

	/**
	 * Sets the sucursal cuenta tarjeta.
	 *
	 * @param sucursalCuentaTarjeta
	 *            the new sucursal cuenta tarjeta
	 */
	public void setSucursalCuentaTarjeta(String sucursalCuentaTarjeta) {
		this.sucursalCuentaTarjeta = sucursalCuentaTarjeta;
	}

	/**
	 * Sets the tiene saldo dolares.
	 *
	 * @param tieneSaldoDolares
	 *            the new tiene saldo dolares
	 */
	public void setTieneSaldoDolares(Boolean tieneSaldoDolares) {
		this.tieneSaldoDolares = tieneSaldoDolares;
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
	 * Gets the mensaje stop debit.
	 *
	 * @return the mensaje stop debit
	 */
	public String getMensajeSwitchOn() {
		return mensajeSwitchOn;
	}

	/**
	 * Sets the mensaje stop debit.
	 *
	 * @param mensajeStopDebit
	 *            the new mensaje stop debit
	 */
	public void setMensajeSwitchOn(String mensajeStopDebit) {
		this.mensajeSwitchOn = mensajeStopDebit;
	}

	/**
	 * Gets the mensaje switch off.
	 *
	 * @return the mensaje switch off
	 */
	public String getMensajeSwitchOff() {
		return mensajeSwitchOff;
	}

	/**
	 * Sets the mensaje switch off.
	 *
	 * @param mensajeSwitchOff
	 *            the new mensaje switch off
	 */
	public void setMensajeSwitchOff(String mensajeSwitchOff) {
		this.mensajeSwitchOff = mensajeSwitchOff;
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
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the pago acumulado mensual pesos.
	 *
	 * @return the pago acumulado mensual pesos
	 */
	public String getPagoAcumuladoMensualPesos() {
		return pagoAcumuladoMensualPesos;
	}

	/**
	 * Sets the pago acumulado mensual pesos.
	 *
	 * @param pagoAcumuladoMensualPesos
	 *            the new pago acumulado mensual pesos
	 */
	public void setPagoAcumuladoMensualPesos(String pagoAcumuladoMensualPesos) {
		this.pagoAcumuladoMensualPesos = pagoAcumuladoMensualPesos;
	}

	/**
	 * Gets the saldo sin siguiente cierre pesos.
	 *
	 * @return the saldo sin siguiente cierre pesos
	 */
	public String getSaldoSinSiguienteCierrePesos() {
		return saldoSinSiguienteCierrePesos;
	}

	/**
	 * Sets the saldo sin siguiente cierre pesos.
	 *
	 * @param saldoSinSiguienteCierrePesos
	 *            the new saldo sin siguiente cierre pesos
	 */
	public void setSaldoSinSiguienteCierrePesos(String saldoSinSiguienteCierrePesos) {
		this.saldoSinSiguienteCierrePesos = saldoSinSiguienteCierrePesos;
	}

	/**
	 * Gets the pago acumulado mensual dolares.
	 *
	 * @return the pago acumulado mensual dolares
	 */
	public String getPagoAcumuladoMensualDolares() {
		return pagoAcumuladoMensualDolares;
	}

	/**
	 * Sets the pago acumulado mensual dolares.
	 *
	 * @param pagoAcumuladoMensualDolares
	 *            the new pago acumulado mensual dolares
	 */
	public void setPagoAcumuladoMensualDolares(String pagoAcumuladoMensualDolares) {
		this.pagoAcumuladoMensualDolares = pagoAcumuladoMensualDolares;
	}

	/**
	 * Gets the saldo sin siguiente cierre dolares.
	 *
	 * @return the saldo sin siguiente cierre dolares
	 */
	public String getSaldoSinSiguienteCierreDolares() {
		return saldoSinSiguienteCierreDolares;
	}

	/**
	 * Sets the saldo sin siguiente cierre dolares.
	 *
	 * @param saldoSinSiguienteCierreDolares
	 *            the new saldo sin siguiente cierre dolares
	 */
	public void setSaldoSinSiguienteCierreDolares(String saldoSinSiguienteCierreDolares) {
		this.saldoSinSiguienteCierreDolares = saldoSinSiguienteCierreDolares;
	}

	/**
	 * Gets the saldo sin siguiente cierre dolares positivo.
	 *
	 * @return the saldo sin siguiente cierre dolares positivo
	 */
	public String getSaldoSinSiguienteCierreDolaresPositivo() {
		return saldoSinSiguienteCierreDolaresPositivo;
	}

	/**
	 * Sets the saldo sin siguiente cierre dolares positivo.
	 *
	 * @param saldoSinSiguienteCierreDolaresPositivo
	 *            the new saldo sin siguiente cierre dolares positivo
	 */
	public void setSaldoSinSiguienteCierreDolaresPositivo(String saldoSinSiguienteCierreDolaresPositivo) {
		this.saldoSinSiguienteCierreDolaresPositivo = saldoSinSiguienteCierreDolaresPositivo;
	}

	/**
	 * Gets the saldo sin siguiente cierre pesos positivo.
	 *
	 * @return the saldo sin siguiente cierre pesos positivo
	 */
	public String getSaldoSinSiguienteCierrePesosPositivo() {
		return saldoSinSiguienteCierrePesosPositivo;
	}

	/**
	 * Sets the saldo sin siguiente cierre pesos positivo.
	 *
	 * @param saldoSinSiguienteCierrePesosPositivo
	 *            the new saldo sin siguiente cierre pesos positivo
	 */
	public void setSaldoSinSiguienteCierrePesosPositivo(String saldoSinSiguienteCierrePesosPositivo) {
		this.saldoSinSiguienteCierrePesosPositivo = saldoSinSiguienteCierrePesosPositivo;
	}

	/**
	 * Gets the saldo dolares TC positivo.
	 *
	 * @return the saldo dolares TC positivo
	 */
	public Boolean getSaldoDolaresTCPositivo() {
		return saldoDolaresTCPositivo;
	}

	/**
	 * Sets the saldo dolares TC positivo.
	 *
	 * @param saldoDolaresTCPositivo
	 *            the new saldo dolares TC positivo
	 */
	public void setSaldoDolaresTCPositivo(Boolean saldoDolaresTCPositivo) {
		this.saldoDolaresTCPositivo = saldoDolaresTCPositivo;
	}

	/**
	 * Gets the saldo pesos TC positivo.
	 *
	 * @return the saldo pesos TC positivo
	 */
	public Boolean getSaldoPesosTCPositivo() {
		return saldoPesosTCPositivo;
	}

	/**
	 * Sets the saldo pesos TC positivo.
	 *
	 * @param saldoPesosTCPositivo
	 *            the new saldo pesos TC positivo
	 */
	public void setSaldoPesosTCPositivo(Boolean saldoPesosTCPositivo) {
		this.saldoPesosTCPositivo = saldoPesosTCPositivo;
	}

	/**
	 * Gets the tiene debito automatico.
	 *
	 * @return the tiene debito automatico
	 */
	public Boolean getTieneDebitoAutomatico() {
		return tieneDebitoAutomatico;
	}

	/**
	 * Sets the tiene debito automatico.
	 *
	 * @param tieneDebitoAutomatico
	 *            the new tiene debito automatico
	 */
	public void setTieneDebitoAutomatico(Boolean tieneDebitoAutomatico) {
		this.tieneDebitoAutomatico = tieneDebitoAutomatico;
	}

	/**
	 * Gets the mensaje debito automatico.
	 *
	 * @return the mensaje debito automatico
	 */
	public String getMensajeDebitoAutomatico() {
		return mensajeDebitoAutomatico;
	}

	/**
	 * Sets the mensaje debito automatico.
	 *
	 * @param mensajeDebitoAutomatico
	 *            the new mensaje debito automatico
	 */
	public void setMensajeDebitoAutomatico(String mensajeDebitoAutomatico) {
		this.mensajeDebitoAutomatico = mensajeDebitoAutomatico;
	}

	/**
	 * Gets the saldo A pagar convertido A dolares.
	 *
	 * @return the saldo A pagar convertido A dolares
	 */
	public String getSaldoAPagarConvertidoADolares() {
		return saldoAPagarConvertidoADolares;
	}

	/**
	 * Sets the saldo A pagar convertido A dolares.
	 *
	 * @param saldoAPagarConvertidoADolares
	 *            the new saldo A pagar convertido A dolares
	 */
	public void setSaldoAPagarConvertidoADolares(String saldoAPagarConvertidoADolares) {
		this.saldoAPagarConvertidoADolares = saldoAPagarConvertidoADolares;
	}

	/**
	 * Gets the saldo A pagar convertido A pesos.
	 *
	 * @return the saldo A pagar convertido A pesos
	 */
	public String getSaldoAPagarConvertidoAPesos() {
		return saldoAPagarConvertidoAPesos;
	}

	/**
	 * Sets the saldo A pagar convertido A pesos.
	 *
	 * @param saldoAPagarConvertidoAPesos
	 *            the new saldo A pagar convertido A pesos
	 */
	public void setSaldoAPagarConvertidoAPesos(String saldoAPagarConvertidoAPesos) {
		this.saldoAPagarConvertidoAPesos = saldoAPagarConvertidoAPesos;
	}

	/**
	 * Gets the numero tarjeta sin marca.
	 *
	 * @return the numero tarjeta sin marca
	 */
	public String getNumeroTarjetaSinMarca() {
		return numeroTarjetaSinMarca;
	}

	/**
	 * Sets the numero tarjeta sin marca.
	 *
	 * @param numeroTarjetaSinMarca
	 *            the new numero tarjeta sin marca
	 */
	public void setNumeroTarjetaSinMarca(String numeroTarjetaSinMarca) {
		this.numeroTarjetaSinMarca = numeroTarjetaSinMarca;
	}

	/**
	 * Gets the saldo sin siguiente cierre pesos convertido A dolares.
	 *
	 * @return the saldo sin siguiente cierre pesos convertido A dolares
	 */
	public String getSaldoSinSiguienteCierrePesosConvertidoADolares() {
		return saldoSinSiguienteCierrePesosConvertidoADolares;
	}

	/**
	 * Sets the saldo sin siguiente cierre pesos convertido A dolares.
	 *
	 * @param saldoSinSiguienteCierrePesosConvertidoADolares
	 *            the new saldo sin siguiente cierre pesos convertido A dolares
	 */
	public void setSaldoSinSiguienteCierrePesosConvertidoADolares(
			String saldoSinSiguienteCierrePesosConvertidoADolares) {
		this.saldoSinSiguienteCierrePesosConvertidoADolares = saldoSinSiguienteCierrePesosConvertidoADolares;
	}

	/**
	 * Gets the saldo sin siguiente cierre dolares convertido A pesos.
	 *
	 * @return the saldo sin siguiente cierre dolares convertido A pesos
	 */
	public String getSaldoSinSiguienteCierreDolaresConvertidoAPesos() {
		return saldoSinSiguienteCierreDolaresConvertidoAPesos;
	}

	/**
	 * Sets the saldo sin siguiente cierre dolares convertido A pesos.
	 *
	 * @param saldoSinSiguienteCierreDolaresConvertidoAPesos
	 *            the new saldo sin siguiente cierre dolares convertido A pesos
	 */
	public void setSaldoSinSiguienteCierreDolaresConvertidoAPesos(
			String saldoSinSiguienteCierreDolaresConvertidoAPesos) {
		this.saldoSinSiguienteCierreDolaresConvertidoAPesos = saldoSinSiguienteCierreDolaresConvertidoAPesos;
	}

	/**
	 * Gets the servicio cotizacion disponible.
	 *
	 * @return the servicio cotizacion disponible
	 */
	public Boolean getServicioCotizacionDisponible() {
		return servicioCotizacionDisponible;
	}

	/**
	 * Sets the servicio cotizacion disponible.
	 *
	 * @param servicioCotizacionDisponible
	 *            the new servicio cotizacion disponible
	 */
	public void setServicioCotizacionDisponible(Boolean servicioCotizacionDisponible) {
		this.servicioCotizacionDisponible = servicioCotizacionDisponible;
	}

	/**
	 * Gets the cotizacion comprador.
	 *
	 * @return the cotizacion comprador
	 */
	public String getCotizacionComprador() {
		return cotizacionComprador;
	}

	/**
	 * Sets the cotizacion comprador.
	 *
	 * @param cotizacionComprador the new cotizacion comprador
	 */
	public void setCotizacionComprador(String cotizacionComprador) {
		this.cotizacionComprador = cotizacionComprador;
	}

	/**
	 * Gets the cotizacion vendedor.
	 *
	 * @return the cotizacion vendedor
	 */
	public String getCotizacionVendedor() {
		return cotizacionVendedor;
	}

	/**
	 * Sets the cotizacion vendedor.
	 *
	 * @param cotizacionVendedor the new cotizacion vendedor
	 */
	public void setCotizacionVendedor(String cotizacionVendedor) {
		this.cotizacionVendedor = cotizacionVendedor;
	}

	/**
	 * Gets the saldo pendiente dolares.
	 *
	 * @return the saldo pendiente dolares
	 */
	public String getSaldoPendienteDolares() {
		return saldoPendienteDolares;
	}

	/**
	 * Sets the saldo pendiente dolares.
	 *
	 * @param saldoPendienteDolares the new saldo pendiente dolares
	 */
	public void setSaldoPendienteDolares(String saldoPendienteDolares) {
		this.saldoPendienteDolares = saldoPendienteDolares;
	}

	/**
	 * Gets the saldo pendiente pesos.
	 *
	 * @return the saldo pendiente pesos
	 */
	public String getSaldoPendientePesos() {
		return saldoPendientePesos;
	}

	/**
	 * Sets the saldo pendiente pesos.
	 *
	 * @param saldoPendientePesos the new saldo pendiente pesos
	 */
	public void setSaldoPendientePesos(String saldoPendientePesos) {
		this.saldoPendientePesos = saldoPendientePesos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagoTarjetaView [tipoTarjeta=" + tipoTarjeta + ", numeroTarjeta=" + numeroTarjeta + ", saldoDolaresTC="
				+ saldoDolaresTC + ", saldoPesosTC=" + saldoPesosTC + ", pagoMinimoPesosTC=" + pagoMinimoPesosTC
				+ ", fechaVencimientoLiquidacionTC=" + fechaVencimientoLiquidacionTC + ", cotizacionDolares="
				+ cotizacionDolares + ", importePesosConvertidoADolares=" + importePesosConvertidoADolares
				+ ", importeDolaresConvertidoAPesos=" + importeDolaresConvertidoAPesos + ", totalAPagarEnPesos="
				+ totalAPagarEnPesos + ", totalAPagarEnDolares=" + totalAPagarEnDolares + ", formaPagoTarjetaCredito="
				+ formaPagoTarjetaCredito + ", tienePagosProgramados=" + tienePagosProgramados
				+ ", mensajePagosProgramados=" + mensajePagosProgramados + ", tieneSaldoDolares=" + tieneSaldoDolares
				+ ", codigoTitularidad=" + codigoTitularidad + ", mensajeStopDebit=" + mensajeSwitchOn + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PagoTarjetaView tarjeta1) {

		int i = tarjeta1.getTipoTarjeta().compareTo(this.getTipoTarjeta());
		if (i != 0) {
			return i;
		}
		return tarjeta1.getCodigoTitularidad().compareTo(this.getCodigoTitularidad());
	}

}
