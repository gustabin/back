/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class Factura.
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class Factura implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The empresa. */
    private String empresa;

    /** The monto. */
    private String monto;

    /** The fecha vencimiento. */
    private String fechaVencimiento;

    /** The numeroReferenciaPago. */
    private String numeroReferenciaPago;

    /** The numero de la factura. */
    private String numeroFactura;

    /** The pre pago. */
    private Boolean prePago;

    /**
     * Getter para empresa.
     *
     * @return la empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Setter para empresa.
     *
     * @param empresa
     *            el nuevo empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Getter para fechaVencimiento.
     *
     * @return la fechaVencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Setter para fechaVencimiento.
     *
     * @param fechaVencimiento
     *            la nueva fecha vencimiento
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Getter para monto.
     *
     * @return el monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Setter para monto.
     *
     * @param monto
     *            el nuevo monto
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * Getter para numero referencia pago.
     *
     * @return el numero referencia pago.
     */
    public String getNumeroReferenciaPago() {
        return numeroReferenciaPago;
    }

    /**
     * Setter para numero referencia pago.
     *
     * @param numeroReferenciaPago
     *            el nuevo numero referencia pago
     */
    public void setNumeroReferenciaPago(String numeroReferenciaPago) {
        this.numeroReferenciaPago = numeroReferenciaPago;
    }

    /**
     * Getter para numero factura.
     *
     * @return el numeroFactura
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Setter para numero factura.
     *
     * @param numeroFactura
     *            el numeroFactura a setear
     */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * Getter para pre pago.
     *
     * @return el pre pago
     */
    public Boolean getPrePago() {
        return prePago;
    }

    /**
     * Setter para pre pago.
     *
     * @param prePago
     *            el nuevo pre pago
     */
    public void setPrePago(Boolean prePago) {
        this.prePago = prePago;
    }

}