/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;

/**
 * The Class DetalleDeudaPCEntity.
 *
 * @author florencia.n.martinez
 */
public class DetalleDeudaPCEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The tipo comprobante deuda. */
    @Field
    private String tipoComprobanteDeuda;

    /** The nro comprobante deuda. */
    @Field
    private String nroComprobanteDeuda;

    /** The nro cuota deuda. */
    @Field
    private String nroCuotaDeuda;

    /** The fecha vencimiento deuda. */
    @Field
    private String fechaVencimientoDeuda;

    /** The importe pago deuda. */
    @Field
    private String importePagoDeuda;

    /**
     * Gets the tipo comprobante deuda.
     *
     * @return the tipoComprobanteDeuda
     */
    public String getTipoComprobanteDeuda() {
        return tipoComprobanteDeuda;
    }

    /**
     * Sets the tipo comprobante deuda.
     *
     * @param tipoComprobanteDeuda
     *            the tipoComprobanteDeuda to set
     */
    public void setTipoComprobanteDeuda(String tipoComprobanteDeuda) {
        this.tipoComprobanteDeuda = tipoComprobanteDeuda;
    }

    /**
     * Gets the nro comprobante deuda.
     *
     * @return the nroComprobanteDeuda
     */
    public String getNroComprobanteDeuda() {
        return nroComprobanteDeuda;
    }

    /**
     * Sets the nro comprobante deuda.
     *
     * @param nroComprobanteDeuda
     *            the nroComprobanteDeuda to set
     */
    public void setNroComprobanteDeuda(String nroComprobanteDeuda) {
        this.nroComprobanteDeuda = nroComprobanteDeuda;
    }

    /**
     * Gets the nro cuota deuda.
     *
     * @return the nroCuotaDeuda
     */
    public String getNroCuotaDeuda() {
        return nroCuotaDeuda;
    }

    /**
     * Sets the nro cuota deuda.
     *
     * @param nroCuotaDeuda
     *            the nroCuotaDeuda to set
     */
    public void setNroCuotaDeuda(String nroCuotaDeuda) {
        this.nroCuotaDeuda = nroCuotaDeuda;
    }

    /**
     * Gets the fecha vencimiento deuda.
     *
     * @return the fechaVencimientoDeuda
     */
    public String getFechaVencimientoDeuda() {
        return fechaVencimientoDeuda;
    }

    /**
     * Sets the fecha vencimiento deuda.
     *
     * @param fechaVencimientoDeuda
     *            the fechaVencimientoDeuda to set
     */
    public void setFechaVencimientoDeuda(String fechaVencimientoDeuda) {
        this.fechaVencimientoDeuda = fechaVencimientoDeuda;
    }

    /**
     * Gets the importe pago deuda.
     *
     * @return the importePagoDeuda
     */
    public String getImportePagoDeuda() {
        return importePagoDeuda;
    }

    /**
     * Sets the importe pago deuda.
     *
     * @param importePagoDeuda
     *            the importePagoDeuda to set
     */
    public void setImportePagoDeuda(String importePagoDeuda) {
        this.importePagoDeuda = importePagoDeuda;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(fechaVencimientoDeuda);
        hcb.append(importePagoDeuda);
        hcb.append(nroComprobanteDeuda);
        hcb.append(nroCuotaDeuda);
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DetalleDeudaPCEntity other = (DetalleDeudaPCEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(fechaVencimientoDeuda, other.getFechaVencimientoDeuda());
        eb.append(importePagoDeuda, other.getImportePagoDeuda());
        eb.append(nroComprobanteDeuda, other.getNroComprobanteDeuda());
        eb.append(nroCuotaDeuda, other.getNroCuotaDeuda());
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
                .append("nroComprobanteDeuda", nroComprobanteDeuda).append("nroCuotaDeuda", nroCuotaDeuda)
                .append("fechaVencimientoDeuda", fechaVencimientoDeuda).append("importePagoDeuda", importePagoDeuda)
                .toString();
    }

}
