package ar.com.santanderrio.obp.servicios.seguros.dto;

public class EmisionOfertaIntegradaDTO {

    private Integer codigoRamo;
    private Long numeroPoliza;
    private Long numeroCertificado;
    private String vigenciaInicio;
    private String vigenciaHasta;
    private String descripcionPoliza;
    private String fecha;
    private String hora;
    private String tipoMedioPago;
    private String numeroMedioPago;
    private String comprobanteAnulacion;

    public Integer getCodigoRamo() {
        return codigoRamo;
    }

    public void setCodigoRamo(Integer codigoRamo) {
        this.codigoRamo = codigoRamo;
    }

    public Long getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(Long numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public Long getNumeroCertificado() {
        return numeroCertificado;
    }

    public void setNumeroCertificado(Long numeroCertificado) {
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

    public String getDescripcionPoliza() {
        return descripcionPoliza;
    }

    public void setDescripcionPoliza(String descripcionPoliza) {
        this.descripcionPoliza = descripcionPoliza;
    }

    public String getComprobanteAnulacion() {
        return comprobanteAnulacion;
    }

    public void setComprobanteAnulacion(String comprobanteAnulacion) {
        this.comprobanteAnulacion = comprobanteAnulacion;
    }
}
