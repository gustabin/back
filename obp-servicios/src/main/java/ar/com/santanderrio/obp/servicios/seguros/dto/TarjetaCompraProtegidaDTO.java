package ar.com.santanderrio.obp.servicios.seguros.dto;

public class TarjetaCompraProtegidaDTO {

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

    public TarjetaCompraProtegidaDTO(String tipoTarjeta, String numeroTarjeta, String numeroCuenta) {
        this.tipoTarjeta = tipoTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.numeroCuenta = numeroCuenta;
    }
    public TarjetaCompraProtegidaDTO() {
    }
}
