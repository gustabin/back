/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * DTO de Comprobante.
 */
public class ComprobanteDTO {

    /** The fecha. */
    @NotNull
    private Date fecha;

    /** The tipo operacion. */
    @NotNull
    private TipoOperacionComprobanteEnum tipoOperacion;

    /** The importe en pesos. */
    private BigDecimal importePesos;

    /** The importe en dolares. */
    private BigDecimal importeDolares;

    /** The destinatario. */
    private String destinatario;

    /** The tipo cuenta destinatario. */
    private TipoCuenta tipoCtaDestinatario;

    /** The cta medio de pago en pesos. */
    private String ctaMedioDePagoPesos;

    /** The tipo cta medio de pago en pesos. */
    private TipoCuenta tipoCtaMedioDePagoPesos;

    /** The cta medio de pago en dolares. */
    private String ctaMedioDePagoDolares;

    /** The tipo cta medio de pago en dolares. */
    private TipoCuenta tipoCtaMedioDePagoDolares;

    /** The tipo cta medio de pago en dolares. */
    private Boolean tieneError = false;

    /** The necesita moneda. */
    private boolean necesitaMoneda;

    /** The no efectuada. */
    private boolean noEfectuada = false;

    /** The hora operacion. */
    private String horaOperacion;

    /** The hora operacion. */
    private TipoCuenta tipoCuentaDestino;

    /** The detalle. */
    private DetalleComprobanteDTO detalle;

    /** The indice. */
    private int indice;

    private boolean esMobile;
    
    /** The id debito DDI**/
    private String idDebitoDDI;
    
    private String cuitEmpresa;
    
    private String partida;
    
    private String servicio;
    
    /** The historialEnum. */
    private HistorialComprobanteEnum historial;

    /**
     * Instantiates a new comprobante DTO.
     */
    public ComprobanteDTO() {
        super();
        fecha = new Date();
        tipoOperacion = TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA;
    }

    /**
     * Instantiates a new comprobante DTO. Inicia Comprobante DTO con error en true;
     *
     * @param b
     *            the b
     */
    public ComprobanteDTO(boolean b) {
        super();
        tieneError = b;
        fecha = new Date();
        tipoOperacion = TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA;
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the tipo operacion.
     *
     * @return the tipoOperacion
     */
    public TipoOperacionComprobanteEnum getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the tipo operacion.
     *
     * @param tipoOperacion
     *            the tipoOperacion to set
     */
    public void setTipoOperacion(TipoOperacionComprobanteEnum tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * Gets the importe pesos.
     *
     * @return the importePesos
     */
    public BigDecimal getImportePesos() {
        return importePesos;
    }

    /**
     * Sets the importe pesos.
     *
     * @param importePesos
     *            the importePesos to set
     */
    public void setImportePesos(BigDecimal importePesos) {
        this.importePesos = importePesos;
    }

    /**
     * Gets the importe dolares.
     *
     * @return the importeDolares
     */
    public BigDecimal getImporteDolares() {
        return importeDolares;
    }

    /**
     * Sets the importe dolares.
     *
     * @param importeDolares
     *            the importeDolares to set
     */
    public void setImporteDolares(BigDecimal importeDolares) {
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
    public TipoCuenta getTipoCtaMedioDePagoPesos() {
        return tipoCtaMedioDePagoPesos;
    }

    /**
     * Sets the tipo cta medio de pago pesos.
     *
     * @param tipoCtaMedioDePagoPesos
     *            the tipoCtaMedioDePagoPesos to set
     */
    public void setTipoCtaMedioDePagoPesos(TipoCuenta tipoCtaMedioDePagoPesos) {
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
    public TipoCuenta getTipoCtaMedioDePagoDolares() {
        return tipoCtaMedioDePagoDolares;
    }

    /**
     * Sets the tipo cta medio de pago dolares.
     *
     * @param tipoCtaMedioDePagoDolares
     *            the tipoCtaMedioDePagoDolares to set
     */
    public void setTipoCtaMedioDePagoDolares(TipoCuenta tipoCtaMedioDePagoDolares) {
        this.tipoCtaMedioDePagoDolares = tipoCtaMedioDePagoDolares;
    }

    /**
     * Checks if is necesita moneda.
     *
     * @return true, if is necesita moneda
     */
    public boolean isNecesitaMoneda() {
        return necesitaMoneda;
    }

    /**
     * Sets the necesita moneda.
     *
     * @param necesitaMoneda
     *            the new necesita moneda
     */
    public void setNecesitaMoneda(boolean necesitaMoneda) {
        this.necesitaMoneda = necesitaMoneda;
    }

    /**
     * Gets the tiene error.
     *
     * @return the error
     */
    public Boolean getTieneError() {
        return tieneError;
    }

    /**
     * Sets the tiene error.
     *
     * @param error
     *            the error to set
     */
    public void setTieneError(Boolean error) {
        this.tieneError = error;
    }

    /**
     * Checks if is no efectuada.
     *
     * @return true, if is no efectuada
     */
    public boolean isNoEfectuada() {
        return noEfectuada;
    }

    /**
     * Sets the no efectuada.
     *
     * @param noEfectuada
     *            the new no efectuada
     */
    public void setNoEfectuada(boolean noEfectuada) {
        this.noEfectuada = noEfectuada;
    }

    /**
     * Gets the detalle.
     *
     * @return the detalle
     */
    public DetalleComprobanteDTO getDetalle() {
        return detalle;
    }

    /**
     * Sets the detalle.
     *
     * @param detalle
     *            the new detalle
     */
    public void setDetalle(DetalleComprobanteDTO detalle) {
        this.detalle = detalle;
    }

    /**
     * Gets the indice.
     *
     * @return the indice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * Sets the indice.
     *
     * @param indice
     *            the new indice
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * Gets the hora operacion.
     *
     * @return the horaOperacion
     */
    public String getHoraOperacion() {
        return horaOperacion;
    }

    /**
     * Sets the hora operacion.
     *
     * @param horaOperacion
     *            the horaOperacion to set
     */
    public void setHoraOperacion(String horaOperacion) {
        this.horaOperacion = horaOperacion;
    }

    /**
     * Gets the tipo cta destinatario.
     *
     * @return the tipo cta destinatario
     */
    public TipoCuenta getTipoCtaDestinatario() {
        return tipoCtaDestinatario;
    }

    /**
     * Sets the tipo cta destinatario.
     *
     * @param tipoCtaDestinatario
     *            the new tipo cta destinatario
     */
    public void setTipoCtaDestinatario(TipoCuenta tipoCtaDestinatario) {
        this.tipoCtaDestinatario = tipoCtaDestinatario;
    }

    /**
     * Gets the tipo cuenta destino.
     *
     * @return the tipo cuenta destino
     */
    public TipoCuenta getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    /**
     * Sets the tipo cuenta destino.
     *
     * @param tipoCuentaDestino
     *            the new tipo cuenta destino
     */
    public void setTipoCuentaDestino(TipoCuenta tipoCuentaDestino) {
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }

        if (a == null) {
            return false;
        }

        if (getClass() != a.getClass()) {
            return false;
        }
        ComprobanteDTO comprobante = (ComprobanteDTO) a;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(importePesos, comprobante.getImportePesos());
        eb.append(importeDolares, comprobante.getImporteDolares());
        eb.append(ctaMedioDePagoPesos, comprobante.getCtaMedioDePagoPesos());
        eb.append(tipoCtaMedioDePagoPesos, comprobante.getTipoCtaMedioDePagoPesos());
        eb.append(ctaMedioDePagoDolares, comprobante.getCtaMedioDePagoDolares());
        eb.append(tipoCtaMedioDePagoDolares, comprobante.getTipoCtaMedioDePagoDolares());
        eb.append(tieneError, comprobante.getTieneError());
        eb.append(necesitaMoneda, comprobante.isNecesitaMoneda());
        eb.append(noEfectuada, comprobante.isNoEfectuada());
        eb.append(detalle, comprobante.getDetalle());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hb = new HashCodeBuilder();
        hb.append(importePesos);
        hb.append(importeDolares);
        hb.append(ctaMedioDePagoPesos);
        hb.append(tipoCtaMedioDePagoPesos);
        hb.append(ctaMedioDePagoDolares);
        hb.append(tipoCtaMedioDePagoDolares);
        hb.append(tieneError);
        hb.append(necesitaMoneda);
        hb.append(noEfectuada);
        hb.append(detalle);
        return hb.toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ComprobanteDTO [fecha=");
        sb.append(fecha);
        sb.append(", tipoOperacion= ");
        sb.append(tipoOperacion);
        sb.append(", importePesos= ");
        sb.append(importePesos);
        sb.append(", importeDolares= ");
        sb.append(importeDolares);
        sb.append(", destinatario= ");
        sb.append(destinatario);
        sb.append(", ctaMedioDePagoPesos= ");
        sb.append(ctaMedioDePagoPesos);
        sb.append(", tipoCtaMedioDePagoPesos= ");
        sb.append(tipoCtaMedioDePagoPesos);
        sb.append(", ctaMedioDePagoDolares= ");
        sb.append(ctaMedioDePagoDolares);
        sb.append(", tipoCtaMedioDePagoDolares= ");
        sb.append(tipoCtaMedioDePagoDolares);
        sb.append(", tieneError= ");
        sb.append(tieneError);
        sb.append(", detalle= ");
        sb.append(detalle);
        sb.append("]");
        return sb.toString();
    }

    /**
	 * Setear medio de pago TC.
	 *
	 * @param tipoCuentaTC
	 *            the tipo cuenta TC
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 */
    public void setearMedioDePagoTC(String tipoCuentaTC, String numeroTarjeta) {
        TipoCuenta tipoCuenta = null;
        if ("VISA".equals(tipoCuentaTC)) {
            tipoCuenta = TipoCuenta.VISA;
        } else if ("AMEX".equals(tipoCuentaTC)) {
            tipoCuenta = TipoCuenta.AMEX;
        } else if ("MASTER".equals(tipoCuentaTC)) {
            tipoCuenta = TipoCuenta.MASTERCARD;
        }
        if (tipoCuenta != null) {
            String nroCuenta = StringUtils.upperCase(tipoCuenta.getAbreviatura()) + " "
                    + TarjetaUtils.obtenerNroTarjetaEnmascarada(numeroTarjeta, tipoCuenta);
            this.tipoCtaMedioDePagoPesos = tipoCuenta;
            this.ctaMedioDePagoPesos = nroCuenta;
        }
    }

    /**
	 * Setear medio de pago cuenta pesos.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param sucursalCuenta
	 *            the sucursal cuenta
	 * @param numeroCuenta
	 *            the numero cuenta
	 */
    public void setearMedioDePagoCuentaPesos(String tipoCuenta, String sucursalCuenta, String numeroCuenta) {
        IdentificacionCuenta identificacion = new IdentificacionCuenta(sucursalCuenta, numeroCuenta);
        this.ctaMedioDePagoPesos = identificacion.toString();
        this.tipoCtaMedioDePagoPesos = TipoCuenta.fromCodigo(tipoCuenta);
    }


	/**
	 * @return the historial
	 */
	public HistorialComprobanteEnum getHistorial() {
		return historial;
	}

	/**
	 * @param historial the historial to set
	 */
	public void setHistorial(HistorialComprobanteEnum historial) {
		this.historial = historial;
	}
    
    public boolean getEsMobile() {
		return esMobile;
	}

	public void setEsMobile(boolean esMobile) {
		this.esMobile = esMobile;
	}

	public String getIdDebitoDDI() {
		return idDebitoDDI;
	}

	public void setIdDebitoDDI(String idDebitoDDI) {
		this.idDebitoDDI = idDebitoDDI;
	}

	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
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

}
