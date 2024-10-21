package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

public class ComprobanteCancelacionTotalPrestamoView {

	private static final String MONTO_KEY = "MONTO";
	private static final String NUMERO_PRESTAMO_KEY = "NUMERO_PRESTAMO";
	private static final String CAPITAL_KEY = "CAPITAL";
	private static final String INTERESES_KEY = "INTERESES";
	private static final String IVA_KEY = "IVA";
	private static final String INGRESOS_BRUTOS_KEY = "INGRESOS_BRUTOS";
	private static final String OTROS_IMPUESTOS_KEY = "OTROS_IMPUESTOS";
	private static final String COMISIONES_KEY = "COMISIONES";
	private static final String GASTOS_KEY = "GASTOS";
	private static final String SEGURO_KEY = "SEGURO";
	private static final String COTIZACION_COEFICIENTE_KEY = "COTIZACION_COEFICIENTE";
	private static final String FECHA_COTIZACION_COEFICIENTE_KEY = "FECHA_COTIZACION_COEFICIENTE";
	private static final String CAPITAL_UVA_KEY = "CAPITAL_UVA";
	private static final String INTERES_UVA_KEY = "INTERES_UVA";
	private static final String NRO_CUENTA_DEBITO_KEY = "NRO_CUENTA_DEBITO";
	private static final String TIPO_CUENTA_DEBITO_KEY = "TIPO_CUENTA_DEBITO";
	private static final String NRO_COMPROBANTE_KEY = "NRO_COMPROBANTE";
	private static final String FECHA_HORA_COMPROBANTE_KEY = "FECHA_HORA_COMPROBANTE";
	private static final String MENSAJE_REFINANCIACION = "MENSAJE_REFINANCIACION";
	private static final String LEGALES = "LEGALES";
	private static final String PATH_EXTRACCION_EFECTIVO = "classpath:/report/cancelacionTotalPrestamo/";
	private static final String NOMBRE_ARCHIVO_JASPER = "comprobanteCancelacionTotalPrestamo.jasper";

	private String monto;
	private String numeroPrestamo;
	private String capital;
	private String intereses;
	private String iva;
	private String ingresosBrutos;
	private String otrosImpuestos;
	private String comisiones;
	private String gastos;
	private String seguro;
	private String cotizacionCoeficiente;
	private String fechaCotizacionComprobante;
	private String capitalUVA;
	private String interesUVA;
	private String nroCuentaDebito;
	private String tipoCuentaDebito;
	private String nroComprobante;
	private String fechaHoraComprobante;
	private String mensajeRefinanciacion;
	private String legalOtrosImpuestos;

	
	public HashMap<String, Object> mapPDFParameters() throws IOException {
	    HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(MONTO_KEY, getMonto());
        parametros.put(NUMERO_PRESTAMO_KEY, getNumeroPrestamo());
        parametros.put(CAPITAL_KEY, getCapital());
        parametros.put(INTERESES_KEY, getIntereses());
        parametros.put(OTROS_IMPUESTOS_KEY, getOtrosImpuestos());
        parametros.put(COMISIONES_KEY, getComisiones());
        parametros.put(IVA_KEY, getIva());
        parametros.put(INGRESOS_BRUTOS_KEY, getIngresosBrutos());
        parametros.put(GASTOS_KEY, getGastos());
        parametros.put(SEGURO_KEY, getSeguro());
        parametros.put(COTIZACION_COEFICIENTE_KEY, getCotizacionCoeficiente());
        parametros.put(CAPITAL_UVA_KEY, getCapitalUVA());
        parametros.put(INTERES_UVA_KEY, getInteresUVA());  
        parametros.put(NRO_CUENTA_DEBITO_KEY, getNroCuentaDebito());
        parametros.put(TIPO_CUENTA_DEBITO_KEY, getTipoCuentaDebito());
        parametros.put(NRO_COMPROBANTE_KEY, getNroComprobante());
        parametros.put(FECHA_HORA_COMPROBANTE_KEY, getFechaHoraComprobante());
        parametros.put(MENSAJE_REFINANCIACION, getMensajeRefinanciacion());
        parametros.put(LEGALES, "(1)" + getLegalOtrosImpuestos());
        parametros.put(FECHA_COTIZACION_COEFICIENTE_KEY, getFechaCotizacionCoeficiente());
        return parametros;
	}

	public String getJasperFilePath() throws FileNotFoundException {
        return ResourceUtils.getFile(PATH_EXTRACCION_EFECTIVO + NOMBRE_ARCHIVO_JASPER).getPath();
    }

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}
	
	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getIngresosBrutos() {
		return ingresosBrutos;
	}

	public void setIngresosBrutos(String ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	public String getOtrosImpuestos() {
		return otrosImpuestos;
	}

	public void setOtrosImpuestos(String otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}

	public String getComisiones() {
		return comisiones;
	}

	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	public String getGastos() {
		return gastos;
	}

	public void setGastos(String gastos) {
		this.gastos = gastos;
	}

	public String getSeguro() {
		return seguro;
	}

	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}

	public String getCotizacionCoeficiente() {
		return cotizacionCoeficiente;
	}

	public void setCotizacionCoeficiente(String cotizacionCoeficiente) {
		this.cotizacionCoeficiente = cotizacionCoeficiente;
	}

	public String getFechaCotizacionCoeficiente() {
		return fechaCotizacionComprobante;
	}

	public void setFechaCotizacionCoeficiente(String fechaCotizacionComprobante) {
		this.fechaCotizacionComprobante = fechaCotizacionComprobante;
	}

	public String getCapitalUVA() {
		return capitalUVA;
	}

	public void setCapitalUVA(String capitalUVA) {
		this.capitalUVA = capitalUVA;
	}

	public String getInteresUVA() {
		return interesUVA;
	}

	public void setInteresUVA(String interesUVA) {
		this.interesUVA = interesUVA;
	}

	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	public void setFechaHoraComprobante(String fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}

	public String getMensajeRefinanciacion() {
		return mensajeRefinanciacion;
	}

	public void setMensajeRefinanciacion(String mensajeRefinanciacion) {
		this.mensajeRefinanciacion = mensajeRefinanciacion;
	}

	public String getLegalOtrosImpuestos() {
		return legalOtrosImpuestos;
	}

	public void setLegalOtrosImpuestos(String legalOtrosImpuestos) {
		this.legalOtrosImpuestos = legalOtrosImpuestos;
	}

}
