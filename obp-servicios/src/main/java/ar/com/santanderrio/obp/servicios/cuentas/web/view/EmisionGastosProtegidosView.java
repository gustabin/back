package ar.com.santanderrio.obp.servicios.cuentas.web.view;

public class EmisionGastosProtegidosView {

    private String codigoRamo;
    private String numeroPoliza;
    private String numeroCertificado;
    private String vigenciaInicio;
    private String vigenciaHasta;
    private String fecha;
    private String hora;
    private String tipoMedioPago;
    private String numeroMedioPago;

    public String getCodigoRamo() {
        return codigoRamo;
    }

    public void setCodigoRamo(String codigoRamo) {
        this.codigoRamo = codigoRamo;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getNumeroCertificado() {
        return numeroCertificado;
    }

    public void setNumeroCertificado(String numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

    public String getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(String vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public String getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(String vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoMedioPago() {
        return tipoMedioPago;
    }

    public void setTipoMedioPago(String tipoMedioPago) {
        this.tipoMedioPago = tipoMedioPago;
    }

    public String getNumeroMedioPago() {
        return numeroMedioPago;
    }

    public void setNumeroMedioPago(String numeroMedioPago) {
        this.numeroMedioPago = numeroMedioPago;
    }
}
