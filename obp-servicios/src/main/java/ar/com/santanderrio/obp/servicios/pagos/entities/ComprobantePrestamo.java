/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class ComprobantePrestamo.
 */
public class ComprobantePrestamo extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The interviniente. */
	private Interviniente interviniente;

	/** The cuenta. */
	private Cuenta cuenta;

	/** The codigo retorno. */
	private String codigoRetorno;

	/** The importe debito. */
	private BigDecimal importeDebito;

	/** The cotizacion prestamo. */
	private BigDecimal cotizacionPrestamo;

	/** The numero recibo. */
	private String numeroRecibo;

	/** The vencimiento recibo. */
	private String vencimientoRecibo;

	/** The importe total recibo. */
	private BigDecimal importeTotalRecibo;

	/** The codigo moneda. */
	private String codigoMoneda;

	/** The tasa prestamo. */
	private BigDecimal tasaPrestamo;

	/** The importe amortizacion. */
	private BigDecimal importeAmortizacion;

	/** The importe intereses. */
	private BigDecimal importeIntereses;

	/** The otros impuestos. */
	private BigDecimal otrosImpuestos;

	/** The importe seguro vida. */
	private BigDecimal importeSeguroVida;

	/** The importe punitorios. */
	private BigDecimal importePunitorios;

	/** The importe complementario. */
	private BigDecimal importeComplementario;

	/** The importe IVA. */
	private BigDecimal importeIVA;

	/** The importe IVA adicional. */
	private BigDecimal importeIVAAdicional;

	/** The importe seguro bien. */
	private BigDecimal importeSeguroBien;

	/** The gastos. */
	private BigDecimal gastos;

	/** The comisiones. */
	private BigDecimal comisiones;

	/** The importe total seguros. */
	private BigDecimal importeTotalSeguros;

	/** The saldo capital. */
	private BigDecimal saldoCapital;

	/** The impuesto end final. */
	private BigDecimal impuestoEndFinal;

	/** The ingresos brutos. */
	private BigDecimal ingresosBrutos;

	/** The nio. */
	private String nio;

	/** The cod IVA. */
	private String codIVA;

	/** The descripcion IVA. */
	private String descripcionIVA;

	/** The leyenda 1. */
	private String leyenda1;

	/** The leyenda 2. */
	private String leyenda2;

	/** The tea. */
	private BigDecimal tea;

	/** The saldo prev. */
	private BigDecimal saldoPrev;

	/** The imp ajussal. */
	private BigDecimal impAjussal;

	/** The factor index. */
	private String factorIndex;

	/** The imp ajus capmor. */
	private BigDecimal impAjusCapmor;

	/** The imp ajus cap. */
	private BigDecimal impAjusCap;

	/** The factor capmor. */
	private BigDecimal factorCapmor;

	/** The coeifici index. */
	private String coeificiIndex;

	/** The rendicion cia seg vida. */
	private BigDecimal rendicionCiaSegVida;

	/** The cftna. */
	private BigDecimal cftna;

	/** The importe total impuestos. */
	private BigDecimal importeTotalImpuestos;

	/** The importa cobranza externa. */
	private BigDecimal importaCobranzaExterna;

	/** The cftna sin imp. */
	private BigDecimal cftnaSinImp;

	/** The ccc. */
	private String ccc;

	/** The fecha liquidacion. */
	private Date fechaLiquidacion;

	/** The num recibo. */
	private String numRecibo;

	/** The unidad exposicion. */
	private String unidadExposicion;

	/** The cot cambio uni expo. */
	private BigDecimal cotCambioUniExpo;

	/** The Fec cotizacion. */
	private Date FecCotizacion;

	/** The imp capital. */
	private BigDecimal impCapital;

	/** The imp intereses. */
	private BigDecimal impIntereses;

	/** The imp comision. */
	private BigDecimal impComision;

	/** The imp gastos. */
	private BigDecimal impGastos;

	/** The imp seguros. */
	private BigDecimal impSeguros;

	/** The imp impuestos. */
	private BigDecimal impImpuestos;

	/** The imp cobex. */
	private BigDecimal impCobex;

	/** The imp mora. */
	private BigDecimal impMora;

	/** The imp compensatori. */
	private BigDecimal impCompensatori;

	/** The seguro vida. */
	private BigDecimal seguroVida;

	/** The seguro bien. */
	private BigDecimal seguroBien;

	/** The iva 1. */
	private BigDecimal iva1;

	/** The iva 2. */
	private BigDecimal iva2;

	/** The impuesto endeudamiento. */
	private BigDecimal impuestoEndeudamiento;

	/** The ingr brutos. */
	private BigDecimal ingrBrutos;

	/** The importe sin IVA. */
	private BigDecimal importeSinIVA;

	/** The importe total. */
	private BigDecimal importeTotal;

	/** The saldo previo. */
	private BigDecimal saldoPrevio;

	/** The ajuste saldo. */
	private BigDecimal ajusteSaldo;

	/** The ajuste capital. */
	private BigDecimal ajusteCapital;

	/** The ajuste capital mora. */
	private BigDecimal ajusteCapitalMora;

	/** The importe rendir CIA. */
	private BigDecimal importeRendirCIA;

	/** The otros seguros. */
	private BigDecimal otrosSeguros;

	/**
	 * Gets the interviniente.
	 *
	 * @return the interviniente
	 */
	public Interviniente getInterviniente() {
		return interviniente;
	}

	/**
	 * Sets the interviniente.
	 *
	 * @param interviniente
	 *            the new interviniente
	 */
	public void setInterviniente(Interviniente interviniente) {
		this.interviniente = interviniente;
	}

	/**
	 * Gets the codigo retorno.
	 *
	 * @return the codigo retorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Sets the codigo retorno.
	 *
	 * @param codigoRetorno
	 *            the new codigo retorno
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Gets the importe debito.
	 *
	 * @return the importe debito
	 */
	public BigDecimal getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the new importe debito
	 */
	public void setImporteDebito(BigDecimal importeDebito) {
		this.importeDebito = importeDebito;
	}

	/**
	 * Gets the cotizacion prestamo.
	 *
	 * @return the cotizacion prestamo
	 */
	public BigDecimal getCotizacionPrestamo() {
		return cotizacionPrestamo;
	}

	/**
	 * Sets the cotizacion prestamo.
	 *
	 * @param cotizacionPrestamo
	 *            the new cotizacion prestamo
	 */
	public void setCotizacionPrestamo(BigDecimal cotizacionPrestamo) {
		this.cotizacionPrestamo = cotizacionPrestamo;
	}

	/**
	 * Gets the numero recibo.
	 *
	 * @return the numero recibo
	 */
	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	/**
	 * Sets the numero recibo.
	 *
	 * @param numeroRecibo
	 *            the new numero recibo
	 */
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	/**
	 * Gets the vencimiento recibo.
	 *
	 * @return the vencimiento recibo
	 */
	public String getVencimientoRecibo() {
		return vencimientoRecibo;
	}

	/**
	 * Sets the vencimiento recibo.
	 *
	 * @param vencimientoRecibo
	 *            the new vencimiento recibo
	 */
	public void setVencimientoRecibo(String vencimientoRecibo) {
		this.vencimientoRecibo = vencimientoRecibo;
	}

	/**
	 * Gets the importe total recibo.
	 *
	 * @return the importe total recibo
	 */
	public BigDecimal getImporteTotalRecibo() {
		return importeTotalRecibo;
	}

	/**
	 * Sets the importe total recibo.
	 *
	 * @param importeTotalRecibo
	 *            the new importe total recibo
	 */
	public void setImporteTotalRecibo(BigDecimal importeTotalRecibo) {
		this.importeTotalRecibo = importeTotalRecibo;
	}

	/**
	 * Gets the codigo moneda.
	 *
	 * @return the codigo moneda
	 */
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	/**
	 * Sets the codigo moneda.
	 *
	 * @param codigoMoneda
	 *            the new codigo moneda
	 */
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	/**
	 * Gets the tasa prestamo.
	 *
	 * @return the tasa prestamo
	 */
	public BigDecimal getTasaPrestamo() {
		return tasaPrestamo;
	}

	/**
	 * Sets the tasa prestamo.
	 *
	 * @param tasaPrestamo
	 *            the new tasa prestamo
	 */
	public void setTasaPrestamo(BigDecimal tasaPrestamo) {
		this.tasaPrestamo = tasaPrestamo;
	}

	/**
	 * Gets the importe amortizacion.
	 *
	 * @return the importe amortizacion
	 */
	public BigDecimal getImporteAmortizacion() {
		return importeAmortizacion;
	}

	/**
	 * Sets the importe amortizacion.
	 *
	 * @param importeAmortizacion
	 *            the new importe amortizacion
	 */
	public void setImporteAmortizacion(BigDecimal importeAmortizacion) {
		this.importeAmortizacion = importeAmortizacion;
	}

	/**
	 * Gets the importe intereses.
	 *
	 * @return the importe intereses
	 */
	public BigDecimal getImporteIntereses() {
		return importeIntereses;
	}

	/**
	 * Sets the importe intereses.
	 *
	 * @param importeIntereses
	 *            the new importe intereses
	 */
	public void setImporteIntereses(BigDecimal importeIntereses) {
		this.importeIntereses = importeIntereses;
	}

	/**
	 * Gets the otros impuestos.
	 *
	 * @return the otros impuestos
	 */
	public BigDecimal getOtrosImpuestos() {
		return otrosImpuestos;
	}

	/**
	 * Sets the otros impuestos.
	 *
	 * @param otrosImpuestos
	 *            the new otros impuestos
	 */
	public void setOtrosImpuestos(BigDecimal otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}

	/**
	 * Gets the importe seguro vida.
	 *
	 * @return the importe seguro vida
	 */
	public BigDecimal getImporteSeguroVida() {
		return importeSeguroVida;
	}

	/**
	 * Sets the importe seguro vida.
	 *
	 * @param importeSeguroVida
	 *            the new importe seguro vida
	 */
	public void setImporteSeguroVida(BigDecimal importeSeguroVida) {
		this.importeSeguroVida = importeSeguroVida;
	}

	/**
	 * Gets the importe punitorios.
	 *
	 * @return the importe punitorios
	 */
	public BigDecimal getImportePunitorios() {
		return importePunitorios;
	}

	/**
	 * Sets the importe punitorios.
	 *
	 * @param importePunitorios
	 *            the new importe punitorios
	 */
	public void setImportePunitorios(BigDecimal importePunitorios) {
		this.importePunitorios = importePunitorios;
	}

	/**
	 * Gets the importe complementario.
	 *
	 * @return the importe complementario
	 */
	public BigDecimal getImporteComplementario() {
		return importeComplementario;
	}

	/**
	 * Sets the importe complementario.
	 *
	 * @param importeComplementario
	 *            the new importe complementario
	 */
	public void setImporteComplementario(BigDecimal importeComplementario) {
		this.importeComplementario = importeComplementario;
	}

	/**
	 * Gets the importe IVA.
	 *
	 * @return the importe IVA
	 */
	public BigDecimal getImporteIVA() {
		return importeIVA;
	}

	/**
	 * Sets the importe IVA.
	 *
	 * @param importeIVA
	 *            the new importe IVA
	 */
	public void setImporteIVA(BigDecimal importeIVA) {
		this.importeIVA = importeIVA;
	}

	/**
	 * Gets the importe IVA adicional.
	 *
	 * @return the importe IVA adicional
	 */
	public BigDecimal getImporteIVAAdicional() {
		return importeIVAAdicional;
	}

	/**
	 * Sets the importe IVA adicional.
	 *
	 * @param importeIVAAdicional
	 *            the new importe IVA adicional
	 */
	public void setImporteIVAAdicional(BigDecimal importeIVAAdicional) {
		this.importeIVAAdicional = importeIVAAdicional;
	}

	/**
	 * Gets the importe seguro bien.
	 *
	 * @return the importe seguro bien
	 */
	public BigDecimal getImporteSeguroBien() {
		return importeSeguroBien;
	}

	/**
	 * Sets the importe seguro bien.
	 *
	 * @param importeSeguroBien
	 *            the new importe seguro bien
	 */
	public void setImporteSeguroBien(BigDecimal importeSeguroBien) {
		this.importeSeguroBien = importeSeguroBien;
	}

	/**
	 * Gets the gastos.
	 *
	 * @return the gastos
	 */
	public BigDecimal getGastos() {
		return gastos;
	}

	/**
	 * Sets the gastos.
	 *
	 * @param gastos
	 *            the new gastos
	 */
	public void setGastos(BigDecimal gastos) {
		this.gastos = gastos;
	}

	/**
	 * Gets the comisiones.
	 *
	 * @return the comisiones
	 */
	public BigDecimal getComisiones() {
		return comisiones;
	}

	/**
	 * Sets the comisiones.
	 *
	 * @param comisiones
	 *            the new comisiones
	 */
	public void setComisiones(BigDecimal comisiones) {
		this.comisiones = comisiones;
	}

	/**
	 * Gets the importe total seguros.
	 *
	 * @return the importe total seguros
	 */
	public BigDecimal getImporteTotalSeguros() {
		return importeTotalSeguros;
	}

	/**
	 * Sets the importe total seguros.
	 *
	 * @param importeTotalSeguros
	 *            the new importe total seguros
	 */
	public void setImporteTotalSeguros(BigDecimal importeTotalSeguros) {
		this.importeTotalSeguros = importeTotalSeguros;
	}

	/**
	 * Gets the saldo capital.
	 *
	 * @return the saldo capital
	 */
	public BigDecimal getSaldoCapital() {
		return saldoCapital;
	}

	/**
	 * Sets the saldo capital.
	 *
	 * @param saldoCapital
	 *            the new saldo capital
	 */
	public void setSaldoCapital(BigDecimal saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	/**
	 * Gets the impuesto end final.
	 *
	 * @return the impuesto end final
	 */
	public BigDecimal getImpuestoEndFinal() {
		return impuestoEndFinal;
	}

	/**
	 * Sets the impuesto end final.
	 *
	 * @param impuestoEndFinal
	 *            the new impuesto end final
	 */
	public void setImpuestoEndFinal(BigDecimal impuestoEndFinal) {
		this.impuestoEndFinal = impuestoEndFinal;
	}

	/**
	 * Gets the ingresos brutos.
	 *
	 * @return the ingresos brutos
	 */
	public BigDecimal getIngresosBrutos() {
		return ingresosBrutos;
	}

	/**
	 * Sets the ingresos brutos.
	 *
	 * @param ingresosBrutos
	 *            the new ingresos brutos
	 */
	public void setIngresosBrutos(BigDecimal ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	/**
	 * Gets the nio.
	 *
	 * @return the nio
	 */
	public String getNio() {
		return nio;
	}

	/**
	 * Sets the nio.
	 *
	 * @param nio
	 *            the new nio
	 */
	public void setNio(String nio) {
		this.nio = nio;
	}

	/**
	 * Gets the cod IVA.
	 *
	 * @return the cod IVA
	 */
	public String getCodIVA() {
		return codIVA;
	}

	/**
	 * Sets the cod IVA.
	 *
	 * @param codIVA
	 *            the new cod IVA
	 */
	public void setCodIVA(String codIVA) {
		this.codIVA = codIVA;
	}

	/**
	 * Gets the descripcion IVA.
	 *
	 * @return the descripcion IVA
	 */
	public String getDescripcionIVA() {
		return descripcionIVA;
	}

	/**
	 * Sets the descripcion IVA.
	 *
	 * @param descripcionIVA
	 *            the new descripcion IVA
	 */
	public void setDescripcionIVA(String descripcionIVA) {
		this.descripcionIVA = descripcionIVA;
	}

	/**
	 * Gets the leyenda 1.
	 *
	 * @return the leyenda 1
	 */
	public String getLeyenda1() {
		return leyenda1;
	}

	/**
	 * Sets the leyenda 1.
	 *
	 * @param leyenda1
	 *            the new leyenda 1
	 */
	public void setLeyenda1(String leyenda1) {
		this.leyenda1 = leyenda1;
	}

	/**
	 * Gets the leyenda 2.
	 *
	 * @return the leyenda 2
	 */
	public String getLeyenda2() {
		return leyenda2;
	}

	/**
	 * Sets the leyenda 2.
	 *
	 * @param leyenda2
	 *            the new leyenda 2
	 */
	public void setLeyenda2(String leyenda2) {
		this.leyenda2 = leyenda2;
	}

	/**
	 * Gets the tea.
	 *
	 * @return the tea
	 */
	public BigDecimal getTea() {
		return tea;
	}

	/**
	 * Sets the tea.
	 *
	 * @param tea
	 *            the new tea
	 */
	public void setTea(BigDecimal tea) {
		this.tea = tea;
	}

	/**
	 * Gets the saldo prev.
	 *
	 * @return the saldo prev
	 */
	public BigDecimal getSaldoPrev() {
		return saldoPrev;
	}

	/**
	 * Sets the saldo prev.
	 *
	 * @param saldoPrev
	 *            the new saldo prev
	 */
	public void setSaldoPrev(BigDecimal saldoPrev) {
		this.saldoPrev = saldoPrev;
	}

	/**
	 * Gets the imp ajussal.
	 *
	 * @return the imp ajussal
	 */
	public BigDecimal getImpAjussal() {
		return impAjussal;
	}

	/**
	 * Sets the imp ajussal.
	 *
	 * @param impAjussal
	 *            the new imp ajussal
	 */
	public void setImpAjussal(BigDecimal impAjussal) {
		this.impAjussal = impAjussal;
	}

	/**
	 * Gets the factor index.
	 *
	 * @return the factor index
	 */
	public String getFactorIndex() {
		return factorIndex;
	}

	/**
	 * Sets the factor index.
	 *
	 * @param factorIndex
	 *            the new factor index
	 */
	public void setFactorIndex(String factorIndex) {
		this.factorIndex = factorIndex;
	}

	/**
	 * Gets the imp ajus capmor.
	 *
	 * @return the imp ajus capmor
	 */
	public BigDecimal getImpAjusCapmor() {
		return impAjusCapmor;
	}

	/**
	 * Sets the imp ajus capmor.
	 *
	 * @param impAjusCapmor
	 *            the new imp ajus capmor
	 */
	public void setImpAjusCapmor(BigDecimal impAjusCapmor) {
		this.impAjusCapmor = impAjusCapmor;
	}

	/**
	 * Gets the imp ajus cap.
	 *
	 * @return the imp ajus cap
	 */
	public BigDecimal getImpAjusCap() {
		return impAjusCap;
	}

	/**
	 * Sets the imp ajus cap.
	 *
	 * @param impAjusCap
	 *            the new imp ajus cap
	 */
	public void setImpAjusCap(BigDecimal impAjusCap) {
		this.impAjusCap = impAjusCap;
	}

	/**
	 * Gets the factor capmor.
	 *
	 * @return the factor capmor
	 */
	public BigDecimal getFactorCapmor() {
		return factorCapmor;
	}

	/**
	 * Sets the factor capmor.
	 *
	 * @param factorCapmor
	 *            the new factor capmor
	 */
	public void setFactorCapmor(BigDecimal factorCapmor) {
		this.factorCapmor = factorCapmor;
	}

	/**
	 * Gets the coeifici index.
	 *
	 * @return the coeifici index
	 */
	public String getCoeificiIndex() {
		return coeificiIndex;
	}

	/**
	 * Sets the coeifici index.
	 *
	 * @param coeificiIndex
	 *            the new coeifici index
	 */
	public void setCoeificiIndex(String coeificiIndex) {
		this.coeificiIndex = coeificiIndex;
	}

	/**
	 * Gets the rendicion cia seg vida.
	 *
	 * @return the rendicion cia seg vida
	 */
	public BigDecimal getRendicionCiaSegVida() {
		return rendicionCiaSegVida;
	}

	/**
	 * Sets the rendicion cia seg vida.
	 *
	 * @param rendicionCiaSegVida
	 *            the new rendicion cia seg vida
	 */
	public void setRendicionCiaSegVida(BigDecimal rendicionCiaSegVida) {
		this.rendicionCiaSegVida = rendicionCiaSegVida;
	}

	/**
	 * Gets the cftna.
	 *
	 * @return the cftna
	 */
	public BigDecimal getCftna() {
		return cftna;
	}

	/**
	 * Sets the cftna.
	 *
	 * @param cftna
	 *            the new cftna
	 */
	public void setCftna(BigDecimal cftna) {
		this.cftna = cftna;
	}

	/**
	 * Gets the importe total impuestos.
	 *
	 * @return the importe total impuestos
	 */
	public BigDecimal getImporteTotalImpuestos() {
		return importeTotalImpuestos;
	}

	/**
	 * Sets the importe total impuestos.
	 *
	 * @param importeTotalImpuestos
	 *            the new importe total impuestos
	 */
	public void setImporteTotalImpuestos(BigDecimal importeTotalImpuestos) {
		this.importeTotalImpuestos = importeTotalImpuestos;
	}

	/**
	 * Gets the importa cobranza externa.
	 *
	 * @return the importa cobranza externa
	 */
	public BigDecimal getImportaCobranzaExterna() {
		return importaCobranzaExterna;
	}

	/**
	 * Sets the importa cobranza externa.
	 *
	 * @param importaCobranzaExterna
	 *            the new importa cobranza externa
	 */
	public void setImportaCobranzaExterna(BigDecimal importaCobranzaExterna) {
		this.importaCobranzaExterna = importaCobranzaExterna;
	}

	/**
	 * Gets the cftna sin imp.
	 *
	 * @return the cftna sin imp
	 */
	public BigDecimal getCftnaSinImp() {
		return cftnaSinImp;
	}

	/**
	 * Sets the cftna sin imp.
	 *
	 * @param cftnaSinImp
	 *            the new cftna sin imp
	 */
	public void setCftnaSinImp(BigDecimal cftnaSinImp) {
		this.cftnaSinImp = cftnaSinImp;
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
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	// campos agregados en version 150

	/**
	 * ENTIDAD+OFICINA+CUENTA PRESTAMO POS 42.
	 *
	 * @return the ccc
	 */
	public String getCcc() {
		return ccc;
	}

	/**
	 * ENTIDAD+OFICINA+CUENTA PRESTAMO POS 42.
	 *
	 * @param ccc
	 *            the ccc to set
	 */
	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	/**
	 * FECHA LIQUIDACION POS 43.
	 *
	 * @return the fechaLiquidacion
	 */
	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * FECHA LIQUIDACION. POS 43
	 * 
	 * @param fechaLiquidacion
	 *            the fechaLiquidacion to set
	 */
	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Numero recibo. Pos44
	 * 
	 * @return the numRecibo
	 */
	public String getNumRecibo() {
		return numRecibo;
	}

	/**
	 * Numero recibo. Pos 44
	 * 
	 * @param numRecibo
	 *            the numRecibo to set
	 */
	public void setNumRecibo(String numRecibo) {
		this.numRecibo = numRecibo;
	}

	/**
	 * Unidad Exposicion. Pos 45
	 * 
	 * @return the unidadExposicion
	 */
	public String getUnidadExposicion() {
		return unidadExposicion;
	}

	/**
	 * Unidad Exposicion. Pos 45
	 * 
	 * @param unidadExposicion
	 *            the unidadExposicion to set
	 */
	public void setUnidadExposicion(String unidadExposicion) {
		this.unidadExposicion = unidadExposicion;
	}

	/**
	 * COT. CAMBIO UNI EXPO. Pos 46
	 * 
	 * @return the cotCambioUniExpo
	 */
	public BigDecimal getCotCambioUniExpo() {
		return cotCambioUniExpo;
	}

	/**
	 * COT. CAMBIO UNI EXPO. Pos 46
	 * 
	 * @param cotCambioUniExpo
	 *            the cotCambioUniExpo to set
	 */
	public void setCotCambioUniExpo(BigDecimal cotCambioUniExpo) {
		this.cotCambioUniExpo = cotCambioUniExpo;
	}

	/**
	 * Fecha COTIZACION. Pos 47
	 * 
	 * @return the fecCotizacion
	 */
	public Date getFecCotizacion() {
		return FecCotizacion;
	}

	/**
	 * Fecha COTIZACION. Pos 47
	 * 
	 * @param fecCotizacion
	 *            the fecCotizacion to set
	 */
	public void setFecCotizacion(Date fecCotizacion) {
		FecCotizacion = fecCotizacion;
	}

	/**
	 * Importe Capital en UVIs. Pos 48
	 * 
	 * @return the impCapital
	 */
	public BigDecimal getImpCapital() {
		return impCapital;
	}

	/**
	 * Importe Capital en UVIs. Pos 48
	 * 
	 * @param impCapital
	 *            the impCapital to set
	 */
	public void setImpCapital(BigDecimal impCapital) {
		this.impCapital = impCapital;
	}

	/**
	 * Importe Intereses en UVIs. Pos 49
	 * 
	 * @return the impIntereses
	 */
	public BigDecimal getImpIntereses() {
		return impIntereses;
	}

	/**
	 * Importe Intereses en UVIs. Pos 49
	 * 
	 * @param impIntereses
	 *            the impIntereses to set
	 */
	public void setImpIntereses(BigDecimal impIntereses) {
		this.impIntereses = impIntereses;
	}

	/**
	 * Importe Comisiones en UVIs. Pos 50
	 * 
	 * @return the impComision
	 */
	public BigDecimal getImpComision() {
		return impComision;
	}

	/**
	 * Importe Comision en UVIs. Pos 50
	 *
	 * @param impComision
	 *            the new imp comision
	 */
	public void setImpComision(BigDecimal impComision) {
		this.impComision = impComision;
	}

	/**
	 * Importe Gastos en UVIs. Pos 51
	 * 
	 * @return the impGastos
	 */
	public BigDecimal getImpGastos() {
		return impGastos;
	}

	/**
	 * Importe Gastos en UVIs. Pos 51
	 * 
	 * @param impGastos
	 *            the impGastos to set
	 */
	public void setImpGastos(BigDecimal impGastos) {
		this.impGastos = impGastos;
	}

	/**
	 * Importe Seguros en UVIs. Pos 52
	 * 
	 * @return the impSeguros
	 */
	public BigDecimal getImpSeguros() {
		return impSeguros;
	}

	/**
	 * Importe Seguros en UVIs. Pos 52
	 * 
	 * @param impSeguros
	 *            the impSeguros to set
	 */
	public void setImpSeguros(BigDecimal impSeguros) {
		this.impSeguros = impSeguros;
	}

	/**
	 * Importe Impuestos en UVIs. Pos 53
	 * 
	 * @return the impImpuestos
	 */
	public BigDecimal getImpImpuestos() {
		return impImpuestos;
	}

	/**
	 * Importe Impuestos en UVIs. Pos 53
	 * 
	 * @param impImpuestos
	 *            the impImpuestos to set
	 */
	public void setImpImpuestos(BigDecimal impImpuestos) {
		this.impImpuestos = impImpuestos;
	}

	/**
	 * Importe COBEX en UVIs. Pos 54
	 * 
	 * @return the impCobex
	 */
	public BigDecimal getImpCobex() {
		return impCobex;
	}

	/**
	 * Importe COBEX en UVIs. Pos 54
	 * 
	 * @param impCobex
	 *            the impCobex to set
	 */
	public void setImpCobex(BigDecimal impCobex) {
		this.impCobex = impCobex;
	}

	/**
	 * Importe Mora en UVIs. Pos 55
	 * 
	 * @return the impMora
	 */
	public BigDecimal getImpMora() {
		return impMora;
	}

	/**
	 * Importe Mora en UVIs. Pos 55
	 * 
	 * @param impMora
	 *            the impMora to set
	 */
	public void setImpMora(BigDecimal impMora) {
		this.impMora = impMora;
	}

	/**
	 * Importe Compensatori en UVIs. Pos 56
	 * 
	 * @return the impCompensatori
	 */
	public BigDecimal getImpCompensatori() {
		return impCompensatori;
	}

	/**
	 * Importe Compensatori en UVIs. Pos 56
	 * 
	 * @param impCompensatori
	 *            the impCompensatori to set
	 */
	public void setImpCompensatori(BigDecimal impCompensatori) {
		this.impCompensatori = impCompensatori;
	}

	/**
	 * Seguro de Vida en UVIs. Pos 57
	 * 
	 * @return the seguroVida
	 */
	public BigDecimal getSeguroVida() {
		return seguroVida;
	}

	/**
	 * Seguro de Vida en UVIs. Pos 57
	 * 
	 * @param seguroVida
	 *            the seguroVida to set
	 */
	public void setSeguroVida(BigDecimal seguroVida) {
		this.seguroVida = seguroVida;
	}

	/**
	 * Seguro de Bien en UVIs. Pos 58
	 * 
	 * @return the seguroBien
	 */
	public BigDecimal getSeguroBien() {
		return seguroBien;
	}

	/**
	 * Seguro de Bien en UVIs. Pos 58
	 * 
	 * @param seguroBien
	 *            the seguroBien to set
	 */
	public void setSeguroBien(BigDecimal seguroBien) {
		this.seguroBien = seguroBien;
	}

	/**
	 * iva1 Pos 59.
	 *
	 * @return the iva1
	 */
	public BigDecimal getIva1() {
		return iva1;
	}

	/**
	 * iva1 Pos 59.
	 *
	 * @param iva1
	 *            the iva1 to set
	 */
	public void setIva1(BigDecimal iva1) {
		this.iva1 = iva1;
	}

	/**
	 * iva2 Pos 60.
	 *
	 * @return the iva2
	 */
	public BigDecimal getIva2() {
		return iva2;
	}

	/**
	 * iva2 Pos 60.
	 *
	 * @param iva2
	 *            the iva2 to set
	 */
	public void setIva2(BigDecimal iva2) {
		this.iva2 = iva2;
	}

	/**
	 * impuesto Endeudamiento Pos 61.
	 *
	 * @return the impuestoEndeudamiento
	 */
	public BigDecimal getImpuestoEndeudamiento() {
		return impuestoEndeudamiento;
	}

	/**
	 * impuesto Endeudamiento Pos 61.
	 *
	 * @param impuestoEndeudamiento
	 *            the impuestoEndeudamiento to set
	 */
	public void setImpuestoEndeudamiento(BigDecimal impuestoEndeudamiento) {
		this.impuestoEndeudamiento = impuestoEndeudamiento;
	}

	/**
	 * Ingresos Brutos Pos 62.
	 *
	 * @return the ingrBrutos
	 */
	public BigDecimal getIngrBrutos() {
		return ingrBrutos;
	}

	/**
	 * Ingresos Brutos Pos 62.
	 *
	 * @param ingrBrutos
	 *            the ingrBrutos to set
	 */
	public void setIngrBrutos(BigDecimal ingrBrutos) {
		this.ingrBrutos = ingrBrutos;
	}

	/**
	 * Importe sin iva Pos 63.
	 *
	 * @return the importeSinIVA
	 */
	public BigDecimal getImporteSinIVA() {
		return importeSinIVA;
	}

	/**
	 * Importe sin iva Pos 63.
	 *
	 * @param importeSinIVA
	 *            the importeSinIVA to set
	 */
	public void setImporteSinIVA(BigDecimal importeSinIVA) {
		this.importeSinIVA = importeSinIVA;
	}

	/**
	 * Importe Total Pos 64.
	 *
	 * @return the importeTotal
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Importe Total Pos 64.
	 *
	 * @param importeTotal
	 *            the importeTotal to set
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Saldo previo Pos 65.
	 *
	 * @return the saldoPrevio
	 */
	public BigDecimal getSaldoPrevio() {
		return saldoPrevio;
	}

	/**
	 * Saldo previo Pos 65.
	 *
	 * @param saldoPrevio
	 *            the saldoPrevio to set
	 */
	public void setSaldoPrevio(BigDecimal saldoPrevio) {
		this.saldoPrevio = saldoPrevio;
	}

	/**
	 * Ajuste Saldo Pos 66.
	 *
	 * @return the ajusteSaldo
	 */
	public BigDecimal getAjusteSaldo() {
		return ajusteSaldo;
	}

	/**
	 * Ajuste Saldo Pos 66.
	 *
	 * @param ajusteSaldo
	 *            the ajusteSaldo to set
	 */
	public void setAjusteSaldo(BigDecimal ajusteSaldo) {
		this.ajusteSaldo = ajusteSaldo;
	}

	/**
	 * Ajuste Capital Pos 67.
	 *
	 * @return the ajusteCapital
	 */
	public BigDecimal getAjusteCapital() {
		return ajusteCapital;
	}

	/**
	 * Ajuste Capital Pos 67.
	 *
	 * @param ajusteCapital
	 *            the ajusteCapital to set
	 */
	public void setAjusteCapital(BigDecimal ajusteCapital) {
		this.ajusteCapital = ajusteCapital;
	}

	/**
	 * Ajuste Capital Mora Pos 68.
	 *
	 * @return the ajusteCapitalMora
	 */
	public BigDecimal getAjusteCapitalMora() {
		return ajusteCapitalMora;
	}

	/**
	 * Ajuste Capital Mora Pos 68.
	 *
	 * @param ajusteCapitalMora
	 *            the ajusteCapitalMora to set
	 */
	public void setAjusteCapitalMora(BigDecimal ajusteCapitalMora) {
		this.ajusteCapitalMora = ajusteCapitalMora;
	}

	/**
	 * Importe rendir CIA Pos 69.
	 *
	 * @return the importeRendirCIA
	 */
	public BigDecimal getImporteRendirCIA() {
		return importeRendirCIA;
	}

	/**
	 * Importe rendir CIA Pos 69.
	 *
	 * @param importeRendirCIA
	 *            the importeRendirCIA to set
	 */
	public void setImporteRendirCIA(BigDecimal importeRendirCIA) {
		this.importeRendirCIA = importeRendirCIA;
	}

	/**
	 * Gets the otros seguros.
	 *
	 * @return the otros seguros
	 */
	public BigDecimal getOtrosSeguros() {
		return otrosSeguros;
	}

	/**
	 * Sets the otros seguros.
	 *
	 * @param otrosSeguros
	 *            the new otros seguros
	 */
	public void setOtrosSeguros(BigDecimal otrosSeguros) {
		this.otrosSeguros = otrosSeguros;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	public int hashCode() {
		return 0;
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
		return true;
	}
}
