/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * Entidad que representa un PF general.
 *
 * @author juan.pablo.picate
 */
public class PFGeneralEntity {

	/** The nro Certificado. */
	@Field
	private String nroCertificado;

	/** The entidad cuenta inversion. */
	@Field
	private String entidadCtaInversion;

	/** The centro cuenta inversion. */
	@Field
	private String centroCtaInversion;

	/** The cuenta inversion. */
	@Field
	private String ctaInversion;

	/** The nro secuencia. */
	@Field
	private String nroSecuencia;

	/** The secuencia renovacion. */
	@Field
	private String secuenciaRenovacion;

	/** The fecha alta. */
	@Field
	private String fechaAlta;

	/** The indicador estado. */
	@Field
	private String indicadorEstado;

	/** The tasa teorica 3 enteros, 5 decimales . */
	@Field
	private String tasaTeorica;

	/** The divisa. */
	@Field
	private String divisa;

	/** The descripcion divisa. */
	@Field
	private String descripcionDivisa;

	/** The saldo inicial PF. */
	@Field
	private String saldoInicialPF;

	/** The plazo. */
	@Field
	private String plazo;

	/** The descripcion periodo liquidacion. */
	@Field
	private String descripcionPeriodoLiquidacion;

	/** The tasa real 3 enteros, 5 decimales. */
	@Field
	private String tasaReal;

	/** The indicador capitaliza intereses. */
	@Field
	private String indicadorCapitalizaIntereses;

	/** The indicador renovacion. */
	@Field
	private String indicadorRenovacion;

	/** The indicador custodia. */
	@Field
	private String indicadorCustodia;

	/** The canal apertura. */
	@Field
	private String canalApertura;

	/** The indicador transferencia. */
	@Field
	private String indicadorTransferencia;

	/** The fecha vencimiento. */
	@Field
	private String fechaVencimiento;

	/** The intereses liquidados. */
	@Field
	private String interesLiquidados;

	/** The intereses pendientes de liquidar. */
	@Field
	private String interesesPendientesDeLiquidar;

	/** The indicador bloqueo. */
	@Field
	private String indicadorBloqueo;

	/** The impu reten sal. */
	@Field
	private String impuRetenSal;

	/** The impu pdte ret sal. */
	@Field
	private String impuPdteRetSal;

	/** The impu tot sal. */
	@Field
	private String impuTotSal;

	/** The fecha proxima liquidacion. */
	@Field
	private String fechaProximaLiquidacion;

	/** The divisa original. */
	@Field
	private String divisaOriginal;

	/** The capital ajustado 13 enteros, 2 decimales. */
	@Field
	private String capitalAjustado;

	/** The desc tipo plazo. */
	@Field
	private String descTipoPlazo;

	/** The codigo UR. */
	@Field
	private String codigoUR;

	/** The Entidad aso 1. */
	@Field
	private String entidadAso1;

	/** The centro cuenta credito. */
	@Field
	private String centroCuentaCredito;

	/** The cuenta credito altair. */
	@Field
	private String cuentaCreditoAltair;

	/** The entidad cuenta debito. */
	@Field
	private String entidadCuentaDebito;

	/** The centro cuenta debito. */
	@Field
	private String centroCuentaDebito;

	/** The cuenta debito altair. */
	@Field
	private String cuentaDebitoAltair;

	/** The ind aju alta 3 enteros, 5 decimales. */
	@Field
	private String indAjuAlta;

	/** The ind aju ult liq 3 enteros, 5 decimales. */
	@Field
	private String indAjuUltLiq;

	/** The sucursal radicacion certificado. */
	@Field
	private String sucursalRadicacionCertificado;

	/** The periodicidad Liquidacion. */
	@Field
	private String periodicidadLiquidacion;

	/** The indicador captaliza reajustes. */
	@Field
	private String indicadorCapitalizaReajustes;

	/** The descripcion impuesto 1. */
	@Field
	private String descrpicionImpuesto1;

	/** The monto moneda local 1 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaLocal1;

	/** The monto moneda extranjera 1 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaExtranjera1;

	/** The momento de cobro 1. */
	@Field
	private String momentoDeCobro1;

	/** The descripcion impuesto 2. */
	@Field
	private String descrpicionImpuesto2;

	/** The monto moneda local 1 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaLocal2;

	/** The monto moneda extranjera 2 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaExtranjera2;

	/** The momento de cobro 2. */
	@Field
	private String momentoDeCobro2;

	/** The descripcion impuesto 3. */
	@Field
	private String descrpicionImpuesto3;

	/** The monto moneda local 3 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaLocal3;

	/** The monto moneda extranjera 3 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaExtranjera3;

	/** The momento de cobro 3. */
	@Field
	private String momentoDeCobro3;

	/** The descripcion impuesto 4. */
	@Field
	private String descrpicionImpuesto4;

	/** The monto moneda local 4 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaLocal4;

	/** The monto moneda extranjera 4 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaExtranjera4;

	/** The momento de cobro 4. */
	@Field
	private String momentoDeCobro4;

	/** The descripcion impuesto 5. */
	@Field
	private String descrpicionImpuesto5;

	/** The monto moneda local 5 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaLocal5;

	/** The monto moneda extranjera 5 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaExtranjera5;

	/** The momento de cobro 5. */
	@Field
	private String momentoDeCobro5;

	/** The monto a cobrar 13 enteros, 2 decimales. */
	@Field
	private String montoACobrar;

	/** The producto. */
	@Field
	private String producto;

	/** The subproducto. */
	@Field
	private String subproducto;

	/** The Saldo inicial UR 13 enteros, 4 decimales. */
	@Field
	private String saldoInicialUR;

	/**
	 * Gets the nro certificado.
	 *
	 * @return the nroCertificado
	 */
	public String getNroCertificado() {
		return nroCertificado;
	}

	/**
	 * Sets the nro certificado.
	 *
	 * @param nroCertificado
	 *            the nroCertificado to set
	 */
	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	/**
	 * Gets the entidad cta inversion.
	 *
	 * @return the entidadCtaInversion
	 */
	public String getEntidadCtaInversion() {
		return entidadCtaInversion;
	}

	/**
	 * Sets the entidad cta inversion.
	 *
	 * @param entidadCtaInversion
	 *            the entidadCtaInversion to set
	 */
	public void setEntidadCtaInversion(String entidadCtaInversion) {
		this.entidadCtaInversion = entidadCtaInversion;
	}

	/**
	 * Gets the centro cta inversion.
	 *
	 * @return the centroCtaInversion
	 */
	public String getCentroCtaInversion() {
		return centroCtaInversion;
	}

	/**
	 * Sets the centro cta inversion.
	 *
	 * @param centroCtaInversion
	 *            the centroCtaInversion to set
	 */
	public void setCentroCtaInversion(String centroCtaInversion) {
		this.centroCtaInversion = centroCtaInversion;
	}

	/**
	 * Gets the cta inversion.
	 *
	 * @return the ctaInversion
	 */
	public String getCtaInversion() {
		return ctaInversion;
	}

	/**
	 * Sets the cta inversion.
	 *
	 * @param ctaInversion
	 *            the ctaInversion to set
	 */
	public void setCtaInversion(String ctaInversion) {
		this.ctaInversion = ctaInversion;
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
	 * Gets the secuencia renovacion.
	 *
	 * @return the secuenciaRenovacion
	 */
	public String getSecuenciaRenovacion() {
		return secuenciaRenovacion;
	}

	/**
	 * Sets the secuencia renovacion.
	 *
	 * @param secuenciaRenovacion
	 *            the secuenciaRenovacion to set
	 */
	public void setSecuenciaRenovacion(String secuenciaRenovacion) {
		this.secuenciaRenovacion = secuenciaRenovacion;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the indicador estado.
	 *
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	/**
	 * Sets the indicador estado.
	 *
	 * @param indicadorEstado
	 *            the indicadorEstado to set
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}

	/**
	 * Gets the tasa teorica.
	 *
	 * @return the tasaTeorica
	 */
	public String getTasaTeorica() {
		return tasaTeorica;
	}

	/**
	 * Sets the tasa teorica.
	 *
	 * @param tasaTeorica
	 *            the tasaTeorica to set
	 */
	public void setTasaTeorica(String tasaTeorica) {
		this.tasaTeorica = tasaTeorica;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the descripcion divisa.
	 *
	 * @return the descripcionDivisa
	 */
	public String getDescripcionDivisa() {
		return descripcionDivisa;
	}

	/**
	 * Sets the descripcion divisa.
	 *
	 * @param descripcionDivisa
	 *            the descripcionDivisa to set
	 */
	public void setDescripcionDivisa(String descripcionDivisa) {
		this.descripcionDivisa = descripcionDivisa;
	}

	/**
	 * Gets the saldo inicial PF.
	 *
	 * @return the saldoInicialPF
	 */
	public String getSaldoInicialPF() {
		return saldoInicialPF;
	}

	/**
	 * Sets the saldo inicial PF.
	 *
	 * @param saldoInicialPF
	 *            the saldoInicialPF to set
	 */
	public void setSaldoInicialPF(String saldoInicialPF) {
		this.saldoInicialPF = saldoInicialPF;
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
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the descripcion periodo liquidacion.
	 *
	 * @return the descripcionPeriodoLiquidacion
	 */
	public String getDescripcionPeriodoLiquidacion() {
		return descripcionPeriodoLiquidacion;
	}

	/**
	 * Sets the descripcion periodo liquidacion.
	 *
	 * @param descripcionPeriodoLiquidacion
	 *            the descripcionPeriodoLiquidacion to set
	 */
	public void setDescripcionPeriodoLiquidacion(String descripcionPeriodoLiquidacion) {
		this.descripcionPeriodoLiquidacion = descripcionPeriodoLiquidacion;
	}

	/**
	 * Gets the tasa real.
	 *
	 * @return the tasaReal
	 */
	public String getTasaReal() {
		return tasaReal;
	}

	/**
	 * Sets the tasa real.
	 *
	 * @param tasaReal
	 *            the tasaReal to set
	 */
	public void setTasaReal(String tasaReal) {
		this.tasaReal = tasaReal;
	}

	/**
	 * Gets the indicador capitaliza intereses.
	 *
	 * @return the indicadorCapitalizaIntereses
	 */
	public String getIndicadorCapitalizaIntereses() {
		return indicadorCapitalizaIntereses;
	}

	/**
	 * Sets the indicador capitaliza intereses.
	 *
	 * @param indicadorCapitalizaIntereses
	 *            the indicadorCapitalizaIntereses to set
	 */
	public void setIndicadorCapitalizaIntereses(String indicadorCapitalizaIntereses) {
		this.indicadorCapitalizaIntereses = indicadorCapitalizaIntereses;
	}

	/**
	 * Gets the indicador renovacion.
	 *
	 * @return the indicadorRenovacion
	 */
	public String getIndicadorRenovacion() {
		return indicadorRenovacion;
	}

	/**
	 * Sets the indicador renovacion.
	 *
	 * @param indicadorRenovacion
	 *            the indicadorRenovacion to set
	 */
	public void setIndicadorRenovacion(String indicadorRenovacion) {
		this.indicadorRenovacion = indicadorRenovacion;
	}

	/**
	 * Gets the indicador custodia.
	 *
	 * @return the indicadorCustodia
	 */
	public String getIndicadorCustodia() {
		return indicadorCustodia;
	}

	/**
	 * Sets the indicador custodia.
	 *
	 * @param indicadorCustodia
	 *            the indicadorCustodia to set
	 */
	public void setIndicadorCustodia(String indicadorCustodia) {
		this.indicadorCustodia = indicadorCustodia;
	}

	/**
	 * Gets the canal apertura.
	 *
	 * @return the canalApertura
	 */
	public String getCanalApertura() {
		return canalApertura;
	}

	/**
	 * Sets the canal apertura.
	 *
	 * @param canalApertura
	 *            the canalApertura to set
	 */
	public void setCanalApertura(String canalApertura) {
		this.canalApertura = canalApertura;
	}

	/**
	 * Gets the indicador transferencia.
	 *
	 * @return the indicadorTransferencia
	 */
	public String getIndicadorTransferencia() {
		return indicadorTransferencia;
	}

	/**
	 * Sets the indicador transferencia.
	 *
	 * @param indicadorTransferencia
	 *            the indicadorTransferencia to set
	 */
	public void setIndicadorTransferencia(String indicadorTransferencia) {
		this.indicadorTransferencia = indicadorTransferencia;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the interes liquidados.
	 *
	 * @return the interesLiquidados
	 */
	public String getInteresLiquidados() {
		return interesLiquidados;
	}

	/**
	 * Sets the interes liquidados.
	 *
	 * @param interesLiquidados
	 *            the interesLiquidados to set
	 */
	public void setInteresLiquidados(String interesLiquidados) {
		this.interesLiquidados = interesLiquidados;
	}

	/**
	 * Gets the intereses pendientes de liquidar.
	 *
	 * @return the interesesPendientesDeLiquidar
	 */
	public String getInteresesPendientesDeLiquidar() {
		return interesesPendientesDeLiquidar;
	}

	/**
	 * Sets the intereses pendientes de liquidar.
	 *
	 * @param interesesPendientesDeLiquidar
	 *            the interesesPendientesDeLiquidar to set
	 */
	public void setInteresesPendientesDeLiquidar(String interesesPendientesDeLiquidar) {
		this.interesesPendientesDeLiquidar = interesesPendientesDeLiquidar;
	}

	/**
	 * Gets the indicador bloqueo.
	 *
	 * @return the indicadorBloqueo
	 */
	public String getIndicadorBloqueo() {
		return indicadorBloqueo;
	}

	/**
	 * Sets the indicador bloqueo.
	 *
	 * @param indicadorBloqueo
	 *            the indicadorBloqueo to set
	 */
	public void setIndicadorBloqueo(String indicadorBloqueo) {
		this.indicadorBloqueo = indicadorBloqueo;
	}

	/**
	 * Gets the impu reten sal.
	 *
	 * @return the impuRetenSal
	 */
	public String getImpuRetenSal() {
		return impuRetenSal;
	}

	/**
	 * Sets the impu reten sal.
	 *
	 * @param impuRetenSal
	 *            the impuRetenSal to set
	 */
	public void setImpuRetenSal(String impuRetenSal) {
		this.impuRetenSal = impuRetenSal;
	}

	/**
	 * Gets the impu pdte ret sal.
	 *
	 * @return the impuPdteRetSal
	 */
	public String getImpuPdteRetSal() {
		return impuPdteRetSal;
	}

	/**
	 * Sets the impu pdte ret sal.
	 *
	 * @param impuPdteRetSal
	 *            the impuPdteRetSal to set
	 */
	public void setImpuPdteRetSal(String impuPdteRetSal) {
		this.impuPdteRetSal = impuPdteRetSal;
	}

	/**
	 * Gets the impu tot sal.
	 *
	 * @return the impuTotSal
	 */
	public String getImpuTotSal() {
		return impuTotSal;
	}

	/**
	 * Sets the impu tot sal.
	 *
	 * @param impuTotSal
	 *            the impuTotSal to set
	 */
	public void setImpuTotSal(String impuTotSal) {
		this.impuTotSal = impuTotSal;
	}

	/**
	 * Gets the fecha proxima liquidacion.
	 *
	 * @return the fechaProximaLiquidacion
	 */
	public String getFechaProximaLiquidacion() {
		return fechaProximaLiquidacion;
	}

	/**
	 * Sets the fecha proxima liquidacion.
	 *
	 * @param fechaProximaLiquidacion
	 *            the fechaProximaLiquidacion to set
	 */
	public void setFechaProximaLiquidacion(String fechaProximaLiquidacion) {
		this.fechaProximaLiquidacion = fechaProximaLiquidacion;
	}

	/**
	 * Gets the divisa original.
	 *
	 * @return the divisaOriginal
	 */
	public String getDivisaOriginal() {
		return divisaOriginal;
	}

	/**
	 * Sets the divisa original.
	 *
	 * @param divisaOriginal
	 *            the divisaOriginal to set
	 */
	public void setDivisaOriginal(String divisaOriginal) {
		this.divisaOriginal = divisaOriginal;
	}

	/**
	 * Gets the capital ajustado.
	 *
	 * @return the capitalAjustado
	 */
	public String getCapitalAjustado() {
		return capitalAjustado;
	}

	/**
	 * Sets the capital ajustado.
	 *
	 * @param capitalAjustado
	 *            the capitalAjustado to set
	 */
	public void setCapitalAjustado(String capitalAjustado) {
		this.capitalAjustado = capitalAjustado;
	}

	/**
	 * Gets the desc tipo plazo.
	 *
	 * @return the descTipoPlazo
	 */
	public String getDescTipoPlazo() {
		return descTipoPlazo;
	}

	/**
	 * Sets the desc tipo plazo.
	 *
	 * @param descTipoPlazo
	 *            the descTipoPlazo to set
	 */
	public void setDescTipoPlazo(String descTipoPlazo) {
		this.descTipoPlazo = descTipoPlazo;
	}

	/**
	 * Gets the codigo UR.
	 *
	 * @return the codigoUR
	 */
	public String getCodigoUR() {
		return codigoUR;
	}

	/**
	 * Sets the codigo UR.
	 *
	 * @param codigoUR
	 *            the codigoUR to set
	 */
	public void setCodigoUR(String codigoUR) {
		this.codigoUR = codigoUR;
	}

	/**
	 * Gets the entidad aso 1.
	 *
	 * @return the entidadAso1
	 */
	public String getEntidadAso1() {
		return entidadAso1;
	}

	/**
	 * Sets the entidad aso 1.
	 *
	 * @param entidadAso1
	 *            the entidadAso1 to set
	 */
	public void setEntidadAso1(String entidadAso1) {
		this.entidadAso1 = entidadAso1;
	}

	/**
	 * Gets the centro cuenta credito.
	 *
	 * @return the centroCuentaCredito
	 */
	public String getCentroCuentaCredito() {
		return centroCuentaCredito;
	}

	/**
	 * Sets the centro cuenta credito.
	 *
	 * @param centroCuentaCredito
	 *            the centroCuentaCredito to set
	 */
	public void setCentroCuentaCredito(String centroCuentaCredito) {
		this.centroCuentaCredito = centroCuentaCredito;
	}

	/**
	 * Gets the cuenta credito altair.
	 *
	 * @return the centroCuentaCredito
	 */
	public String getCuentaCreditoAltair() {
		return cuentaCreditoAltair;
	}

	/**
	 * Sets the cuenta credito altair.
	 *
	 * @param cuentaCreditoAltair
	 *            the new cuenta credito altair
	 */
	public void setCuentaCreditoAltair(String cuentaCreditoAltair) {
		this.cuentaCreditoAltair = cuentaCreditoAltair;
	}

	/**
	 * Gets the entidad cuenta debito.
	 *
	 * @return the centroCuentaCredito
	 */
	public String getEntidadCuentaDebito() {
		return entidadCuentaDebito;
	}

	/**
	 * Sets the entidad cuenta debito.
	 *
	 * @param entidadCuentaDebito
	 *            the new entidad cuenta debito
	 */
	public void setEntidadCuentaDebito(String entidadCuentaDebito) {
		this.entidadCuentaDebito = entidadCuentaDebito;
	}

	/**
	 * Gets the centro cuenta debito.
	 *
	 * @return the centroCuentaCredito
	 */
	public String getCentroCuentaDebito() {
		return centroCuentaDebito;
	}

	/**
	 * Sets the centro cuenta debito.
	 *
	 * @param centroCuentaDebito
	 *            the new centro cuenta debito
	 */
	public void setCentroCuentaDebito(String centroCuentaDebito) {
		this.centroCuentaDebito = centroCuentaDebito;
	}

	/**
	 * Gets the cuenta debito altair.
	 *
	 * @return the cuentaDebitoAltair
	 */
	public String getCuentaDebitoAltair() {
		return cuentaDebitoAltair;
	}

	/**
	 * Sets the cuenta debito altair.
	 *
	 * @param cuentaDebitoAltair
	 *            the cuentaDebitoAltair to set
	 */
	public void setCuentaDebitoAltair(String cuentaDebitoAltair) {
		this.cuentaDebitoAltair = cuentaDebitoAltair;
	}

	/**
	 * Gets the ind aju alta.
	 *
	 * @return the indAjuAlta
	 */
	public String getIndAjuAlta() {
		return indAjuAlta;
	}

	/**
	 * Sets the ind aju alta.
	 *
	 * @param indAjuAlta
	 *            the indAjuAlta to set
	 */
	public void setIndAjuAlta(String indAjuAlta) {
		this.indAjuAlta = indAjuAlta;
	}

	/**
	 * Gets the ind aju ult liq.
	 *
	 * @return the indAjuUltLiq
	 */
	public String getIndAjuUltLiq() {
		return indAjuUltLiq;
	}

	/**
	 * Sets the ind aju ult liq.
	 *
	 * @param indAjuUltLiq
	 *            the indAjuUltLiq to set
	 */
	public void setIndAjuUltLiq(String indAjuUltLiq) {
		this.indAjuUltLiq = indAjuUltLiq;
	}

	/**
	 * Gets the sucursal radicacion certificado.
	 *
	 * @return the sucursalRadicacionCertificado
	 */
	public String getSucursalRadicacionCertificado() {
		return sucursalRadicacionCertificado;
	}

	/**
	 * Sets the sucursal radicacion certificado.
	 *
	 * @param sucursalRadicacionCertificado
	 *            the sucursalRadicacionCertificado to set
	 */
	public void setSucursalRadicacionCertificado(String sucursalRadicacionCertificado) {
		this.sucursalRadicacionCertificado = sucursalRadicacionCertificado;
	}

	/**
	 * Gets the periodicidad liquidacion.
	 *
	 * @return the periodicidadLiquidacion
	 */
	public String getPeriodicidadLiquidacion() {
		return periodicidadLiquidacion;
	}

	/**
	 * Sets the periodicidad liquidacion.
	 *
	 * @param periodicidadLiquidacion
	 *            the periodicidadLiquidacion to set
	 */
	public void setPeriodicidadLiquidacion(String periodicidadLiquidacion) {
		this.periodicidadLiquidacion = periodicidadLiquidacion;
	}

	/**
	 * Gets the indicador capitaliza reajustes.
	 *
	 * @return the indicadorCapitalizaReajustes
	 */
	public String getIndicadorCapitalizaReajustes() {
		return indicadorCapitalizaReajustes;
	}

	/**
	 * Sets the indicador capitaliza reajustes.
	 *
	 * @param indicadorCapitalizaReajustes
	 *            the indicadorCapitalizaReajustes to set
	 */
	public void setIndicadorCapitalizaReajustes(String indicadorCapitalizaReajustes) {
		this.indicadorCapitalizaReajustes = indicadorCapitalizaReajustes;
	}

	/**
	 * Gets the descrpicion impuesto 1.
	 *
	 * @return the descrpicionImpuesto1
	 */
	public String getDescrpicionImpuesto1() {
		return descrpicionImpuesto1;
	}

	/**
	 * Sets the descrpicion impuesto 1.
	 *
	 * @param descrpicionImpuesto1
	 *            the descrpicionImpuesto1 to set
	 */
	public void setDescrpicionImpuesto1(String descrpicionImpuesto1) {
		this.descrpicionImpuesto1 = descrpicionImpuesto1;
	}

	/**
	 * Gets the monto moneda local 1.
	 *
	 * @return the montoMonedaLocal1
	 */
	public String getMontoMonedaLocal1() {
		return montoMonedaLocal1;
	}

	/**
	 * Sets the monto moneda local 1.
	 *
	 * @param montoMonedaLocal1
	 *            the montoMonedaLocal1 to set
	 */
	public void setMontoMonedaLocal1(String montoMonedaLocal1) {
		this.montoMonedaLocal1 = montoMonedaLocal1;
	}

	/**
	 * Gets the monto moneda extranjera 1.
	 *
	 * @return the montoMonedaExtranjera1
	 */
	public String getMontoMonedaExtranjera1() {
		return montoMonedaExtranjera1;
	}

	/**
	 * Sets the monto moneda extranjera 1.
	 *
	 * @param montoMonedaExtranjera1
	 *            the montoMonedaExtranjera1 to set
	 */
	public void setMontoMonedaExtranjera1(String montoMonedaExtranjera1) {
		this.montoMonedaExtranjera1 = montoMonedaExtranjera1;
	}

	/**
	 * Gets the momento de cobro 1.
	 *
	 * @return the momentoDeCobro1
	 */
	public String getMomentoDeCobro1() {
		return momentoDeCobro1;
	}

	/**
	 * Sets the momento de cobro 1.
	 *
	 * @param momentoDeCobro1
	 *            the momentoDeCobro1 to set
	 */
	public void setMomentoDeCobro1(String momentoDeCobro1) {
		this.momentoDeCobro1 = momentoDeCobro1;
	}

	/**
	 * Gets the descrpicion impuesto 2.
	 *
	 * @return the descrpicionImpuesto2
	 */
	public String getDescrpicionImpuesto2() {
		return descrpicionImpuesto2;
	}

	/**
	 * Sets the descrpicion impuesto 2.
	 *
	 * @param descrpicionImpuesto2
	 *            the descrpicionImpuesto2 to set
	 */
	public void setDescrpicionImpuesto2(String descrpicionImpuesto2) {
		this.descrpicionImpuesto2 = descrpicionImpuesto2;
	}

	/**
	 * Gets the monto moneda local 2.
	 *
	 * @return the montoMonedaLocal2
	 */
	public String getMontoMonedaLocal2() {
		return montoMonedaLocal2;
	}

	/**
	 * Sets the monto moneda local 2.
	 *
	 * @param montoMonedaLocal2
	 *            the montoMonedaLocal2 to set
	 */
	public void setMontoMonedaLocal2(String montoMonedaLocal2) {
		this.montoMonedaLocal2 = montoMonedaLocal2;
	}

	/**
	 * Gets the monto moneda extranjera 2.
	 *
	 * @return the montoMonedaExtranjera2
	 */
	public String getMontoMonedaExtranjera2() {
		return montoMonedaExtranjera2;
	}

	/**
	 * Sets the monto moneda extranjera 2.
	 *
	 * @param montoMonedaExtranjera2
	 *            the montoMonedaExtranjera2 to set
	 */
	public void setMontoMonedaExtranjera2(String montoMonedaExtranjera2) {
		this.montoMonedaExtranjera2 = montoMonedaExtranjera2;
	}

	/**
	 * Gets the momento de cobro 2.
	 *
	 * @return the momentoDeCobro2
	 */
	public String getMomentoDeCobro2() {
		return momentoDeCobro2;
	}

	/**
	 * Sets the momento de cobro 2.
	 *
	 * @param momentoDeCobro2
	 *            the momentoDeCobro2 to set
	 */
	public void setMomentoDeCobro2(String momentoDeCobro2) {
		this.momentoDeCobro2 = momentoDeCobro2;
	}

	/**
	 * Gets the descrpicion impuesto 3.
	 *
	 * @return the descrpicionImpuesto3
	 */
	public String getDescrpicionImpuesto3() {
		return descrpicionImpuesto3;
	}

	/**
	 * Sets the descrpicion impuesto 3.
	 *
	 * @param descrpicionImpuesto3
	 *            the descrpicionImpuesto3 to set
	 */
	public void setDescrpicionImpuesto3(String descrpicionImpuesto3) {
		this.descrpicionImpuesto3 = descrpicionImpuesto3;
	}

	/**
	 * Gets the monto moneda local 3.
	 *
	 * @return the montoMonedaLocal3
	 */
	public String getMontoMonedaLocal3() {
		return montoMonedaLocal3;
	}

	/**
	 * Sets the monto moneda local 3.
	 *
	 * @param montoMonedaLocal3
	 *            the montoMonedaLocal3 to set
	 */
	public void setMontoMonedaLocal3(String montoMonedaLocal3) {
		this.montoMonedaLocal3 = montoMonedaLocal3;
	}

	/**
	 * Gets the monto moneda extranjera 3.
	 *
	 * @return the montoMonedaExtranjera3
	 */
	public String getMontoMonedaExtranjera3() {
		return montoMonedaExtranjera3;
	}

	/**
	 * Sets the monto moneda extranjera 3.
	 *
	 * @param montoMonedaExtranjera3
	 *            the montoMonedaExtranjera3 to set
	 */
	public void setMontoMonedaExtranjera3(String montoMonedaExtranjera3) {
		this.montoMonedaExtranjera3 = montoMonedaExtranjera3;
	}

	/**
	 * Gets the momento de cobro 3.
	 *
	 * @return the momentoDeCobro3
	 */
	public String getMomentoDeCobro3() {
		return momentoDeCobro3;
	}

	/**
	 * Sets the momento de cobro 3.
	 *
	 * @param momentoDeCobro3
	 *            the momentoDeCobro3 to set
	 */
	public void setMomentoDeCobro3(String momentoDeCobro3) {
		this.momentoDeCobro3 = momentoDeCobro3;
	}

	/**
	 * Gets the descrpicion impuesto 4.
	 *
	 * @return the descrpicionImpuesto4
	 */
	public String getDescrpicionImpuesto4() {
		return descrpicionImpuesto4;
	}

	/**
	 * Sets the descrpicion impuesto 4.
	 *
	 * @param descrpicionImpuesto4
	 *            the descrpicionImpuesto4 to set
	 */
	public void setDescrpicionImpuesto4(String descrpicionImpuesto4) {
		this.descrpicionImpuesto4 = descrpicionImpuesto4;
	}

	/**
	 * Gets the monto moneda local 4.
	 *
	 * @return the montoMonedaLocal4
	 */
	public String getMontoMonedaLocal4() {
		return montoMonedaLocal4;
	}

	/**
	 * Sets the monto moneda local 4.
	 *
	 * @param montoMonedaLocal4
	 *            the montoMonedaLocal4 to set
	 */
	public void setMontoMonedaLocal4(String montoMonedaLocal4) {
		this.montoMonedaLocal4 = montoMonedaLocal4;
	}

	/**
	 * Gets the monto moneda extranjera 4.
	 *
	 * @return the montoMonedaExtranjera4
	 */
	public String getMontoMonedaExtranjera4() {
		return montoMonedaExtranjera4;
	}

	/**
	 * Sets the monto moneda extranjera 4.
	 *
	 * @param montoMonedaExtranjera4
	 *            the montoMonedaExtranjera4 to set
	 */
	public void setMontoMonedaExtranjera4(String montoMonedaExtranjera4) {
		this.montoMonedaExtranjera4 = montoMonedaExtranjera4;
	}

	/**
	 * Gets the momento de cobro 4.
	 *
	 * @return the momentoDeCobro4
	 */
	public String getMomentoDeCobro4() {
		return momentoDeCobro4;
	}

	/**
	 * Sets the momento de cobro 4.
	 *
	 * @param momentoDeCobro4
	 *            the momentoDeCobro4 to set
	 */
	public void setMomentoDeCobro4(String momentoDeCobro4) {
		this.momentoDeCobro4 = momentoDeCobro4;
	}

	/**
	 * Gets the descrpicion impuesto 5.
	 *
	 * @return the descrpicionImpuesto5
	 */
	public String getDescrpicionImpuesto5() {
		return descrpicionImpuesto5;
	}

	/**
	 * Sets the descrpicion impuesto 5.
	 *
	 * @param descrpicionImpuesto5
	 *            the descrpicionImpuesto5 to set
	 */
	public void setDescrpicionImpuesto5(String descrpicionImpuesto5) {
		this.descrpicionImpuesto5 = descrpicionImpuesto5;
	}

	/**
	 * Gets the monto moneda local 5.
	 *
	 * @return the montoMonedaLocal5
	 */
	public String getMontoMonedaLocal5() {
		return montoMonedaLocal5;
	}

	/**
	 * Sets the monto moneda local 5.
	 *
	 * @param montoMonedaLocal5
	 *            the montoMonedaLocal5 to set
	 */
	public void setMontoMonedaLocal5(String montoMonedaLocal5) {
		this.montoMonedaLocal5 = montoMonedaLocal5;
	}

	/**
	 * Gets the monto moneda extranjera 5.
	 *
	 * @return the montoMonedaExtranjera5
	 */
	public String getMontoMonedaExtranjera5() {
		return montoMonedaExtranjera5;
	}

	/**
	 * Sets the monto moneda extranjera 5.
	 *
	 * @param montoMonedaExtranjera5
	 *            the montoMonedaExtranjera5 to set
	 */
	public void setMontoMonedaExtranjera5(String montoMonedaExtranjera5) {
		this.montoMonedaExtranjera5 = montoMonedaExtranjera5;
	}

	/**
	 * Gets the momento de cobro 5.
	 *
	 * @return the momentoDeCobro5
	 */
	public String getMomentoDeCobro5() {
		return momentoDeCobro5;
	}

	/**
	 * Sets the momento de cobro 5.
	 *
	 * @param momentoDeCobro5
	 *            the momentoDeCobro5 to set
	 */
	public void setMomentoDeCobro5(String momentoDeCobro5) {
		this.momentoDeCobro5 = momentoDeCobro5;
	}

	/**
	 * Gets the monto A cobrar.
	 *
	 * @return the montoACobrar
	 */
	public String getMontoACobrar() {
		return montoACobrar;
	}

	/**
	 * Sets the monto A cobrar.
	 *
	 * @param montoACobrar
	 *            the montoACobrar to set
	 */
	public void setMontoACobrar(String montoACobrar) {
		this.montoACobrar = montoACobrar;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the subproducto.
	 *
	 * @return the subproducto
	 */
	public String getSubproducto() {
		return subproducto;
	}

	/**
	 * Sets the subproducto.
	 *
	 * @param subproducto
	 *            the subproducto to set
	 */
	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}

	/**
	 * Gets the saldo inicial UR.
	 *
	 * @return the saldoInicialUR
	 */
	public String getSaldoInicialUR() {
		return saldoInicialUR;
	}

	/**
	 * Sets the saldo inicial UR.
	 *
	 * @param saldoInicialUR
	 *            the saldoInicialUR to set
	 */
	public void setSaldoInicialUR(String saldoInicialUR) {
		this.saldoInicialUR = saldoInicialUR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).toString();
	}

}
