/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ComprobanteView.
 *
 * @author sabrina.cis
 */
public class ComprobanteView extends View {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The fecha. */
    private String fecha;

    /** The transaccion. */
    private String transaccion;

    /** The importe. */
    private String importePesos;

    /** The importe dolares. */
    private String importeDolares;

    /** The destinatario. */
    private String destinatario;

    /** The cta medio de pago. */
    private String ctaMedioDePagoPesos;

    /** The tipo cta medio de pago. */
    private String tipoCtaMedioDePagoPesos;

    /** The cta medio de pago. */
    private String ctaMedioDePagoDolares;

    /** The tipo cta medio de pago. */
    private String tipoCtaMedioDePagoDolares;

    /** The is efectuada. */
    private boolean isEfectuada;

    /** The id comprobante. */
    private String idComprobante;

    /** The destinatario. */
    protected final String destinatarioKey = "DESTINATARIO";

    /** The fecha ejecucion. */
    protected final String fechaEjecucionKey = "FECHA_EJECUCION";

    /** The importe. */
    protected final String importeKey = "IMPORTE";

    /** The tipo cta medio de pago. */
    private String tipoCuentaDestino;
    
    private Boolean mostrarDevolucionDA = Boolean.FALSE;

    private String idCliente;
    
    private String cuitEmpresa;
    
    private String servicio;
    
    private String partida;
    
    /**
     * Instantiates a new comprobante view.
     */
    public ComprobanteView() {
        super();
    }

    /**
     * Constructor de comprobante view a partir de comprobante dto.
     *
     * @param dto
     *            the dto
     * @param isMobile
     *            the is mobile
     */
    public ComprobanteView(ComprobanteDTO dto, Boolean isMobile) {
        idComprobante = String.valueOf(dto.getIndice());
        isEfectuada = !dto.isNoEfectuada();
        fecha = setearHora(dto.getFecha(), isMobile);
        this.transaccion = dto.getTipoOperacion().getEtiqueta();
        if (dto.getImportePesos() != null) {
            this.importePesos = ISBANStringUtils.formatearSaldoConSigno(dto.getImportePesos());
        }
        if (dto.getImporteDolares() != null) {
            this.importeDolares = ISBANStringUtils.formatearSaldoConSigno(dto.getImporteDolares());
        }
        this.destinatario = dto.getDestinatario();
        if (null != dto.getTipoCuentaDestino() && !TarjetaUtils.esTarjetaDeCredito(dto.getTipoCuentaDestino())) {
            tipoCuentaDestino = dto.getTipoCuentaDestino().getDescripcion();
        }
        this.idCliente = dto.getIdDebitoDDI();
        this.cuitEmpresa = dto.getCuitEmpresa();
        this.servicio = dto.getServicio();
        this.partida = dto.getPartida();
        setearMedioPagoPesosDolares(dto);
    }

    /**
     * Sets the ear medio pago pesos dolares.
     *
     * @param dto
     *            the new ear medio pago pesos dolares
     */
    protected void setearMedioPagoPesosDolares(ComprobanteDTO dto) {
        if (dto.getCtaMedioDePagoPesos() != null) {
            this.ctaMedioDePagoPesos = dto.getCtaMedioDePagoPesos();
            this.tipoCtaMedioDePagoPesos = obtenerTipoConSinMoneda(dto.getTipoCtaMedioDePagoPesos(),
                    dto.isNecesitaMoneda());
        }
        if (dto.getCtaMedioDePagoDolares() != null) {
            this.ctaMedioDePagoDolares = dto.getCtaMedioDePagoDolares();
            this.tipoCtaMedioDePagoDolares = obtenerTipoConSinMoneda(dto.getTipoCtaMedioDePagoDolares(),
                    dto.isNecesitaMoneda());
        }

    }

    /**
     * Setear hora.
     *
     * @param fecha
     *            the fecha
     * @param isMobile
     *            the is mobile
     * @return the string
     */
    protected String setearHora(Date fecha, Boolean isMobile) {
        if (isMobile) {
            SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA_ANIO_CORTO);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.format(fecha);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.format(fecha);
        }
    }

    /**
     * Obtener tipo con sin moneda.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @param necesitaMoneda
     *            the necesita moneda
     * @return the string
     */
    // TODO quitar la validacion del view, hacerla en el dto
    protected String obtenerTipoConSinMoneda(TipoCuenta tipoCuenta, boolean necesitaMoneda) {
        try {
            if (necesitaMoneda) {
                return tipoCuenta.getDescripcionConMoneda();
            } else {
                return tipoCuenta.getDescripcionConMoneda().split(" en ")[0];
            }
        } catch (NullPointerException e) {
            return "";
        }
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the transaccion.
     *
     * @return the transaccion
     */
    public String getTransaccion() {
        return transaccion;
    }

    /**
     * Sets the transaccion.
     *
     * @param transaccion
     *            the transaccion to set
     */
    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * Gets the importe pesos.
     *
     * @return the importePesos
     */
    public String getImportePesos() {
        return importePesos;
    }

    /**
     * Sets the importe pesos.
     *
     * @param importePesos
     *            the importePesos to set
     */
    public void setImportePesos(String importePesos) {
        this.importePesos = importePesos;
    }

    /**
     * Gets the importe dolares.
     *
     * @return the importeDolares
     */
    public String getImporteDolares() {
        return importeDolares;
    }

    /**
     * Sets the importe dolares.
     *
     * @param importeDolares
     *            the importeDolares to set
     */
    public void setImporteDolares(String importeDolares) {
        this.importeDolares = importeDolares;
    }

    /**
     * Gets the destinatario.
     *
     * @return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * Sets the destinatario.
     *
     * @param destinatario
     *            the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * Gets the cta medio de pago pesos.
     *
     * @return the ctaMedioDePagoPesos
     */
    public String getCtaMedioDePagoPesos() {
        return ctaMedioDePagoPesos;
    }

    /**
     * Sets the cta medio de pago pesos.
     *
     * @param ctaMedioDePagoPesos
     *            the ctaMedioDePagoPesos to set
     */
    public void setCtaMedioDePagoPesos(String ctaMedioDePagoPesos) {
        this.ctaMedioDePagoPesos = ctaMedioDePagoPesos;
    }

    /**
     * Gets the tipo cta medio de pago pesos.
     *
     * @return the tipoCtaMedioDePagoPesos
     */
    public String getTipoCtaMedioDePagoPesos() {
        return tipoCtaMedioDePagoPesos;
    }

    /**
     * Sets the tipo cta medio de pago pesos.
     *
     * @param tipoCtaMedioDePagoPesos
     *            the tipoCtaMedioDePagoPesos to set
     */
    public void setTipoCtaMedioDePagoPesos(String tipoCtaMedioDePagoPesos) {
        this.tipoCtaMedioDePagoPesos = tipoCtaMedioDePagoPesos;
    }

    /**
     * Gets the cta medio de pago dolares.
     *
     * @return the ctaMedioDePagoDolares
     */
    public String getCtaMedioDePagoDolares() {
        return ctaMedioDePagoDolares;
    }

    /**
     * Sets the cta medio de pago dolares.
     *
     * @param ctaMedioDePagoDolares
     *            the ctaMedioDePagoDolares to set
     */
    public void setCtaMedioDePagoDolares(String ctaMedioDePagoDolares) {
        this.ctaMedioDePagoDolares = ctaMedioDePagoDolares;
    }

    /**
     * Gets the tipo cta medio de pago dolares.
     *
     * @return the tipoCtaMedioDePagoDolares
     */
    public String getTipoCtaMedioDePagoDolares() {
        return tipoCtaMedioDePagoDolares;
    }

    /**
     * Sets the tipo cta medio de pago dolares.
     *
     * @param tipoCtaMedioDePagoDolares
     *            the tipoCtaMedioDePagoDolares to set
     */
    public void setTipoCtaMedioDePagoDolares(String tipoCtaMedioDePagoDolares) {
        this.tipoCtaMedioDePagoDolares = tipoCtaMedioDePagoDolares;
    }

    /**
     * Checks if is efectuada.
     *
     * @return true, if is efectuada
     */
    public boolean isEfectuada() {
        return isEfectuada;
    }

    /**
     * Sets the efectuada.
     *
     * @param isEfectuada
     *            the new efectuada
     */
    public void setEfectuada(boolean isEfectuada) {
        this.isEfectuada = isEfectuada;
    }

    /**
     * Gets the id comprobante.
     *
     * @return the id comprobante
     */
    public String getIdComprobante() {
        return idComprobante;
    }

    /**
     * Sets the id comprobante.
     *
     * @param idComprobante
     *            the new id comprobante
     */
    public void setIdComprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    /**
     * Gets the tipo cuenta destino.
     *
     * @return the tipo cuenta destino
     */
    public String getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    /**
     * Sets the tipo cuenta destino.
     *
     * @param tipoCuentaDestino
     *            the new tipo cuenta destino
     */
    public void setTipoCuentaDestino(String tipoCuentaDestino) {
        this.tipoCuentaDestino = tipoCuentaDestino;
    }
      
	public Boolean getMostrarDevolucionDA() {
		return mostrarDevolucionDA;
	}

	public void setMostrarDevolucionDA(Boolean mostrarDevolucionDA) {
		this.mostrarDevolucionDA = mostrarDevolucionDA;
	}

	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(getId());
        hcb.append(fecha);
        hcb.append(transaccion);
        hcb.append(importePesos);
        hcb.append(importeDolares);
        hcb.append(destinatario);
        hcb.append(ctaMedioDePagoPesos);
        hcb.append(tipoCtaMedioDePagoPesos);
        hcb.append(ctaMedioDePagoDolares);
        hcb.append(tipoCtaMedioDePagoDolares);
        return hcb.toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ComprobanteView other = (ComprobanteView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(fecha, other.getFecha());
        eb.append(transaccion, other.getTransaccion());
        eb.append(importePesos, other.getImportePesos());
        eb.append(importeDolares, other.getImporteDolares());
        eb.append(destinatario, other.getDestinatario());
        eb.append(ctaMedioDePagoPesos, other.getCtaMedioDePagoPesos());
        eb.append(tipoCtaMedioDePagoPesos, other.getTipoCtaMedioDePagoPesos());
        eb.append(ctaMedioDePagoDolares, other.getCtaMedioDePagoDolares());
        eb.append(tipoCtaMedioDePagoDolares, other.getTipoCtaMedioDePagoDolares());
        eb.append(getId(), other.getId());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(fecha);
        sb.append(transaccion);
        sb.append(importePesos);
        sb.append(importeDolares);
        sb.append(destinatario);
        sb.append(ctaMedioDePagoPesos);
        sb.append(tipoCtaMedioDePagoPesos);
        sb.append(ctaMedioDePagoDolares);
        sb.append(tipoCtaMedioDePagoDolares);
        sb.append(getId());
        return sb.toString();
    }

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

}
