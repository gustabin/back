package ar.com.santanderrio.obp.servicios.transferencias.entities;


import org.apache.commons.lang3.StringUtils;

public class DatosTransferenciaDestino {

    private String id;
    private String alias;
    private String cbu;
    private String numeroCuenta;
    private String fechaCreacion;
    private boolean transferenciaAgendadaHaciaOtroBanco;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = normalizarParametro(alias);
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = normalizarParametro(cbu);
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isTransferenciaAgendadaHaciaOtroBanco() {
        return transferenciaAgendadaHaciaOtroBanco;
    }

    public void setTransferenciaAgendadaHaciaOtroBanco(boolean transferenciaAgendadaHaciaOtroBanco) {
        this.transferenciaAgendadaHaciaOtroBanco = transferenciaAgendadaHaciaOtroBanco;
    }

    private String normalizarParametro(String parametro) {

        return StringUtils.defaultIfBlank(parametro, null);

    }

    @Override
    public String toString() {
        return "DatosTransferenciaDestino{" +
                "alias='" + alias + '\'' +
                ", cbu='" + cbu + '\'' +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                '}';
    }
}
