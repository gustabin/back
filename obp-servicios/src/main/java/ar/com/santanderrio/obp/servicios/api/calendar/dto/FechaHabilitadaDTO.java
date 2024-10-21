package ar.com.santanderrio.obp.servicios.api.calendar.dto;

import org.apache.commons.lang.StringUtils;

public class FechaHabilitadaDTO {

    private final int dia;
    private final int mes;
    private final int anio;

    public FechaHabilitadaDTO(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {return dia;}

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    @Override
    public String toString(){
        return anio+"-"+StringUtils.leftPad(String.valueOf(mes), 2, "0")+"-"+StringUtils.leftPad(String.valueOf(dia), 2, "0");
    }

}
