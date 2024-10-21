/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

/**
 * The Class TarjetaBlackNacional.
 */
public class TarjetaBlackNacional {
    
    /** The tiene black. */
    private Boolean tieneBlack;
    
    /** The tiene nacional. */
    private Boolean tieneNacional;

    /** The solo nacional. */
    private Boolean soloNacional;

    /** The tiene tarjetas. */
    private Boolean tieneTarjetas;

    /**
     * Instantiates a new tarjeta black nacional.
     */
    public TarjetaBlackNacional() {
        super();
        this.tieneBlack = Boolean.FALSE;
        this.tieneNacional = Boolean.FALSE;
        this.tieneTarjetas = Boolean.FALSE;
        this.soloNacional = Boolean.TRUE;
    }

    /**
     * Gets the solo nacional.
     *
     * @return the soloNacional
     */
    public Boolean getSoloNacional() {
        return soloNacional;
    }

    /**
     * Sets the solo nacional.
     *
     * @param soloNacional the soloNacional to set
     */
    public void setSoloNacional(Boolean soloNacional) {
        this.soloNacional = soloNacional;
    }

    /**
     * Gets the tiene tarjetas.
     *
     * @return the tieneTarjetas
     */
    public Boolean getTieneTarjetas() {
        return tieneTarjetas;
    }

    /**
     * Sets the tiene tarjetas.
     *
     * @param tieneTarjetas the tieneTarjetas to set
     */
    public void setTieneTarjetas(Boolean tieneTarjetas) {
        this.tieneTarjetas = tieneTarjetas;
    }

    /**
     * Gets the tiene black.
     *
     * @return the tieneBlack
     */
    public Boolean getTieneBlack() {
        return tieneBlack;
    }

    /**
     * Sets the tiene black.
     *
     * @param tieneBlack the tieneBlack to set
     */
    public void setTieneBlack(Boolean tieneBlack) {
        this.tieneBlack = tieneBlack;
    }

    /**
     * Gets the tiene nacional.
     *
     * @return the tieneNacional
     */
    public Boolean getTieneNacional() {
        return tieneNacional;
    }

    /**
     * Sets the tiene nacional.
     *
     * @param tieneNacional the tieneNacional to set
     */
    public void setTieneNacional(Boolean tieneNacional) {
        this.tieneNacional = tieneNacional;
    }
    
}
