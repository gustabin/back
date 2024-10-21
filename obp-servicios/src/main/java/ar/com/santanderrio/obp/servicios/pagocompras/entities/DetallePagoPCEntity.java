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
import org.beanio.annotation.Record;

/**
 * The Class DetallePagoPCEntity.
 *
 * @author florencia.n.martinez
 */
@Record
public class DetallePagoPCEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The forma pago deuda. */
    @Field
    private String formaPagoDeuda;

    /** The nro banco cuenta. */
    @Field
    private String nroBancoCuenta;

    /** The codigo postal. */
    @Field
    private String codigoPostal;

    /** The nro cheque. */
    @Field
    private String nroCheque;

    /** The sucursal cuenta. */
    @Field
    private String sucursalCuenta;

    /** The tipo cuenta. */
    @Field
    private String tipoCuenta;

    /** The nro cuenta generico. */
    @Field
    private String nroCuentaGenerico;

    /** The importe pago. */
    @Field
    private String importePago;

    /** The fecha vencimiento CPD. */
    @Field
    private String fechaVencimientoCPD;

    /** The mar libre disp. */
    @Field
    private String marLibreDisp;

    /**
     * Gets the forma pago deuda.
     *
     * @return the formaPagoDeuda
     */
    public String getFormaPagoDeuda() {
        return formaPagoDeuda;
    }

    /**
     * Sets the forma pago deuda.
     *
     * @param formaPagoDeuda
     *            the formaPagoDeuda to set
     */
    public void setFormaPagoDeuda(String formaPagoDeuda) {
        this.formaPagoDeuda = formaPagoDeuda;
    }

    /**
     * Gets the nro banco cuenta.
     *
     * @return the nroBancoCuenta
     */
    public String getNroBancoCuenta() {
        return nroBancoCuenta;
    }

    /**
     * Sets the nro banco cuenta.
     *
     * @param nroBancoCuenta
     *            the nroBancoCuenta to set
     */
    public void setNroBancoCuenta(String nroBancoCuenta) {
        this.nroBancoCuenta = nroBancoCuenta;
    }

    /**
     * Gets the codigo postal.
     *
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Sets the codigo postal.
     *
     * @param codigoPostal
     *            the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Gets the nro cheque.
     *
     * @return the nroCheque
     */
    public String getNroCheque() {
        return nroCheque;
    }

    /**
     * Sets the nro cheque.
     *
     * @param nroCheque
     *            the nroCheque to set
     */
    public void setNroCheque(String nroCheque) {
        this.nroCheque = nroCheque;
    }

    /**
     * Gets the sucursal cuenta.
     *
     * @return the sucursalCuenta
     */
    public String getSucursalCuenta() {
        return sucursalCuenta;
    }

    /**
     * Sets the sucursal cuenta.
     *
     * @param sucursalCuenta
     *            the sucursalCuenta to set
     */
    public void setSucursalCuenta(String sucursalCuenta) {
        this.sucursalCuenta = sucursalCuenta;
    }

    /**
     * Gets the tipo cuenta.
     *
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the tipo cuenta.
     *
     * @param tipoCuenta
     *            the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Gets the nro cuenta generico.
     *
     * @return the nroCuentaGenerico
     */
    public String getNroCuentaGenerico() {
        return nroCuentaGenerico;
    }

    /**
     * Sets the nro cuenta generico.
     *
     * @param nroCuentaGenerico
     *            the nroCuentaGenerico to set
     */
    public void setNroCuentaGenerico(String nroCuentaGenerico) {
        this.nroCuentaGenerico = nroCuentaGenerico;
    }

    /**
     * Gets the importe pago.
     *
     * @return the importePago
     */
    public String getImportePago() {
        return importePago;
    }

    /**
     * Sets the importe pago.
     *
     * @param importePago
     *            the importePago to set
     */
    public void setImportePago(String importePago) {
        this.importePago = importePago;
    }

    /**
     * Gets the fecha vencimiento CPD.
     *
     * @return the fechaVencimientoCPD
     */
    public String getFechaVencimientoCPD() {
        return fechaVencimientoCPD;
    }

    /**
     * Sets the fecha vencimiento CPD.
     *
     * @param fechaVencimientoCPD
     *            the fechaVencimientoCPD to set
     */
    public void setFechaVencimientoCPD(String fechaVencimientoCPD) {
        this.fechaVencimientoCPD = fechaVencimientoCPD;
    }

    /**
     * Gets the mar libre disp.
     *
     * @return the marLibreDisp
     */
    public String getMarLibreDisp() {
        return marLibreDisp;
    }

    /**
     * Sets the mar libre disp.
     *
     * @param marLibreDisp
     *            the marLibreDisp to set
     */
    public void setMarLibreDisp(String marLibreDisp) {
        this.marLibreDisp = marLibreDisp;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(codigoPostal);
        hcb.append(fechaVencimientoCPD);
        hcb.append(formaPagoDeuda);
        hcb.append(nroBancoCuenta);
        hcb.append(importePago);
        hcb.append(marLibreDisp);
        hcb.append(nroCheque);
        hcb.append(nroCuentaGenerico);
        hcb.append(sucursalCuenta);
        hcb.append(tipoCuenta);
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
        DetallePagoPCEntity other = (DetallePagoPCEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codigoPostal, other.getCodigoPostal());
        eb.append(fechaVencimientoCPD, other.getFechaVencimientoCPD());
        eb.append(formaPagoDeuda, other.getFormaPagoDeuda());
        eb.append(nroBancoCuenta, other.getNroBancoCuenta());
        eb.append(importePago, other.getImportePago());
        eb.append(marLibreDisp, other.getMarLibreDisp());
        eb.append(nroCheque, other.getNroCheque());
        eb.append(nroCuentaGenerico, other.getNroCuentaGenerico());
        eb.append(sucursalCuenta, other.getSucursalCuenta());
        eb.append(tipoCuenta, other.getTipoCuenta());
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
        return new ToStringBuilder(this).append("formaPagoDeuda", formaPagoDeuda)
                .append("nroBancoCuenta", nroBancoCuenta).append("codigoPostal", codigoPostal)
                .append("nroCheque", nroCheque).append("sucursalCuenta", sucursalCuenta)
                .append("tipoCuenta", tipoCuenta).append("nroCuentaGenerico", nroCuentaGenerico)
                .append("importePago", importePago).append("fechaVencimientoCPD", fechaVencimientoCPD)
                .append("marLibreDisp", marLibreDisp).toString();
    }

}
