/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaAdicionalSolicitadaDTO;

/**
 * The Class TarjetaAdicionalSolicitadaView.
 */
/**
 * @author b046800
 *
 */
public class TarjetaAdicionalSolicitadaView {

    /** The tipo cuenta. */
    private String tipoCuenta;

    /** The nro tarjeta con formato. */
    private String nroTarjetaConFormato;

    /** The cuenta. */
    private String cuenta;

    /** The limite de compra. */
    private String porcentajeLimiteDeCompra;

    /** The solicitud nro. */
    private String solicitudNro;

    /** The con error. */
    private boolean conError;

    /** The nombre tarjeta. */
    private String nombreTarjeta;

    /**
	 * Instantiates a new tarjeta adicional solicitada view.
	 */
    public TarjetaAdicionalSolicitadaView() {
        super();
    }

    /**
	 * Instantiates a new tarjeta adicional solicitada view.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 */
    public TarjetaAdicionalSolicitadaView(TarjetaAdicionalSolicitadaDTO tarjeta) {
        this.tipoCuenta = tarjeta.getTipoCuenta();
        this.nroTarjetaConFormato = tarjeta.getNroTarjetaConFormato();
        this.cuenta = tarjeta.getCuenta();
        this.porcentajeLimiteDeCompra = tarjeta.getPorcentajeLimiteDeCompra();
        this.solicitudNro = tarjeta.getSolicitudNro();
        this.conError = tarjeta.isConError();
        this.nombreTarjeta = tarjeta.getNombreTarjeta();
    }

    /**
     * Gets the tipo cuenta.
     *
     * @return the tipo cuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the tipo cuenta.
     *
     * @param tipoCuenta
     *            the new tipo cuenta
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Gets the nro tarjeta con formato.
     *
     * @return the nro tarjeta con formato
     */
    public String getNroTarjetaConFormato() {
        return nroTarjetaConFormato;
    }

    /**
     * Sets the nro tarjeta con formato.
     *
     * @param nroTarjetaConFormato
     *            the new nro tarjeta con formato
     */
    public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
        this.nroTarjetaConFormato = nroTarjetaConFormato;
    }

    /**
     * Gets the cuenta.
     *
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Sets the cuenta.
     *
     * @param cuenta
     *            the new cuenta
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Gets the porcentaje limite de compra.
     *
     * @return the porcentaje limite de compra
     */
    public String getPorcentajeLimiteDeCompra() {
        return porcentajeLimiteDeCompra;
    }

    /**
     * Sets the porcentaje limite de compra.
     *
     * @param porcentajeLimiteDeCompra
     *            the new porcentaje limite de compra
     */
    public void setPorcentajeLimiteDeCompra(String porcentajeLimiteDeCompra) {
        this.porcentajeLimiteDeCompra = porcentajeLimiteDeCompra;
    }

    /**
     * Gets the solicitud nro.
     *
     * @return the solicitud nro
     */
    public String getSolicitudNro() {
        return solicitudNro;
    }

    /**
     * Sets the solicitud nro.
     *
     * @param solicitudNro
     *            the new solicitud nro
     */
    public void setSolicitudNro(String solicitudNro) {
        this.solicitudNro = solicitudNro;
    }

    /**
     * Checks if is con error.
     *
     * @return true, if is con error
     */
    public boolean isConError() {
        return conError;
    }

    /**
     * Sets the con error.
     *
     * @param conError
     *            the new con error
     */
    public void setConError(boolean conError) {
        this.conError = conError;
    }

    /**
	 * Gets the nombre tarjeta.
	 *
	 * @return the nombre tarjeta
	 */
    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    /**
	 * Sets the nombre tarjeta.
	 *
	 * @param nombreTarjeta
	 *            the new nombre tarjeta
	 */
    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

}
