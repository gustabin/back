/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;

/**
 * The Class DetallePrestamosView.
 */
public class DetallePrestamosView extends ReportComprobanteView {

    /** The id. */
    private String id;

    /** The detalle cuota. */
    private DetalleCuotaPrestamoView detalleCuota;

    /** The cuil. */
    private String cuil;

    /** The titular. */
    private String titular;

    /** The numero prestamo. */
    private String numeroPrestamo;

    /** The cuenta. */
    private CuentaView cuenta;

    /** The tasas. */
    private TasaValorView tasas;

    /** The destino prestamo. */
    private String destinoPrestamo;

    /** The importe solicitado. */
    private String importeSolicitado;

    /** The fecha vencimiento. */
    private String fechaVencimiento;

    /** The tipo prestamo. */
    private String tipoPrestamo;

    /** The tiene otros impuestos. */
    private Boolean tieneOtrosImpuestos = false;

    /** The has alias. */
    private Boolean hasAlias;

    /** The alias. */
    private String alias;

    /** The nro prestamo. */
    private String nroPrestamo;

    /** The cuota. */
    private String cuota;

    /** The plazo. */
    private String plazo;

    /** The fecha inicio. */
    private String fechaInicio;

    /** The fecha vencimiento mobile. */
    private String fechaVencimientoMobile;

    /** The fecha inicio mobile. */
    private String fechaInicioMobile;

    /** The monto prestamo. */
    private String montoPrestamo;

    /** The motivo prestamo. */
    private String motivoPrestamo;

    /** The fecha finalizacion mobile. */
    private String fechaFinalizacionMobile;

    /** The importe total. */
    private String importeTotal;

    /** The cbu. */
    private String cbu;

    /** The num vin. */
    private String numVin;

    /** The is isUva. */
    private boolean isUva;

    /** The seguro. */
    private String seguro;

    /** The legales. */
    private String legales;

    /** The impuestos. */
    private String impuestos;

    /** The plazo prestamo. */
    private String plazoPrestamo;

    /** The tiene seguro. */
    private Boolean tieneSeguro = false;

    /** the tipoError. */
    private String tipoError;

    /** The prestamos jasper. */
    protected final String prestamosJasper = "detalle-prestamo.jasper";

    /** The path. */
    protected final String path = "classpath:/report/prestamos/comprobante/";

    /** The fecha hora actual key. */
    protected final String fechaHoraActualKey = "FECHA_ACTUAL";

    /** The titulo prestamo. */
    protected final String tituloPrestamo = "Detalle de prestamo";

    /** The importe acreditar key. */
    protected final String importeAcreditarKey = "IMPORTE_ACREDITAR";

    /** The titulo comprobante key. */
    protected final String tituloComprobanteKey = "TITULO_COMPROBANTE";

    /** The importe cuota key. */
    protected final String importeCuotaKey = "IMPORTE_CUOTA";

    /** The tipo prestamo key. */
    protected final String tipoPrestamoKey = "TIPO_PRESTAMO";

    /** The numero prestamo key. */
    protected final String numeroPrestamoKey = "NUMERO_PRESTAMO";

    /** The mostrar logo key. */
    protected final String mostrarLogoKey = "MOSTRAR_LOGO";

    /** The titular key. */
    protected final String titularKey = "TITULAR";

    /** The importe solicitado key. */
    protected final String importeSolicitadoKey = "IMPORTE_SOLICITADO";

    /** The cuil key. */
    protected final String cuilKey = "CUIL";

    /** The plazo prestamo key. */
    protected final String plazoPrestamoKey = "PLAZO_PRESTAMO";

    /** The cuenta debito key. */
    protected final String cuentaDebitoKey = "CUENTA_DEBITO";

    /** The destino prestamo key. */
    protected final String destinoPrestamoKey = "DESTINO_PRESTAMO";

    /** The tiene otros impuestos key. */
    protected final String tieneOtrosImpuestosKey = "TIENE_OTROS_IMPUESTOS";

    /** The nro cuota key. */
    protected final String nroCuotaKey = "NRO_CUOTA";

    /** The capital key. */
    protected final String capitalKey = "CAPITAL";

    /** The intereses compensatorios key. */
    protected final String interesesCompensatoriosKey = "INTERESES_COMPENSATORIOS";

    /** The seguro key. */
    protected final String seguroKey = "SEGURO";

    /** The titulo seguro key. */
    protected final String tituloSeguroKey = "TITULO_SEGURO";

    /** The otros impuestos key. */
    protected final String otrosImpuestosKey = "OTROS_IMPUESTOS";

    /** The titulo otros impuestos key. */
    protected final String tituloOtrosImpuestosKey = "TITULO_OTROS_IMPUESTOS";

    /** The gastos administrativos key. */
    protected final String gastosAdministrativosKey = "GASTOS_ADMINISTRATIVOS";

    /** The tasa efectiva anual key. */
    protected final String tasaEfectivaAnualKey = "TASA_EFECTIVA_ANUAL";

    /** The tasa nominal anual key. */
    protected final String tasaNominalAnualKey = "TASA_NOMINAL_ANUAL";

    /** The fecha vencimiento key. */
    protected final String fechaVencimientoKey = "FECHA_VENCIMIENTO";

    /** The fecha vencimiento mobile key. */
    protected final String fechaVencimientoMobileKey = "FECHA_VENCIMIENTO_MOBILE";

    /** The costo efectivo key. */
    protected final String costoEfectivoKey = "COSTO_EFECTIVO_CON_IMPUESTOS";

    /** The costo efectivo sin impuestos key. */
    protected final String costoEfectivoSinImpuestosKey = "COSTO_EFECTIVO_SIN_IMPUESTOS";

    /** The tiene seguro key. */
    protected final String tieneSeguroKey = "TIENE_SEGURO";

    /** The legal 1 key. */
    protected final String legal1Key = "LEGAL1";

    /** The legal 2 key. */
    protected final String legal2Key = "LEGAL2";

    /** The legal 3 key. */
    protected final String legal3Key = "LEGAL3";

    /**
     * Instantiates a new detalle prestamos view.
     */
    public DetallePrestamosView() {
        super();

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * ReportComprobanteView#()
     */
    @Override
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(path + prestamosJasper).getPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * ReportComprobanteView#obtenerParametrosPDF()
     */
    @Override
    public HashMap<String, Object> obtenerParametrosPDF() {
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        tituloComprobante = tituloPrestamo;
        parametros.put(tituloComprobanteKey, tituloPrestamo);
        parametros.put(tipoPrestamoKey, tipoPrestamo);

        if ("PRENDARIO".equals(tipoPrestamo)) {
            if (cuil != null) {
                parametros.put(cuilKey, cuil);
            } else {
                parametros.put(cuilKey, "-");
            }
            if (titular != null) {
                parametros.put(titularKey, titular);
            } else {
                parametros.put(titularKey, "-");
            }
        }
        parametros.put(numeroPrestamoKey, numeroPrestamo);
        parametros.put(mostrarLogoKey, "1");
        String cuentaDebito = "";
        if (cuenta != null) {
            if (cuenta.getAlias() != null) {
                cuentaDebito = "<b>Cuenta \"" + cuenta.getAlias() + "\"</b>" + "<br/>";
            }
            if (StringUtils.isNotBlank(cuentaDebito)) {
                cuentaDebito = cuentaDebito + "<b>" + cuenta.getDescripcionTipoCuenta() + "</b><br/>";
            } else {
                cuentaDebito = cuentaDebito + cuenta.getDescripcionTipoCuenta() + "<br/>";
            }
            cuentaDebito = cuentaDebito + cuenta.getNumeroFormateado();
        }
        parametros.put(cuentaDebitoKey, cuentaDebito);
        parametros.put(tasaEfectivaAnualKey, tasas.getTasaEfectivaAnual());
        parametros.put(tasaNominalAnualKey, tasas.getTasaNominalAnual());
        parametros.put(destinoPrestamoKey, destinoPrestamo);
        parametros.put(importeSolicitadoKey, importeSolicitado);
        parametros.put(plazoPrestamoKey, plazoPrestamo);
        parametros.put(tieneOtrosImpuestosKey, tieneOtrosImpuestos);
        parametros.put(tieneSeguroKey, tieneSeguro);
        parametros.put(costoEfectivoKey, "CFTEA: " + obtenerCampoOVacio(tasas.getCostoFinancieroTotalAnual()));
        parametros.put(costoEfectivoSinImpuestosKey,
                "CFTEA: " + obtenerCampoOVacio(tasas.getCostoFinancieroTotalAnualSinImpuestos()));
        parametros.put(legal1Key,
                "(1) De existir diferencia entre la sumatoria de los rubros que se detalla y el \"Importe de la cuota\", la misma corresponde a los intereses punitorios.");
        int legalActual = 2;
        if (!"$ 0,00".equals(seguro)) {
            parametros.put(seguroKey, seguro);
            parametros.put(legal2Key, "(" + legalActual + ") " + legales);
            parametros.put(tituloSeguroKey, "Seguro (" + legalActual + ")");
            legalActual++;
        }
        if (!"$ 0,00".equals(impuestos)) {
            parametros.put(legal3Key, "(" + legalActual + ") IVA y Sellados");
            parametros.put(tituloOtrosImpuestosKey, "Otros impuestos (" + legalActual + ")");
        }
        parametros.put(fechaHoraActualKey, ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
        return parametros;
    }

    /**
     * Obtener campo O vacio.
     *
     * @param valor
     *            the valor
     * @return the string
     */
    private String obtenerCampoOVacio(String valor) {
        if (valor == null) {
            return "";
        }
        return valor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                // agregar los append con los atributos que correspondan
                // .append(extra)
                .toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        EqualsBuilder eb = new EqualsBuilder();
        return eb
                // agregar los appends que corresponda segun los atributos
                // cargados, Ej: .append(extra, other.getExtra())
                .isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                // agregar los appends con los atributos que corresponda, ej:
                // .append("Extra", extra)
                .toString();
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(String id) {
        this.id = id;
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
     * @param fechaVencimiento
     *            the new fecha vencimiento
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * ReportComprobanteView#getTipoPrestamo()
     */
    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * ReportComprobanteView#setTipoPrestamo(java.lang.String)
     */
    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    /**
     * Gets the checks for alias.
     *
     * @return the checks for alias
     */
    public Boolean getHasAlias() {
        return hasAlias;
    }

    /**
     * Sets the checks for alias.
     *
     * @param hasAlias
     *            the new checks for alias
     */
    public void setHasAlias(Boolean hasAlias) {
        this.hasAlias = hasAlias;
    }

    /**
     * Gets the alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias.
     *
     * @param alias
     *            the new alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the nro prestamo.
     *
     * @return the nro prestamo
     */
    public String getNroPrestamo() {
        return nroPrestamo;
    }

    /**
     * Sets the nro prestamo.
     *
     * @param nroPrestamo
     *            the new nro prestamo
     */
    public void setNroPrestamo(String nroPrestamo) {
        this.nroPrestamo = nroPrestamo;
    }

    /**
     * Gets the cuota.
     *
     * @return the cuota
     */
    public String getCuota() {
        return cuota;
    }

    /**
     * Sets the cuota.
     *
     * @param cuota
     *            the new cuota
     */
    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    /**
     * Gets the plazo.
     *
     * @return the plazo
     */
    public String getPlazo() {
        return plazo;
    }

    /**
     * Sets the plazo.
     *
     * @param plazo
     *            the new plazo
     */
    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    /**
     * Gets the fecha inicio.
     *
     * @return the fecha inicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the fecha inicio.
     *
     * @param fechaInicio
     *            the new fecha inicio
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Gets the fecha vencimiento mobile.
     *
     * @return the fecha vencimiento mobile
     */
    public String getFechaVencimientoMobile() {
        return fechaVencimientoMobile;
    }

    /**
     * Sets the fecha vencimiento mobile.
     *
     * @param fechaVencimientoMobile
     *            the new fecha vencimiento mobile
     */
    public void setFechaVencimientoMobile(String fechaVencimientoMobile) {
        this.fechaVencimientoMobile = fechaVencimientoMobile;
    }

    /**
     * Gets the fecha inicio mobile.
     *
     * @return the fecha inicio mobile
     */
    public String getFechaInicioMobile() {
        return fechaInicioMobile;
    }

    /**
     * Sets the fecha inicio mobile.
     *
     * @param fechaInicioMobile
     *            the new fecha inicio mobile
     */
    public void setFechaInicioMobile(String fechaInicioMobile) {
        this.fechaInicioMobile = fechaInicioMobile;
    }

    /**
     * Gets the monto prestamo.
     *
     * @return the monto prestamo
     */
    public String getMontoPrestamo() {
        return montoPrestamo;
    }

    /**
     * Sets the monto prestamo.
     *
     * @param montoPrestamo
     *            the new monto prestamo
     */
    public void setMontoPrestamo(String montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    /**
     * Gets the motivo prestamo.
     *
     * @return the motivo prestamo
     */
    public String getMotivoPrestamo() {
        return motivoPrestamo;
    }

    /**
     * Sets the motivo prestamo.
     *
     * @param motivoPrestamo
     *            the new motivo prestamo
     */
    public void setMotivoPrestamo(String motivoPrestamo) {
        this.motivoPrestamo = motivoPrestamo;
    }

    /**
     * Gets the fecha finalizacion mobile.
     *
     * @return the fecha finalizacion mobile
     */
    public String getFechaFinalizacionMobile() {
        return fechaFinalizacionMobile;
    }

    /**
     * Sets the fecha finalizacion mobile.
     *
     * @param fechaFinalizacionMobile
     *            the new fecha finalizacion mobile
     */
    public void setFechaFinalizacionMobile(String fechaFinalizacionMobile) {
        this.fechaFinalizacionMobile = fechaFinalizacionMobile;
    }

    /**
     * Gets the importe total.
     *
     * @return the importe total
     */
    public String getImporteTotal() {
        return importeTotal;
    }

    /**
     * Sets the importe total.
     *
     * @param importeTotal
     *            the new importe total
     */
    public void setImporteTotal(String importeTotal) {
        this.importeTotal = importeTotal;
    }

    /**
     * Gets the cbu.
     *
     * @return the cbu
     */
    public String getCbu() {
        return cbu;
    }

    /**
     * Sets the cbu.
     *
     * @param cbu
     *            the new cbu
     */
    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    /**
     * Gets the num vin.
     *
     * @return the num vin
     */
    public String getNumVin() {
        return numVin;
    }

    /**
     * Sets the num vin.
     *
     * @param numVin
     *            the new num vin
     */
    public void setNumVin(String numVin) {
        this.numVin = numVin;
    }

    /**
     * Checks if is uva.
     *
     * @return true, if is uva
     */
    public boolean isUva() {
        return isUva;
    }

    /**
     * Sets the uva.
     *
     * @param isUva
     *            the new uva
     */
    public void setUva(boolean isUva) {
        this.isUva = isUva;
    }

    /**
     * Gets the tipo error.
     *
     * @return the tipo error
     */
    public String getTipoError() {
        return tipoError;
    }

    /**
     * Sets the tipo error.
     *
     * @param tipoError
     *            the new tipo error
     */
    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

    /**
     * Gets the cuenta.
     *
     * @return the cuenta
     */
    public CuentaView getCuenta() {
        return cuenta;
    }

    /**
     * Sets the cuenta.
     *
     * @param cuenta
     *            the new cuenta
     */
    public void setCuenta(CuentaView cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Gets the tasas.
     *
     * @return the tasas
     */
    public TasaValorView getTasas() {
        return tasas;
    }

    /**
     * Sets the tasas.
     *
     * @param tasas
     *            the new tasas
     */
    public void setTasas(TasaValorView tasas) {
        this.tasas = tasas;
    }

    /**
     * Gets the seguro.
     *
     * @return the seguro
     */
    public String getSeguro() {
        return seguro;
    }

    /**
     * Sets the seguro.
     *
     * @param seguro
     *            the new seguro
     */
    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    /**
     * Gets the impuestos.
     *
     * @return the impuestos
     */
    public String getImpuestos() {
        return impuestos;
    }

    /**
     * Sets the impuestos.
     *
     * @param impuestos
     *            the new impuestos
     */
    public void setImpuestos(String impuestos) {

        this.impuestos = impuestos;
    }

    /**
     * Gets the destino prestamo.
     *
     * @return the destino prestamo
     */
    public String getDestinoPrestamo() {
        return destinoPrestamo;
    }

    /**
     * Sets the destino prestamo.
     *
     * @param destinoPrestamo
     *            the new destino prestamo
     */
    public void setDestinoPrestamo(String destinoPrestamo) {
        this.destinoPrestamo = destinoPrestamo;
    }

    /**
     * Gets the importe solicitado.
     *
     * @return the importe solicitado
     */
    public String getImporteSolicitado() {
        return importeSolicitado;
    }

    /**
     * Sets the importe solicitado.
     *
     * @param importeSolicitado
     *            the new importe solicitado
     */
    public void setImporteSolicitado(String importeSolicitado) {
        this.importeSolicitado = importeSolicitado;
    }

    /**
     * Gets the plazo prestamo.
     *
     * @return the plazo prestamo
     */
    public String getPlazoPrestamo() {
        return plazoPrestamo;
    }

    /**
     * Sets the plazo prestamo.
     *
     * @param plazoPrestamo
     *            the new plazo prestamo
     */
    public void setPlazoPrestamo(String plazoPrestamo) {
        this.plazoPrestamo = plazoPrestamo;
    }

    /**
     * Gets the tiene otros impuestos.
     *
     * @return the tiene otros impuestos
     */
    public Boolean getTieneOtrosImpuestos() {
        return tieneOtrosImpuestos;
    }

    /**
     * Sets the tiene otros impuestos.
     *
     * @param tieneOtrosImpuestos
     *            the new tiene otros impuestos
     */
    public void setTieneOtrosImpuestos(Boolean tieneOtrosImpuestos) {
        this.tieneOtrosImpuestos = tieneOtrosImpuestos;
    }

    /**
     * Gets the numero prestamo.
     *
     * @return the numero prestamo
     */
    public String getNumeroPrestamo() {
        return numeroPrestamo;
    }

    /**
     * Sets the numero prestamo.
     *
     * @param numeroPrestamo
     *            the new numero prestamo
     */
    public void setNumeroPrestamo(String numeroPrestamo) {
        this.numeroPrestamo = numeroPrestamo;
    }

    /**
     * Gets the detalle cuota.
     *
     * @return the detalle cuota
     */
    public DetalleCuotaPrestamoView getDetalleCuota() {
        return detalleCuota;
    }

    /**
     * Sets the detalle cuota.
     *
     * @param detalleCuota
     *            the new detalle cuota
     */
    public void setDetalleCuota(DetalleCuotaPrestamoView detalleCuota) {
        this.detalleCuota = detalleCuota;
    }

    /**
     * Gets the legales.
     *
     * @return the legales
     */
    public String getLegales() {
        return legales;
    }

    /**
     * Sets the legales.
     *
     * @param legales
     *            the new legales
     */
    public void setLegales(String legales) {
        this.legales = legales;
    }

    /**
     * Gets the titular.
     *
     * @return the titular
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Sets the titular.
     *
     * @param titular
     *            the new titular
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * Gets the cuil.
     *
     * @return the cuil
     */
    public String getCuil() {
        return cuil;
    }

    /**
     * Sets the cuil.
     *
     * @param cuil
     *            the new cuil
     */
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    /**
     * Gets the tiene seguro.
     *
     * @return the tiene seguro
     */
    public Boolean getTieneSeguro() {
        return tieneSeguro;
    }

    /**
     * Sets the tiene seguro.
     *
     * @param tieneSeguro
     *            the new tiene seguro
     */
    public void setTieneSeguro(Boolean tieneSeguro) {
        this.tieneSeguro = tieneSeguro;
    }

}
