package ar.com.santanderrio.obp.servicios.seguros.dto;

import java.util.List;

public class CoberturasDTO {

    private String codigoCobertura;
    private String cobertura;
    private String codigoPlan;
    private String codigoProducto;
    private Long sumaAsegurada;
    private Double montoPrima;
    private Double montoComision;
    private List<ItemsCoberturaDTO> itemsCobertura;

    public String getCodigoCobertura() {
        return codigoCobertura;
    }

    public void setCodigoCobertura(String codigoCobertura) {
        this.codigoCobertura = codigoCobertura;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getCodigoPlan() {
        return codigoPlan;
    }

    public void setCodigoPlan(String codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Long getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(Long sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }

    public Double getMontoPrima() {
        return montoPrima;
    }

    public void setMontoPrima(Double montoPrima) {
        this.montoPrima = montoPrima;
    }

    public Double getMontoComision() {
        return montoComision;
    }

    public void setMontoComision(Double montoComision) {
        this.montoComision = montoComision;
    }

    public List<ItemsCoberturaDTO> getItemsCobertura() {
        return itemsCobertura;
    }

    public void setItemsCobertura(List<ItemsCoberturaDTO> itemsCobertura) {
        this.itemsCobertura = itemsCobertura;
    }
}
