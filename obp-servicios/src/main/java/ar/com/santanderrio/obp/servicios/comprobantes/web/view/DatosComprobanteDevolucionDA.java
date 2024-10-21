package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * The Class DatosComprobanteDevolucionDA.
 */
public class DatosComprobanteDevolucionDA {
	
	/** The empresa. */
	private String empresa;
	
	/** The Constant EMPRESA_KEY. */
	private static final String EMPRESA_KEY = "EMPRESA";
	
	/** The nro identificacion. */
	private String nroIdentificacion;
	
	/** The Constant NRO_IDENTIFICACION_KEY. */
	private static final String NRO_IDENTIFICACION_KEY = "NRO_IDENTIFICACION";
	
	/** The nro cuenta debito. */
	private String nroCuentaDebito;
	
	/** The Constant NRO_CUENTA_DEBITO_KEY. */
	private static final String NRO_CUENTA_DEBITO_KEY = "NRO_CUENTA_DEBITO";
	
	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;
	
	/** The Constant TIPO_CUENTA_DEBITO_KEY. */
	private static final String TIPO_CUENTA_DEBITO_KEY = "TIPO_CUENTA_DEBITO";
	
	/** The monto. */
	private String monto;
	
	/** The Constant MONTO_KEY. */
	private static final String MONTO_KEY = "MONTO";
	
	/** The fecha vencimiento. */
	private String fechaVencimiento;
	
	/** The Constant FECHA_VENCIMIENTO_KEY. */
	private static final String FECHA_VENCIMIENTO_KEY = "FECHA_VENCIMIENTO";
	
	/** The fecha pago. */
	private String fechaPago;
	
	/** The Constant FECHA_PAGO_KEY. */
	private static final String FECHA_PAGO_KEY = "FECHA_PAGO";
	
	/** The fecha devolucion. */
	private String fechaDevolucion;
	
	/** The Constant FECHA_DEVOLUCION_KEY. */
	private static final String FECHA_DEVOLUCION_KEY = "FECHA_DEVOLUCION";
	
	/** The nro comprobante. */
	private String nroComprobante;
	
	/** The Constant NRO_COMPROBANTE_KEY. */
	private static final String NRO_COMPROBANTE_KEY = "NRO_COMPROBANTE";

	/** The fecha hora comprobante. */
	private String fechaHoraComprobante;

	/** The Constant FECHA_HORA_COMPROBANTE_KEY. */
	private static final String FECHA_HORA_COMPROBANTE_KEY = "FECHA_HORA_COMPROBANTE";

	/** The Constant PATH_EXTRACCION_EFECTIVO. */
	private static final String PATH_EXTRACCION_EFECTIVO = "classpath:/report/devolucionDebitoAutomatico/";
	
    /** The Constant NOMBRE_ARCHIVO_JASPER. */
    private static final String NOMBRE_ARCHIVO_JASPER = "comprobanteDevolucionDebitoAutomatico.jasper";

	
	/**
	 * Obtener parametros PDF.
	 *
	 * @return the hash map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
	    HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(EMPRESA_KEY, getEmpresa());
        parametros.put(NRO_IDENTIFICACION_KEY, getNroIdentificacion());
        parametros.put(NRO_CUENTA_DEBITO_KEY, getNroCuentaDebito());
        parametros.put(TIPO_CUENTA_DEBITO_KEY, getTipoCuentaDebito());
	    parametros.put(MONTO_KEY, DivisaEnum.PESO.getSimbolo() + " " + getMonto());
        parametros.put(FECHA_VENCIMIENTO_KEY, getFechaVencimiento());
        parametros.put(FECHA_PAGO_KEY, getFechaPago());
        parametros.put(FECHA_DEVOLUCION_KEY, getFechaDevolucion());
        parametros.put(NRO_COMPROBANTE_KEY, getNroComprobante());
        parametros.put(FECHA_HORA_COMPROBANTE_KEY, getFechaHoraComprobante());
        return parametros;
	}
	
    /**
     * Obtener jasper.
     *
     * @return the string
     * @throws FileNotFoundException the file not found exception
     */
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(PATH_EXTRACCION_EFECTIVO + NOMBRE_ARCHIVO_JASPER).getPath();
    }
	
	
	
	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa the new empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the nro identificacion.
	 *
	 * @return the nro identificacion
	 */
	public String getNroIdentificacion() {
		return nroIdentificacion;
	}

	/**
	 * Sets the nro identificacion.
	 *
	 * @param nroIdentificacion the new nro identificacion
	 */
	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	/**
	 * Gets the nro cuenta debito.
	 *
	 * @return the nro cuenta debito
	 */
	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	/**
	 * Sets the nro cuenta debito.
	 *
	 * @param nroCuentaDebito the new nro cuenta debito
	 */
	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the fecha pago.
	 *
	 * @return the fecha pago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fechaPago the new fecha pago
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Gets the fecha devolucion.
	 *
	 * @return the fecha devolucion
	 */
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	/**
	 * Sets the fecha devolucion.
	 *
	 * @param fechaDevolucion the new fecha devolucion
	 */
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the fecha hora comprobante.
	 *
	 * @return the fecha hora comprobante
	 */
	public String getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	/**
	 * Sets the fecha hora comprobante.
	 *
	 * @param fechaHoraComprobante the new fecha hora comprobante
	 */
	public void setFechaHoraComprobante(String fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}
	
}
