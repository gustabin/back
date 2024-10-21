package ar.com.santanderrio.obp.servicios.prestamos.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class PrestamoEncoladoView {

    private String id;

    private String monto;

    private String fechaCreacion;

    private String tipoPrestamo;

    private String descripcion;

    private String nroPrestamo;

    private String cuotas;

    private String fechaPrimerVencimiento;

    private String tna;

    private Boolean isUva;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNroPrestamo() {
        return nroPrestamo;
    }

    public void setNroPrestamo(String nroPrestamo) {
        this.nroPrestamo = nroPrestamo;
    }

    public String getCuotas() {
        return cuotas;
    }

    public void setCuotas(String cuotas) {
        this.cuotas = cuotas;
    }

    public String getFechaPrimerVencimiento() {
        return fechaPrimerVencimiento;
    }

    public void setFechaPrimerVencimiento(String fechaPrimerVencimiento) {
        this.fechaPrimerVencimiento = fechaPrimerVencimiento;
    }

    public String getTna() {
        return tna;
    }

    public void setTna(String tna) {
        this.tna = tna;
    }

    public Boolean getUva() {
        return isUva;
    }

    public void setUva(Boolean uva) {
        isUva = uva;
    }

}
