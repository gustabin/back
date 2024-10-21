package ar.com.santanderrio.obp.servicios.seguros.dto;

public class DatosCotizacionDTO {

    private Long numeroCotizacion;
    private Double cuota;
    private Long sumaAsegurada;
    private Integer codigoRamo;
    private Double impuesto;
    private Integer codSucursal;


    public Long getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(Long numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public Long getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(Long sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }

    public Integer getCodigoRamo() {
        return codigoRamo;
    }

    public void setCodigoRamo(Integer codigoRamo) {
        this.codigoRamo = codigoRamo;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Integer getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(Integer codSucursal) {
        this.codSucursal = codSucursal;
    }
}
