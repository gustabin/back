package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;

public class GastoProtegidoView {

    private String cuota;
    private String sumaAsegurada;
    private String mensajeSwitchOff;
    private Boolean showOfertaIntegrada;
    private Boolean stopOfertaIntegrada;
    private String contrato;
    private Boolean showContrato;
    private String gastoProtegidoTitulo;
    private String workingCondition;
    private String codigoOcupacion;
    private String tipoPoliza;
    private String indicadorPEP;
    private String origenFondos;
    private EmisionOfertaIntegrada emisionOfertaIntegrada;


    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public String getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(String sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }

    public String getMensajeSwitchOff() {
        return mensajeSwitchOff;
    }

    public void setMensajeSwitchOff(String mensajeSwitchOff) {
        this.mensajeSwitchOff = mensajeSwitchOff;
    }

    public Boolean getShowOfertaIntegrada() {
        return showOfertaIntegrada;
    }

    public void setShowOfertaIntegrada(Boolean showOfertaIntegrada) {
        this.showOfertaIntegrada = showOfertaIntegrada;
    }

    public Boolean getStopOfertaIntegrada() {
        return stopOfertaIntegrada;
    }

    public void setStopOfertaIntegrada(Boolean stopOfertaIntegrada) {
        this.stopOfertaIntegrada = stopOfertaIntegrada;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Boolean getShowContrato() {
        return showContrato;
    }

    public void setShowContrato(Boolean showContrato) {
        this.showContrato = showContrato;
    }

    public String getGastoProtegidoTitulo() {
        return gastoProtegidoTitulo;
    }

    public void setGastoProtegidoTitulo(String gastoProtegidoTitulo) {
        this.gastoProtegidoTitulo = gastoProtegidoTitulo;
    }

    public String getWorkingCondition() {
        return workingCondition;
    }

    public void setWorkingCondition(String workingCondition) {
        this.workingCondition = workingCondition;
    }

    public EmisionOfertaIntegrada getEmisionOfertaIntegrada() {
        return emisionOfertaIntegrada;
    }

    public void setEmisionOfertaIntegrada(EmisionOfertaIntegrada emisionOfertaIntegrada) {
        this.emisionOfertaIntegrada = emisionOfertaIntegrada;
    }

    public String getCodigoOcupacion() {
        return codigoOcupacion;
    }

    public void setCodigoOcupacion(String codigoOcupacion) {
        this.codigoOcupacion = codigoOcupacion;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public String getIndicadorPEP() {
        return indicadorPEP;
    }

    public void setIndicadorPEP(String indicadorPEP) {
        this.indicadorPEP = indicadorPEP;
    }

    public String getOrigenFondos() {
        return origenFondos;
    }

    public void setOrigenFondos(String origenFondos) {
        this.origenFondos = origenFondos;
    }

}
