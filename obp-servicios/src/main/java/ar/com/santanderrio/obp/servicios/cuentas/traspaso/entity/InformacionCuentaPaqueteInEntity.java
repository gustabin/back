/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity;

import java.io.Serializable;

/**
 * The Class InformacionCuentaPaqueteInEntity.
 */
public class InformacionCuentaPaqueteInEntity implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5374560051727193406L;

    /** The tipo cuenta. */
    private String tipoCuenta;

    /** The sucursal cuenta. */
    private String sucursalCuenta;

    /** The numero cuenta. */
    private String numeroCuenta;
    
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
     * Gets the sucursal cuenta.
     *
     * @return the sucursal cuenta
     */
    public String getSucursalCuenta() {
        return sucursalCuenta;
    }

    /**
     * Sets the sucursal cuenta.
     *
     * @param sucursalCuenta
     *            the new sucursal cuenta
     */
    public void setSucursalCuenta(String sucursalCuenta) {
        this.sucursalCuenta = sucursalCuenta;
    }

    /**
     * Gets the numero cuenta.
     *
     * @return the numero cuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Sets the numero cuenta.
     *
     * @param numeroCuenta
     *            the new numero cuenta
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

}
