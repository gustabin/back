/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTimeComparator;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;

/**
 * The Class Prestamo.
 */
public class Prestamo {

	/** The cuenta relacionada. */
	private String cuentaRelacionada;

	/** Alias. */
	private String alias;

	/** The vencimiento cuota. */
	private Date vencimientoCuota;

	/** The numero recibo. */
	private String numeroRecibo;

	/** The importe amortizacion. */
	private BigDecimal importeAmortizacion;

	/** The importe intereses. */
	private BigDecimal importeIntereses;

	/** The comisiones pendientes. */
	private BigDecimal comisionesPendientes;

	/** The gastos pendientes. */
	private BigDecimal gastosPendientes;

	/** The importe total seguros. */
	private BigDecimal importeTotalSeguros;

	/** The impuestos pendientes. */
	private String impuestosPendientes;

	/** The importe cobranzas pendientes. */
	private String importeCobranzasPendientes;

	/** The importes punitorios. */
	private BigDecimal importesPunitorios;

	/** The interes compensatorio pendiente. */
	private BigDecimal interesCompensatorioPendiente;

	/** The tasa prestamo. */
	private BigDecimal tasaPrestamo;

	/** The importe seguro vida. */
	private BigDecimal importeSeguroVida;

	/** The importe seguro del bien. */
	private BigDecimal importeSeguroDelBien;

	/** The importe IVA. */
	private BigDecimal importeIVA;

	/** The importe IVA adicional. */
	private BigDecimal importeIVAAdicional;

	/** The importe endeudamiento. */
	private BigDecimal importeEndeudamiento;

	/** The ingresos brutos. */
	private BigDecimal ingresosBrutos;

	/** The otros impuestos. */
	private BigDecimal otrosImpuestos;

	/** The importe total cuota. */
	private BigDecimal importeTotalCuota;

	/** The divisa. */
	private DivisaEnum divisa;

	/** The saldo previo. */
	private BigDecimal saldoPrevio;

	/** The factor index. */
	private String factorIndex;

	/** The importe ajussal. */
	private BigDecimal importeAjussal;

	/** The importe ajuscap. */
	private BigDecimal importeAjuscap;

	/** The importe ajuste capmor. */
	private BigDecimal importeAjusteCapmor;

	/** The factor capmor. */
	private String factorCapmor;

	/** The index. */
	private String index;

	/** The rendicion seguro vida. */
	private String rendicionSeguroVida;

	/** The tasa anual efectiva. */
	private BigDecimal tasaAnualEfectiva;

	/** The costo financiero total. */
	private BigDecimal costoFinancieroTotal;

	/** The clase cuenta. */
	private String claseCuenta;

	/** The numero cuenta producto. */
	private String numeroCuentaProducto;

	/** The importe total cuota cambio. */
	private String importeTotalCuotaCambio;

	/** The costo financiero total sin impuestos. */
	private BigDecimal costoFinancieroTotalSinImpuestos;

	/** The cuenta. */
	private Cuenta cuenta;

	/** The tipo prestamo enum. */
	private TipoPrestamoEnum tipoPrestamoEnum;

	/** The plazo. */
	private String plazo;

	/** The nro prestamo. */
	private String nroPrestamo;

	/** cambioi para version 140 del serv *. */
	private String ctaRelacionada;

	/** The fec liq recibo. */
	private Date fecLiqRecibo;

	/** The nro recibo. */
	private String nroRecibo;

	/** The nro exp. */
	private String nroExp;

	/** The cotiz cambio. */
	private BigDecimal cotizCambio;

	/** The fec cotiz. */
	private Date fecCotiz;

	/** The capital pend. */
	private BigDecimal capitalPend;

	/** The intereses pend. */
	private BigDecimal interesesPend;

	/** The comisiones pend. */
	private BigDecimal comisionesPend;

	/** The gastos pend. */
	private BigDecimal gastosPend;

	/** The seguros pend. */
	private BigDecimal segurosPend;

	/** The impuestos pend. */
	private BigDecimal impuestosPend;

	/** The cobranza ext. */
	private BigDecimal cobranzaExt;

	/** The int mora pend. */
	private BigDecimal intMoraPend;

	/** The int compens pend. */
	private BigDecimal intCompensPend;

	/** The seguro vida. */
	private BigDecimal seguroVida;

	/** The seguro bien. */
	private BigDecimal seguroBien;

	/** The iva 1. */
	private BigDecimal iva1;

	/** The iva 2. */
	private BigDecimal iva2;

	/** The imp endeudamiento. */
	private BigDecimal impEndeudamiento;

	/** The ingresos brut. */
	private BigDecimal ingresosBrut;

	/** The imp sin IVA. */
	private BigDecimal impSinIVA;

	/** The importe total. */
	private BigDecimal importeTotal;

	/** The sal previo. */
	private BigDecimal salPrevio;

	/** The ajuste saldo. */
	private BigDecimal ajusteSaldo;

	/** The ajuste cuota cap. */
	private BigDecimal ajusteCuotaCap;

	/** The ajuste cuota mora. */
	private BigDecimal ajusteCuotaMora;

	/** The importe rendir. */
	private BigDecimal importeRendir;

	/** The informe coeficiente no disponible. */
	private boolean informeCoeficienteNoDisponible = false;

	private Boolean isRefinanciacion = Boolean.FALSE;

	private Boolean isPrestamoNormativo = Boolean.FALSE;

	private PrestamoNormativoEnum codigoPrestamoNormativo;

	private String numeroPrestamoViejo;

	private String numeroReciboRefinanciado;

	/**
	 * @return the isRefinanciacion
	 */
	public Boolean getIsRefinanciacion() {
		return isRefinanciacion;
	}

	/**
	 * @param isRefinanciacion the isRefinanciacion to set
	 */
	public void setIsRefinanciacion(Boolean isRefinanciacion) {
		this.isRefinanciacion = isRefinanciacion;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the nro prestamo.
	 *
	 * @return the nro prestamo
	 */
	public String getNroPrestamo() {
		return nroPrestamo;
	}

	/**
	 * Sets the nro prestamo.
	 *
	 * @param nroPrestamo the new nro prestamo
	 */
	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	/**
	 * Gets the cuenta relacionada.
	 *
	 * @return the cuentaRelacionada
	 */
	public String getCuentaRelacionada() {
		return cuentaRelacionada;
	}

	/**
	 * Sets the cuenta relacionada.
	 *
	 * @param cuentaRelacionada the cuentaRelacionada to set
	 */
	public void setCuentaRelacionada(String cuentaRelacionada) {
		this.cuentaRelacionada = cuentaRelacionada;
	}

	/**
	 * Gets the vencimiento cuota.
	 *
	 * @return the vencimientoCuota
	 */
	public Date getVencimientoCuota() {
		return vencimientoCuota == null ? null : new Date(vencimientoCuota.getTime());
	}

	/**
	 * Sets the vencimiento cuota.
	 *
	 * @param vencimientoCuota the vencimientoCuota to set
	 */
	public void setVencimientoCuota(Date vencimientoCuota) {
		this.vencimientoCuota = vencimientoCuota == null ? null : new Date(vencimientoCuota.getTime());
	}

	/**
	 * Gets the numero recibo.
	 *
	 * @return the numeroRecibo
	 */
	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	/**
	 * Sets the numero recibo.
	 *
	 * @param numeroRecibo the numeroRecibo to set
	 */
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	/**
	 * Gets the importe amortizacion.
	 *
	 * @return the importeAmortizacion
	 */
	public BigDecimal getImporteAmortizacion() {
		return importeAmortizacion;
	}

	/**
	 * Sets the importe amortizacion.
	 *
	 * @param importeAmortizacion the importeAmortizacion to set
	 */
	public void setImporteAmortizacion(BigDecimal importeAmortizacion) {
		this.importeAmortizacion = importeAmortizacion;
	}

	/**
	 * Gets the importe intereses.
	 *
	 * @return the importeIntereses
	 */
	public BigDecimal getImporteIntereses() {
		return importeIntereses;
	}

	/**
	 * Sets the importe intereses.
	 *
	 * @param importeIntereses the importeIntereses to set
	 */
	public void setImporteIntereses(BigDecimal importeIntereses) {
		this.importeIntereses = importeIntereses;
	}

	/**
	 * Gets the comisiones pendientes.
	 *
	 * @return the comisionesPendientes
	 */
	public BigDecimal getComisionesPendientes() {
		return comisionesPendientes;
	}

	/**
	 * Sets the comisiones pendientes.
	 *
	 * @param comisionesPendientes the comisionesPendientes to set
	 */
	public void setComisionesPendientes(BigDecimal comisionesPendientes) {
		this.comisionesPendientes = comisionesPendientes;
	}

	/**
	 * Gets the gastos pendientes.
	 *
	 * @return the gastosPendientes
	 */
	public BigDecimal getGastosPendientes() {
		return gastosPendientes;
	}

	/**
	 * Sets the gastos pendientes.
	 *
	 * @param gastosPendientes the gastosPendientes to set
	 */
	public void setGastosPendientes(BigDecimal gastosPendientes) {
		this.gastosPendientes = gastosPendientes;
	}

	/**
	 * Gets the importe total seguros.
	 *
	 * @return the importeTotalSeguros
	 */
	public BigDecimal getImporteTotalSeguros() {
		return importeTotalSeguros;
	}

	/**
	 * Sets the importe total seguros.
	 *
	 * @param importeTotalSeguros the importeTotalSeguros to set
	 */
	public void setImporteTotalSeguros(BigDecimal importeTotalSeguros) {
		this.importeTotalSeguros = importeTotalSeguros;
	}

	/**
	 * Gets the impuestos pendientes.
	 *
	 * @return the impuestosPendientes
	 */
	public String getImpuestosPendientes() {
		return impuestosPendientes;
	}

	/**
	 * Sets the impuestos pendientes.
	 *
	 * @param impuestosPendientes the impuestosPendientes to set
	 */
	public void setImpuestosPendientes(String impuestosPendientes) {
		this.impuestosPendientes = impuestosPendientes;
	}

	/**
	 * Gets the importe cobranzas pendientes.
	 *
	 * @return the importeCobranzasPendientes
	 */
	public String getImporteCobranzasPendientes() {
		return importeCobranzasPendientes;
	}

	/**
	 * Sets the importe cobranzas pendientes.
	 *
	 * @param importeCobranzasPendientes the importeCobranzasPendientes to set
	 */
	public void setImporteCobranzasPendientes(String importeCobranzasPendientes) {
		this.importeCobranzasPendientes = importeCobranzasPendientes;
	}

	/**
	 * Gets the importes punitorios.
	 *
	 * @return the importesPunitorios
	 */
	public BigDecimal getImportesPunitorios() {
		return importesPunitorios;
	}

	/**
	 * Sets the importes punitorios.
	 *
	 * @param importesPunitorios the importesPunitorios to set
	 */
	public void setImportesPunitorios(BigDecimal importesPunitorios) {
		this.importesPunitorios = importesPunitorios;
	}

	/**
	 * Gets the interes compensatorio pendiente.
	 *
	 * @return the interesCompensatorioPendiente
	 */
	public BigDecimal getInteresCompensatorioPendiente() {
		return interesCompensatorioPendiente;
	}

	/**
	 * Sets the interes compensatorio pendiente.
	 *
	 * @param interesCompensatorioPendiente the interesCompensatorioPendiente to set
	 */
	public void setInteresCompensatorioPendiente(BigDecimal interesCompensatorioPendiente) {
		this.interesCompensatorioPendiente = interesCompensatorioPendiente;
	}

	/**
	 * Gets the tasa prestamo.
	 *
	 * @return the tasaPrestamo
	 */
	public BigDecimal getTasaPrestamo() {
		return tasaPrestamo;
	}

	/**
	 * Sets the tasa prestamo.
	 *
	 * @param tasaPrestamo the tasaPrestamo to set
	 */
	public void setTasaPrestamo(BigDecimal tasaPrestamo) {
		this.tasaPrestamo = tasaPrestamo;
	}

	/**
	 * Gets the importe seguro vida.
	 *
	 * @return the importeSeguroVida
	 */
	public BigDecimal getImporteSeguroVida() {
		return importeSeguroVida;
	}

	/**
	 * Sets the importe seguro vida.
	 *
	 * @param importeSeguroVida the importeSeguroVida to set
	 */
	public void setImporteSeguroVida(BigDecimal importeSeguroVida) {
		this.importeSeguroVida = importeSeguroVida;
	}

	/**
	 * Gets the importe seguro del bien.
	 *
	 * @return the importeSeguroDelBien
	 */
	public BigDecimal getImporteSeguroDelBien() {
		return importeSeguroDelBien;
	}

	/**
	 * Sets the importe seguro del bien.
	 *
	 * @param importeSeguroDelBien the importeSeguroDelBien to set
	 */
	public void setImporteSeguroDelBien(BigDecimal importeSeguroDelBien) {
		this.importeSeguroDelBien = importeSeguroDelBien;
	}

	/**
	 * Gets the importe IVA.
	 *
	 * @return the importeIVA
	 */
	public BigDecimal getImporteIVA() {
		return importeIVA;
	}

	/**
	 * Sets the importe IVA.
	 *
	 * @param importeIVA the importeIVA to set
	 */
	public void setImporteIVA(BigDecimal importeIVA) {
		this.importeIVA = importeIVA;
	}

	/**
	 * Gets the importe IVA adicional.
	 *
	 * @return the importeIVAAdicional
	 */
	public BigDecimal getImporteIVAAdicional() {
		return importeIVAAdicional;
	}

	/**
	 * Sets the importe IVA adicional.
	 *
	 * @param importeIVAAdicional the importeIVAAdicional to set
	 */
	public void setImporteIVAAdicional(BigDecimal importeIVAAdicional) {
		this.importeIVAAdicional = importeIVAAdicional;
	}

	/**
	 * Gets the importe endeudamiento.
	 *
	 * @return the importeEndeudamiento
	 */
	public BigDecimal getImporteEndeudamiento() {
		return importeEndeudamiento;
	}

	/**
	 * Sets the importe endeudamiento.
	 *
	 * @param importeEndeudamiento the importeEndeudamiento to set
	 */
	public void setImporteEndeudamiento(BigDecimal importeEndeudamiento) {
		this.importeEndeudamiento = importeEndeudamiento;
	}

	/**
	 * Gets the ingresos brutos.
	 *
	 * @return the ingresosBrutos
	 */
	public BigDecimal getIngresosBrutos() {
		return ingresosBrutos;
	}

	/**
	 * Sets the ingresos brutos.
	 *
	 * @param ingresosBrutos the ingresosBrutos to set
	 */
	public void setIngresosBrutos(BigDecimal ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	/**
	 * Gets the otros impuestos.
	 *
	 * @return the otrosImpuestos
	 */
	public BigDecimal getOtrosImpuestos() {
		return otrosImpuestos;
	}

	/**
	 * Sets the otros impuestos.
	 *
	 * @param otrosImpuestos the otrosImpuestos to set
	 */
	public void setOtrosImpuestos(BigDecimal otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}

	/**
	 * Gets the importe total cuota.
	 *
	 * @return the importeTotalCuota
	 */
	public BigDecimal getImporteTotalCuota() {
		return importeTotalCuota;
	}

	/**
	 * Sets the importe total cuota.
	 *
	 * @param importeTotalCuota the importeTotalCuota to set
	 */
	public void setImporteTotalCuota(BigDecimal importeTotalCuota) {
		this.importeTotalCuota = importeTotalCuota;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public DivisaEnum getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa the divisa to set
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the saldo previo.
	 *
	 * @return the saldoPrevio
	 */
	public BigDecimal getSaldoPrevio() {
		return saldoPrevio;
	}

	/**
	 * Sets the saldo previo.
	 *
	 * @param saldoPrevio the saldoPrevio to set
	 */
	public void setSaldoPrevio(BigDecimal saldoPrevio) {
		this.saldoPrevio = saldoPrevio;
	}

	/**
	 * Gets the factor index.
	 *
	 * @return the factorIndex
	 */
	public String getFactorIndex() {
		return factorIndex;
	}

	/**
	 * Sets the factor index.
	 *
	 * @param factorIndex the factorIndex to set
	 */
	public void setFactorIndex(String factorIndex) {
		this.factorIndex = factorIndex;
	}

	/**
	 * Gets the importe ajussal.
	 *
	 * @return the importeAjussal
	 */
	public BigDecimal getImporteAjussal() {
		return importeAjussal;
	}

	/**
	 * Sets the importe ajussal.
	 *
	 * @param importeAjussal the importeAjussal to set
	 */
	public void setImporteAjussal(BigDecimal importeAjussal) {
		this.importeAjussal = importeAjussal;
	}

	/**
	 * Gets the importe ajuscap.
	 *
	 * @return the importeAjuscap
	 */
	public BigDecimal getImporteAjuscap() {
		return importeAjuscap;
	}

	/**
	 * Sets the importe ajuscap.
	 *
	 * @param importeAjuscap the importeAjuscap to set
	 */
	public void setImporteAjuscap(BigDecimal importeAjuscap) {
		this.importeAjuscap = importeAjuscap;
	}

	/**
	 * Gets the importe ajuste capmor.
	 *
	 * @return the importeAjusteCapmor
	 */
	public BigDecimal getImporteAjusteCapmor() {
		return importeAjusteCapmor;
	}

	/**
	 * Sets the importe ajuste capmor.
	 *
	 * @param importeAjusteCapmor the importeAjusteCapmor to set
	 */
	public void setImporteAjusteCapmor(BigDecimal importeAjusteCapmor) {
		this.importeAjusteCapmor = importeAjusteCapmor;
	}

	/**
	 * Gets the factor capmor.
	 *
	 * @return the factorCapmor
	 */
	public String getFactorCapmor() {
		return factorCapmor;
	}

	/**
	 * Sets the factor capmor.
	 *
	 * @param factorCapmor the factorCapmor to set
	 */
	public void setFactorCapmor(String factorCapmor) {
		this.factorCapmor = factorCapmor;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * Gets the rendicion seguro vida.
	 *
	 * @return the rendicionSeguroVida
	 */
	public String getRendicionSeguroVida() {
		return rendicionSeguroVida;
	}

	/**
	 * Sets the rendicion seguro vida.
	 *
	 * @param rendicionSeguroVida the rendicionSeguroVida to set
	 */
	public void setRendicionSeguroVida(String rendicionSeguroVida) {
		this.rendicionSeguroVida = rendicionSeguroVida;
	}

	/**
	 * Gets the tasa anual efectiva.
	 *
	 * @return the tasaAnualEfectiva
	 */
	public BigDecimal getTasaAnualEfectiva() {
		return tasaAnualEfectiva;
	}

	/**
	 * Sets the tasa anual efectiva.
	 *
	 * @param tasaAnualEfectiva the tasaAnualEfectiva to set
	 */
	public void setTasaAnualEfectiva(BigDecimal tasaAnualEfectiva) {
		this.tasaAnualEfectiva = tasaAnualEfectiva;
	}

	/**
	 * Gets the costo financiero total.
	 *
	 * @return the costoFinancieroTotal
	 */
	public BigDecimal getCostoFinancieroTotal() {
		return costoFinancieroTotal;
	}

	/**
	 * Sets the costo financiero total.
	 *
	 * @param costoFinancieroTotal the costoFinancieroTotal to set
	 */
	public void setCostoFinancieroTotal(BigDecimal costoFinancieroTotal) {
		this.costoFinancieroTotal = costoFinancieroTotal;
	}

	/**
	 * Gets the clase cuenta.
	 *
	 * @return the claseCuenta
	 */
	public String getClaseCuenta() {
		return claseCuenta;
	}

	/**
	 * Sets the clase cuenta.
	 *
	 * @param claseCuenta the claseCuenta to set
	 */
	public void setClaseCuenta(String claseCuenta) {
		this.claseCuenta = claseCuenta;
	}

	/**
	 * Gets the numero cuenta producto.
	 *
	 * @return the numeroCuentaProducto
	 */
	public String getNumeroCuentaProducto() {
		return numeroCuentaProducto;
	}

	/**
	 * Sets the numero cuenta producto.
	 *
	 * @param numeroCuentaProducto the numeroCuentaProducto to set
	 */
	public void setNumeroCuentaProducto(String numeroCuentaProducto) {
		this.numeroCuentaProducto = numeroCuentaProducto;
	}

	/**
	 * Gets the importe total cuota cambio.
	 *
	 * @return the importeTotalCuotaCambio
	 */
	public String getImporteTotalCuotaCambio() {
		return importeTotalCuotaCambio;
	}

	/**
	 * Sets the importe total cuota cambio.
	 *
	 * @param importeTotalCuotaCambio the importeTotalCuotaCambio to set
	 */
	public void setImporteTotalCuotaCambio(String importeTotalCuotaCambio) {
		this.importeTotalCuotaCambio = importeTotalCuotaCambio;
	}

	/**
	 * Gets the costo financiero total sin impuestos.
	 *
	 * @return the costoFinancieroTotalSinImpuestos
	 */
	public BigDecimal getCostoFinancieroTotalSinImpuestos() {
		return costoFinancieroTotalSinImpuestos;
	}

	/**
	 * Sets the costo financiero total sin impuestos.
	 *
	 * @param costoFinancieroTotalSinImpuestos the costoFinancieroTotalSinImpuestos
	 *                                         to set
	 */
	public void setCostoFinancieroTotalSinImpuestos(BigDecimal costoFinancieroTotalSinImpuestos) {
		this.costoFinancieroTotalSinImpuestos = costoFinancieroTotalSinImpuestos;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the tipo prestamo enum.
	 *
	 * @return the tipo prestamo enum
	 */
	public TipoPrestamoEnum getTipoPrestamoEnum() {
		return tipoPrestamoEnum;
	}

	/**
	 * Sets the tipo prestamo enum.
	 *
	 * @param tipoPrestamoEnum the new tipo prestamo enum
	 */
	public void setTipoPrestamoEnum(TipoPrestamoEnum tipoPrestamoEnum) {
		this.tipoPrestamoEnum = tipoPrestamoEnum;
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
	 * @param alias the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the cta relacionada.
	 *
	 * @return the ctaRelacionada. Pos 35.
	 */
	public String getCtaRelacionada() {
		return ctaRelacionada;
	}

	/**
	 * Sets the cta relacionada.
	 *
	 * @param ctaRelacionada the ctaRelacionada to set . Pos 35.
	 */
	public void setCtaRelacionada(String ctaRelacionada) {
		this.ctaRelacionada = ctaRelacionada;
	}

	/**
	 * Gets the fec liq recibo.
	 *
	 * @return the fecLiqRecibo . Pos 36.
	 */
	public Date getFecLiqRecibo() {
		return fecLiqRecibo;
	}

	/**
	 * Sets the fec liq recibo.
	 *
	 * @param fecLiqRecibo the fecLiqRecibo to set. Pos 36.
	 */
	public void setFecLiqRecibo(Date fecLiqRecibo) {
		this.fecLiqRecibo = fecLiqRecibo;
	}

	/**
	 * Gets the nro recibo.
	 *
	 * @return the nroRecibo. Pos 37.
	 */
	public String getNroRecibo() {
		return nroRecibo;
	}

	/**
	 * Sets the nro recibo.
	 *
	 * @param nroRecibo the nroRecibo to set. Pos 37.
	 */
	public void setNroRecibo(String nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	/**
	 * Gets the nro exp.
	 *
	 * @return the nroExp. unidad de exposicion. Pos 38.
	 */
	public String getNroExp() {
		return nroExp;
	}

	/**
	 * Sets the nro exp.
	 *
	 * @param nroExp the nroExp to set. unidad de exposicion. Pos 38.
	 */
	public void setNroExp(String nroExp) {
		this.nroExp = nroExp;
	}

	/**
	 * Gets the cotiz cambio.
	 *
	 * @return the cotizCambio. cotiz de cambio uni. Pos 39.
	 */
	public BigDecimal getCotizCambio() {
		return cotizCambio;
	}

	/**
	 * Sets the cotiz cambio.
	 *
	 * @param cotizCambio the cotizCambio to set cotiz de cambio uni. Pos 39.
	 */
	public void setCotizCambio(BigDecimal cotizCambio) {
		this.cotizCambio = cotizCambio;
	}

	/**
	 * Gets the fec cotiz.
	 *
	 * @return the fecCotiz fecha cotizacion Pos 40.
	 */
	public Date getFecCotiz() {
		return fecCotiz;
	}

	/**
	 * Sets the fec cotiz.
	 *
	 * @param fecCotiz the fecCotiz to set fecha cotizacion Pos 40.
	 */
	public void setFecCotiz(Date fecCotiz) {
		this.fecCotiz = fecCotiz;
	}

	/**
	 * Gets the capital pend.
	 *
	 * @return the capitalPend capital pendiente Pos41.
	 */
	public BigDecimal getCapitalPend() {
		return capitalPend;
	}

	/**
	 * Sets the capital pend.
	 *
	 * @param capitalPend the capitalPend to set capital pendiente Pos41.
	 */
	public void setCapitalPend(BigDecimal capitalPend) {
		this.capitalPend = capitalPend;
	}

	/**
	 * Gets the intereses pend.
	 *
	 * @return the interesesPend intereses pendientes. Pos 42.
	 */
	public BigDecimal getInteresesPend() {
		return interesesPend;
	}

	/**
	 * Sets the intereses pend.
	 *
	 * @param interesesPend the interesesPend to set intereses pendientes. Pos 42.
	 */
	public void setInteresesPend(BigDecimal interesesPend) {
		this.interesesPend = interesesPend;
	}

	/**
	 * Gets the comisiones pend.
	 *
	 * @return the comisionesPend comisiones pendientes. Pos 43.
	 */
	public BigDecimal getComisionesPend() {
		return comisionesPend;
	}

	/**
	 * Sets the comisiones pend.
	 *
	 * @param comisionesPend the comisionesPend to set comisiones pendientes. Pos
	 *                       43.
	 */
	public void setComisionesPend(BigDecimal comisionesPend) {
		this.comisionesPend = comisionesPend;
	}

	/**
	 * Gets the gastos pend.
	 *
	 * @return the gastosPend gastos pendientes. Pos 44.
	 */
	public BigDecimal getGastosPend() {
		return gastosPend;
	}

	/**
	 * Sets the gastos pend.
	 *
	 * @param gastosPend the gastosPend to set gastos pendientes. Pos 44.
	 */
	public void setGastosPend(BigDecimal gastosPend) {
		this.gastosPend = gastosPend;
	}

	/**
	 * Gets the seguros pend.
	 *
	 * @return the segurosPend seguros pendientes. Pos 45.
	 */
	public BigDecimal getSegurosPend() {
		return segurosPend;
	}

	/**
	 * Sets the seguros pend.
	 *
	 * @param segurosPend the segurosPend to set seguros pendientes. Pos 45.
	 */
	public void setSegurosPend(BigDecimal segurosPend) {
		this.segurosPend = segurosPend;
	}

	/**
	 * Gets the impuestos pend.
	 *
	 * @return the impuestosPend impuestos pendientes. Pos 46.
	 */
	public BigDecimal getImpuestosPend() {
		return impuestosPend;
	}

	/**
	 * Sets the impuestos pend.
	 *
	 * @param impuestosPend the impuestosPend to set impuestos pendientes. Pos 46.
	 */
	public void setImpuestosPend(BigDecimal impuestosPend) {
		this.impuestosPend = impuestosPend;
	}

	/**
	 * Gets the cobranza ext.
	 *
	 * @return the cobranzaExt Cobranza externa pendiente. Pos 47.
	 */
	public BigDecimal getCobranzaExt() {
		return cobranzaExt;
	}

	/**
	 * Sets the cobranza ext.
	 *
	 * @param cobranzaExt the cobranzaExt to set Cobranza externa pendiente. Pos 47.
	 */
	public void setCobranzaExt(BigDecimal cobranzaExt) {
		this.cobranzaExt = cobranzaExt;
	}

	/**
	 * Gets the int mora pend.
	 *
	 * @return the intMoraPend Intereses mora pendiente. Pos 48.
	 */
	public BigDecimal getIntMoraPend() {
		return intMoraPend;
	}

	/**
	 * Sets the int mora pend.
	 *
	 * @param intMoraPend the intMoraPend to set Intereses mora pendiente. Pos 48.
	 */
	public void setIntMoraPend(BigDecimal intMoraPend) {
		this.intMoraPend = intMoraPend;
	}

	/**
	 * Gets the int compens pend.
	 *
	 * @return the intCompensPend Intereses compens. pendiente. Pos 49.
	 */
	public BigDecimal getIntCompensPend() {
		return intCompensPend;
	}

	/**
	 * Sets the int compens pend.
	 *
	 * @param intCompensPend the intCompensPend to set Intereses compens. pendiente.
	 *                       Pos 49.
	 */
	public void setIntCompensPend(BigDecimal intCompensPend) {
		this.intCompensPend = intCompensPend;
	}

	/**
	 * Gets the seguro vida.
	 *
	 * @return the seguroVida seguro vida. Pos 50
	 */
	public BigDecimal getSeguroVida() {
		return seguroVida;
	}

	/**
	 * Sets the seguro vida.
	 *
	 * @param seguroVida the seguroVida to set seguro vida. Pos 50
	 */
	public void setSeguroVida(BigDecimal seguroVida) {
		this.seguroVida = seguroVida;
	}

	/**
	 * Gets the seguro bien.
	 *
	 * @return the seguroBien Seguro vida. Pos 51
	 */
	public BigDecimal getSeguroBien() {
		return seguroBien;
	}

	/**
	 * Sets the seguro bien.
	 *
	 * @param seguroBien the seguroBien to set Seguro vida. Pos 51
	 */
	public void setSeguroBien(BigDecimal seguroBien) {
		this.seguroBien = seguroBien;
	}

	/**
	 * Gets the iva 1.
	 *
	 * @return the iva1 IVA1 Pos 52.
	 */
	public BigDecimal getIva1() {
		return iva1;
	}

	/**
	 * Sets the iva 1.
	 *
	 * @param iva1 the iva1 to set IVA1 Pos 52.
	 */
	public void setIva1(BigDecimal iva1) {
		this.iva1 = iva1;
	}

	/**
	 * Gets the iva 2.
	 *
	 * @return the iva2 IVA1 Pos 53.
	 */
	public BigDecimal getIva2() {
		return iva2;
	}

	/**
	 * Sets the iva 2.
	 *
	 * @param iva2 the iva2 to set IVA1 Pos 53.
	 */
	public void setIva2(BigDecimal iva2) {
		this.iva2 = iva2;
	}

	/**
	 * Gets the imp endeudamiento.
	 *
	 * @return the impEndeudamiento Impuesto al endeudamiente. Pos 54.
	 */
	public BigDecimal getImpEndeudamiento() {
		return impEndeudamiento;
	}

	/**
	 * Sets the imp endeudamiento.
	 *
	 * @param impEndeudamiento the impEndeudamiento to set Impuesto al
	 *                         endeudamiente. Pos 54.
	 */
	public void setImpEndeudamiento(BigDecimal impEndeudamiento) {
		this.impEndeudamiento = impEndeudamiento;
	}

	/**
	 * Gets the ingresos brut.
	 *
	 * @return the ingresosBrut Ingresos brutos. Pos 55.
	 */
	public BigDecimal getIngresosBrut() {
		return ingresosBrut;
	}

	/**
	 * Sets the ingresos brut.
	 *
	 * @param ingresosBrut the ingresosBrut to set Ingresos brutos. Pos 55.
	 */
	public void setIngresosBrut(BigDecimal ingresosBrut) {
		this.ingresosBrut = ingresosBrut;
	}

	/**
	 * Gets the imp sin IVA.
	 *
	 * @return the impSinIVA Impuesto sin IVA. Pos 56.
	 */
	public BigDecimal getImpSinIVA() {
		return impSinIVA;
	}

	/**
	 * Sets the imp sin IVA.
	 *
	 * @param impSinIVA the impSinIVA to set Impuesto sin IVA. Pos 56.
	 */
	public void setImpSinIVA(BigDecimal impSinIVA) {
		this.impSinIVA = impSinIVA;
	}

	/**
	 * Gets the importe total.
	 *
	 * @return the importeTotal Importe total. Pos 57.
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 *
	 * @param importeTotal the importeTotal to set Importe total. Pos 57.
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Gets the sal previo.
	 *
	 * @return the salPrevio Saldo Previo. Pos 58.
	 */
	public BigDecimal getSalPrevio() {
		return salPrevio;
	}

	/**
	 * Sets the sal previo.
	 *
	 * @param salPrevio the salPrevio to set Saldo Previo. Pos 58.
	 */
	public void setSalPrevio(BigDecimal salPrevio) {
		this.salPrevio = salPrevio;
	}

	/**
	 * Gets the ajuste saldo.
	 *
	 * @return the ajusteSaldo Ajuste Saldo Pos 59.
	 */
	public BigDecimal getAjusteSaldo() {
		return ajusteSaldo;
	}

	/**
	 * Sets the ajuste saldo.
	 *
	 * @param ajusteSaldo the ajusteSaldo to set Ajuste Saldo Pos 59.
	 */
	public void setAjusteSaldo(BigDecimal ajusteSaldo) {
		this.ajusteSaldo = ajusteSaldo;
	}

	/**
	 * Gets the ajuste cuota cap.
	 *
	 * @return the ajusteCuotaCap Ajuste Cuota capital. Pos 60.
	 */
	public BigDecimal getAjusteCuotaCap() {
		return ajusteCuotaCap;
	}

	/**
	 * Sets the ajuste cuota cap.
	 *
	 * @param ajusteCuotaCap the ajusteCuotaCap to set Ajuste Cuota capital. Pos 60.
	 */
	public void setAjusteCuotaCap(BigDecimal ajusteCuotaCap) {
		this.ajusteCuotaCap = ajusteCuotaCap;
	}

	/**
	 * Gets the ajuste cuota mora.
	 *
	 * @return the ajusteCuotaMora Ajuste Mora. Pos 61.
	 */
	public BigDecimal getAjusteCuotaMora() {
		return ajusteCuotaMora;
	}

	/**
	 * Sets the ajuste cuota mora.
	 *
	 * @param ajusteCuotaMora the ajusteCuotaMora to set Ajuste Mora. Pos 61.
	 */
	public void setAjusteCuotaMora(BigDecimal ajusteCuotaMora) {
		this.ajusteCuotaMora = ajusteCuotaMora;
	}

	/**
	 * Gets the importe rendir.
	 *
	 * @return the importeRendir Importe a Rendir. Pos 62.
	 */
	public BigDecimal getImporteRendir() {
		return importeRendir;
	}

	/**
	 * Sets the importe rendir.
	 *
	 * @param importeRendir the importeRendir to set Importe a Rendir. Pos 62.
	 */
	public void setImporteRendir(BigDecimal importeRendir) {
		this.importeRendir = importeRendir;
	}

	/**
	 * Checks if is informe coeficiente no disponible.
	 *
	 * @return true, if is informe coeficiente no disponible
	 */
	public boolean isInformeCoeficienteNoDisponible() {
		return informeCoeficienteNoDisponible;
	}

	/**
	 * Sets the informe coeficiente no disponible.
	 *
	 * @param informeCoeficienteNoDisponible the new informe coeficiente no
	 *                                       disponible
	 */
	public void setInformeCoeficienteNoDisponible(boolean informeCoeficienteNoDisponible) {
		this.informeCoeficienteNoDisponible = informeCoeficienteNoDisponible;
	}

	/**
	 * Gets the checks if is cuota vencida.
	 *
	 * @return the checks if is cuota vencida
	 */
	public Boolean getIsCuotaVencida() {
		return DateTimeComparator.getDateOnlyInstance().compare(this.vencimientoCuota,
				DateUtils.truncate(Calendar.getInstance().getTime(), java.util.Calendar.DAY_OF_MONTH)) == -1;
	}

	public Boolean getIsPrestamoNormativo() {
		return isPrestamoNormativo;
	}

	public void setIsPrestamoNormativo(Boolean isPrestamoNormativo) {
		this.isPrestamoNormativo = isPrestamoNormativo;
	}

	public String getNumeroPrestamoViejo() {
		return numeroPrestamoViejo;
	}

	public void setNumeroPrestamoViejo(String numeroPrestamoViejo) {
		this.numeroPrestamoViejo = numeroPrestamoViejo;
	}

	public PrestamoNormativoEnum getCodigoPrestamoNormativo() {
		return codigoPrestamoNormativo;
	}

	public void setCodigoPrestamoNormativo(PrestamoNormativoEnum codigoPrestamoNormativo) {
		this.codigoPrestamoNormativo = codigoPrestamoNormativo;
	}

	public String getNumeroReciboRefinanciado() {
		return numeroReciboRefinanciado ;
	}

	public void setNumeroReciboRefinanciado(String numeroReciboRefinanciado) {
		this.numeroReciboRefinanciado = numeroReciboRefinanciado;
	}

}
