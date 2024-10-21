/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class ComprobanteModificacionLimiteDebitoView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComprobanteModificacionLimiteDebitoView extends RsaDTOParaDesafio {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6800903829236899187L;

    /** The clase tarjeta debito. */
    private String claseTarjetaDebito;

    /** The id cuenta. */
    private String idCuenta;

    /** The comprobante. */
    private String comprobante;

    /** The mensaje. */
    private String mensaje;

    /** The fecha hora. */
    private String fechaHora;

    /** The legales SEO. */
    private String legalesSEO;

    /** The tiene celular my A. Se usa en RSA. */
    private boolean tieneCelularMyA = false;

    /** The monto seleccionado. */
    private String montoSeleccionado;

    /** The monto actual. */
    private String montoActual;

    /**
     * Instantiates a new comprobante modificacion limite debito view.
     */
    public ComprobanteModificacionLimiteDebitoView() {
        super(OperacionesRSAEnum.MODIFICACION_LIMITE_DEBITO);
    }

    /**
     * Gets the comprobante.
     *
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * Sets the comprobante.
     *
     * @param comprobante
     *            the new comprobante
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
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
     * Gets the legales SEO.
     *
     * @return the legales SEO
     */
    public String getLegalesSEO() {
        return legalesSEO;
    }

    /**
     * Sets the legales SEO.
     *
     * @param legalesSEO
     *            the new legales SEO
     */
    public void setLegalesSEO(String legalesSEO) {
        this.legalesSEO = legalesSEO;
    }

    /**
     * Gets the mensaje.
     *
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the mensaje.
     *
     * @param mensaje
     *            the new mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets the clase tarjeta debito.
     *
     * @return the clase tarjeta debito
     */
    public String getClaseTarjetaDebito() {
        return claseTarjetaDebito;
    }

    /**
     * Sets the clase tarjeta debito.
     *
     * @param claseTarjetaDebito
     *            the new clase tarjeta debito
     */
    public void setClaseTarjetaDebito(String claseTarjetaDebito) {
        this.claseTarjetaDebito = claseTarjetaDebito;
    }

    /**
     * Gets the id cuenta.
     *
     * @return the id cuenta
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * Sets the id cuenta.
     *
     * @param idCuenta
     *            the new id cuenta
     */
    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * Checks if is tiene celular my A.
     *
     * @return true, if is tiene celular my A
     */
    public boolean isTieneCelularMyA() {
        return tieneCelularMyA;
    }

    /**
     * Sets the tiene celular my A.
     *
     * @param tieneCelularMyA
     *            the new tiene celular my A
     */
    public void setTieneCelularMyA(boolean tieneCelularMyA) {
        this.tieneCelularMyA = tieneCelularMyA;
    }

    /**
     * Gets the monto seleccionado.
     *
     * @return the montoSeleccionado
     */
    public String getMontoSeleccionado() {
        return montoSeleccionado;
    }

    /**
     * Sets the monto seleccionado.
     *
     * @param montoSeleccionado
     *            the montoSeleccionado to set
     */
    public void setMontoSeleccionado(String montoSeleccionado) {
        this.montoSeleccionado = montoSeleccionado;
    }

    /**
     * Gets the monto actual.
     *
     * @return the montoActual
     */
    public String getMontoActual() {
        return montoActual;
    }

    /**
     * Sets the monto actual.
     *
     * @param montoActual
     *            the montoActual to set
     */
    public void setMontoActual(String montoActual) {
        this.montoActual = montoActual;
    }

}
