package ar.com.santanderrio.obp.servicios.seguros.entities;

import ar.com.santanderrio.obp.servicios.seguros.dto.TarjetaCompraProtegidaDTO;

import java.io.Serializable;
import java.util.List;

public class EmisionOfertaIntegradaOut implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1160042353847004739L;

    private Long numeroCotizacion;
    private Integer codigoRamo;
    private String codigoProducto;
    private String codigoPlan;
    private String tipoCuenta;
    private String numeroCuenta;
    private String sucursal;
    private String tipoTarjeta;
    private String numeroTarjeta;
    private String cargoPEP;
    private String origenFondos;
    private String codigoOcupacion;
    private String indicadorPEP;
    private String tipoPoliza;
    private String origenServicio;

    private List<TarjetaCompraProtegidaDTO> tarjetas; // esto es para compra protegida

    public Long getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(Long numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public Integer getCodigoRamo() {
        return codigoRamo;
    }

    public void setCodigoRamo(Integer codigoRamo) {
        this.codigoRamo = codigoRamo;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoPlan() {
        return codigoPlan;
    }

    public void setCodigoPlan(String codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

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

    public String getCargoPEP() {
        return cargoPEP;
    }

    public void setCargoPEP(String cargoPEP) {
        this.cargoPEP = cargoPEP;
    }

    public String getOrigenFondos() {
        return origenFondos;
    }

    public void setOrigenFondos(String origenFondos) {
        this.origenFondos = origenFondos;
    }

    public List<TarjetaCompraProtegidaDTO> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaCompraProtegidaDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public String getCodigoOcupacion() {
        return codigoOcupacion;
    }

    public void setCodigoOcupacion(String codigoOcupacion) {
        this.codigoOcupacion = codigoOcupacion;
    }

    public String getIndicadorPEP() {
        return indicadorPEP;
    }

    public void setIndicadorPEP(String indicadorPEP) {
        this.indicadorPEP = indicadorPEP;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public String getOrigenServicio() {
        return origenServicio;
    }

    public void setOrigenServicio(String origenServicio) {
        this.origenServicio = origenServicio;
    }
}
