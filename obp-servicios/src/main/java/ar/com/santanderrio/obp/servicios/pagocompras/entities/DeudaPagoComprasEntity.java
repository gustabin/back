/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class DeudaPagoComprasEntity.
 */
@Record
public class DeudaPagoComprasEntity {

    /** The tipo comprobante deuda. */
    @Field
    private String tipoComprobanteDeuda;

    /** The codigo comprobante deuda. */
    @Field
    private String codigoComprobanteDeuda;

    /** The descripcion codigo comprobante deuda. */
    @Field
    private String descripcionCodigoComprobanteDeuda;

    /** The nro comprobante deuda. */
    @Field
    private String nroComprobanteDeuda;

    /** The nro cuota deuda. */
    @Field
    private String nroCuotaDeuda;

    /** The fecha vencimiento deuda. */
    @Field
    private String fechaVencimientoDeuda;

    /** The forma pago deuda. */
    @Field
    private String formaPagoDeuda;

    /** The fecha vencimiento prontopago. */
    @Field
    private String fechaVencimientoProntopago;

    /** The importe prontopago. */
    @Field
    private String importeProntopago;

    /** The importe saldo deuda. */
    @Field
    private String importeSaldoDeuda;

    /** The importe punitorios deuda. */
    @Field
    private String importePunitoriosDeuda;

    /** The importe iva intereses deuda. */
    @Field
    private String importeIvaInteresesDeuda;

    /** The importe iva adicional intereses deuda. */
    @Field
    private String importeIvaAdicionalInteresesDeuda;

    /** The tasa interes punitorios. */
    @Field
    private String tasaInteresPunitorios;

    /** The texto 1. */
    @Field
    private String texto1;

    /** The texto 2. */
    @Field
    private String texto2;

    /** The texto 3. */
    @Field
    private String texto3;

    /** The texto 4. */
    @Field
    private String texto4;

    /** The texto 5. */
    @Field
    private String texto5;

    /** The estado deuda terceros. */
    @Field
    private String estadoDeudaTerceros;

    /**
     * Gets the tipo comprobante deuda.
     *
     * @return the tipo comprobante deuda
     */
    public String getTipoComprobanteDeuda() {
        return tipoComprobanteDeuda;
    }

    /**
     * Sets the tipo comprobante deuda.
     *
     * @param tipoComprobanteDeuda
     *            the new tipo comprobante deuda
     */
    public void setTipoComprobanteDeuda(String tipoComprobanteDeuda) {
        this.tipoComprobanteDeuda = tipoComprobanteDeuda;
    }

    /**
     * Gets the codigo comprobante deuda.
     *
     * @return the codigo comprobante deuda
     */
    public String getCodigoComprobanteDeuda() {
        return codigoComprobanteDeuda;
    }

    /**
     * Sets the codigo comprobante deuda.
     *
     * @param codigoComprobanteDeuda
     *            the new codigo comprobante deuda
     */
    public void setCodigoComprobanteDeuda(String codigoComprobanteDeuda) {
        this.codigoComprobanteDeuda = codigoComprobanteDeuda;
    }

    /**
     * Gets the descripcion codigo comprobante deuda.
     *
     * @return the descripcion codigo comprobante deuda
     */
    public String getDescripcionCodigoComprobanteDeuda() {
        return descripcionCodigoComprobanteDeuda;
    }

    /**
     * Sets the descripcion codigo comprobante deuda.
     *
     * @param descripcionCodigoComprobanteDeuda
     *            the new descripcion codigo comprobante deuda
     */
    public void setDescripcionCodigoComprobanteDeuda(String descripcionCodigoComprobanteDeuda) {
        this.descripcionCodigoComprobanteDeuda = descripcionCodigoComprobanteDeuda;
    }

    /**
     * Gets the nro comprobante deuda.
     *
     * @return the nro comprobante deuda
     */
    public String getNroComprobanteDeuda() {
        return nroComprobanteDeuda;
    }

    /**
     * Sets the nro comprobante deuda.
     *
     * @param nroComprobanteDeuda
     *            the new nro comprobante deuda
     */
    public void setNroComprobanteDeuda(String nroComprobanteDeuda) {
        this.nroComprobanteDeuda = nroComprobanteDeuda;
    }

    /**
     * Gets the nro cuota deuda.
     *
     * @return the nro cuota deuda
     */
    public String getNroCuotaDeuda() {
        return nroCuotaDeuda;
    }

    /**
     * Sets the nro cuota deuda.
     *
     * @param nroCuotaDeuda
     *            the new nro cuota deuda
     */
    public void setNroCuotaDeuda(String nroCuotaDeuda) {
        this.nroCuotaDeuda = nroCuotaDeuda;
    }

    /**
     * Gets the fecha vencimiento deuda.
     *
     * @return the fecha vencimiento deuda
     */
    public String getFechaVencimientoDeuda() {
        return fechaVencimientoDeuda;
    }

    /**
     * Sets the fecha vencimiento deuda.
     *
     * @param fechaVencimientoDeuda
     *            the new fecha vencimiento deuda
     */
    public void setFechaVencimientoDeuda(String fechaVencimientoDeuda) {
        this.fechaVencimientoDeuda = fechaVencimientoDeuda;
    }

    /**
     * Gets the forma pago deuda.
     *
     * @return the forma pago deuda
     */
    public String getFormaPagoDeuda() {
        return formaPagoDeuda;
    }

    /**
     * Sets the forma pago deuda.
     *
     * @param formaPagoDeuda
     *            the new forma pago deuda
     */
    public void setFormaPagoDeuda(String formaPagoDeuda) {
        this.formaPagoDeuda = formaPagoDeuda;
    }

    /**
     * Gets the fecha vencimiento prontopago.
     *
     * @return the fecha vencimiento prontopago
     */
    public String getFechaVencimientoProntopago() {
        return fechaVencimientoProntopago;
    }

    /**
     * Sets the fecha vencimiento prontopago.
     *
     * @param fechaVencimeintoProntopago
     *            the new fecha vencimiento prontopago
     */
    public void setFechaVencimientoProntopago(String fechaVencimeintoProntopago) {
        this.fechaVencimientoProntopago = fechaVencimeintoProntopago;
    }

    /**
     * Gets the importe prontopago.
     *
     * @return the importe prontopago
     */
    public String getImporteProntopago() {
        return importeProntopago;
    }

    /**
     * Sets the importe prontopago.
     *
     * @param importeProntopago
     *            the new importe prontopago
     */
    public void setImporteProntopago(String importeProntopago) {
        this.importeProntopago = importeProntopago;
    }

    /**
     * Gets the importe saldo deuda.
     *
     * @return the importe saldo deuda
     */
    public String getImporteSaldoDeuda() {
        return importeSaldoDeuda;
    }

    /**
     * Sets the importe saldo deuda.
     *
     * @param importeSaldoDeuda
     *            the new importe saldo deuda
     */
    public void setImporteSaldoDeuda(String importeSaldoDeuda) {
        this.importeSaldoDeuda = importeSaldoDeuda;
    }

    /**
     * Gets the importe punitorios deuda.
     *
     * @return the importe punitorios deuda
     */
    public String getImportePunitoriosDeuda() {
        return importePunitoriosDeuda;
    }

    /**
     * Sets the importe punitorios deuda.
     *
     * @param importePunitoriosDeuda
     *            the new importe punitorios deuda
     */
    public void setImportePunitoriosDeuda(String importePunitoriosDeuda) {
        this.importePunitoriosDeuda = importePunitoriosDeuda;
    }

    /**
     * Gets the importe iva intereses deuda.
     *
     * @return the importe iva intereses deuda
     */
    public String getImporteIvaInteresesDeuda() {
        return importeIvaInteresesDeuda;
    }

    /**
     * Sets the importe iva intereses deuda.
     *
     * @param importeIvaInteresesDeuda
     *            the new importe iva intereses deuda
     */
    public void setImporteIvaInteresesDeuda(String importeIvaInteresesDeuda) {
        this.importeIvaInteresesDeuda = importeIvaInteresesDeuda;
    }

    /**
     * Gets the importe iva adicional intereses deuda.
     *
     * @return the importe iva adicional intereses deuda
     */
    public String getImporteIvaAdicionalInteresesDeuda() {
        return importeIvaAdicionalInteresesDeuda;
    }

    /**
     * Sets the importe iva adicional intereses deuda.
     *
     * @param importeIvaAdicionalInteresesDeuda
     *            the new importe iva adicional intereses deuda
     */
    public void setImporteIvaAdicionalInteresesDeuda(String importeIvaAdicionalInteresesDeuda) {
        this.importeIvaAdicionalInteresesDeuda = importeIvaAdicionalInteresesDeuda;
    }

    /**
     * Gets the tasa interes punitorios.
     *
     * @return the tasa interes punitorios
     */
    public String getTasaInteresPunitorios() {
        return tasaInteresPunitorios;
    }

    /**
     * Sets the tasa interes punitorios.
     *
     * @param tasaInteresPunitorios
     *            the new tasa interes punitorios
     */
    public void setTasaInteresPunitorios(String tasaInteresPunitorios) {
        this.tasaInteresPunitorios = tasaInteresPunitorios;
    }

    /**
     * Gets the texto 1.
     *
     * @return the texto 1
     */
    public String getTexto1() {
        return texto1;
    }

    /**
     * Sets the texto 1.
     *
     * @param texto1
     *            the new texto 1
     */
    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }

    /**
     * Gets the texto 2.
     *
     * @return the texto 2
     */
    public String getTexto2() {
        return texto2;
    }

    /**
     * Sets the texto 2.
     *
     * @param texto2
     *            the new texto 2
     */
    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    /**
     * Gets the texto 3.
     *
     * @return the texto 3
     */
    public String getTexto3() {
        return texto3;
    }

    /**
     * Sets the texto 3.
     *
     * @param texto3
     *            the new texto 3
     */
    public void setTexto3(String texto3) {
        this.texto3 = texto3;
    }

    /**
     * Gets the texto 4.
     *
     * @return the texto 4
     */
    public String getTexto4() {
        return texto4;
    }

    /**
     * Sets the texto 4.
     *
     * @param texto4
     *            the new texto 4
     */
    public void setTexto4(String texto4) {
        this.texto4 = texto4;
    }

    /**
     * Gets the texto 5.
     *
     * @return the texto 5
     */
    public String getTexto5() {
        return texto5;
    }

    /**
     * Sets the texto 5.
     *
     * @param texto5
     *            the new texto 5
     */
    public void setTexto5(String texto5) {
        this.texto5 = texto5;
    }

    /**
     * Gets the estado deuda terceros.
     *
     * @return the estado deuda terceros
     */
    public String getEstadoDeudaTerceros() {
        return estadoDeudaTerceros;
    }

    /**
     * Sets the estado deuda terceros.
     *
     * @param estadoDeudaTerceros
     *            the new estado deuda terceros
     */
    public void setEstadoDeudaTerceros(String estadoDeudaTerceros) {
        this.estadoDeudaTerceros = estadoDeudaTerceros;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(codigoComprobanteDeuda);
        hcb.append(fechaVencimientoDeuda);
        hcb.append(formaPagoDeuda);
        hcb.append(importeSaldoDeuda);
        hcb.append(nroComprobanteDeuda);
        hcb.append(nroCuotaDeuda);
        hcb.append(tasaInteresPunitorios);
        hcb.append(tipoComprobanteDeuda);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DeudaPagoComprasEntity other = (DeudaPagoComprasEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codigoComprobanteDeuda, other.getCodigoComprobanteDeuda());
        eb.append(fechaVencimientoDeuda, other.getFechaVencimientoDeuda());
        eb.append(formaPagoDeuda, other.getFormaPagoDeuda());
        eb.append(importeSaldoDeuda, other.getImporteSaldoDeuda());
        eb.append(nroComprobanteDeuda, other.getNroComprobanteDeuda());
        eb.append(nroCuotaDeuda, other.getNroCuotaDeuda());
        eb.append(tasaInteresPunitorios, other.getTasaInteresPunitorios());
        eb.append(tipoComprobanteDeuda, other.getTipoComprobanteDeuda());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("tipoComprobanteDeuda", tipoComprobanteDeuda)
                .append("codigoComprobanteDeuda", codigoComprobanteDeuda)
                .append("descripcionCodigoComprobanteDeuda", descripcionCodigoComprobanteDeuda)
                .append("nroComprobanteDeuda", nroComprobanteDeuda).append("nroCuotaDeuda", nroCuotaDeuda)
                .append("fechaVencimientoDeuda", fechaVencimientoDeuda).append("formaPagoDeuda", formaPagoDeuda)
                .append("fechaVencimientoProntopago", fechaVencimientoProntopago)
                .append("importeProntopago", importeProntopago).append("importeSaldoDeuda", importeSaldoDeuda)
                .append("importePunitoriosDeuda", importePunitoriosDeuda)
                .append("importeIvaInteresesDeuda", importeIvaInteresesDeuda)
                .append("importeIvaAdicionalInteresesDeuda", importeIvaAdicionalInteresesDeuda)
                .append("tasaInteresPunitorios", tasaInteresPunitorios).append("texto1", texto1)
                .append("texto2", texto2).append("texto3", texto3).append("texto4", texto4).append("texto5", texto5)
                .append("estadoDeudaTerceros", estadoDeudaTerceros).toString();
    }
}
