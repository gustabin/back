package ar.com.santanderrio.obp.servicios.seguros.entities;

import java.io.Serializable;

public class CompraProtegidaOut implements Serializable {

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = 1160042353847004789L;

        private String tipoTarjeta;
        private String numeroTarjeta;
        private String numeroCuenta;

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
