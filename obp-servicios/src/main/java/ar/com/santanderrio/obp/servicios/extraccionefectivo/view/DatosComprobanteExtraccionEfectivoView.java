package ar.com.santanderrio.obp.servicios.extraccionefectivo.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

public class DatosComprobanteExtraccionEfectivoView extends DetalleComprobanteView{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String monto;
	
	private static final String MONTO_KEY = "MONTO";

    private static final String MONEDA_PESOS = "$ ";

	private String nroCuentaOrigen;
	
	private static final String NRO_CUENTA_ORIGEN_KEY = "NRO_CUENTA_ORIGEN";
	
	private String tipoCuentaOrigen;
	
	private static final String TIPO_CUENTA_ORIGEN_KEY = "TIPO_CUENTA_ORIGEN";
	
	private String nombreDestinatario;
	
	private static final String NOMBRE_DESTINATARIO_KEY = "NOMBRE_DESTINATARIO";

	private String dniDestinatario;
	
	private static final String DNI_DESTINATARIO_KEY = "DNI_DESTINATARIO";

	private String emailDestinatario;
	
	private static final String EMAIL_DESTINATARIO_KEY = "EMAIL_DESTINATARIO";

	private String fechaVencimiento;
	
	private static final String FECHA_VENCIMIENTO_KEY = "FECHA_VENCIMIENTO";

	private String codigoTransaccion;
	
	private static final String CODIGO_TRANSACCION_KEY = "CODIGO_TRANSACCION";

	private String nroComprobante;
	
	private static final String NRO_COMPROBANTE_KEY = "NRO_COMPROBANTE";

	private String fechaHoraComprobante;

	private static final String FECHA_HORA_COMPROBANTE_KEY = "FECHA_HORA_COMPROBANTE";

    private String codigoExtraccion;

    private static final String CODIGO_EXTRACCION_KEY = "CODIGO_EXTRACCION";

	private static final String PATH_EXTRACCION_EFECTIVO = "classpath:/report/extraccionEfectivo/";
	
    private static final String NOMBRE_ARCHIVO_JASPER = "comprobanteExtraccionEfectivo.jasper";

	
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
	    HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(MONTO_KEY, MONEDA_PESOS + getMonto());
        parametros.put(NRO_CUENTA_ORIGEN_KEY, getNroCuentaOrigen());
        parametros.put(TIPO_CUENTA_ORIGEN_KEY, getTipoCuentaOrigen());
        parametros.put(NOMBRE_DESTINATARIO_KEY, getNombreDestinatario());
        parametros.put(DNI_DESTINATARIO_KEY, getDniDestinatario());
        parametros.put(EMAIL_DESTINATARIO_KEY, getEmailDestinatario());
        parametros.put(FECHA_VENCIMIENTO_KEY, getFechaVencimiento());
        parametros.put(CODIGO_TRANSACCION_KEY, getCodigoTransaccion());
        parametros.put(NRO_COMPROBANTE_KEY, getNroComprobante());
        parametros.put(FECHA_HORA_COMPROBANTE_KEY, getFechaHoraComprobante());
        parametros.put(CODIGO_EXTRACCION_KEY, getCodigoExtraccion());
        return parametros;
	}
	
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(PATH_EXTRACCION_EFECTIVO + NOMBRE_ARCHIVO_JASPER).getPath();
    }
    
	
	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
	}

	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
	}

	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public String getDniDestinatario() {
		return dniDestinatario;
	}

	public void setDniDestinatario(String dniDestinatario) {
		this.dniDestinatario = dniDestinatario;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCodigoTransaccion() {
		return codigoTransaccion;
	}

	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
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

    /**
     * @return the codigoExtraccion
     */
    public String getCodigoExtraccion() {
        return codigoExtraccion;
    }

    /**
     * @param codigoExtraccion the codigoExtraccion to set
     */
    public void setCodigoExtraccion(String codigoExtraccion) {
        this.codigoExtraccion = codigoExtraccion;
    }
	
}
