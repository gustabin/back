package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

@Record
public class SimulacionPrecancelableUvaOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	@Field
	private String codigoRetornoExtendido;

	@Field
	private String cuentaPlazo;

	@Field
	private String secuencia;

	@Field
	private String formaPago;

	@Field
	private String codigoOperacion;

	@Field
	private String capital;

	@Field
	private String interesPrecancelacion;

	@Field
	private String impuestosPrecancelacion;

	@Field
	private String impuestosAlta;

	@Field
	private String porcentajePenalizacion;

	@Field
	private String totalAPagar;

	@Field
	private String interesAlVencimiento;

	@Field
	private String impuestosAlVencimiento;

	@Field
	private String interesesAFecha;

	@Field
	private String impuestosAFecha;

	@Field
	private String tasaRealPF;

	@Field
	private String plazoRealPF;

	@Field
	private String plazoActual;

	@Field
	private String fechaAltaPF;

	@Field
	private String fechaVencimientoPF;

	@Field
	private String cuentaDebito;

	@Field
	private String divisaCuentaDebito;

	@Field
	private String nroCuentaCredito;

	@Field
	private String divisaCuentaCredito;

	@Field
	private String diferenciaIntFechaPrec;

	@Field
	private String diferenciaImpFechaPrec;

	@Field
	private String diferenciaIntVtoPrec;

	@Field
	private String diferenciaImpVtoPrec;

	@Field
	private String tasaMinimaActual;

	@Field
	private String tasaMaximaActual;

	@Field
	private String importeReajuste;

	@Field
	private String nroOperac;

	@Field
	private String cotizacionAlta;

	@Field
	private String cotizacionPreca;

	@Field
	private String saldoInic;

	@Field
	private String saldoAjustado;

	@Field
	private String fechaPrecancelacion;

	@Field
	private String fechaMinimaPreaviso;

	@Field
	private String FechaMinimaPrecancelacion;

	@Field
	private String tipoFormulaPrecancelacion;

	/** The cant impuestos. */
	@Field()
	private Long cantImpuestos;

	/** The impuestos PF. */
	@Segment(occursRef = "cantImpuestos")
	private List<PrecancelacionImpuestosPlazoFijoEntity> impuestosPF = new ArrayList<PrecancelacionImpuestosPlazoFijoEntity>();
	
	private String codigoError;
	private String mensaje;

	public String getHeaderTrama() {
		return headerTrama;
	}

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	public String getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getInteresPrecancelacion() {
		return interesPrecancelacion;
	}

	public void setInteresPrecancelacion(String interesPrecancelacion) {
		this.interesPrecancelacion = interesPrecancelacion;
	}

	public String getImpuestosPrecancelacion() {
		return impuestosPrecancelacion;
	}

	public void setImpuestosPrecancelacion(String impuestosPrecancelacion) {
		this.impuestosPrecancelacion = impuestosPrecancelacion;
	}

	public String getImpuestosAlta() {
		return impuestosAlta;
	}

	public void setImpuestosAlta(String impuestosAlta) {
		this.impuestosAlta = impuestosAlta;
	}

	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	public String getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(String totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public String getInteresAlVencimiento() {
		return interesAlVencimiento;
	}

	public void setInteresAlVencimiento(String interesAlVencimiento) {
		this.interesAlVencimiento = interesAlVencimiento;
	}

	public String getImpuestosAlVencimiento() {
		return impuestosAlVencimiento;
	}

	public void setImpuestosAlVencimiento(String impuestosAlVencimiento) {
		this.impuestosAlVencimiento = impuestosAlVencimiento;
	}

	public String getInteresesAFecha() {
		return interesesAFecha;
	}

	public void setInteresesAFecha(String interesesAFecha) {
		this.interesesAFecha = interesesAFecha;
	}

	public String getImpuestosAFecha() {
		return impuestosAFecha;
	}

	public void setImpuestosAFecha(String impuestosAFecha) {
		this.impuestosAFecha = impuestosAFecha;
	}

	public String getTasaRealPF() {
		return tasaRealPF;
	}

	public void setTasaRealPF(String tasaRealPF) {
		this.tasaRealPF = tasaRealPF;
	}

	public String getPlazoRealPF() {
		return plazoRealPF;
	}

	public void setPlazoRealPF(String plazoRealPF) {
		this.plazoRealPF = plazoRealPF;
	}

	public String getPlazoActual() {
		return plazoActual;
	}

	public void setPlazoActual(String plazoActual) {
		this.plazoActual = plazoActual;
	}

	public String getFechaAltaPF() {
		return fechaAltaPF;
	}

	public void setFechaAltaPF(String fechaAltaPF) {
		this.fechaAltaPF = fechaAltaPF;
	}

	public String getFechaVencimientoPF() {
		return fechaVencimientoPF;
	}

	public void setFechaVencimientoPF(String fechaVencimientoPF) {
		this.fechaVencimientoPF = fechaVencimientoPF;
	}

	public String getCuentaDebito() {
		return cuentaDebito;
	}

	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	public String getDivisaCuentaDebito() {
		return divisaCuentaDebito;
	}

	public void setDivisaCuentaDebito(String divisaCuentaDebito) {
		this.divisaCuentaDebito = divisaCuentaDebito;
	}

	public String getNroCuentaCredito() {
		return nroCuentaCredito;
	}

	public void setNroCuentaCredito(String nroCuentaCredito) {
		this.nroCuentaCredito = nroCuentaCredito;
	}

	public String getDivisaCuentaCredito() {
		return divisaCuentaCredito;
	}

	public void setDivisaCuentaCredito(String divisaCuentaCredito) {
		this.divisaCuentaCredito = divisaCuentaCredito;
	}

	public String getDiferenciaIntFechaPrec() {
		return diferenciaIntFechaPrec;
	}

	public void setDiferenciaIntFechaPrec(String diferenciaIntFechaPrec) {
		this.diferenciaIntFechaPrec = diferenciaIntFechaPrec;
	}

	public String getDiferenciaImpFechaPrec() {
		return diferenciaImpFechaPrec;
	}

	public void setDiferenciaImpFechaPrec(String diferenciaImpFechaPrec) {
		this.diferenciaImpFechaPrec = diferenciaImpFechaPrec;
	}

	public String getDiferenciaIntVtoPrec() {
		return diferenciaIntVtoPrec;
	}

	public void setDiferenciaIntVtoPrec(String diferenciaIntVtoPrec) {
		this.diferenciaIntVtoPrec = diferenciaIntVtoPrec;
	}

	public String getDiferenciaImpVtoPrec() {
		return diferenciaImpVtoPrec;
	}

	public void setDiferenciaImpVtoPrec(String diferenciaImpVtoPrec) {
		this.diferenciaImpVtoPrec = diferenciaImpVtoPrec;
	}

	public String getTasaMinimaActual() {
		return tasaMinimaActual;
	}

	public void setTasaMinimaActual(String tasaMinimaActual) {
		this.tasaMinimaActual = tasaMinimaActual;
	}

	public String getTasaMaximaActual() {
		return tasaMaximaActual;
	}

	public void setTasaMaximaActual(String tasaMaximaActual) {
		this.tasaMaximaActual = tasaMaximaActual;
	}

	public String getImporteReajuste() {
		return importeReajuste;
	}

	public void setImporteReajuste(String importeReajuste) {
		this.importeReajuste = importeReajuste;
	}

	public String getNroOperac() {
		return nroOperac;
	}

	public void setNroOperac(String nroOperac) {
		this.nroOperac = nroOperac;
	}

	public String getCotizacionAlta() {
		return cotizacionAlta;
	}

	public void setCotizacionAlta(String cotizacionAlta) {
		this.cotizacionAlta = cotizacionAlta;
	}

	public String getCotizacionPreca() {
		return cotizacionPreca;
	}

	public void setCotizacionPreca(String cotizacionPreca) {
		this.cotizacionPreca = cotizacionPreca;
	}

	public String getSaldoInic() {
		return saldoInic;
	}

	public void setSaldoInic(String saldoInic) {
		this.saldoInic = saldoInic;
	}

	public String getSaldoAjustado() {
		return saldoAjustado;
	}

	public void setSaldoAjustado(String saldoAjustado) {
		this.saldoAjustado = saldoAjustado;
	}

	public String getFechaPrecancelacion() {
		return fechaPrecancelacion;
	}

	public void setFechaPrecancelacion(String fechaPrecancelacion) {
		this.fechaPrecancelacion = fechaPrecancelacion;
	}

	public String getFechaMinimaPreaviso() {
		return fechaMinimaPreaviso;
	}

	public void setFechaMinimaPreaviso(String fechaMinimaPreaviso) {
		this.fechaMinimaPreaviso = fechaMinimaPreaviso;
	}

	public String getFechaMinimaPrecancelacion() {
		return FechaMinimaPrecancelacion;
	}

	public void setFechaMinimaPrecancelacion(String fechaMinimaPrecancelacion) {
		FechaMinimaPrecancelacion = fechaMinimaPrecancelacion;
	}

	public String getTipoFormulaPrecancelacion() {
		return tipoFormulaPrecancelacion;
	}

	public void setTipoFormulaPrecancelacion(String tipoFormulaPrecancelacion) {
		this.tipoFormulaPrecancelacion = tipoFormulaPrecancelacion;
	}

	public Long getCantImpuestos() {
		return cantImpuestos;
	}

	public void setCantImpuestos(Long cantImpuestos) {
		this.cantImpuestos = cantImpuestos;
	}

	public List<PrecancelacionImpuestosPlazoFijoEntity> getImpuestosPF() {
		return impuestosPF;
	}

	public void setImpuestosPF(List<PrecancelacionImpuestosPlazoFijoEntity> impuestosPF) {
		this.impuestosPF = impuestosPF;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
