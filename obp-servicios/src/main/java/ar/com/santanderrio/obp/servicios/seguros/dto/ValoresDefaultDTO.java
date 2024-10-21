package ar.com.santanderrio.obp.servicios.seguros.dto;

import java.util.List;

public class ValoresDefaultDTO {

    private String tipoPoliza;
    private String indicadorPEP;
    private String origenFondos;

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
