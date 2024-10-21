package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosComprobanteDebinRecurrente extends DetalleComprobanteView {

	private static final long serialVersionUID = 1L;

	private static final String EMPRESA_KEY = "NOMBRE_EMPRESA";

	private static final String SERVICIO_KEY = "SERVICIO";

	private static final String CUIT_VENDEDOR_KEY = "CUIT_VENDEDOR";

	private static final String REFERENCIA_KEY = "REFERENCIA";

	private static final String NRO_CUENTA_DEBITO_KEY = "NRO_CUENTA_DEBITO";

	private static final String TIPO_CUENTA_DEBITO_KEY = "TIPO_CUENTA_DEBITO";

	private static final String CUIT_COMPRADOR_KEY = "CUIT_COMPRADOR";

	private static final String CONCEPTO_KEY = "CONCEPTO";

	private static final String DESCRIPCION_KEY = "DESCRIPCION";

	private static final String FECHA_OPERACION_KEY = "FECHA_OPERACION";

	private static final String NUMERO_COMPROBANTE_KEY = "NRO_COMPROBANTE";

	private static final String RECURRENCIA_KEY = "NRO_RECURRENCIA";

	private static final String FECHA_HORA_COMPROBANTE_KEY = "FECHA_HORA_COMPROBANTE";

	private static final String PATH_EXTRACCION_EFECTIVO = "classpath:/report/debinRecurrente/";

	private static final String NOMBRE_ARCHIVO_JASPER = "comprobanteDebinRecurrente.jasper";


	private String empresa;

	private String servicio;

	private String cuitVendedor;

	private String referencia;
	
	private String numeroCuenta;

	private String tipoCuenta;
	
	private String cuitComprador;

	private String concepto;

	private String descripcion;

	private String fechaOperacion;
	
	private String numeroComprobante;

	private String idRecurrencia;
	
	private String fechaHoraComprobante;
	
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
	    HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(EMPRESA_KEY, getEmpresa());
        parametros.put(SERVICIO_KEY, getServicio());
        parametros.put(CUIT_VENDEDOR_KEY, getCuitVendedor());
        parametros.put(REFERENCIA_KEY, getReferencia());
        parametros.put(NRO_CUENTA_DEBITO_KEY, getNumeroCuenta());
        parametros.put(TIPO_CUENTA_DEBITO_KEY, getTipoCuenta());
        parametros.put(CUIT_COMPRADOR_KEY, getCuitComprador());
        parametros.put(CONCEPTO_KEY, getConcepto());
        parametros.put(DESCRIPCION_KEY, getDescripcion());
        parametros.put(FECHA_OPERACION_KEY, getFechaOperacion());
        parametros.put(NUMERO_COMPROBANTE_KEY, getNumeroComprobante());
        parametros.put(RECURRENCIA_KEY, getIdRecurrencia());
        parametros.put(FECHA_HORA_COMPROBANTE_KEY, getFechaHoraComprobante());
        return parametros;
	}
	
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(PATH_EXTRACCION_EFECTIVO + NOMBRE_ARCHIVO_JASPER).getPath();
    }
    
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getServicio() {
		return servicio;
	}
	
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	public String getCuitVendedor() {
		return cuitVendedor;
	}
	
	public void setCuitVendedor(String cuitVendedor) {
		this.cuitVendedor = cuitVendedor;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getCuitComprador() {
		return cuitComprador;
	}
	
	public void setCuitComprador(String cuitComprador) {
		this.cuitComprador = cuitComprador;
	}
	
	public String getConcepto() {
		return concepto;
	}
	
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	
	public String getIdRecurrencia() {
		return idRecurrencia;
	}
	
	public void setIdRecurrencia(String idRecurrencia) {
		this.idRecurrencia = idRecurrencia;
	}

	public String getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	public void setFechaHoraComprobante(String fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}
			
}
