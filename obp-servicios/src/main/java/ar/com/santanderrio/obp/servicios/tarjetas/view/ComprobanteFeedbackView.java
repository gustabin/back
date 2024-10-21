/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.WebContentView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;

/**
 * The Class ComprobanteFeedbackView.
 */
@XmlRootElement(name = "comprobanteFeedback", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ComprobanteFeedbackView implements Serializable {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteFeedbackView.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The mensaje feedback. */
    private String mensajeFeedback;
    
    /** The numero préstamo */
    private String nroPrestamo;
    
    /** The numero cuotas */
    private String nroCuota;
    
    /** The numero cuotas */
    private String nroCuenta;
    
    private String importe;

    /** The nro de comprobante. */
    private String nroDeComprobante;

    /** The fecha hora. */
    private String fechaHora;

    /** The legales SEUO. */
    private String legalesSEUO;

    /** numero de control. */
    private String nroControl;

    /** variable q maneja los reintentos. */
    private String reintentar;

    /** The fecha hora. */
    private String fechaHoraMobile;

    /** The accion realizada. */
    private Boolean accionRealizada;

    /** oferta comercial si corresponde. */
    private WebContentView contenido;
    
    private AutentificacionDTO desafio;

    private boolean openStack;
    
    //TODO
    /** The cbu. */
	@NotNull
	@Size(min = 1)
	private String cbu;

	/** The importe. */
	@NotNull
	private String importeSeleccionado;

	/** The cuotas. */
	@NotNull
	private String cuotaSeleccionada;

	/** The fecha. */
	@NotNull
	@Pattern(regexp = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$")
	private String fechaSeleccionada;

	/** The motivo seleccionado. */
	@NotNull
	private DestinoPrestamoSeleccionView motivoSeleccionado;
	
	/** The isUVA. */
	@NotNull
	private boolean isUva;
	
	/** The isTasaFija. */
	@NotNull
	private boolean isTasaFija;
	
	/** The idRangoSeleccionado. */
    @NotNull
    private String idRangoSeleccionado;

    /** The legal. */
    @NotNull
    private String legal;
    
    /** The cantiCuentas. */
    private String cantiCuentas;
    
    /** The destino. */
    private String destino;
    
    //FIN TODO
	
    public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
     * Instantiates a new comprobante feedback view.
     */
    public ComprobanteFeedbackView() {
        super();
    }

    /**
     * Instantiates a new comprobante feedback view.
     *
     * @param nroDeComprobante
     *            the nro de comprobante
     * @param nroControl
     *            the nro control
     */
    public ComprobanteFeedbackView(String nroDeComprobante, String nroControl) {
        generarFechaHoraComprobante(null);
        this.nroDeComprobante = nroDeComprobante;
        this.nroControl = nroControl;
    }

    /**
     * Instantiates a new comprobante feedback view.
     *
     * @param fecha
     *            the fecha
     * @param nroDeComprobante
     *            the nro de comprobante
     * @param reintentar
     *            the reintentar
     */
    public ComprobanteFeedbackView(String fecha, String nroDeComprobante, String reintentar) {
        this.nroDeComprobante = nroDeComprobante;
        this.reintentar = reintentar;
        SimpleDateFormat dateFormatter = this.formatoFechaComprobanteFront();
        SimpleDateFormat formatoFechaHoraTrama = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            this.fechaHora = dateFormatter.format(formatoFechaHoraTrama.parse(fecha));
        } catch (java.text.ParseException ex) {
            LOGGER.error("Ha ocurrido un error al parsear la fecha", ex);
        }

    }

    /**
     * Gets the mensaje feedback.
     *
     * @return the mensaje feedback
     */
    public String getMensajeFeedback() {
        return mensajeFeedback;
    }

    /**
     * Sets the mensaje feedback.
     *
     * @param mensajeFeedback
     *            the new mensaje feedback
     */
    public void setMensajeFeedback(String mensajeFeedback) {
        this.mensajeFeedback = mensajeFeedback;
    }

    /**
     * Sets the mensaje feedback.
     *
     * @param mensaje
     *            the new mensaje feedback
     * @param importe
     *            the importe
     * @param cuota
     *            the cuota
     * @param nroPrestamo
     *            the nro prestamo
     */
    public void setMensajeFeedback(String mensaje, String importe, String cuota, String nroPrestamo) {
        this.mensajeFeedback = StringUtils.isNotBlank(mensaje)
                ? MessageFormat.format(mensaje, nroPrestamo,
                        "$ " + ISBANStringUtils.formatearConComaYDosDecimales(importe), cuota)
                : StringUtils.EMPTY;
    }

    /**
     * Gets the reintentar.
     *
     * @return the reintentar
     */
    public String getReintentar() {
        return reintentar;
    }

    /**
     * Sets the reintentar.
     *
     * @param reintentar
     *            the new reintentar
     */
    public void setReintentar(String reintentar) {
        this.reintentar = reintentar;
    }

    /**
     * Gets the nro de comprobante.
     *
     * @return the nro de comprobante
     */
    public String getNroDeComprobante() {
        return nroDeComprobante;
    }

    /**
     * Sets the nro de comprobante.
     *
     * @param nroDeComprobante
     *            the new nro de comprobante
     */
    public void setNroDeComprobante(String nroDeComprobante) {
        this.nroDeComprobante = nroDeComprobante;
    }

    /**
     * Gets the fecha hora.
     *
     * @return the fecha hora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the fecha hora.
     *
     * @param fechaHora
     *            the new fecha hora
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
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
     * Gets the legales SEUO.
     *
     * @return the legales SEUO
     */
    public String getLegalesSEUO() {
        return legalesSEUO;
    }

    /**
     * Sets the legales SEUO.
     *
     * @param legalesSEUO
     *            the new legales SEUO
     */
    public void setLegalesSEUO(String legalesSEUO) {
        this.legalesSEUO = legalesSEUO;
    }

    /**
     * Gets the fecha hora mobile.
     *
     * @return the fecha hora mobile
     */
    public String getFechaHoraMobile() {
        return fechaHoraMobile;
    }

    /**
     * Sets the fecha hora mobile.
     *
     * @param fechaHoraMobile
     *            the new fecha hora mobile
     */
    public void setFechaHoraMobile(String fechaHoraMobile) {
        this.fechaHoraMobile = fechaHoraMobile;
    }

    /**
     * Gets the accion realizada.
     *
     * @return the accion realizada
     */
    public Boolean getAccionRealizada() {
        return accionRealizada;
    }

    /**
     * Sets the accion realizada.
     *
     * @param accionRealizada
     *            the new accion realizada
     */
    public void setAccionRealizada(Boolean accionRealizada) {
        this.accionRealizada = accionRealizada;
    }

    public boolean isOpenStack() {
        return openStack;
    }

    public void setOpenStack(boolean openStack) {
        this.openStack = openStack;
    }

    /**
     * transformamos el feedback a un objeto NuevoPago.
     *
     * @return the nuevo pago
     */
    public NuevoPago transformarANuevoPago() {
        NuevoPago nuevoPago = new NuevoPago();

        nuevoPago.setNroDeComprobante(this.getNroDeComprobante());
        nuevoPago.setFechaHora(this.getFechaHora());
        nuevoPago.setLegalesSEUO(this.getLegalesSEUO());
        nuevoPago.setMensajeFeedback(this.getMensajeFeedback());
        nuevoPago.setNroControl(this.getNroControl());
        nuevoPago.setReintentar(this.getReintentar());

        return nuevoPago;
    }

    /**
     * Generar fecha hora comprobante.
     */
    public void generarFechaHoraComprobante() {
        this.generarFechaHoraComprobante(null);
    }

    /**
     * Para generar la fecha y hora por herencia.
     *
     * @param fechaHoraActual
     *            the fecha hora actual
     */
    public void generarFechaHoraComprobante(Date fechaHoraActual) {
        if (fechaHoraActual == null) {
            fechaHoraActual = new Date();
        }
        SimpleDateFormat sdf = this.formatoFechaComprobanteFront();
        this.fechaHora = sdf.format(fechaHoraActual);
        this.reintentar = "false";
    }

    /**
     * Formato fecha comprobante front.
     *
     * @return the simple date format
     */
    private SimpleDateFormat formatoFechaComprobanteFront() {
        return new SimpleDateFormat("dd/MM/yyyy - HH:mm");
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(fechaHora);
        hcb.append(fechaHoraMobile);
        hcb.append(legalesSEUO);
        hcb.append(mensajeFeedback);
        hcb.append(nroControl);
        hcb.append(nroDeComprobante);
        hcb.append(reintentar);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ComprobanteFeedbackView other = (ComprobanteFeedbackView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(fechaHora, other.getFechaHora());
        eb.append(fechaHoraMobile, other.getFechaHoraMobile());
        eb.append(legalesSEUO, other.getLegalesSEUO());
        eb.append(mensajeFeedback, other.getMensajeFeedback());
        eb.append(nroControl, other.getNroControl());
        eb.append(nroDeComprobante, other.getNroDeComprobante());
        eb.append(reintentar, other.getReintentar());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("mensajeFeedback", mensajeFeedback)
                .append("nroDeComprobante", nroDeComprobante).append("fechaHora", fechaHora)
                .append("legalesSEUO", legalesSEUO).append("nroControl", nroControl).append("reintentar", reintentar)
                .append("fechaHoraMobile", fechaHoraMobile).toString();
    }

    /**
	 * Gets the contenido.
	 *
	 * @return the contenido
	 */
    public WebContentView getContenido() {
        return contenido;
    }

    /**
	 * Sets the contenido.
	 *
	 * @param contenido
	 *            the new contenido
	 */
    public void setContenido(WebContentView contenido) {
        this.contenido = contenido;
    }

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * @return the importeSeleccionado
	 */
	public String getImporteSeleccionado() {
		return importeSeleccionado;
	}

	/**
	 * @return the cuotaSeleccionada
	 */
	public String getCuotaSeleccionada() {
		return cuotaSeleccionada;
	}

	/**
	 * @return the fechaSeleccionada
	 */
	public String getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	/**
	 * @return the motivoSeleccionado
	 */
	public DestinoPrestamoSeleccionView getMotivoSeleccionado() {
		return motivoSeleccionado;
	}

	/**
	 * @return the isUva
	 */
	public boolean isUva() {
		return isUva;
	}

	/**
	 * @return the isTasaFija
	 */
	public boolean isTasaFija() {
		return isTasaFija;
	}

	/**
	 * @return the idRangoSeleccionado
	 */
	public String getIdRangoSeleccionado() {
		return idRangoSeleccionado;
	}

	/**
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * @return the cantiCuentas
	 */
	public String getCantiCuentas() {
		return cantiCuentas;
	}

	/**
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * @param cbu the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * @param importeSeleccionado the importeSeleccionado to set
	 */
	public void setImporteSeleccionado(String importeSeleccionado) {
		this.importeSeleccionado = importeSeleccionado;
	}

	/**
	 * @param cuotaSeleccionada the cuotaSeleccionada to set
	 */
	public void setCuotaSeleccionada(String cuotaSeleccionada) {
		this.cuotaSeleccionada = cuotaSeleccionada;
	}

	/**
	 * @param fechaSeleccionada the fechaSeleccionada to set
	 */
	public void setFechaSeleccionada(String fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	/**
	 * @param motivoSeleccionado the motivoSeleccionado to set
	 */
	public void setMotivoSeleccionado(DestinoPrestamoSeleccionView motivoSeleccionado) {
		this.motivoSeleccionado = motivoSeleccionado;
	}

	/**
	 * @param isUva the isUva to set
	 */
	public void setUva(boolean isUva) {
		this.isUva = isUva;
	}

	/**
	 * @param isTasaFija the isTasaFija to set
	 */
	public void setTasaFija(boolean isTasaFija) {
		this.isTasaFija = isTasaFija;
	}

	/**
	 * @param idRangoSeleccionado the idRangoSeleccionado to set
	 */
	public void setIdRangoSeleccionado(String idRangoSeleccionado) {
		this.idRangoSeleccionado = idRangoSeleccionado;
	}

	/**
	 * @param legal the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * @param cantiCuentas the cantiCuentas to set
	 */
	public void setCantiCuentas(String cantiCuentas) {
		this.cantiCuentas = cantiCuentas;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getNroPrestamo() {
		return nroPrestamo;
	}

	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	public String getNroCuota() {
		return nroCuota;
	}

	public void setNroCuota(String nroCuota) {
		this.nroCuota = nroCuota;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	

	

}