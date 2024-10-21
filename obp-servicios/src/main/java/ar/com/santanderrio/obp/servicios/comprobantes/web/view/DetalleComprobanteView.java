/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleComprobanteView.
 *
 * @author dante.omar.olmedo
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetalleComprobanteView extends ComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo comprobante. */
	private String tituloComprobante;

	/** The tipo comprobante. */
	private String tipoComprobante;

	/** The nro cuenta origen. */
	private String nroCuentaOrigen;

	/** The tipo cuenta origen. */
	private String tipoCuentaOrigen;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The importe acreditado. */
	private String importeAcreditado;

	/** The identificacion. */
	private String identificacion;

	/** The fecha *. */
	private String fechaOperacion;

	/** The empresa *. */
	private String empresa;

	/** The cuit. */
	private String cuit;

	/** The tipo cuit. */
	private String tipoCuit;

	/** The estado. */
	private String estado;

	/** The estado. */
	private String cotizacionAplicada;
	
	/** The hora. */
	private String hora;
	
	/** The nro control. */
	private String nroControl;
	
	/** The label dinamico. */
	private String labelDinamico;
	
	/** The nro transaccion. */
	private String nroTransaccion;

	/** The titulo. */
	protected final String tituloKey = "TITULO";

	/** The peso. */
	private final String peso = "$ ";

	/** The dolar. */
	private final String dolar = "u$s ";

	/** The salto linea. */
	protected final String saltoLinea = "<br>";

	/** The numero origen. */
	protected final String numeroOrigenKey = "NUMERO_ORIGEN";

	/** The tipo origen. */
	protected final String tipoOrigenKey = "TIPO_ORIGEN";

	/** The cuit. */
	protected final String cuitKey = "CUIT";

	/** The tipo cuit. */
	protected final String tipoCuitKey = "TIPO_CUIT";

	/** The tipo cuit. */
	protected final String elementoAdicionalKey = "ELEMENTO_ADICIONAL";

	/** The tipo cuit. */
	protected final String tipoElementoAdicionalKey = "TIPO_ELEMENTO_ADICIONAL";

	/** The fecha actual *. */
	protected final String fechaActualKey = "FECHA_ACTUAL";

	/** The numero comprobante. */
	protected final String numeroComprobanteKey = "NUMERO_COMPROBANTE";
	
	/** The logo pmc. */
    protected final String logoPMCKey = "LOGO_PMC";

	/** The logo titulo. */
	protected final String logoTituloKey = "LOGO_TITULO";

	/** The importe. */
	protected final String importeKey = "IMPORTE";

	/** The descripcion. */
	protected final String descripcionKey = "DESCRIPCION";

	/** The fecha pago tarjeta. */
	protected final String fechaPagoKey = "FECHA_PAGO";

	/** The empresa. */
	protected final String empresaKey = "EMPRESA";

	/** The medio pago. */
	protected final String medioPagoKey = "MEDIO_PAGO";
	
	/** The tipo medio pago. */
    private final String tipoMedioPagoKey = "TIPO_MEDIO_PAGO";
    
    /** The fecha y hora. */
    private final String fechaYHoraKey = "FECHA_Y_HORA";
    
    /** The numero control. */
    private final String numeroControlKey = "NUMERO_CONTROL";

	/** The estado. */
	protected final String estadoKey = "ESTADO";

	/** The tipo identificacion. */
	protected final String tipoIdentificacionKey = "TIPO_IDENTIFICACION";

	/** The identificacion. */
	protected final String identificacionKey = "IDENTIFICACION";

	/** The fecha vencimiento top. */
	protected final String fechaVencimientoKey = "FECHA_VENCIMIENTO";

	/** The path. */
	protected final String path = "classpath:/report/comprobantes/";
	
	/** The logo pmc. */
    private final String logoPMC = "logo-pago-mis-cuentas.png";

	/** The transferencias jasper. */
	protected final String transferenciasJasper = "transferencias.jasper";

	/** The logo. */
	protected final String logoGenerico = "logo-generico-nuevo-pago.png";
	
	/** The pmc servicio. */
    private String pmcServicioJasper = "pmc-servicio.jasper";
    
	private boolean esDebitoAutomatico;
    
	/**
	 * Instantiates a new detalle comprobante view.
	 */
	public DetalleComprobanteView() {
		super();
	}

	/**
	 * Gets the titulo comprobante.
	 *
	 * @return the titulo comprobante
	 */
	public String getTituloComprobante() {
		return tituloComprobante;
	}

	/**
	 * Sets the titulo comprobante.
	 *
	 * @param tituloComprobante
	 *            the new titulo comprobante
	 */
	public void setTituloComprobante(String tituloComprobante) {
		this.tituloComprobante = tituloComprobante;
	}

	/**
	 * Gets the tipo comprobante.
	 *
	 * @return the tipo comprobante
	 */
	public String getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * Sets the tipo comprobante.
	 *
	 * @param tipoComprobante
	 *            the new tipo comprobante
	 */
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	/**
	 * Gets the nro cuenta origen.
	 *
	 * @return the nro cuenta origen
	 */
	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	/**
	 * Sets the nro cuenta origen.
	 *
	 * @param nroCuentaOrigen
	 *            the new nro cuenta origen
	 */
	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipo cuenta origen
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the new tipo cuenta origen
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
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
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the importe acreditado.
	 *
	 * @return the importe acreditado
	 */
	public String getImporteAcreditado() {
		return importeAcreditado;
	}

	/**
	 * Sets the importe acreditado.
	 *
	 * @param importeAcreditado
	 *            the new importe acreditado
	 */
	public void setImporteAcreditado(String importeAcreditado) {
		this.importeAcreditado = importeAcreditado;
	}

	/**
	 * Gets the identificacion.
	 *
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * Sets the identificacion.
	 *
	 * @param identificacion
	 *            the new identificacion
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
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
	 * @param empresa
	 *            the new empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the fecha operacion.
	 *
	 * @return the fecha operacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion
	 *            the new fecha operacion
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the tipo cuit.
	 *
	 * @return the tipoCuit
	 */
	public String getTipoCuit() {
		return tipoCuit;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Sets the tipo cuit.
	 *
	 * @param tipoCuit
	 *            the tipoCuit to set
	 */
	public void setTipoCuit(String tipoCuit) {
		this.tipoCuit = tipoCuit;
	}

	/**
	 * Gets the cotizacion aplicada.
	 *
	 * @return the cotizacion aplicada
	 */
	public String getCotizacionAplicada() {
		return cotizacionAplicada;
	}

	/**
	 * Sets the cotizacion aplicada.
	 *
	 * @param cotizacionAplicada
	 *            the new cotizacion aplicada
	 */
	public void setCotizacionAplicada(String cotizacionAplicada) {
		this.cotizacionAplicada = cotizacionAplicada;
	}

	/**
	 * Obtener importe.
	 *
	 * @return the string
	 */
	protected String obtenerImporte() {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(getImportePesos()) && StringUtils.isNotBlank(getImporteDolares())) {
			return sb.append(peso).append(getImportePesos()).append(saltoLinea).append(dolar)
					.append(getImporteDolares()).toString();
		} else if (StringUtils.isNotBlank(getImportePesos())) {
			return sb.append(peso).append(getImportePesos()).toString();
		}
		return sb.append(dolar).append(getImporteDolares()).toString();
	}

	/**
	 * Obtener parametros PDF.
	 *
	 * @return the hash map
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
	    HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(tituloKey, getEmpresa());
        parametros.put(importeKey, obtenerImporte());
        parametros.put(empresaKey, getEmpresa());
        parametros.put(tipoIdentificacionKey, getLabelDinamico());
        parametros.put(identificacionKey, getIdentificacion());
        parametros.put(medioPagoKey, getNroCuentaOrigen());
        parametros.put(tipoMedioPagoKey, getTipoCuentaOrigen());
        parametros.put(fechaYHoraKey, obtenerFechaHora());
        parametros.put(numeroControlKey, getNroControl());
        parametros.put(numeroComprobanteKey, getNroTransaccion());
        parametros.put(logoPMCKey, ResourceUtils.getFile(path + logoPMC).getPath());
        return parametros;
	}
	
	/**
     * Obtener fecha hora.
     *
     * @return the string
     */
    protected String obtenerFechaHora() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(getHora())) {
            return sb.append(getFechaOperacion()).append(" - ").append(getHora()).append(" hs").toString();
        }
        return getFechaOperacion();
    }

	/**
	 * Obtener jasper.
	 *
	 * @return the string
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public String obtenerJasper() throws FileNotFoundException {
	    return ResourceUtils.getFile(path + pmcServicioJasper).getPath();
	}

    /**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
    public String getHora() {
        return hora;
    }

    /**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
	 * Gets the nro control.
	 *
	 * @return the nro control
	 */
    public String getNroControl() {
        return nroControl;
    }

    /**
	 * Sets the nro control.
	 *
	 * @param nroControl
	 *            the new nro control
	 */
    public void setNroControl(String nroControl) {
        this.nroControl = nroControl;
    }

    /**
	 * Gets the label dinamico.
	 *
	 * @return the label dinamico
	 */
    public String getLabelDinamico() {
        return labelDinamico;
    }

    /**
	 * Sets the label dinamico.
	 *
	 * @param labelDinamico
	 *            the new label dinamico
	 */
    public void setLabelDinamico(String labelDinamico) {
        this.labelDinamico = labelDinamico;
    }

    /**
	 * Gets the nro transaccion.
	 *
	 * @return the nro transaccion
	 */
    public String getNroTransaccion() {
        return nroTransaccion;
    }

    /**
	 * Sets the nro transaccion.
	 *
	 * @param nroTransaccion
	 *            the new nro transaccion
	 */
    public void setNroTransaccion(String nroTransaccion) {
        this.nroTransaccion = nroTransaccion;
    }
    
	public boolean getEsDebitoAutomatico() {
		return esDebitoAutomatico;
	}

	public void setEsDebitoAutomatico(boolean esDebitoAutomatico) {
		this.esDebitoAutomatico = esDebitoAutomatico;
	}
	
    public String getPmcServicioJasper() {
		return pmcServicioJasper;
	}

	public void setPmcServicioJasper(String pmcServicioJasper) {
		this.pmcServicioJasper = pmcServicioJasper;
	}
}
