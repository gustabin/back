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
public class SimulacionPrecancelableOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** numerico. */
	@Field
	private String codigoRetornoExtendido;

	/** The cuenta plazo. */
	@Field
	private String cuentaPlazo;

	/** numerico. */
	@Field
	private String secuencia;

	/** The forma pago. */
	@Field
	private String formaPago;

	/** The codigo operacion. */
	@Field
	private String codigoOperacion;

	/** 13 enteros, 2 decimales. */
	@Field
	private String capital;

	/** 13 enteros, 2 decimales. */
	@Field
	private String interesesPrecancelacion;

	/** 13 enteros, 2 decimales. */
	@Field
	private String impuestosPrecancelacion;

	/** 13 enteros, 2 decimales. */
	@Field
	private String impuestosAlta;

	/** 3 enteros, 5 decimales. */
	@Field
	private String porcentajePenalizacion;

	/** 13 enteros, 2 decimales. */
	@Field
	private String totalAPagar;

	/** 13 enteros, 2 decimales. */
	@Field
	private String interesAlVencimiento;

	/** 13 enteros, 2 decimales. */
	@Field
	private String impuestosAlVencimiento;

	/** 13 enteros, 2 decimales. */
	@Field
	private String interesAFecha;

	/** 13 enteros, 2 decimales. */
	@Field
	private String impuestosAFecha;

	/** 3 enteros, 5 decimales. */
	@Field
	private String tasaRealPF;

	/** numerico. */
	@Field
	private String plazoRealPF;

	/** numerico. */
	@Field
	private String plazoActual;

	/** DDMMAAAA. */
	@Field
	private String fechaAltaPF;

	/** DDMMAAAA. */
	@Field
	private String fechaVencimientoPF;

	/** The cuenta debito. */
	@Field
	private String cuentaDebito;

	/** The divisa cuenta debito. */
	@Field
	private String divisaCuentaDebito;

	/** The cuenta credito. */
	@Field
	private String cuentaCredito;

	/** The divisa cuenta credito. */
	@Field
	private String divisaCuentaCredito;

	/** 13 enteros, 2 decimales. */
	@Field
	private String diferenciaIntAFecha;

	/** 13 enteros, 2 decimales. */
	@Field
	private String diferenciaImpAFecha;

	/** 13 enteros, 2 decimales. */
	@Field
	private String diferenciaIntAVencimiento;

	/** 13 enteros, 2 decimales. */
	@Field
	private String diferenciaImpAVencimiento;

	/** 3 enteros, 5 decimales. */
	@Field
	private String tasaMinimaActual;

	/** 7 enteros, 5 decimales. */
	@Field
	private String tasaMaximaActual;

	/** 13 enteros, 2 decimales. */
	@Field
	private String importeReajuste;

	/** The cant impuestos. */
	@Field()
	private Long cantImpuestos;
	
	
	/** The impuestos PF. */
	@Segment(occursRef = "cantImpuestos")
	private List<PrecancelacionImpuestosPlazoFijoEntity> impuestosPF = new ArrayList<PrecancelacionImpuestosPlazoFijoEntity>();

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
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the cuenta plazo.
	 *
	 * @return the cuentaPlazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo
	 *            the cuentaPlazo to set
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Gets the forma pago.
	 *
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * Gets the codigo operacion.
	 *
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * Sets the codigo operacion.
	 *
	 * @param codigoOperacion
	 *            the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the intereses precancelacion.
	 *
	 * @return the interesesPrecancelacion
	 */
	public String getInteresesPrecancelacion() {
		return interesesPrecancelacion;
	}

	/**
	 * Sets the intereses precancelacion.
	 *
	 * @param interesesPrecancelacion
	 *            the interesesPrecancelacion to set
	 */
	public void setInteresesPrecancelacion(String interesesPrecancelacion) {
		this.interesesPrecancelacion = interesesPrecancelacion;
	}

	/**
	 * Gets the impuestos precancelacion.
	 *
	 * @return the impuestosPrecancelacion
	 */
	public String getImpuestosPrecancelacion() {
		return impuestosPrecancelacion;
	}

	/**
	 * Sets the impuestos precancelacion.
	 *
	 * @param impuestosPrecancelacion
	 *            the impuestosPrecancelacion to set
	 */
	public void setImpuestosPrecancelacion(String impuestosPrecancelacion) {
		this.impuestosPrecancelacion = impuestosPrecancelacion;
	}

	/**
	 * Gets the impuestos alta.
	 *
	 * @return the impuestosAlta
	 */
	public String getImpuestosAlta() {
		return impuestosAlta;
	}

	/**
	 * Sets the impuestos alta.
	 *
	 * @param impuestosAlta
	 *            the impuestosAlta to set
	 */
	public void setImpuestosAlta(String impuestosAlta) {
		this.impuestosAlta = impuestosAlta;
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
	 * Gets the total A pagar.
	 *
	 * @return the totalAPagar
	 */
	public String getTotalAPagar() {
		return totalAPagar;
	}

	/**
	 * Sets the total A pagar.
	 *
	 * @param totalAPagar
	 *            the totalAPagar to set
	 */
	public void setTotalAPagar(String totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	/**
	 * Gets the interes al vencimiento.
	 *
	 * @return the interesAlVencimiento
	 */
	public String getInteresAlVencimiento() {
		return interesAlVencimiento;
	}

	/**
	 * Sets the interes al vencimiento.
	 *
	 * @param interesAlVencimiento
	 *            the interesAlVencimiento to set
	 */
	public void setInteresAlVencimiento(String interesAlVencimiento) {
		this.interesAlVencimiento = interesAlVencimiento;
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
	 * Gets the interes A fecha.
	 *
	 * @return the interesAFecha
	 */
	public String getInteresAFecha() {
		return interesAFecha;
	}

	/**
	 * Sets the interes A fecha.
	 *
	 * @param interesAFecha
	 *            the interesAFecha to set
	 */
	public void setInteresAFecha(String interesAFecha) {
		this.interesAFecha = interesAFecha;
	}

	/**
	 * Gets the impuestos A fecha.
	 *
	 * @return the impuestosAFecha
	 */
	public String getImpuestosAFecha() {
		return impuestosAFecha;
	}

	/**
	 * Sets the impuestos A fecha.
	 *
	 * @param impuestosAFecha
	 *            the impuestosAFecha to set
	 */
	public void setImpuestosAFecha(String impuestosAFecha) {
		this.impuestosAFecha = impuestosAFecha;
	}

	/**
	 * Gets the tasa real PF.
	 *
	 * @return the tasaRealPF
	 */
	public String getTasaRealPF() {
		return tasaRealPF;
	}

	/**
	 * Sets the tasa real PF.
	 *
	 * @param tasaRealPF
	 *            the tasaRealPF to set
	 */
	public void setTasaRealPF(String tasaRealPF) {
		this.tasaRealPF = tasaRealPF;
	}

	/**
	 * Gets the plazo real PF.
	 *
	 * @return the plazoRealPF
	 */
	public String getPlazoRealPF() {
		return plazoRealPF;
	}

	/**
	 * Sets the plazo real PF.
	 *
	 * @param plazoRealPF
	 *            the plazoRealPF to set
	 */
	public void setPlazoRealPF(String plazoRealPF) {
		this.plazoRealPF = plazoRealPF;
	}

	/**
	 * Gets the plazo actual.
	 *
	 * @return the plazoActual
	 */
	public String getPlazoActual() {
		return plazoActual;
	}

	/**
	 * Sets the plazo actual.
	 *
	 * @param plazoActual
	 *            the plazoActual to set
	 */
	public void setPlazoActual(String plazoActual) {
		this.plazoActual = plazoActual;
	}

	/**
	 * Gets the fecha alta PF.
	 *
	 * @return the fechaAltaPF
	 */
	public String getFechaAltaPF() {
		return fechaAltaPF;
	}

	/**
	 * Sets the fecha alta PF.
	 *
	 * @param fechaAltaPF
	 *            the fechaAltaPF to set
	 */
	public void setFechaAltaPF(String fechaAltaPF) {
		this.fechaAltaPF = fechaAltaPF;
	}

	/**
	 * Gets the fecha vencimiento PF.
	 *
	 * @return the fechaVencimientoPF
	 */
	public String getFechaVencimientoPF() {
		return fechaVencimientoPF;
	}

	/**
	 * Sets the fecha vencimiento PF.
	 *
	 * @param fechaVencimientoPF
	 *            the fechaVencimientoPF to set
	 */
	public void setFechaVencimientoPF(String fechaVencimientoPF) {
		this.fechaVencimientoPF = fechaVencimientoPF;
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
	 * Gets the divisa cuenta debito.
	 *
	 * @return the divisaCuentaDebito
	 */
	public String getDivisaCuentaDebito() {
		return divisaCuentaDebito;
	}

	/**
	 * Sets the divisa cuenta debito.
	 *
	 * @param divisaCuentaDebito
	 *            the divisaCuentaDebito to set
	 */
	public void setDivisaCuentaDebito(String divisaCuentaDebito) {
		this.divisaCuentaDebito = divisaCuentaDebito;
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
	 * Gets the divisa cuenta credito.
	 *
	 * @return the divisaCuentaCredito
	 */
	public String getDivisaCuentaCredito() {
		return divisaCuentaCredito;
	}

	/**
	 * Sets the divisa cuenta credito.
	 *
	 * @param divisaCuentaCredito
	 *            the divisaCuentaCredito to set
	 */
	public void setDivisaCuentaCredito(String divisaCuentaCredito) {
		this.divisaCuentaCredito = divisaCuentaCredito;
	}

	/**
	 * Gets the diferencia int A fecha.
	 *
	 * @return the diferenciaIntAFecha
	 */
	public String getDiferenciaIntAFecha() {
		return diferenciaIntAFecha;
	}

	/**
	 * Sets the diferencia int A fecha.
	 *
	 * @param diferenciaIntAFecha
	 *            the diferenciaIntAFecha to set
	 */
	public void setDiferenciaIntAFecha(String diferenciaIntAFecha) {
		this.diferenciaIntAFecha = diferenciaIntAFecha;
	}

	/**
	 * Gets the diferencia imp A fecha.
	 *
	 * @return the diferenciaImpAFecha
	 */
	public String getDiferenciaImpAFecha() {
		return diferenciaImpAFecha;
	}

	/**
	 * Sets the diferencia imp A fecha.
	 *
	 * @param diferenciaImpAFecha
	 *            the diferenciaImpAFecha to set
	 */
	public void setDiferenciaImpAFecha(String diferenciaImpAFecha) {
		this.diferenciaImpAFecha = diferenciaImpAFecha;
	}

	/**
	 * Gets the diferencia int A vencimiento.
	 *
	 * @return the diferenciaIntAVencimiento
	 */
	public String getDiferenciaIntAVencimiento() {
		return diferenciaIntAVencimiento;
	}

	/**
	 * Sets the diferencia int A vencimiento.
	 *
	 * @param diferenciaIntAVencimiento
	 *            the diferenciaIntAVencimiento to set
	 */
	public void setDiferenciaIntAVencimiento(String diferenciaIntAVencimiento) {
		this.diferenciaIntAVencimiento = diferenciaIntAVencimiento;
	}

	/**
	 * Gets the diferencia imp A vencimiento.
	 *
	 * @return the diferenciaImpAVencimiento
	 */
	public String getDiferenciaImpAVencimiento() {
		return diferenciaImpAVencimiento;
	}

	/**
	 * Sets the diferencia imp A vencimiento.
	 *
	 * @param diferenciaImpAVencimiento
	 *            the diferenciaImpAVencimiento to set
	 */
	public void setDiferenciaImpAVencimiento(String diferenciaImpAVencimiento) {
		this.diferenciaImpAVencimiento = diferenciaImpAVencimiento;
	}

	/**
	 * Gets the tasa minima actual.
	 *
	 * @return the tasaMinimaActual
	 */
	public String getTasaMinimaActual() {
		return tasaMinimaActual;
	}

	/**
	 * Sets the tasa minima actual.
	 *
	 * @param tasaMinimaActual
	 *            the tasaMinimaActual to set
	 */
	public void setTasaMinimaActual(String tasaMinimaActual) {
		this.tasaMinimaActual = tasaMinimaActual;
	}

	/**
	 * Gets the tasa maxima actual.
	 *
	 * @return the tasaMaximaActual
	 */
	public String getTasaMaximaActual() {
		return tasaMaximaActual;
	}

	/**
	 * Sets the tasa maxima actual.
	 *
	 * @param tasaMaximaActual
	 *            the tasaMaximaActual to set
	 */
	public void setTasaMaximaActual(String tasaMaximaActual) {
		this.tasaMaximaActual = tasaMaximaActual;
	}

	/**
	 * Gets the importe reajuste.
	 *
	 * @return the importeReajuste
	 */
	public String getImporteReajuste() {
		return importeReajuste;
	}

	/**
	 * Sets the importe reajuste.
	 *
	 * @param importeReajuste
	 *            the importeReajuste to set
	 */
	public void setImporteReajuste(String importeReajuste) {
		this.importeReajuste = importeReajuste;
	}

	/**
	 * Gets the cant impuestos.
	 *
	 * @return the cantImpuestos
	 */
	public Long getCantImpuestos() {
		return cantImpuestos;
	}

	/**
	 * Sets the cant impuestos.
	 *
	 * @param cantImpuestos
	 *            the cantImpuestos to set
	 */
	public void setCantImpuestos(Long cantImpuestos) {
		this.cantImpuestos = cantImpuestos;
	}

	/**
	 * Gets the impuestos PF.
	 *
	 * @return the impuestosPF
	 */
	public List<PrecancelacionImpuestosPlazoFijoEntity> getImpuestosPF() {
		return impuestosPF;
	}

	/**
	 * Sets the impuestos PF.
	 *
	 * @param impuestosPF
	 *            the impuestosPF to set
	 */
	public void setImpuestosPF(List<PrecancelacionImpuestosPlazoFijoEntity> impuestosPF) {
		this.impuestosPF = impuestosPF;
	}

	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public static String getDelimiter() {
		return DELIMITER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
