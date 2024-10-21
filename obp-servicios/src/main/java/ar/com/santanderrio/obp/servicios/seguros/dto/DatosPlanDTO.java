package ar.com.santanderrio.obp.servicios.seguros.dto;

public class DatosPlanDTO {

    private String codigoPlan;
    private String nombrePlan;
    private String codigoProducto;
    private String claseProducto;
    private Integer codigoRamo;
    private Integer numeroPlan;
    private String grupoOrden;
    private String mensaje;

    public String getCodigoPlan() {
        return codigoPlan;
    }

    public void setCodigoPlan(String codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getCodigoRamo() {
        return codigoRamo;
    }

    public void setCodigoRamo(Integer codigoRamo) {
        this.codigoRamo = codigoRamo;
    }

    public String getClaseProducto() {
        return claseProducto;
    }

    public void setClaseProducto(String claseProducto) {
        this.claseProducto = claseProducto;
    }

    public Integer getNumeroPlan() {
        return numeroPlan;
    }

    public void setNumeroPlan(Integer numeroPlan) {
        this.numeroPlan = numeroPlan;
    }

    public String getGrupoOrden() {
        return grupoOrden;
    }

    public void setGrupoOrden(String grupoOrden) {
        this.grupoOrden = grupoOrden;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
