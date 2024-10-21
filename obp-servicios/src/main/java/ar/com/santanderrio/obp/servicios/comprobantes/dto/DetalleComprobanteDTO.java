/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class DetalleComprobanteDTO.
 */
public class DetalleComprobanteDTO {

    /** The titulo comprobante. */
    protected String tituloComprobante;

    /** The tipo comprobante. */
    protected TipoDetalleComprobanteEnum tipoComprobante;

    /** The cuit cuil contribuyente. */
    protected CuitDTO cuit;

    /** The nro comprobante. */
    protected String nroComprobante;

    /** The identificacion. */
    protected String identificacion;

    /** The fecha de pago. */
    protected Date fechaDePago;

    /** The importe. */
    protected BigDecimal importeDolar;

    /** The informacion adicional. */
    protected String informacionAdicional;

    /** The anotaciones. */
    protected String anotaciones;

    /** The nro cuenta origen. */
    protected String nroCuentaOrigen;

    /** The tipo cuenta origen. */
    protected TipoCuenta tipoCuentaOrigen;

    /** The motivo rechazo. */
    protected String motivoRechazo;
    
	/** The esScomp. */
	protected Boolean esScomp = Boolean.FALSE;

    protected String nroTransaccion;

    /**
     * Gets the motivo rechazo.
     *
     * @return the motivoRechazo
     */
    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    /**
     * Sets the motivo rechazo.
     *
     * @param motivoRechazo
     *            the motivoRechazo to set
     */
    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    /**
     * Instantiates a new detalle comprobante entity.
     */
    public DetalleComprobanteDTO() {
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
     * Gets the tipo comprobante.
     *
     * @return the tipo comprobante
     */
    public TipoDetalleComprobanteEnum getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * Sets the tipo comprobante.
     *
     * @param tipoComprobante
     *            the new tipo comprobante
     */
    public void setTipoComprobante(TipoDetalleComprobanteEnum tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
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
     * Gets the fecha de pago.
     *
     * @return the fecha
     */
    public Date getFechaDePago() {
        return fechaDePago;
    }

    /**
     * Sets the fecha de pago.
     *
     * @param fechaDePago
     *            the new fecha de pago
     */
    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    /**
     * Gets the importe dolar.
     *
     * @return the importe dolar
     */
    public BigDecimal getImporteDolar() {
        return importeDolar;
    }

    /**
     * Sets the importe dolar.
     *
     * @param importeDolar
     *            the new importe dolar
     */
    public void setImporteDolar(BigDecimal importeDolar) {
        this.importeDolar = importeDolar;
    }

    /**
     * Gets the cuit.
     *
     * @return the cuit
     */
    public CuitDTO getCuit() {
        return cuit;
    }

    /**
     * Sets the cuit.
     *
     * @param cuit
     *            the cuit to set
     */
    public void setCuit(CuitDTO cuit) {
        this.cuit = cuit;
    }

    /**
     * Gets the informacion adicional.
     *
     * @return the informacion adicional
     */
    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    /**
     * Sets the informacion adicional.
     *
     * @param informacionAdicional
     *            the new informacion adicional
     */
    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    /**
     * Gets the anotaciones.
     *
     * @return the anotaciones
     */
    public String getAnotaciones() {
        return anotaciones;
    }

    /**
     * Sets the anotaciones.
     *
     * @param anotaciones
     *            the new anotaciones
     */
    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
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
    public TipoCuenta getTipoCuentaOrigen() {
        return tipoCuentaOrigen;
    }

    /**
     * Sets the tipo cuenta origen.
     *
     * @param tipoCuentaOrigen
     *            the new tipo cuenta origen
     */
    public void setTipoCuentaOrigen(TipoCuenta tipoCuentaOrigen) {
        this.tipoCuentaOrigen = tipoCuentaOrigen;
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
        DetalleComprobanteDTO comprobante = (DetalleComprobanteDTO) a;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(tituloComprobante, comprobante.getTituloComprobante());
        eb.append(tipoComprobante, comprobante.getTipoComprobante());
        eb.append(nroCuentaOrigen, comprobante.getNroCuentaOrigen());
        eb.append(tipoCuentaOrigen, comprobante.getTipoCuentaOrigen());
        eb.append(cuit, comprobante.getCuit());
        eb.append(informacionAdicional, comprobante.getInformacionAdicional());
		eb.append(nroComprobante, ISBANStringUtils.eliminarCeros(comprobante.getNroComprobante()));
        eb.append(identificacion, comprobante.getIdentificacion());
        eb.append(fechaDePago, comprobante.getFechaDePago());
        eb.append(importeDolar, comprobante.getImporteDolar());
        eb.append(anotaciones, comprobante.getAnotaciones());
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
        hb.append(tituloComprobante);
        hb.append(tipoComprobante);
        hb.append(nroCuentaOrigen);
        hb.append(tipoCuentaOrigen);
        hb.append(cuit);
        hb.append(informacionAdicional);
        hb.append(nroComprobante);
        hb.append(identificacion);
        hb.append(fechaDePago);
        hb.append(importeDolar);
        hb.append(anotaciones);
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
        sb.append("DetalleComprobanteEntity [tipoComprobante=");
        sb.append(tipoComprobante);
        sb.append(", nroCuentaOrigen= ");
        sb.append(nroCuentaOrigen);
        sb.append(", tipoCuentaOrigen= ");
        sb.append(tipoCuentaOrigen);
        sb.append(", cuit= ");
        sb.append(cuit);
        sb.append(", informacionAdicional= ");
        sb.append(informacionAdicional);
        sb.append(", nroComprobante= ");
        sb.append(nroComprobante);
        sb.append(", identificacion= ");
        sb.append(identificacion);
        sb.append(", fechaDePago= ");
        sb.append(fechaDePago);
        sb.append(", importeDolar= ");
        sb.append(importeDolar);
        sb.append(", anotaciones= ");
        sb.append(anotaciones);
        sb.append("]");
        return sb.toString();
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
     * Setear importes.
     *
     * @param view
     *            the view
     * @param comprobante
     *            the comprobante
     */
    public void setearImportes(DetalleComprobanteView view, ComprobanteDTO comprobante) {
        if (comprobante.getImportePesos() != null) {
            view.setImportePesos(ISBANStringUtils.formatearSaldoConSigno(comprobante.getImportePesos()));
        }
        if (comprobante.getImporteDolares() != null) {
            view.setImporteDolares(ISBANStringUtils.formatearSaldoConSigno(comprobante.getImporteDolares()));
        }
    }

    /**
     * Setear num Y tipo cuenta origen.
     *
     * @param view
     *            the view
     * @param numero
     *            the numero
     * @param tipoCuenta
     *            the tipo cuenta
     */
    public void setearNumeroYTipoCuentaOrigen(DetalleComprobanteView view, String numero, String tipoCuenta) {
        view.setNroCuentaOrigen(numero);
        view.setTipoCuentaOrigen(tipoCuenta);
    }

    /**
     * Setear numero Y tipo cuenta origen.
     *
     * @param view
     *            the view
     * @param comprobante
     *            the comprobante
     */
    public void setearNumeroYTipoCuentaOrigen(DetalleComprobanteView view, ComprobanteDTO comprobante) {
        TipoCuenta tipoCuenta = null;
        if (comprobante.getCtaMedioDePagoPesos() != null) {
            view.setNroCuentaOrigen(comprobante.getCtaMedioDePagoPesos());
            tipoCuenta = comprobante.getTipoCtaMedioDePagoPesos();
        }
        if (comprobante.getCtaMedioDePagoDolares() != null) {
            view.setNroCuentaOrigen(comprobante.getCtaMedioDePagoDolares());
            tipoCuenta = comprobante.getTipoCtaMedioDePagoDolares();
        }
        if (tipoCuenta != null && !TarjetaUtils.esTarjetaDeCredito(tipoCuenta)) {
            view.setTipoCuentaOrigen(ISBANStringUtils.formatearFraseInicialMayuscula(
                    obtenerTipoConSinMoneda(tipoCuenta, comprobante.isNecesitaMoneda())));
        }
    }

    /**
     * Setear numero tipo Y titulo.
     *
     * @param view
     *            the view
     */
    public void setearNumeroTipoYTitulo(DetalleComprobanteView view) {
        view.setNroComprobante(getNroComprobante());
        view.setTipoComprobante(getTipoComprobante().getTag());
        view.setTituloComprobante(getTituloComprobante());
    }

    /**
     * Sets the ear cuit Y tipo cuit.
     *
     * @param view
     *            the new ear cuit Y tipo cuit
     */
    public void setearCuitYTipoCuit(DetalleComprobanteView view) {
        view.setCuit(getCuit().getNumero());
        view.setTipoCuit(getCuit().getTipo().getDetalle());
    }

    /**
     * Gets the view.
     *
     * @param comprobante
     *            the comprobante
     * @return the view
     */
    public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
        return new DetalleComprobanteView();
    }
    
    public String obtenerIdentificacionHistorial() {
    	return identificacion;
    }
    
	public Boolean getEsScomp() {
		return esScomp;
	}

	public void setEsScomp(Boolean esScomp) {
		this.esScomp = esScomp;
	}

    public String getNroTransaccion() {
        return nroTransaccion;
    }

    public void setNroTransaccion(String nroTransaccion) {
        this.nroTransaccion = nroTransaccion;
    }

}
