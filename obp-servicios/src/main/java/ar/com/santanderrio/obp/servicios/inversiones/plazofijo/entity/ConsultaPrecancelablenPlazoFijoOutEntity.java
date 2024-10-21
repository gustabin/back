/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * Objeto utilizado para retornar datos del DAO.
 * 
 * @author juan.pablo.picate
 *
 */
@Record
public class ConsultaPrecancelablenPlazoFijoOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Numerico. */
	@Field
	private String codRetornoExtendido;

	/** 13 enteros, 2 decimales. */
	@Field
	private String saldoInicial;

	/** 3 enteros, 5 decimales. */
	@Field
	private String tipoInteres;

	/** 3 enteros, 5 decimales. */
	@Field
	String incrementoPorcVigente;

	/** numerico. */
	@Field
	private String fechaProximoVencimiento;

	/** numerico. */
	@Field
	private String fechaProximaLiquidacion;

	/** 13 enteros, 4 decimales. */
	@Field
	private String interesesPendientesDeAbonar;

	/** The certificado. */
	@Field
	private String certificado;

	/** The divisa. */
	@Field
	private String divisa;

	/** The ind precancelable. */
	@Field
	private String indPrecancelable;

	/** numerico. */
	@Field
	private String plazoMinimoPrecancelacion;

	/** 3 enteros, 5 decimales. */
	@Field
	private String porcentajePenalizacion;

	/** numerico. */
	@Field
	private String diaParaLiquidar;

	/** 13 enteros 2 decimales. */
	@Field
	private String impuestosAlAlta;

	/** 13 enteros 2 decimales. */
	@Field
	private String impuestosAlVencimiento;

	/** 13 enteros 2 decimales. */
	@Field
	private String totalACobrar;

	/** numerico. */
	@Field
	private String cuentaDebito;

	/** The divisa debito. */
	@Field
	private String divisaDebito;

	/** numerico. */
	@Field
	private String cuentaCredito;

	/** The divisa credito. */
	@Field
	private String divisaCredito;

	/** The cant impuestos alta. */
	@Field()
	private Long cantImpuestosAlta;

	/** The impuestos alta PF. */
	@Segment(minOccurs = 10, maxOccurs = 10)
	private List<ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity> impuestosAltaPF = new ArrayList<ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity>();

	/** The cant impuestos vencimiento. */
	@Field()
	private Long cantImpuestosVencimiento;

	/** The impuestos venc PF. */
	@Segment(minOccurs = 10, maxOccurs = 10)
	private List<ConsultaPrecancelacionImpuestosVencPlazoFijoEntity> impuestosVencPF = new ArrayList<ConsultaPrecancelacionImpuestosVencPlazoFijoEntity>();

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public static String getDelimiter() {
		return DELIMITER;
	}

	/**
	 * Gets the cod retorno extendido.
	 *
	 * @return the codRetornoExtendido
	 */
	public String getCodRetornoExtendido() {
		return codRetornoExtendido;
	}

	/**
	 * Sets the cod retorno extendido.
	 *
	 * @param codRetornoExtendido
	 *            the codRetornoExtendido to set
	 */
	public void setCodRetornoExtendido(String codRetornoExtendido) {
		this.codRetornoExtendido = codRetornoExtendido;
	}

	/**
	 * Gets the saldo inicial.
	 *
	 * @return the saldoInicial
	 */
	public String getSaldoInicial() {
		return saldoInicial;
	}

	/**
	 * Sets the saldo inicial.
	 *
	 * @param saldoInicial
	 *            the saldoInicial to set
	 */
	public void setSaldoInicial(String saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	/**
	 * Gets the tipo interes.
	 *
	 * @return the tipoInteres
	 */
	public String getTipoInteres() {
		return tipoInteres;
	}

	/**
	 * Sets the tipo interes.
	 *
	 * @param tipoInteres
	 *            the tipoInteres to set
	 */
	public void setTipoInteres(String tipoInteres) {
		this.tipoInteres = tipoInteres;
	}

	/**
	 * Gets the incremento porc vigente.
	 *
	 * @return the incrementoPorcVigente
	 */
	public String getIncrementoPorcVigente() {
		return incrementoPorcVigente;
	}

	/**
	 * Sets the incremento porc vigente.
	 *
	 * @param incrementoPorcVigente
	 *            the incrementoPorcVigente to set
	 */
	public void setIncrementoPorcVigente(String incrementoPorcVigente) {
		this.incrementoPorcVigente = incrementoPorcVigente;
	}

	/**
	 * Gets the fecha proximo vencimiento.
	 *
	 * @return the fechaProximoVencimiento
	 */
	public String getFechaProximoVencimiento() {
		return fechaProximoVencimiento;
	}

	/**
	 * Sets the fecha proximo vencimiento.
	 *
	 * @param fechaProximoVencimiento
	 *            the fechaProximoVencimiento to set
	 */
	public void setFechaProximoVencimiento(String fechaProximoVencimiento) {
		this.fechaProximoVencimiento = fechaProximoVencimiento;
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
	 * Gets the intereses pendientes de abonar.
	 *
	 * @return the interesesPendientesDeAbonar
	 */
	public String getInteresesPendientesDeAbonar() {
		return interesesPendientesDeAbonar;
	}

	/**
	 * Sets the intereses pendientes de abonar.
	 *
	 * @param interesesPendientesDeAbonar
	 *            the interesesPendientesDeAbonar to set
	 */
	public void setInteresesPendientesDeAbonar(String interesesPendientesDeAbonar) {
		this.interesesPendientesDeAbonar = interesesPendientesDeAbonar;
	}

	/**
	 * Gets the certificado.
	 *
	 * @return the certificado
	 */
	public String getCertificado() {
		return certificado;
	}

	/**
	 * Sets the certificado.
	 *
	 * @param certificado
	 *            the certificado to set
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
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
	 * Gets the ind precancelable.
	 *
	 * @return the indPrecancelable
	 */
	public String getIndPrecancelable() {
		return indPrecancelable;
	}

	/**
	 * Sets the ind precancelable.
	 *
	 * @param indPrecancelable
	 *            the indPrecancelable to set
	 */
	public void setIndPrecancelable(String indPrecancelable) {
		this.indPrecancelable = indPrecancelable;
	}

	/**
	 * Gets the plazo minimo precancelacion.
	 *
	 * @return the plazoMinimoPrecancelacion
	 */
	public String getPlazoMinimoPrecancelacion() {
		return plazoMinimoPrecancelacion;
	}

	/**
	 * Sets the plazo minimo precancelacion.
	 *
	 * @param plazoMinimoPrecancelacion
	 *            the plazoMinimoPrecancelacion to set
	 */
	public void setPlazoMinimoPrecancelacion(String plazoMinimoPrecancelacion) {
		this.plazoMinimoPrecancelacion = plazoMinimoPrecancelacion;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentajePenalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the porcentajePenalizacion to set
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the dia para liquidar.
	 *
	 * @return the diaParaLiquidar
	 */
	public String getDiaParaLiquidar() {
		return diaParaLiquidar;
	}

	/**
	 * Sets the dia para liquidar.
	 *
	 * @param diaParaLiquidar
	 *            the diaParaLiquidar to set
	 */
	public void setDiaParaLiquidar(String diaParaLiquidar) {
		this.diaParaLiquidar = diaParaLiquidar;
	}

	/**
	 * Gets the impuestos al alta.
	 *
	 * @return the impuestosAlAlta
	 */
	public String getImpuestosAlAlta() {
		return impuestosAlAlta;
	}

	/**
	 * Sets the impuestos al alta.
	 *
	 * @param impuestosAlAlta
	 *            the impuestosAlAlta to set
	 */
	public void setImpuestosAlAlta(String impuestosAlAlta) {
		this.impuestosAlAlta = impuestosAlAlta;
	}

	/**
	 * Gets the impuestos al vencimiento.
	 *
	 * @return the impuestosAlVencimiento
	 */
	public String getImpuestosAlVencimiento() {
		return impuestosAlVencimiento;
	}

	/**
	 * Sets the impuestos al vencimiento.
	 *
	 * @param impuestosAlVencimiento
	 *            the impuestosAlVencimiento to set
	 */
	public void setImpuestosAlVencimiento(String impuestosAlVencimiento) {
		this.impuestosAlVencimiento = impuestosAlVencimiento;
	}

	/**
	 * Gets the total A cobrar.
	 *
	 * @return the totalACobrar
	 */
	public String getTotalACobrar() {
		return totalACobrar;
	}

	/**
	 * Sets the total A cobrar.
	 *
	 * @param totalACobrar
	 *            the totalACobrar to set
	 */
	public void setTotalACobrar(String totalACobrar) {
		this.totalACobrar = totalACobrar;
	}

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuentaDebito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the cuentaDebito to set
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the divisa debito.
	 *
	 * @return the divisaDebito
	 */
	public String getDivisaDebito() {
		return divisaDebito;
	}

	/**
	 * Sets the divisa debito.
	 *
	 * @param divisaDebito
	 *            the divisaDebito to set
	 */
	public void setDivisaDebito(String divisaDebito) {
		this.divisaDebito = divisaDebito;
	}

	/**
	 * Gets the cuenta credito.
	 *
	 * @return the cuentaCredito
	 */
	public String getCuentaCredito() {
		return cuentaCredito;
	}

	/**
	 * Sets the cuenta credito.
	 *
	 * @param cuentaCredito
	 *            the cuentaCredito to set
	 */
	public void setCuentaCredito(String cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	/**
	 * Gets the divisa credito.
	 *
	 * @return the divisaCredito
	 */
	public String getDivisaCredito() {
		return divisaCredito;
	}

	/**
	 * Sets the divisa credito.
	 *
	 * @param divisaCredito
	 *            the divisaCredito to set
	 */
	public void setDivisaCredito(String divisaCredito) {
		this.divisaCredito = divisaCredito;
	}

	/**
	 * Gets the cant impuestos alta.
	 *
	 * @return the cantImpuestosAlta
	 */
	public Long getCantImpuestosAlta() {
		return cantImpuestosAlta;
	}

	/**
	 * Sets the cant impuestos alta.
	 *
	 * @param cantImpuestosAlta
	 *            the cantImpuestosAlta to set
	 */
	public void setCantImpuestosAlta(Long cantImpuestosAlta) {
		this.cantImpuestosAlta = cantImpuestosAlta;
	}

	/**
	 * Gets the impuestos alta PF.
	 *
	 * @return the impuestosAltaPF
	 */
	public List<ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity> getImpuestosAltaPF() {
		return impuestosAltaPF;
	}

	/**
	 * Sets the impuestos alta PF.
	 *
	 * @param impuestosAltaPF
	 *            the impuestosAltaPF to set
	 */
	public void setImpuestosAltaPF(List<ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity> impuestosAltaPF) {
		this.impuestosAltaPF = impuestosAltaPF;
	}

	/**
	 * Gets the cant impuestos vencimiento.
	 *
	 * @return the cantImpuestosVencimiento
	 */
	public Long getCantImpuestosVencimiento() {
		return cantImpuestosVencimiento;
	}

	/**
	 * Sets the cant impuestos vencimiento.
	 *
	 * @param cantImpuestosVencimiento
	 *            the cantImpuestosVencimiento to set
	 */
	public void setCantImpuestosVencimiento(Long cantImpuestosVencimiento) {
		this.cantImpuestosVencimiento = cantImpuestosVencimiento;
	}

	/**
	 * Gets the impuestos venc PF.
	 *
	 * @return the impuestosVencPF
	 */
	public List<ConsultaPrecancelacionImpuestosVencPlazoFijoEntity> getImpuestosVencPF() {
		return impuestosVencPF;
	}

	/**
	 * Sets the impuestos venc PF.
	 *
	 * @param impuestosVencPF
	 *            the impuestosVencPF to set
	 */
	public void setImpuestosVencPF(List<ConsultaPrecancelacionImpuestosVencPlazoFijoEntity> impuestosVencPF) {
		this.impuestosVencPF = impuestosVencPF;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("codRetornoExtendido", codRetornoExtendido)
				.append("impuestosAltaPF", impuestosAltaPF).append("impuestosVencPF", impuestosVencPF).toString();
	}
}
