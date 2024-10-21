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
 * The Class ComprobanteDeudaEntity.
 *
 * @author florencia.n.martinez
 */
public class ComprobanteDeudaEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The tpo cprb. */
    @Field
    private String tpoCprb;

    /** The nro cprb. */
    @Field
    private String nroCprb;

    /** The nro cuo. */
    @Field
    private String nroCuo;

    /** The fec vto. */
    @Field
    private String fecVto;

    /** The tasa int punit. */
    @Field
    private String tasaIntPunit;

    /** The sdo ant. */
    @Field
    private String sdoAnt;

    /** The imp puni. */
    @Field
    private String impPuni;

    /** The imp iva. */
    @Field
    private String impIva;

    /** The imp iva adicional. */
    @Field
    private String impIvaAdicional;

    /** The imp pago. */
    @Field
    private String impPago;

    /** The sdo actual. */
    @Field
    private String sdoActual;

    /**
     * Gets the tpo cprb.
     *
     * @return the tpo cprb
     */
    public String getTpoCprb() {
        return tpoCprb;
    }

    /**
     * Sets the tpo cprb.
     *
     * @param tpoCprb
     *            the new tpo cprb
     */
    public void setTpoCprb(String tpoCprb) {
        this.tpoCprb = tpoCprb;
    }

    /**
     * Gets the nro cprb.
     *
     * @return the nro cprb
     */
    public String getNroCprb() {
        return nroCprb;
    }

    /**
     * Sets the nro cprb.
     *
     * @param nroCprb
     *            the new nro cprb
     */
    public void setNroCprb(String nroCprb) {
        this.nroCprb = nroCprb;
    }

    /**
     * Gets the nro cuo.
     *
     * @return the nro cuo
     */
    public String getNroCuo() {
        return nroCuo;
    }

    /**
     * Sets the nro cuo.
     *
     * @param nroCuo
     *            the new nro cuo
     */
    public void setNroCuo(String nroCuo) {
        this.nroCuo = nroCuo;
    }

    /**
     * Gets the fec vto.
     *
     * @return the fec vto
     */
    public String getFecVto() {
        return fecVto;
    }

    /**
     * Sets the fec vto.
     *
     * @param fecVto
     *            the new fec vto
     */
    public void setFecVto(String fecVto) {
        this.fecVto = fecVto;
    }

    /**
     * Gets the tasa int punit.
     *
     * @return the tasa int punit
     */
    public String getTasaIntPunit() {
        return tasaIntPunit;
    }

    /**
     * Sets the tasa int punit.
     *
     * @param tasaIntPunit
     *            the new tasa int punit
     */
    public void setTasaIntPunit(String tasaIntPunit) {
        this.tasaIntPunit = tasaIntPunit;
    }

    /**
     * Gets the sdo ant.
     *
     * @return the sdo ant
     */
    public String getSdoAnt() {
        return sdoAnt;
    }

    /**
     * Sets the sdo ant.
     *
     * @param sdoAnt
     *            the new sdo ant
     */
    public void setSdoAnt(String sdoAnt) {
        this.sdoAnt = sdoAnt;
    }

    /**
     * Gets the imp puni.
     *
     * @return the imp puni
     */
    public String getImpPuni() {
        return impPuni;
    }

    /**
     * Sets the imp puni.
     *
     * @param impPuni
     *            the new imp puni
     */
    public void setImpPuni(String impPuni) {
        this.impPuni = impPuni;
    }

    /**
     * Gets the imp iva.
     *
     * @return the imp iva
     */
    public String getImpIva() {
        return impIva;
    }

    /**
     * Sets the imp iva.
     *
     * @param impIva
     *            the new imp iva
     */
    public void setImpIva(String impIva) {
        this.impIva = impIva;
    }

    /**
     * Gets the imp iva adicional.
     *
     * @return the imp iva adicional
     */
    public String getImpIvaAdicional() {
        return impIvaAdicional;
    }

    /**
     * Sets the imp iva adicional.
     *
     * @param impIvaAdicional
     *            the new imp iva adicional
     */
    public void setImpIvaAdicional(String impIvaAdicional) {
        this.impIvaAdicional = impIvaAdicional;
    }

    /**
     * Gets the imp pago.
     *
     * @return the imp pago
     */
    public String getImpPago() {
        return impPago;
    }

    /**
     * Sets the imp pago.
     *
     * @param impPago
     *            the new imp pago
     */
    public void setImpPago(String impPago) {
        this.impPago = impPago;
    }

    /**
     * Gets the sdo actual.
     *
     * @return the sdo actual
     */
    public String getSdoActual() {
        return sdoActual;
    }

    /**
     * Sets the sdo actual.
     *
     * @param sdoActual
     *            the new sdo actual
     */
    public void setSdoActual(String sdoActual) {
        this.sdoActual = sdoActual;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(fecVto);
        hcb.append(impIva);
        hcb.append(impIvaAdicional);
        hcb.append(impPago);
        hcb.append(impPuni);
        hcb.append(nroCprb);
        hcb.append(nroCuo);
        hcb.append(sdoActual);
        hcb.append(sdoAnt);
        hcb.append(tasaIntPunit);
        hcb.append(tpoCprb);
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
        ComprobanteDeudaEntity other = (ComprobanteDeudaEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(fecVto, other.getFecVto());
        eb.append(impIva, other.getImpIva());
        eb.append(impIvaAdicional, other.getImpIvaAdicional());
        eb.append(impPago, other.getImpPago());
        eb.append(impPuni, other.getImpPuni());
        eb.append(nroCprb, other.getNroCprb());
        eb.append(nroCuo, other.getNroCuo());
        eb.append(sdoActual, other.getSdoActual());
        eb.append(sdoAnt, other.getSdoAnt());
        eb.append(tasaIntPunit, other.getTasaIntPunit());
        eb.append(tpoCprb, other.getTpoCprb());
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
        return new ToStringBuilder(this).append("tpoCprb", tpoCprb).append("nroCprb", nroCprb).append("nroCuo", nroCuo)
                .append("fecVto", fecVto).append("tasaIntPunit", tasaIntPunit).append("sdoAnt", sdoAnt)
                .append("impPuni", impPuni).append("impIva", impIva).append("impIvaAdicional", impIvaAdicional)
                .append("impPago", impPago).append("sdoActual", sdoActual).toString();
    }
}
