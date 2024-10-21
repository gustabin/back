/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaCandidataDTO;

/**
 * The Class TarjetaCandidataView.
 */
public class TarjetaCandidataView {

    /** The tipo cuenta. */
    private String tipoCuenta;

    /** The tipo cuenta descripcion. */
    private String tipoCuentaDescripcion;

    /** The nro tarjeta con formato. */
    private String nroTarjetaConFormato;

    /** The alias. */
    private String alias;

    /** The porcentaje limite de compra. */
    private String porcentajeLimiteDeCompra;

    /** The limite de compra. */
    private String limiteDeCompra;

    /** The cuenta id. */
    private String cuentaId;

    /** The moneda. */
    private String moneda;

    /** The tipo tarjeta. */
    private String tipoTarjeta;

    /**
     * Instantiates a new tarjeta candidata view.
     */
    public TarjetaCandidataView() {
        super();
    }

    /**
     * Instantiates a new tarjeta candidata view.
     *
     * @param tarjetaCandidataDTO
     *            the tarjeta candidata DTO
     */
    public TarjetaCandidataView(TarjetaCandidataDTO tarjetaCandidataDTO) {
        this.tipoCuenta = tarjetaCandidataDTO.getTipoCuenta();
        this.tipoCuentaDescripcion = tarjetaCandidataDTO.getTipoCuentaDescripcion();
        this.nroTarjetaConFormato = tarjetaCandidataDTO.getNroTarjetaConFormato();
        this.alias = tarjetaCandidataDTO.getAlias();
        this.limiteDeCompra = tarjetaCandidataDTO.getLimiteDeCompra();
        this.cuentaId = tarjetaCandidataDTO.getCuentaId();
        this.moneda = tarjetaCandidataDTO.getMoneda();
        this.tipoTarjeta = tarjetaCandidataDTO.getTipoTarjeta();
    }

    /**
     * Gets the tipo cuenta.
     *
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the tipo cuenta.
     *
     * @param tipoCuenta
     *            the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Gets the tipo cuenta descripcion.
     *
     * @return the tipo cuenta descripcion
     */
    public String getTipoCuentaDescripcion() {
        return tipoCuentaDescripcion;
    }

    /**
     * Sets the tipo cuenta descripcion.
     *
     * @param tipoCuentaDescripcion
     *            the new tipo cuenta descripcion
     */
    public void setTipoCuentaDescripcion(String tipoCuentaDescripcion) {
        this.tipoCuentaDescripcion = tipoCuentaDescripcion;
    }

    /**
     * Gets the nro tarjeta con formato.
     *
     * @return the nroTarjetaConFormato
     */
    public String getNroTarjetaConFormato() {
        return nroTarjetaConFormato;
    }

    /**
     * Sets the nro tarjeta con formato.
     *
     * @param nroTarjetaConFormato
     *            the nroTarjetaConFormato to set
     */
    public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
        this.nroTarjetaConFormato = nroTarjetaConFormato;
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
     *            the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the porcentaje limite de compra.
     *
     * @return the porcentajeLimiteDeCompra
     */
    public String getPorcentajeLimiteDeCompra() {
        return porcentajeLimiteDeCompra;
    }

    /**
     * Sets the porcentaje limite de compra.
     *
     * @param porcentajeLimiteDeCompra
     *            the porcentajeLimiteDeCompra to set
     */
    public void setPorcentajeLimiteDeCompra(String porcentajeLimiteDeCompra) {
        this.porcentajeLimiteDeCompra = porcentajeLimiteDeCompra;
    }

    /**
     * Gets the limite de compra.
     *
     * @return the limiteDeCompra
     */
    public String getLimiteDeCompra() {
        return limiteDeCompra;
    }

    /**
     * Sets the limite de compra.
     *
     * @param limiteDeCompra
     *            the limiteDeCompra to set
     */
    public void setLimiteDeCompra(String limiteDeCompra) {
        this.limiteDeCompra = limiteDeCompra;
    }

    /**
     * Gets the cuenta id.
     *
     * @return the cuentaId
     */
    public String getCuentaId() {
        return cuentaId;
    }

    /**
     * Sets the cuenta id.
     *
     * @param cuentaId
     *            the cuentaId to set
     */
    public void setCuentaId(String cuentaId) {
        this.cuentaId = cuentaId;
    }

    /**
     * Gets the moneda.
     *
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the moneda.
     *
     * @param moneda
     *            the new moneda
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * Gets the tipo tarjeta.
     *
     * @return the tipo tarjeta
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Sets the tipo tarjeta.
     *
     * @param tipoTarjeta
     *            the new tipo tarjeta
     */
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

}
