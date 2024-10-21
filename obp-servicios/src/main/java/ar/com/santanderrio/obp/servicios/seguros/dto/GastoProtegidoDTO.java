package ar.com.santanderrio.obp.servicios.seguros.dto;

import java.util.List;

public class GastoProtegidoDTO {

    private DatosCotizacionDTO datosCotizacion;
    private DatosAseguradoraDTO datosAseguradora;
    private DatosPlanDTO datosPlan;
    private DatosAseguradoDTO datosAsegurado;
    private ValoresDefaultDTO valoresDefault;
    private List<CoberturasDTO> coberturas;
    private List<ItemsCoberturaDTO> textos;

    public DatosCotizacionDTO getDatosCotizacion() {
        return datosCotizacion;
    }

    public void setDatosCotizacion(DatosCotizacionDTO datosCotizacion) {
        this.datosCotizacion = datosCotizacion;
    }

    public DatosAseguradoraDTO getDatosAseguradora() {
        return datosAseguradora;
    }

    public void setDatosAseguradora(DatosAseguradoraDTO datosAseguradora) {
        this.datosAseguradora = datosAseguradora;
    }

    public DatosPlanDTO getDatosPlan() {
        return datosPlan;
    }

    public void setDatosPlan(DatosPlanDTO datosPlan) {
        this.datosPlan = datosPlan;
    }

    public DatosAseguradoDTO getDatosAsegurado() {
        return datosAsegurado;
    }

    public void setDatosAsegurado(DatosAseguradoDTO datosAsegurado) {
        this.datosAsegurado = datosAsegurado;
    }

    public ValoresDefaultDTO getValoresDefault() {
        return valoresDefault;
    }

    public void setValoresDefault(ValoresDefaultDTO valoresDefault) {
        this.valoresDefault = valoresDefault;
    }

    public List<CoberturasDTO> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<CoberturasDTO> coberturas) {
        this.coberturas = coberturas;
    }

    public List<ItemsCoberturaDTO> getTextos() {
        return textos;
    }

    public void setTextos(List<ItemsCoberturaDTO> textos) {
        this.textos = textos;
    }
}
