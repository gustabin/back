package ar.com.santanderrio.obp.servicios.seguros.entities;

import java.io.Serializable;

public class GastoProtegido implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1160042353847004789L;

    /** The condicion Laboral. */
    private String condicionLaboral;
    private String numeroCuenta;
    private String sucursal;
    private String cbu;

    public String getCondicionLaboral() {
        return condicionLaboral;
    }

    public void setCondicionLaboral(String condicionLaboral) {
        this.condicionLaboral = condicionLaboral;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }
}

